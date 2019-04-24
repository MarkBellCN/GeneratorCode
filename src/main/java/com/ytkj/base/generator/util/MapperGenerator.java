package com.ytkj.base.generator.util;


import com.ytkj.base.generator.model.MapperXmlModel;
import com.ytkj.base.generator.prop.GeneratorProperties;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public  class MapperGenerator extends Generator {


    public MapperGenerator(String fullClassPath) {
        super(new MapperXmlModel(),fullClassPath);
    }

    @Override
    public void init() {
        this.tempSource ="Mapper.vm";
    }

    @Override
    public void setModel() {
        GeneratorProperties generatorProperties = new GeneratorProperties();
        String packagePath = this.fullClassPath.substring(0,this.fullClassPath.indexOf("."+generatorProperties.getModelPackageName()+"."))
                +"."+generatorProperties.getDaoPackageName();
        MapperXmlModel javaModel = (MapperXmlModel) this.javaModel;
        String className =this.fullClassPath.substring(this.fullClassPath.lastIndexOf(".")+1);
        String classNickName = className.substring(0,1).toLowerCase()+className.substring(1);
        javaModel.setClassName(className);
        javaModel.setClassNickName(classNickName);
        javaModel.setPackageName(packagePath);
        List<String> filedNames = new ArrayList<String>();
        try {
            Class clazz =  Class.forName(this.fullClassPath);
            Field[] fs = clazz.getDeclaredFields();
            for(Field f:fs){
                filedNames.add(f.getName());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        javaModel.setPropNames(filedNames);
        this.javaModel = javaModel;

    }
}
