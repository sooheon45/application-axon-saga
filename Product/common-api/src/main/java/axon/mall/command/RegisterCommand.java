package axon.mall.command;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@ToString
@Data
@AllArgsConstructor
public class RegisterCommand {

    private String productId; // Please comment here if you want user to enter the id directly
    private String productName;
    private Integer stock;
}
