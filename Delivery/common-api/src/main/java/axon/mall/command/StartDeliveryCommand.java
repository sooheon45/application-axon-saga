package axon.mall.command;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@ToString
@Data
@AllArgsConstructor
public class StartDeliveryCommand {

    @TargetAggregateIdentifier
    private String deliveryId;

    private String userId;
    private String address;
    private String orderId;
    private String productId;
    private Integer qty;
    private String status;
}
