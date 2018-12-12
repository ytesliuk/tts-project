package com.javacourse.model.dao;

import com.javacourse.model.dao.mysql.MySQLDaoFactory;

/**
 * @author Yuliia Tesliuk
 */
public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract UserDao createUserDao();
    public abstract TaskDao createTaskDao();

    public static DaoFactory getInstance(){
        if(daoFactory == null){
            synchronized(DaoFactory.class) {
                if(daoFactory == null) {
                    daoFactory = new MySQLDaoFactory();
                }
            }
        }
        return daoFactory;
    }
}
