package application.external;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Data
public class DecreaseStockCommand {

    @Id
    private Long id;

    private Integer stock;
    private Long orderId;
}
