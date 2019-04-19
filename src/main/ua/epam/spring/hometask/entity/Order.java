package ua.epam.spring.hometask.entity;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Order extends DomainObject implements Comparable<Order> {

    private User user;

    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order that = (Order) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getUser(), getPrice());
    }

    @Override
    public int compareTo(Order o) {
        if (o == null || o.getId()==null) {
            return 1;
        }
        return this.getId().compareTo(o.getId());
    }

    @Override
    public String toString() {
        return "Order{" +
                "id="+ super.getId()+
                "user=" + user +
                ", price=" + price +
                '}';
    }
}
