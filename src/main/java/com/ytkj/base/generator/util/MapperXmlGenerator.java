package com.ytkj.base.generator.util;

import com.ytkj.base.generator.model.JavaModel;

public class MapperXmlGenerator extends  Generator {


    public MapperXmlGenerator(String fullClassPath) {
        super(new JavaModel(),fullClassPath);
    }

    @Override
    public void init() {
        this.tempSource = "Mapper.xml.vm";
    }
}
