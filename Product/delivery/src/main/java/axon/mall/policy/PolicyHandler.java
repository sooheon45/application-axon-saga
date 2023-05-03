package axon.mall.policy;

import axon.mall.aggregate.*;
import axon.mall.command.*;
import axon.mall.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.DisallowReplay;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@ProcessingGroup("delivery")
public class PolicyHandler {

    @Autowired
    CommandGateway commandGateway;

    @EventHandler
    //@DisallowReplay
    public void wheneverOrderPlaced_StartDelivery(
        OrderPlacedEvent orderPlaced
    ) {
        System.out.println(orderPlaced.toString());
        // This time, it processed by Orchestraion Saga
        /*  StartDeliveryCommand command = new StartDeliveryCommand();
        command.setUserId(orderPlaced.getUserId());
        command.setAddress("SEOUL AMSADONG");
        command.setOrderId(orderPlaced.getOrderId());
        command.setProductId(orderPlaced.getProductId());
        command.setQty(orderPlaced.getQty());
        // command.setStatus("DeliveryStarted");    EventSourcingHandler에서 Status 설정
        commandGateway.send(command); */
    }
}
