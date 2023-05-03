package application.domain;

import application.domain.*;
import application.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class OrderComplated extends AbstractEvent {

    private Long id;
    private String userId;
    private Long productId;

    public OrderComplated(Order aggregate) {
        super(aggregate);
    }

    public OrderComplated() {
        super();
    }
}
