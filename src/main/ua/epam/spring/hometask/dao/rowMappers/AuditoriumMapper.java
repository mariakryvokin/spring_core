package ua.epam.spring.hometask.dao.rowMappers;

import org.springframework.jdbc.core.RowMapper;
import ua.epam.spring.hometask.entity.Auditorium;

import java.sql.ResultSet;
import java.sql.SQLException;


public class AuditoriumMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Auditorium auditorium = new Auditorium();
        auditorium.setName(resultSet.getString("name"));
        auditorium.setNumberOfSeats(resultSet.getLong("amount_of_seats"));
        return auditorium;
    }
}
