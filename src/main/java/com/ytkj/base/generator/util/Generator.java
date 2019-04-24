package com.ytkj.base.generator.util;

import com.ytkj.base.generator.model.JavaModel;
import com.ytkj.base.generator.prop.GeneratorProperties;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;

public abstract class Generator {

    public String fullClassPath;
    public JavaModel javaModel;
    public String tempSource;


    public Generator(JavaModel javaModel, String fullClassPath) {
        if (javaModel == null) {
            throw new RuntimeException("javaModel is null");
        }
        GeneratorProperties generatorProperties = new GeneratorProperties();
        this.javaModel = javaModel;
        this.fullClassPath = fullClassPath;
        this.javaModel.setModelClass(fullClassPath);
        String basePackage = fullClassPath.substring(0, fullClassPath.indexOf(generatorProperties.getModelPackageName()) - 1);
        this.javaModel.setBasePackage(basePackage);
    }


    public abstract void init();

    public String gen() {
        init();
        setModel();
        Configuration freemarkConfig = new Configuration();
        String tempResult = null;
        try {
            freemarkConfig.setClassForTemplateLoading(ServiceGenerator.class, "/generatorCode");
            Template temp = freemarkConfig.getTemplate(tempSource);
            StringWriter writer = new StringWriter();
            temp.process(javaModel, writer);
            tempResult = writer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return tempResult;
    }

    public void setModel() {

    }
}
