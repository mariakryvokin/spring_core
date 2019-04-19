package ua.epam.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.dao.JdbcAuditoriumDao;
import ua.epam.spring.hometask.entity.Auditorium;
import ua.epam.spring.hometask.service.AuditoriumService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

@Service
public class AuditoriumServiceImpl implements AuditoriumService {

    private JdbcAuditoriumDao jdbcAuditoriumDao;

    @Autowired
    public AuditoriumServiceImpl(JdbcAuditoriumDao jdbcAuditoriumDao) {
        this.jdbcAuditoriumDao = jdbcAuditoriumDao;
    }

    @Nonnull
    @Override
    public Set<Auditorium> getAll() {
        return null;
    }

    @Nullable
    @Override
    public Auditorium getByName(@Nonnull String name) {
        return jdbcAuditoriumDao.getByName(name);
    }

/*    private List<Auditorium> auditorium;

    @Autowired
    public AuditoriumServiceImpl(List<Auditorium> auditorium) {
        this.auditorium = auditorium;
    }

    @Nonnull
    @Override
    public Set<Auditorium> getAll() {
        return auditorium.stream().collect(Collectors.toSet());
    }

    @Nullable
    @Override
    public Auditorium getByName(@Nonnull String name) {
        return auditorium.stream().filter(c->c.getName().equals(name)).collect(Collectors.toList()).get(0);
    }*/

}
