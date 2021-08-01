package com.github.prgrms.orders;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReviewDto {
    private Long seq;

    private Long productId;

    private Long userSeq;

    private String content;

    private LocalDateTime createAt;

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getUserSeq() {
        return userSeq;
    }

    public void setUserSeq(Long userSeq) {
        this.userSeq = userSeq;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public static ReviewDto reviewDtoFromReview(Review Review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.seq = Review.getSeq();
        reviewDto.productId = Review.getProductSeq();
        reviewDto.userSeq = Review.getUserSeq();
        reviewDto.content = Review.getContent();
        reviewDto.createAt = defaultIfNull(Review.getCreateAt(), now());
        return reviewDto;
    }
}
