package axon.mall.event;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class StockIncreasedEvent {

    private String productId;
    private String productName;
    private Integer stock;
}
