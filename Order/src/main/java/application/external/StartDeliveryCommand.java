package application.external;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Data
public class StartDeliveryCommand {

    @Id
    private Long id;
}
