package hu.elte.progtech.jdbc.mysql.examples.macilaci.persistence.entity;

public interface Identifiable <T extends Number> {

    T getId();

    void setId(T id);
}