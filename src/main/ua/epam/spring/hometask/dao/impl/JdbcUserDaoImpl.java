package ua.epam.spring.hometask.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.epam.spring.hometask.dao.JdbcUserDao;
import ua.epam.spring.hometask.dao.rowMappers.UserRowMapper;
import ua.epam.spring.hometask.entity.Ticket;
import ua.epam.spring.hometask.entity.User;
import ua.epam.spring.hometask.entity.UserRole;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Repository
public class JdbcUserDaoImpl implements JdbcUserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcUserDaoImpl(@Qualifier("mainJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Nullable
    @Override
    public Optional<User> getUserByEmail(@Nonnull String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        User user = (User)jdbcTemplate.queryForObject(sql, new Object[] {email}, new UserRowMapper());
        return Optional.of(user);
    }

    @Override
    public Collection<Ticket> getAllUserTickets(@Nonnull User user) {
        String sql = "SELECT t.id  FROM tickets t, `orders` o, users u WHERE t.orders_id = o.id AND o.users_id = u.id";
        List<Map<String,Object>> map  = jdbcTemplate.queryForList(sql);
        Collection<Ticket> collection = new ArrayList<>();
        for (Map row : map){
            Ticket ticket = new Ticket();
            ticket.setId((Long) row.get("id"));
            collection.add(ticket);
        }
        return collection;
    }

    @Override
    public User save(@Nonnull User object) {
        String sql = "INSERT INTO users (first_name, last_name, email, birthday) values (?, ?, ?, ?)";
        this.jdbcTemplate.update(sql,object.getFirstName(),object.getLastName(),object.getEmail(),Date.valueOf(object.getBirthday()));
        return object;
    }

    @Override
    public void remove(@Nonnull User object) {
        this.jdbcTemplate.update("DELETE FROM users WHERE id = ?", object.getId());
    }

    @Override
    public User getById(@Nonnull Long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        return  (User)jdbcTemplate.queryForObject(sql, new Object[] {id}, new UserRowMapper());
    }

    @Nonnull
    @Override
    public Collection<User> getAll(){
        String sql = "SELECT * FROM users";
        Collection<User> users = new ArrayList<>();
        List<Map<String,Object>> map  = jdbcTemplate.queryForList(sql);
        for (Map row : map){
            User user = new User();
            user.setId((Long) row.get(row.get("id")));
            user.setFirstName((String) row.getOrDefault("first_name", null));
            user.setLastName((String) row.getOrDefault("last_name", null));
            user.setEmail((String) row.getOrDefault("email", null));
            user.setBirthday(LocalDate.parse(row.get("birthday").toString()));
            user.setRole(UserRole.valueOf(row.get("role").toString()));
            users.add(user);
        }
        return users;
    }

}
