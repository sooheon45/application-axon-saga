package application.aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.*;

import application.command.*;
import application.event.*;
import application.query.*;
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
    private Long id;

    private String name;
    private Integer stock;

    public ProductAggregate() {}

    @CommandHandler
    public void handle(DecreaseStockCommand command) {
        StockDecreasedEvent event = new StockDecreasedEvent();
        BeanUtils.copyProperties(command, event);

        apply(event);
    }

    @CommandHandler
    public ProductAggregate(IncreaseStockCommand command) {
        StockIncreasedEvent event = new StockIncreasedEvent();
        BeanUtils.copyProperties(command, event);

        //TODO: check key generation is properly done
        if (event.getId() == null) event.setId(createUUID());

        apply(event);
    }

    private String createUUID() {
        return UUID.randomUUID().toString();
    }

    @EventSourcingHandler
    public void on(StockDecreasedEvent event) {
        //TODO: business logic here

    }

    @EventSourcingHandler
    public void on(StockIncreasedEvent event) {
        BeanUtils.copyProperties(event, this);
        //TODO: business logic here

    }
}
