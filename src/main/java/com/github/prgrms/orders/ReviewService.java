package com.github.prgrms.orders;

import com.github.prgrms.errors.NotFoundException;
import com.github.prgrms.errors.UnableInsertException;
import com.github.prgrms.products.Product;
import com.github.prgrms.products.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewService {
    private final OrderRepository orderRepository;
    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;

    public ReviewService(OrderRepository orderRepository, ReviewRepository reviewRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
    }

    public ReviewDto review(Long orderId, ReviewDto reviewDto) {
        Optional<OrderDto> orderOptional = orderRepository.findById(orderId);
        if (!orderOptional.isPresent()) {
            throw new NotFoundException("Could not write review for order " + orderId + " because have already written");
        }
        if (orderOptional.get().getReview().getSeq() != null) {
            throw new UnableInsertException("review is already written");
        } else if (!orderOptional.get().getState().getValue().equals(OrderState.COMPLETED.getValue())) {
            throw new UnableInsertException("Could not write review for order "+ orderId + " because state(REQUESTED) is not allowed");
        }

        Optional<Product> productOptional = productRepository.findById(orderOptional.get().getProductId());
        //추가 예외처리 필요하긴함. product가 없어졌거나.

        Product updateProduct = new Product.Builder(productOptional.get())
                .reviewCount(productOptional.get().getReviewCount() + 1)
                .build();
        productRepository.update(updateProduct);

        Review review = reviewRepository.insert(Review.reviewFromReviewDto(reviewDto));

        Order updateOrder = Order.orderFromOrderDto(orderOptional.get());
        updateOrder.setReviewSeq(review.getSeq());
        orderRepository.update(updateOrder);

        return ReviewDto.reviewDtoFromReview(review);
    }
}
