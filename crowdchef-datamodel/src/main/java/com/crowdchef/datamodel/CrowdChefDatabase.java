package com.crowdchef.datamodel;

import org.hibernate.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class CrowdChefDatabase {

    /*Todo
    Create a visitor pattern with a interface called Datamodel which accepts a DatamodelVisitor.
    Datamodel visitor visits each datamodel class. The idea database layer testable and more clean.
     */

    @NotNull
    private Session session;

    @NotNull
    private Transaction transaction; //bla

    public <T> void save(@NotNull T aToBeSaved, Class<T> aClass)
    {
        session = DatabaseUtil.getSession();
        transaction = session.beginTransaction();
        session.save(aToBeSaved);
        transaction.commit();
        session.close();
    }

    public <T> void persist(@NotNull T aToBeSaved,
                            Class<T> aClass)
    {
        session = DatabaseUtil.getSession();
        transaction = session.beginTransaction();
        session.persist(aToBeSaved);
        transaction.commit();
        session.close();
    }

    @SuppressWarnings({"List conversion", "unchecked"})
    public <T> List<T> retrieve(
            String queryName,
            Map<String, String> parameters,
            Class<T> aClass)
    {
        session = DatabaseUtil.getSession();
        transaction = session.beginTransaction();
        Query myQuery = session.getNamedQuery(queryName);
        for (String parameter : parameters.keySet())
        {
            myQuery.setString(parameter, parameters.get(parameter));
        }
        List<T> recordSet = myQuery.list();
        session.close();

        return recordSet;
    }

    public <T> List<T> retrieve(String queryName,
                                Class<T> aClass)
    {
        session = DatabaseUtil.getSession();
        transaction = session.beginTransaction();
        Query myQuery = session.getNamedQuery(queryName);
        List<T> recordSet = myQuery.list();
        session.close();

        return recordSet;
    }

    public <T> List<T> retrieve(String queryName,
                                String parameter,
                                String value,
                                Class<T> aClass)
    {
        session = DatabaseUtil.getSession();
        transaction = session.beginTransaction();
        Query myQuery = session.getNamedQuery(queryName);
        myQuery.setString(parameter, value);

        List<T> recordSet = myQuery.list();
        session.close();

        return recordSet;
    }

    public <T> void saveOrUpdate(@NotNull T aToBeSaved,
                                 Class<T> aClass)
    {
        session = DatabaseUtil.getSession();
        transaction = session.beginTransaction();
        session.saveOrUpdate(aToBeSaved);
        transaction.commit();
        session.close();
    }

    public <T> void saveOrUpdate(@NotNull Collection<T> aToBeSaved,
                                 Class<T> aClass)
    {
        session = DatabaseUtil.getSession();
        transaction = session.beginTransaction();
        session.saveOrUpdate(aToBeSaved);
        transaction.commit();
        session.close();
    }

    public <T> void delete(@NotNull T aToBeSaved,
                           Class<T> aClass)
    {
        session = DatabaseUtil.getSession();
        transaction = session.beginTransaction();
        session.delete(aToBeSaved);
        transaction.commit();
        session.close();
    }

    public <T> void delete(@NotNull List<T> aToBeDeleted,
                           Class<T> aClass)
    {
        session = DatabaseUtil.getSession();
        transaction = session.beginTransaction();

        for(T myObjectToBeDeleted : aToBeDeleted)
        {
            session.delete(myObjectToBeDeleted);
        }

        transaction.commit();
        session.close();
    }
}