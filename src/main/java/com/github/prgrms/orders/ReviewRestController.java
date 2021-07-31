package com.github.prgrms.orders;

import com.github.prgrms.utils.ApiUtils;
import org.springframework.web.bind.annotation.*;

import static com.github.prgrms.utils.ApiUtils.success;

@RestController
@RequestMapping("api/orders")
public class ReviewRestController {
    private final ReviewService reviewService;

    public ReviewRestController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // TODO review 메소드 구현이 필요합니다.
    @PostMapping("{id}/review")
    public ApiUtils.ApiResult<ReviewDto> review(@PathVariable Long id, @RequestBody ReviewDto reviewDto) {
        return success(reviewService.review(id, reviewDto));
    }
}