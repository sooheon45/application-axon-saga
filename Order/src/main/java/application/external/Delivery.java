package application.external;

import java.util.Date;
import lombok.Data;

@Data
public class Delivery {

    private Long id;
    private String address;
    private Long orderId;
}
