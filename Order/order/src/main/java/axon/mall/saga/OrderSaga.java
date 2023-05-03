package axon.mall.saga;

import axon.mall.command.DecreaseStockCommand;
import axon.mall.command.IncreaseStockCommand;
import axon.mall.command.OrderCancelCommand;
import axon.mall.command.StartDeliveryCommand;
import axon.mall.event.OrderCancelledEvent;
import axon.mall.event.OrderCompletedEvent;
import axon.mall.event.OrderPlacedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.CommandResultMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

@Saga
@Slf4j
public class OrderSaga {

    @Autowired
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void createOrder(OrderPlacedEvent event) {
        log.info("[StartSaga] order saga");

        commandGateway.send(
            new DecreaseStockCommand(event.getProductId(), event.getQty()),
            new CommandCallback<DecreaseStockCommand, Object>() {
                @Override
                public void onResult(
                    CommandMessage<? extends DecreaseStockCommand> commandMessage,
                    CommandResultMessage<?> commandResultMessage
                ) {
                    if (commandResultMessage.isExceptional()) {
                        // Exec. Compensation Trx
                        log.info(
                            "Compensation Trx: Cancelling order, cause of Out of Stock."
                        );
                        commandGateway.send(
                            new OrderCancelCommand(event.getOrderId())
                        );
                    } else // e.g. SagaLifecycle.associateWith("delivery", deliveryId)              // You can use Associate Saga,
                    {
                        commandGateway.send(
                            new StartDeliveryCommand(
                                null,
                                event.getUserId(),
                                "Seoul",
                                event.getOrderId(),
                                event.getProductId(),
                                event.getQty(),
                                "DeliveryStarted"
                            ),
                            new CommandCallback<StartDeliveryCommand, Object>() {
                                @Override
                                public void onResult(
                                    CommandMessage<? extends StartDeliveryCommand> commandMessage,
                                    CommandResultMessage<?> commandResultMessage
                                ) {
                                    if (commandResultMessage.isExceptional()) {
                                        // Exec. Compensation Trx
                                        log.info(
                                            "Compensation Trx: Cancelling order, cause of Delivery Service."
                                        );
                                        commandGateway.send(
                                            new IncreaseStockCommand(
                                                event.getProductId(),
                                                event.getQty()
                                            )
                                        );
                                        commandGateway.send(
                                            new OrderCancelCommand(
                                                event.getOrderId()
                                            )
                                        );
                                    }
                                }
                            }
                        );
                    }
                }
            }
        );
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void endOrder(OrderCompletedEvent event) {
        log.info("[EndSaga] order saga");
        SagaLifecycle.end();
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void endOrder(OrderCancelledEvent event) {
        log.info("[EndSaga] order saga");
        SagaLifecycle.end();
    }
}
