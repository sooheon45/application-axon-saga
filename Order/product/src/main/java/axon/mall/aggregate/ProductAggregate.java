package axon.mall.aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.*;

import axon.mall.command.*;
import axon.mall.event.*;
import axon.mall.query.*;
import java.util.List;
import java.util.UUID;
import lombok.Data;
import lombok.ToString;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@Data
@ToString
public class ProductAggregate {

    @AggregateIdentifier
    private String productId;

    private String productName;
    private Integer stock;

    public ProductAggregate() {}

    @CommandHandler
    public ProductAggregate(RegisterCommand command) {
        ProductRegisteredEvent event = new ProductRegisteredEvent();
        BeanUtils.copyProperties(command, event);

        //TODO: check key generation is properly done
        if (event.getProductId() == null) event.setProductId(createUUID());

        apply(event);
    }

    @CommandHandler
    public void handle(DecreaseStockCommand command) {
        StockDecreasedEvent event = new StockDecreasedEvent();
        BeanUtils.copyProperties(command, event);

        if (
            this.getStock() < command.getStock()
        ) throw new IllegalStateException("Out of Stock. !");
        apply(event);
    }

    @CommandHandler
    public void handle(IncreaseStockCommand command) {
        StockIncreasedEvent event = new StockIncreasedEvent();
        BeanUtils.copyProperties(command, event);

        if (
            this.getStock() < command.getStock()
        ) throw new IllegalStateException("Out of Stock. !");
        apply(event);
    }

    private String createUUID() {
        return UUID.randomUUID().toString();
    }

    @EventSourcingHandler
    public void on(StockDecreasedEvent event) {
        //TODO: business logic here
        this.setStock(getStock() - event.getStock());
    }

    @EventSourcingHandler
    public void on(StockIncreasedEvent event) {
        //TODO: business logic here
        this.setStock(getStock() + event.getStock());
    }

    @EventSourcingHandler
    public void on(ProductRegisteredEvent event) {
        BeanUtils.copyProperties(event, this);
        //TODO: business logic here

    }
}
