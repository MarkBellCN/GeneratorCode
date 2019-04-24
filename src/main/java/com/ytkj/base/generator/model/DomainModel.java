package com.ytkj.base.generator.model;

import java.util.HashMap;
import java.util.Map;

public class DomainModel extends JavaModel{

	private String tableName;
	private String tableRemarks;
	private Map<String,String> propertiesMap = new HashMap<String,String>();
	private Map<String,String> columnMap = new HashMap<String,String>();
	private Map<String,String> remarksMap = new HashMap<String,String>();
	private Map<String,String> typeMap = new HashMap<String,String>();
	{
		initMap();
	}
	public Map<String, String> getPropertiesMap() {
		return propertiesMap;
	}
	public Map<String, String> getColumnMap() {
		return columnMap;
	}
	public Map<String, String> getRemarksMap() {
		return remarksMap;
	}
	public void setProperties(String prop,String type){
		this.propertiesMap.put(this.db2JavaStyle(prop), typeMap.get(type));
	}
	public void setColumn(String key,String value){
		this.columnMap.put(this.db2JavaStyle(key), value);
	}
	public void setRemarks(String key,String value){
		this.remarksMap.put(this.db2JavaStyle(key), value);
	}

	public String getTableRemarks() {
		return tableRemarks;
	}

	public void setTableRemarks(String tableRemarks) {
		this.tableRemarks = tableRemarks;
	}

	private void initMap (){
		typeMap.put("VARCHAR", "String");
		typeMap.put("DATETIME", "Date");
		typeMap.put("BIGINT", "Integer");
		typeMap.put("DOUBLE", "Double");
		typeMap.put("TINYINT", "Integer");
		typeMap.put("INT", "Integer");
		typeMap.put("LONGTEXT", "String");
		typeMap.put("TEXT", "String");
		typeMap.put("NVARCHAR2","String");
		typeMap.put("CHAR","String");
		typeMap.put("NUMBER", "Integer");
		typeMap.put("DATE", "Date");
		typeMap.put("TIMESTAMP(6)", "Date");
		typeMap.put("VARCHAR2", "String");
		typeMap.put("CLOB", "String");
		typeMap.put("LONG", "Long");
	}
	public void setPropertiesMap(Map<String, String> propertiesMap) {
		this.propertiesMap = propertiesMap;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public DomainModel() {
	}
	public DomainModel(JavaModel javaModel) {
		this.packageName = javaModel.packageName;
		this.className = javaModel.className;
		this.classNickName = javaModel.classNickName;
		this.basePackage = javaModel.basePackage;
		this.modelClass = javaModel.modelClass;
	}
}
