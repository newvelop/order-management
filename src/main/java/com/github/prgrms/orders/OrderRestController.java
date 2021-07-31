package com.github.prgrms.orders;

import com.github.prgrms.configures.web.Pageable;
import com.github.prgrms.errors.NotFoundException;
import com.github.prgrms.errors.UnableUpdateException;
import com.github.prgrms.utils.ApiUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.github.prgrms.utils.ApiUtils.success;

@RestController
@RequestMapping("api/orders")
public class OrderRestController {
    private final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }
    // TODO findAll, findById, accept, reject, shipping, complete 메소드 구현이 필요합니다.

    @GetMapping("")
    ApiUtils.ApiResult<List<OrderDto>> findAll(Pageable pageable) {
        return success(orderService.findAll(pageable));
    }

    @GetMapping("/{id}")
    ApiUtils.ApiResult<OrderDto> findById(@PathVariable Long id) {
        return success(orderService.findById(id)
        .orElseThrow(() -> new NotFoundException("Could not found order for " + id)));
    }

    @PatchMapping("/{id}/accept")
    ApiUtils.ApiResult<Boolean> accept(@PathVariable Long id) {
        return success(orderService.accept(id));
    }

    @PatchMapping("/{id}/reject")
    ApiUtils.ApiResult<Boolean> reject(@PathVariable Long id, @RequestBody(required = false) Map<String, String> map) {
        if (map == null || map.get("message") == null) {
            throw new UnableUpdateException("reject message not found");
        }
        return success(orderService.reject(id, map.get("message")));
    }

    @PatchMapping("/{id}/shipping")
    ApiUtils.ApiResult<Boolean> shipping(@PathVariable Long id) {
        return success(orderService.shipping(id));
    }

    @PatchMapping("/{id}/complete")
    ApiUtils.ApiResult<Boolean> complete(@PathVariable Long id) {
        return success(orderService.complete(id));
    }
}