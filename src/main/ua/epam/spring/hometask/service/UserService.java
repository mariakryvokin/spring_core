package ua.epam.spring.hometask.service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import ua.epam.spring.hometask.entity.Event;
import ua.epam.spring.hometask.entity.Ticket;
import ua.epam.spring.hometask.entity.User;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;


public interface UserService extends AbstractDomainObjectService<User> {

    /**
     * Finding user by email
     * 
     * @param email
     *            Email of the user
     * @return found user or <code>null</code>
     */
    public @Nullable
    Optional<User> getUserByEmail(@Nonnull String email);

    Collection<Ticket> getAllUserTickets(@Nonnull User user);

}
