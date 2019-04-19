package ua.epam.spring.hometask.dao;

import ua.epam.spring.hometask.entity.Event;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

public interface JdbcEventDao extends AbstractDomainObjectDao<Event> {
    public @Nullable
    Optional<Event> getByName(@Nonnull String name);

    public @Nonnull
    Set<Event> getForDateRange(@Nonnull LocalDateTime from, @Nonnull LocalDateTime to);

    public @Nonnull
    Set<Event> getNextEvents(@Nonnull LocalDateTime to);
}
