package com.github.prgrms.orders;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;

import static com.github.prgrms.utils.DateTimeUtils.dateTimeOf;

@Repository
public class JdbcReviewRepository implements ReviewRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcReviewRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Review insert(Review review) {
        String insertSql = "INSERT INTO REVIEWS(user_seq, product_seq, content, create_at) "
                + "VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
            new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement statement = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
                    statement.setLong(1, review.getUserSeq());
                    statement.setLong(2, review.getProductSeq());
                    statement.setString(3, review.getContent());
                    statement.setTimestamp(4, Timestamp.valueOf(review.getCreateAt()));
                    return statement;
                }
            }, keyHolder);

        review.setSeq(keyHolder.getKey().longValue());
        return review;
    }

    public static RowMapper<Review> reviewRowMapper = (rs, rowNum) -> {
        Review review = new Review();
        review.setSeq(rs.getLong("seq"));
        review.setUserSeq(rs.getLong("user_seq"));
        review.setProductSeq(rs.getLong("product_seq"));
        review.setContent(rs.getString("content"));
        review.setCreateAt(dateTimeOf(rs.getTimestamp("create_at")));
        return review;
    };


//    seq           bigint NOT NULL AUTO_INCREMENT, --?????? PK
//    user_seq      bigint NOT NULL,                --?????? ????????? PK (users ????????? ??????)
//    product_seq   bigint NOT NULL,                --?????? ?????? PK (products ????????? ??????)
//    content       varchar(1000) NOT NULL,         --?????? ??????
//    create_at     datetime NOT NULL DEFAULT CURRENT_TIMESTAMP(),
}
