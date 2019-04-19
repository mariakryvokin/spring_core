package ua.epam.spring.hometask.entity;

import java.util.Objects;

/**
 * @author Yuriy_Tkach
 */
public class DomainObject {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DomainObject)) return false;
        DomainObject that = (DomainObject) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId());
    }
}
