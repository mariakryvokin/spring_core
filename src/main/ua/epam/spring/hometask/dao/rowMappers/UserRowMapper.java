package ua.epam.spring.hometask.dao.rowMappers;

import org.springframework.jdbc.core.RowMapper;
import ua.epam.spring.hometask.entity.User;
import ua.epam.spring.hometask.entity.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class UserRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setEmail(resultSet.getString("email"));
        user.setBirthday(resultSet.getDate("birthday").toLocalDate());
        user.setRole(UserRole.valueOf(resultSet.getString("role")));
        return user;
    }

}
