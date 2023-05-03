package application.event;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class StockIncreasedEvent {

    private Long id;
    private String name;
    private Integer stock;
    private Long orderId;
}
