package application.command;

import java.util.List;
import lombok.Data;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@ToString
@Data
public class DecreaseStockCommand {

    @TargetAggregateIdentifier
    private Long id;

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private Integer stock;
    private Long orderId;
}
