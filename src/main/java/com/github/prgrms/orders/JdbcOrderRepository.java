package com.github.prgrms.orders;

import com.github.prgrms.configures.web.Pageable;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.github.prgrms.utils.DateTimeUtils.dateTimeOf;

@Repository
public class JdbcOrderRepository implements OrderRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcOrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<OrderDto> findById(long id) {
        List<OrderDto> results = jdbcTemplate.query(
                "SELECT o.seq, o.user_seq, o.product_seq, o.review_seq, o.state, " +
                        "o.request_msg, o.reject_msg, o.completed_at, o.rejected_at, o.create_at, r.content, r.create_at AS r_create_at FROM orders o left join reviews r on o.review_seq = r.seq where o.seq=?",
                new ResultSetExtractor<List<OrderDto>>() {
                    @Override
                    public List<OrderDto> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                        List<OrderDto> list = new ArrayList<>();
                        int i = 0;
                        if (resultSet.next()) {
                            OrderDto orderDto = dtoMapper.mapRow(resultSet,i);
                            orderDto.setReview(reviewRowMapper.mapRow(resultSet, i));
                            list.add(orderDto);
                        }
                        return list;
                    }
                },
                id
        );
        return Optional.ofNullable(results.isEmpty() ? null : results.get(0));
    }

    @Override
    public void update(Order order) {
        jdbcTemplate.update(
            "UPDATE orders SET user_seq=?,product_seq=?,review_seq=?, state=?, " +
                    "request_msg=?, reject_msg=?, completed_at=?, rejected_at=?, create_at=? WHERE seq=?",
                order.getUserSeq(), order.getProductSeq(), order.getReviewSeq(), order.getState().getValue(),
                order.getRequestMsg(), order.getRejectMsg(), order.getCompletedAt(),
                order.getRejectedAt(), order.getCreateAt(), order.getSeq()
        );
    }

    @Override
    public List<OrderDto> findAll(Pageable pageable) {
        return jdbcTemplate.query(
                "SELECT o.seq, o.user_seq, o.product_seq, o.review_seq, o.state, " +
                        "o.request_msg, o.reject_msg, o.completed_at, o.rejected_at, o.create_at, r.content, r.create_at AS r_create_at FROM orders o left join reviews r on o.review_seq = r.seq ORDER BY seq DESC LIMIT ? OFFSET ?",
                new ResultSetExtractor<List<OrderDto>>() {
                    @Override
                    public List<OrderDto> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                        List<OrderDto> list = new ArrayList<>();
                        int i = 0;
                        while (resultSet.next()) {
                            OrderDto orderDto = dtoMapper.mapRow(resultSet,i);
                            orderDto.setReview(reviewRowMapper.mapRow(resultSet, i));
                            list.add(orderDto);
                        }
                        return list;
                    }
                },
                pageable.getSize(),
                pageable.getOffset()
        );
    }

    static RowMapper<Order> mapper = (rs, rowNum) -> {
      Order order = new Order();
      order.setSeq(rs.getLong("seq"));
      order.setUserSeq(rs.getLong("user_seq"));
      order.setProductSeq(rs.getLong("product_seq"));
      order.setReviewSeq(rs.getLong("review_seq"));
      order.setState(OrderState.valueOf(rs.getString("state")));
      order.setRequestMsg(rs.getString("request_msg"));
      order.setRejectMsg(rs.getString("reject_msg"));
      order.setCompletedAt(dateTimeOf(rs.getTimestamp("completed_at")));
      order.setRejectedAt(dateTimeOf(rs.getTimestamp("rejected_at")));
      order.setCreateAt(dateTimeOf(rs.getTimestamp("create_at")));
      return order;
    };

    static RowMapper<OrderDto> dtoMapper = (rs, rowNum) -> {
        OrderDto order = new OrderDto();
        order.setSeq(rs.getLong("seq"));
        order.setUserSeq(rs.getLong("user_seq"));
        order.setProductId(rs.getLong("product_seq"));
        order.setState(OrderState.valueOf(rs.getString("state")));
        order.setRequestMessage(rs.getString("request_msg"));
        order.setRejectMessage(rs.getString("reject_msg"));
        order.setCompletedAt(dateTimeOf(rs.getTimestamp("completed_at")));
        order.setRejectedAt(dateTimeOf(rs.getTimestamp("rejected_at")));
        order.setCreateAt(dateTimeOf(rs.getTimestamp("create_at")));
        return order;
    };

    public static RowMapper<ReviewDto> reviewRowMapper = (rs, rowNum) -> {
        String idStr = rs.getString("review_seq");
        if (idStr != null) {
            ReviewDto review = new ReviewDto();
            review.setSeq(rs.getLong("review_seq"));
            review.setUserSeq(rs.getLong("user_seq"));
            review.setProductId(rs.getLong("product_seq"));
            review.setContent(rs.getString("content"));
            review.setCreateAt(dateTimeOf(rs.getTimestamp("r_create_at")));
            return review;
        } else {
            return null;
        }
    };
}
