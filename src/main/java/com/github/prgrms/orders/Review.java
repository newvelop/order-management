package com.github.prgrms.orders;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

public class Review {
    private Long seq;

    private Long productSeq;

    private Long userSeq;

    private String content;

    private LocalDateTime createAt;

    public static Review reviewFromReviewDto(ReviewDto reviewDto) {
        Review review = new Review();
        review.seq = reviewDto.getSeq();
        review.productSeq = reviewDto.getProductId();
        review.userSeq = reviewDto.getUserSeq();
        review.content = reviewDto.getContent();
        review.createAt = defaultIfNull(reviewDto.getCreateAt(), now());
        return review;
    }

    public Long getSeq() {
        return seq;
    }

    public Long getProductSeq() {
        return productSeq;
    }

    public Long getUserSeq() {
        return userSeq;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public void setProductSeq(Long productSeq) {
        this.productSeq = productSeq;
    }

    public void setUserSeq(Long userSeq) {
        this.userSeq = userSeq;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }
}
