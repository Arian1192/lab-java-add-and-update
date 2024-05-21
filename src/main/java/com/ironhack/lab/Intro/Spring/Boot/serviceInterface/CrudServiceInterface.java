package com.ironhack.lab.Intro.Spring.Boot.serviceInterface;

import java.util.List;

public interface CrudServiceInterface<T> {

    public T getEntityById(String id);

    public List<T> getAllEntities();

    public void addNewEntity(T entity);

    public void putEntity(String id, T entity);

    public void patchEntity(Object dto);

}
