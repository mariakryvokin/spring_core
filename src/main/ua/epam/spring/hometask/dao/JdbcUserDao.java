package ua.epam.spring.hometask.dao;

import ua.epam.spring.hometask.entity.Ticket;
import ua.epam.spring.hometask.entity.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Optional;

public interface JdbcUserDao extends AbstractDomainObjectDao<User> {
    @Nullable
    Optional<User> getUserByEmail(@Nonnull String email);

    Collection<Ticket> getAllUserTickets(@Nonnull User user);
}
