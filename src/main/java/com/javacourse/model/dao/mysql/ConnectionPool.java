package com.javacourse.model.dao.mysql;

import com.javacourse.controller.util.PropertiesLoader;
import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author Yuliia Tesliuk
 */
class ConnectionPool {
    private static volatile BasicDataSource dataSource;
    private static Properties properties = new PropertiesLoader().getLoadedProperties("mysql_db.properties");
    private static final String URL = properties.getProperty("url"); //TODO field is nullable. need to check for exceptional situations
    private static final String USERNAME = properties.getProperty("username");
    private static final String PASSWORD = properties.getProperty("password");

    static DataSource getDataSource(){
        if(dataSource == null) {
            synchronized (ConnectionPool.class){
                if (dataSource == null){
                    dataSource = new BasicDataSource();
                    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
                    dataSource.setUrl(URL);
                    dataSource.setUsername(USERNAME);
                    dataSource.setPassword(PASSWORD);
                    dataSource.setMinIdle(5);
                    dataSource.setMaxIdle(10);
                }
            }
        }
        return dataSource;
    }
}
