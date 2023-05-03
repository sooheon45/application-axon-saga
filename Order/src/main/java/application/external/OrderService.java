package application.external;

import application.domain.*;
import java.util.Date;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "Order", url = "${api.url.Order}")
public interface OrderService {
    @RequestMapping(
        method = RequestMethod.PUT,
        path = "/orders/{id}/updatestatus"
    )
    public void updateStatus(
        @PathVariable("id") Long id,
        @RequestBody UpdateStatusCommand updateStatusCommand
    );
}
