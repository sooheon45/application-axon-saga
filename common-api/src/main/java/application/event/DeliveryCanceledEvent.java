package application.event;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DeliveryCanceledEvent {

    private Long id;
    private String address;
    private Long orderId;
}
