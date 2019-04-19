package ua.epam.spring.hometask.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.epam.spring.hometask.aspects.Counters;
import ua.epam.spring.hometask.dao.JdbcStatisticsDao;
import ua.epam.spring.hometask.entity.Event;
import ua.epam.spring.hometask.entity.Statistics;
import ua.epam.spring.hometask.entity.User;

import javax.annotation.Nonnull;
import java.util.*;

@Repository
public class JdbcStatisticsDaoImpl implements JdbcStatisticsDao {


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcStatisticsDaoImpl(@Qualifier("mainJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Statistics save(@Nonnull Statistics statistics) {
        User user = statistics.getUser();
        if (user != null){
            String sql = "INSERT INTO statistics (`action`, amount, users_id, events_id) values(?, ?, ?, ?)";
            jdbcTemplate.update(sql, statistics.getAction(), statistics.getAmount(), user.getId(), statistics.getEvent().getId());
            return statistics;
        }
        else {
            String sql = "INSERT INTO statistics (`action`, amount, events_id) values(?, ?, ?)";
            jdbcTemplate.update(sql, statistics.getAction(), statistics.getAmount(), statistics.getEvent().getId());
            return statistics;
        }
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
        String sql = "SELECT sum(amount) FROM statistics WHERE events_id = "+event.getId()+" AND `action` = '" + counters.toString() + "'";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    @Override
    public Map<String, Integer> countEachGivenDiscountAndGivenDiscountForUser(List<String> discountClassNames, User user) {
        Map<String,Integer> res= new HashMap<>();

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("discountClassNames", discountClassNames);
        String sql = null;
        if(user == null){
            sql = "SELECT `action`, sum(amount) as amount FROM statistics WHERE `action` IN (:discountClassNames) GROUP BY `action`";
        }else{
            sql = "SELECT `action`, sum(amount) as amount FROM statistics WHERE `action` IN (:discountClassNames) AND users_id = "+user.getId()+"  GROUP BY `action`";
        }

        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());

        List<Map<String,Object>> map = template.queryForList(sql,parameters);

        for(Map row : map){
            res.put(row.get("action").toString(),Integer.valueOf(row.get("amount").toString()));
        }

        return res;
    }


}
