package app.dao;

import app.util.Database;

import java.sql.Connection;

public abstract class DAO<T> {

    public Connection connection = Database.getConnection();

    public abstract T findById(long id);

    public abstract T create(T obj);

    public abstract T update(T obj);

    public abstract void delete(T obj);

}
