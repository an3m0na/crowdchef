package com.crowdchef.datamodel;

import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.service.*;

public class DatabaseUtil {
    private static final SessionFactory sessionFactory;
    private static final String HYBERNATE_CONFIG = "database_config.xml";
    static
    {
        try
        {
            Configuration myConfiguration = new Configuration().configure(HYBERNATE_CONFIG);
            ServiceRegistry myServiceRegistry = new ServiceRegistryBuilder().applySettings(myConfiguration.getProperties())
                    .buildServiceRegistry();
            sessionFactory = myConfiguration.buildSessionFactory(myServiceRegistry);

        }
        catch (Throwable ex)
        {
            // Log exception!
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession()
            throws HibernateException
    {
        return sessionFactory.openSession();
    }

    public static void close()
    {
        sessionFactory.close();
    }
}
