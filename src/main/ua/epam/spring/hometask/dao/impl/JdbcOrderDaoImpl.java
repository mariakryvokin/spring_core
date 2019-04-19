package ua.epam.spring.hometask.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ua.epam.spring.hometask.dao.JdbcOrderDao;
import ua.epam.spring.hometask.entity.Order;
import ua.epam.spring.hometask.entity.User;

import javax.annotation.Nonnull;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Optional;

@Repository
public class JdbcOrderDaoImpl implements JdbcOrderDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcOrderDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Order save(@Nonnull Order object) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        User user = new User();
        if(object.getUser() != null){
            String sql = "INSERT INTO `orders` (price, users_id) values (?, ?)";
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setDouble(1,object.getPrice());
                    ps.setLong(2,object.getUser().getId());
                    return ps;
                }
            },keyHolder);
        }else {
            String sql = "INSERT INTO `orders` (price) values (?)";
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setDouble(1,object.getPrice());
                    return ps;
                }
            },keyHolder);

        }
        object.setId((Long) keyHolder.getKey());
        return object;
    }

    @Override
    public void remove(@Nonnull Order object) {

    }

    @Override
    public Order getById(@Nonnull Long id) {
        return null;
    }

    @Nonnull
    @Override
    public Collection<Order> getAll() {
        return null;
    }
}
