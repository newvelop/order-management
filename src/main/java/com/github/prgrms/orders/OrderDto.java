package com.github.prgrms.orders;

import java.time.LocalDateTime;

public class OrderDto {
    private Long seq;

    private Long userSeq;

    private Long productId;

    private ReviewDto review;

    private OrderState state;

    private String requestMessage;

    private String rejectMessage;

    private LocalDateTime completedAt;

    private LocalDateTime rejectedAt;

    private LocalDateTime createAt;

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public Long getUserSeq() {
        return userSeq;
    }

    public void setUserSeq(Long userSeq) {
        this.userSeq = userSeq;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public ReviewDto getReview() {
        return review;
    }

    public void setReview(ReviewDto review) {
        this.review = review;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public String getRequestMessage() {
        return requestMessage;
    }

    public void setRequestMessage(String requestMessage) {
        this.requestMessage = requestMessage;
    }

    public String getRejectMessage() {
        return rejectMessage;
    }

    public void setRejectMessage(String rejectMessage) {
        this.rejectMessage = rejectMessage;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public LocalDateTime getRejectedAt() {
        return rejectedAt;
    }

    public void setRejectedAt(LocalDateTime rejectedAt) {
        this.rejectedAt = rejectedAt;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public static OrderDto orderDtoFromOrder(Order order) {
        OrderDto orderDto = new OrderDto();

        return orderDto;
    }
}
