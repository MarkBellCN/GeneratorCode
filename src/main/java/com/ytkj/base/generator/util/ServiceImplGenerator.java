package com.ytkj.base.generator.util;


import com.ytkj.base.generator.model.JavaModel;

public class ServiceImplGenerator extends Generator{

    public ServiceImplGenerator(String fullClassPath) {
        super(new JavaModel(),fullClassPath);
    }
    @Override
    public void init() {
        this.tempSource= "ServiceImpl.vm";
    }
}
