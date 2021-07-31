package com.github.prgrms.orders;

import java.time.LocalDateTime;

public class Order {
    private Long seq;

    private Long userSeq;

    private Long productSeq;

    private Long reviewSeq;

    private OrderState state;

    private String requestMsg;

    private String rejectMsg;

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

    public Long getProductSeq() {
        return productSeq;
    }

    public void setProductSeq(Long productSeq) {
        this.productSeq = productSeq;
    }

    public Long getReviewSeq() {
        return reviewSeq;
    }

    public void setReviewSeq(Long reviewSeq) {
        this.reviewSeq = reviewSeq;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public String getRequestMsg() {
        return requestMsg;
    }

    public void setRequestMsg(String requestMsg) {
        this.requestMsg = requestMsg;
    }

    public String getRejectMsg() {
        return rejectMsg;
    }

    public void setRejectMsg(String rejectMsg) {
        this.rejectMsg = rejectMsg;
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

    public static Order orderFromOrderDto(OrderDto orderDto) {
        Order order = new Order();
        order.seq = orderDto.getSeq();
        order.userSeq = orderDto.getUserSeq();
        order.productSeq = orderDto.getProductId();
        order.reviewSeq = orderDto.getReview() == null ? null : orderDto.getReview().getSeq();
        order.state = orderDto.getState();
        order.requestMsg = orderDto.getRequestMessage();
        order.rejectMsg = orderDto.getRejectMessage();
        order.completedAt = orderDto.getCompletedAt();
        order.rejectedAt = orderDto.getRejectedAt();
        order.createAt = orderDto.getCreateAt();
        return order;
    }
}
