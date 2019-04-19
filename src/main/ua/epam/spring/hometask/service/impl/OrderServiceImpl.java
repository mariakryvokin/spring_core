package ua.epam.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.dao.JdbcOrderDao;
import ua.epam.spring.hometask.entity.Order;
import ua.epam.spring.hometask.service.OrderService;

import javax.annotation.Nonnull;
import java.util.Collection;

@Service
public class OrderServiceImpl implements OrderService {

    private JdbcOrderDao jdbcOrderDao;

    @Autowired
    public OrderServiceImpl(JdbcOrderDao jdbcOrderDao) {
        this.jdbcOrderDao = jdbcOrderDao;
    }

    @Override
    public Order save(@Nonnull Order object) {
        return jdbcOrderDao.save(object);
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
