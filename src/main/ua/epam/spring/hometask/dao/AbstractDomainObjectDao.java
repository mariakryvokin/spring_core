package ua.epam.spring.hometask.dao;

import ua.epam.spring.hometask.entity.DomainObject;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Optional;

public interface AbstractDomainObjectDao<T> {
    public T save(@Nonnull T object);

    public void remove(@Nonnull T object);

    public T getById(@Nonnull Long id);

    public @Nonnull Collection<T> getAll();
}
