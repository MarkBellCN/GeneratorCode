package com.ytkj.base.generator.model;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MapperXmlModel extends  JavaModel {

   private String tableName;
    private List<String> filedNames = new LinkedList<String>();
    public Map<String,String> fieldProperties;
    public List<String> propNames = new LinkedList<String>();
    public String getTableName() {
        return java2DBStyle(this.getClassName());
    }

    public Map<String, String> getFieldProperties() {
        if(this.fieldProperties==null){
            fieldProperties = new LinkedHashMap<String,String>();
            if(filedNames.size()>0){
                for(String filedName:filedNames){
                    fieldProperties.put(filedName,db2JavaStyle(filedName));
                }
            }else if(propNames.size()>0){
                for(String pName:propNames){
                    fieldProperties.put(java2DBStyle(pName),pName);
                }
            }

        }

        return fieldProperties;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<String> getFiledNames() {
        return filedNames;
    }

    public void setFiledNames(List<String> filedNames) {
        this.filedNames = filedNames;
    }

    public void setFieldProperties(Map<String, String> fieldProperties) {
        this.fieldProperties = fieldProperties;
    }

    public List<String> getPropNames() {
        return propNames;
    }

    public void setPropNames(List<String> propNames) {
        this.propNames = propNames;
    }
}
