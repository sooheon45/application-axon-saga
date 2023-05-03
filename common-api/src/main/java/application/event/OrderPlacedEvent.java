package application.event;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderPlacedEvent {

    private Long id;
    private String userId;
    private Long productId;
}
