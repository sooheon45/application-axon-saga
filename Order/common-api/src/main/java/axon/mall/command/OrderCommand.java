package axon.mall.command;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@ToString
@Data
@AllArgsConstructor
public class OrderCommand {

    private String orderId; // Please comment here if you want user to enter the id directly
    private String productName;
    private String productId;
    private String status;
    private Integer qty;
    private String userId;
}
