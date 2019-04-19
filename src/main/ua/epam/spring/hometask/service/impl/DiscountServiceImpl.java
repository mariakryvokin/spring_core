package ua.epam.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.entity.Event;
import ua.epam.spring.hometask.entity.User;
import ua.epam.spring.hometask.service.DiscountService;
import ua.epam.spring.hometask.service.discountStrategies.DiscountStrategies;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {

    private List<DiscountStrategies> discountStrategies;

    @Autowired
    public DiscountServiceImpl(List<DiscountStrategies> discountStrategies) {
        this.discountStrategies = discountStrategies;
    }

    @Override
    public double getDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long numberOfTickets,double totalPrice){
        return discountStrategies.stream().map(d->d.getDiscount(user,numberOfTickets,airDateTime,event,totalPrice)).max(Double::compareTo).get();
    }

    public List<DiscountStrategies> getDiscountStrategies() {
        return discountStrategies;
    }
}
