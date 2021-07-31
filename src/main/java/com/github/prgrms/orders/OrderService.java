package com.github.prgrms.orders;

import com.github.prgrms.configures.web.Pageable;
import com.github.prgrms.errors.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.time.LocalDateTime.now;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderDto> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    public Optional<OrderDto> findById(Long id) {
        return orderRepository.findById(id);
    }

    public Boolean accept(Long id) {
        Optional<OrderDto> orderOptional = orderRepository.findById(id);
        if (!orderOptional.isPresent()) {
            throw new NotFoundException("Could not found order for " + id);
        }
        if (!orderOptional.get().getState().getValue().equals(OrderState.REQUESTED.getValue())) {
            return false;
        } else {
            Order updateOrder = Order.orderFromOrderDto(orderOptional.get());
            updateOrder.setState(OrderState.ACCEPTED);
            orderRepository.update(updateOrder);
            return true;
        }
    }

    public Boolean reject(Long id, String message) {
        Optional<OrderDto> orderOptional = orderRepository.findById(id);
        if (!orderOptional.isPresent()) {
            throw new NotFoundException("Could not found order for " + id);
        }
        if (!orderOptional.get().getState().getValue().equals(OrderState.REQUESTED.getValue())) {
            return false;
        } else {
            Order updateOrder = Order.orderFromOrderDto(orderOptional.get());
            updateOrder.setState(OrderState.REJECTED);
            updateOrder.setRejectMsg(message);
            updateOrder.setRejectedAt(now());
            orderRepository.update(updateOrder);
            return true;
        }
    }

    public Boolean shipping(Long id) {
        Optional<OrderDto> orderOptional = orderRepository.findById(id);
        if (!orderOptional.isPresent()) {
            throw new NotFoundException("Could not found order for " + id);
        }
        if (!orderOptional.get().getState().getValue().equals(OrderState.ACCEPTED.getValue())) {
            return false;
        } else {
            Order updateOrder = Order.orderFromOrderDto(orderOptional.get());
            updateOrder.setState(OrderState.SHIPPING);
            orderRepository.update(updateOrder);
            return true;
        }
    }

    public Boolean complete(Long id) {
        Optional<OrderDto> orderOptional = orderRepository.findById(id);
        if (!orderOptional.isPresent()) {
            throw new NotFoundException("Could not found order for " + id);
        }
        if (!orderOptional.get().getState().getValue().equals(OrderState.SHIPPING.getValue())) {
            return false;
        } else {
            Order updateOrder = Order.orderFromOrderDto(orderOptional.get());
            updateOrder.setState(OrderState.COMPLETED);
            updateOrder.setCompletedAt(now());
            orderRepository.update(updateOrder);
            return true;
        }
    }
}
