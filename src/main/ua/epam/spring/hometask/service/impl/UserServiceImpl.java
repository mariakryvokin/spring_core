package ua.epam.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.dao.JdbcUserDao;
import ua.epam.spring.hometask.entity.Ticket;
import ua.epam.spring.hometask.entity.User;
import ua.epam.spring.hometask.service.UserService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private JdbcUserDao jdbcUserDao;

    @Autowired
    public UserServiceImpl(JdbcUserDao userDao) {
        this.jdbcUserDao = userDao;
    }

    @Nullable
    @Override
    public Optional<User> getUserByEmail(@Nonnull String email) {
        return jdbcUserDao.getUserByEmail(email);
    }

    @Override
    public Collection<Ticket> getAllUserTickets(@Nonnull User user) {
        return jdbcUserDao.getAllUserTickets(user);
    }

    @Override
    public User save(@Nonnull User object) {
        return jdbcUserDao.save(object);
    }

    @Override
    public void remove(@Nonnull User object) {
        jdbcUserDao.remove(object);
    }

    @Override
    public User getById(@Nonnull Long id) {
        return jdbcUserDao.getById(id);
    }

    @Nonnull
    @Override
    public Collection<User> getAll() {
        return jdbcUserDao.getAll();
    }

}
