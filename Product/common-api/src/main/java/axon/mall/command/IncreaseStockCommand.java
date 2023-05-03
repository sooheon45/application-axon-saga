package axon.mall.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@ToString
@Data
@AllArgsConstructor
public class IncreaseStockCommand {

    @TargetAggregateIdentifier
    private String productId;

    private Integer stock;
}
