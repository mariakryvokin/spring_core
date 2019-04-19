package ua.epam.spring.hometask.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.epam.spring.hometask.dao.JdbcAuditoriumDao;
import ua.epam.spring.hometask.dao.rowMappers.AuditoriumMapper;
import ua.epam.spring.hometask.entity.Auditorium;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

@Repository
@PropertySource("classpath:auditorium.properties")
public class JdbcAuditoriumDaoImpl implements JdbcAuditoriumDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcAuditoriumDaoImpl(@Qualifier("mainJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Nonnull
    @Override
    public Set<Auditorium> getAll() {
        return null;
    }

    @Nullable
    @Override
    public Auditorium getByName(@Nonnull String name) {
        String sql = "SELECT * FROM auditoriums WHERE name = ?";
        return  (Auditorium) jdbcTemplate.queryForObject(sql, new Object[] {name}, new AuditoriumMapper());
    }
}
