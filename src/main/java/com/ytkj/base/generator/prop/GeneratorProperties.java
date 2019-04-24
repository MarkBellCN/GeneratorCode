package com.ytkj.base.generator.prop;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;
import java.util.Set;

public class GeneratorProperties {
    private String username;
    private String password;
    private String schema;
    private String url ;
    private String driverClassName;
    private String database;
    private String modelPackageName;
    private String daoPackageName;
    private String servicePackageName;
    private String baseMapper;
    private String controllerPackageName;

    {
        Properties props = new Properties();
        Properties applicationProp = new Properties();
        try {
            props.load(this.getClass().getResourceAsStream("/generator.properties"));
            applicationProp.load(this.getClass().getResourceAsStream("/application.properties"));
            Set<String> propsNames = props.stringPropertyNames();
            for(String name:propsNames){
                try {
                    Field field = this.getClass().getDeclaredField(name);
                    field.setAccessible(true);
                    field.set(this,props.get(name));
                } catch (NoSuchFieldException e) {
                    continue;
                } catch (IllegalAccessException e) {
                    continue;
                }
            }
            baseMapper = applicationProp.getProperty("mapper.mappers");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getModelPackageName() {
        return modelPackageName;
    }

    public void setModelPackageName(String modelPackageName) {
        this.modelPackageName = modelPackageName;
    }

    public String getServicePackageName() {
        return servicePackageName;
    }

    public void setServicePackageName(String servicePackageName) {
        this.servicePackageName = servicePackageName;
    }

    public String getDaoPackageName() {
        return daoPackageName;
    }

    public void setDaoPackageName(String daoPackageName) {
        this.daoPackageName = daoPackageName;
    }

    public String getBaseMapper() {
        return baseMapper;
    }

    public void setBaseMapper(String baseMapper) {
        this.baseMapper = baseMapper;
    }

    public String getControllerPackageName() {
        return controllerPackageName;
    }

    public void setControllerPackageName(String controllerPackageName) {
        this.controllerPackageName = controllerPackageName;
    }
}
