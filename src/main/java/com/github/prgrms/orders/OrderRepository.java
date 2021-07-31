package com.github.prgrms.orders;

import com.github.prgrms.configures.web.Pageable;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Optional<OrderDto> findById(long id);

    void update(Order order);

    List<OrderDto> findAll(Pageable pageable);
}
