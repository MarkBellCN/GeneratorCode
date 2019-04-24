package com.ytkj.base.generator.util;

import com.ytkj.base.generator.model.DomainModel;
import com.ytkj.base.generator.model.JavaModel;
import com.ytkj.base.generator.prop.GeneratorProperties;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DomainGenerator extends Generator {

    private String database;
    private String table;
    private DomainModel model;
    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public DomainGenerator(String fullClassPath) {
        super(new DomainModel(), fullClassPath);

    }

    public DomainGenerator(String fullClassPath, String table) {
        super(new DomainModel(), fullClassPath);
        String name = new JavaModel().db2JavaStyle(table);
        String className = name.substring(0, 1).toUpperCase() + name.substring(1);
        model = (DomainModel) this.javaModel;
        model.setPackageName(this.fullClassPath.substring(0, this.fullClassPath.lastIndexOf(".")));
        model.setClassName(className);
        model.setTableName(table.toUpperCase());
        this.table = table;
        this.javaModel = this.model;
    }

    @Override
    public void init() {
        this.tempSource = "Domain.vm";
        this.database = new GeneratorProperties().getDatabase();
        getColumns(table);
    }

    private List<Map<String, String>> getColumns(String tableName) {
        DatabaseMetaData metaData = null;
        try {
            Map table=getTable(tableName.toUpperCase());
            metaData = connection.getMetaData();
            ResultSet rs = metaData.getColumns(null, database, table.get("tableName").toString(), "%");
            if(table.get("remarks")!=null){
                model.setTableRemarks(table.get("remarks").toString());
            }else{
                model.setTableRemarks(table.get("tableName").toString());
            }

            while (rs.next()) {
                String columnName = rs.getString("COLUMN_NAME");//列名
                String dataTypeName = rs.getString("TYPE_NAME");
                String remarks = rs.getString("REMARKS");//列描述
                model.setProperties(columnName, dataTypeName);
                model.setColumn(columnName,columnName);
                if(remarks==null){
                    model.setRemarks(columnName,columnName);
                }else{
                    model.setRemarks(columnName,remarks);
                }
                model.setClassName(model.db2JavaStyle(tableName));
            }
            model.getTableName();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }

    private Map<String, String> getTable(String tabName) {
        Map map = new HashMap<String, String>();
        DatabaseMetaData metaData = null;
        try {
            metaData = connection.getMetaData();
            // table type. Typical types are "TABLE", "VIEW", "SYSTEM TABLE", "GLOBAL TEMPORARY", "LOCAL TEMPORARY", "ALIAS", "SYNONYM".
            String[] types = {"TABLE"};
            ResultSet rs = metaData.getTables(null, database, tabName.toUpperCase(), types);
            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME");  //表名
                String tableType = rs.getString("TABLE_TYPE");  //表类型
                String remarks = rs.getString("REMARKS");       //表备注
                map.put("tableName", tableName);
                map.put("tableType", tableType);
                map.put("remarks", remarks);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;

    }

    private List<Map<String, String>> getTables() {
        DatabaseMetaData metaData = null;
        List<Map<String, String>> tables = new ArrayList<Map<String, String>>();
        try {
            metaData = connection.getMetaData();
            // table type. Typical types are "TABLE", "VIEW", "SYSTEM TABLE", "GLOBAL TEMPORARY", "LOCAL TEMPORARY", "ALIAS", "SYNONYM".
            String[] types = {"TABLE"};
            ResultSet rs = metaData.getTables(null, database, "%", types);
            while (rs.next()) {
                Map map = new HashMap<String, String>();
                String tableName = rs.getString("TABLE_NAME");  //表名
                String tableType = rs.getString("TABLE_TYPE");  //表类型
                String remarks = rs.getString("REMARKS");       //表备注
                map.put("tableName", tableName);
                map.put("tableType", tableType);
                map.put("remarks", remarks);
                tables.add(map);
                System.out.println(tableName + "-" + tableType + "-" + remarks);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tables;

    }


}
