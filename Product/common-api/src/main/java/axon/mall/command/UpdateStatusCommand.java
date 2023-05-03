package axon.mall.command;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@ToString
@Data
// @AllArgsConstructor
public class UpdateStatusCommand {

    @TargetAggregateIdentifier
    private String orderId;

    public UpdateStatusCommand() {}

    public UpdateStatusCommand(String orderId) {
        this.orderId = orderId;
    }
}
