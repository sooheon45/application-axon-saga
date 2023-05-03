package application.event;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class StockDecreasedEvent {

    private Long id;
    private String name;
    private Integer stock;
    private Long orderId;
}
