package ua.epam.spring.hometask.dao;


import ua.epam.spring.hometask.entity.Auditorium;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

public interface JdbcAuditoriumDao {
    public @Nonnull
    Set<Auditorium> getAll();

    public @Nullable
    Auditorium getByName(@Nonnull String name);
}
