package application.command;

import java.util.List;
import lombok.Data;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@ToString
@Data
public class IncreaseStockCommand {

    private Long id; // Please comment here if you want user to enter the id directly
    private String name;
    private Integer stock;
}
