package ua.epam.spring.hometask.service;

import ua.epam.spring.hometask.aspects.Counters;
import ua.epam.spring.hometask.entity.Event;
import ua.epam.spring.hometask.entity.Statistics;
import ua.epam.spring.hometask.entity.User;

import java.util.List;
import java.util.Map;

public interface StatisticsService extends AbstractDomainObjectService<Statistics> {

    Long countMethodsCallForEvent(Event event, Counters counters);

    Map<String,Integer> countEachGivenDiscountAndGivenDiscountForUser(List<String> discountClassNames, User user);


}
