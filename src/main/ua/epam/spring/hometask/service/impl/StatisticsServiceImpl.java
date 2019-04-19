package ua.epam.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.aspects.Counters;
import ua.epam.spring.hometask.dao.JdbcStatisticsDao;
import ua.epam.spring.hometask.entity.Event;
import ua.epam.spring.hometask.entity.Statistics;
import ua.epam.spring.hometask.entity.User;
import ua.epam.spring.hometask.service.StatisticsService;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    private JdbcStatisticsDao jdbcStatisticsDao;

    @Override
    public Statistics save(@Nonnull Statistics object) {
        return jdbcStatisticsDao.save(object);
    }

    @Override
    public void remove(@Nonnull Statistics object) {

    }

    @Override
    public Statistics getById(@Nonnull Long id) {
        return null;
    }

    @Nonnull
    @Override
    public Collection<Statistics> getAll() {
        return null;
    }

    @Override
    public Long countMethodsCallForEvent(Event event, Counters counters) {
        return jdbcStatisticsDao.countMethodsCallForEvent(event, counters);
    }

    @Override
    public Map<String, Integer> countEachGivenDiscountAndGivenDiscountForUser(List<String> discountClassNames, User user) {
        return jdbcStatisticsDao.countEachGivenDiscountAndGivenDiscountForUser(discountClassNames, user);
    }


}
