package hu.elte.progtech.jdbc.mysql.examples.macilaci.persistence.entity;

import java.io.Serializable;

public abstract class AbstractEntity implements Identifiable<Long>, Serializable {
    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
