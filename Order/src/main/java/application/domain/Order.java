package application.domain;

import application.OrderApplication;
import application.domain.OrderPlaced;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import org.springframework.context.ApplicationContext;

@Entity
@Table(name = "Order_table")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userId;

    private Long productId;

    @PostPersist
    public void onPostPersist() {
        OrderPlaced orderPlaced = new OrderPlaced(this);
        orderPlaced.publishAfterCommit();
    }

    public static OrderRepository repository() {
        OrderRepository orderRepository = applicationContext()
            .getBean(OrderRepository.class);
        return orderRepository;
    }

    public static ApplicationContext applicationContext() {
        return OrderApplication.applicationContext;
    }

    public void updateStatus(UpdateStatusCommand updateStatusCommand) {
        // implement the business logics here:

        OrderComplated orderComplated = new OrderComplated(this);
        orderComplated.setId(updateStatusCommand.getId());
        /** Logic **/

        orderComplated.publishAfterCommit();
    }
}
