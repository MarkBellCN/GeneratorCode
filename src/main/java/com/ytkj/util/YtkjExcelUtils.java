package com.ytkj.util;

import com.github.crab2died.ExcelUtils;
import com.github.crab2died.annotation.ExcelField;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @Author: MarkBell
 * @Description:
 * @Date 2018/4/26
 */
@Slf4j
public class YtkjExcelUtils {
    private YtkjExcelUtils(){}

    private static YtkjExcelUtils utils;

    private List<Object[]> data ;

    private List<String> header;

    public static YtkjExcelUtils getInstance(){
        return new YtkjExcelUtils();
    }

    public  <T> List<T> readExcel2Objects(InputStream is, Class<T> clazz) {
        try {
            List<T>  list = ExcelUtils.getInstance().readExcel2Objects(is,clazz);
            return list;
        } catch (Exception e) {
            log.debug(e.getStackTrace().toString());
            log.info(e.getMessage());
            return null;
        }
    }

    public static <T> List<T> convert(List<?> list, Class<T> c) {
        return (List<T>)list;
    }

    public <T> void export(List<T> dataList,List<String> headerEn, Class clz,OutputStream os) throws Exception {
        initHeaderAndData(headerEn,clz,dataList);
        ExcelUtils.getInstance().exportObjects2Excel(data,header,os);
    }

    private <T> void initHeaderAndData(List<String> headerEn, Class clz,List<T> dataList) throws IllegalAccessException {
        List<Field> fields = getFields(clz,headerEn);
        if(header==null){
            header = new ArrayList<String>();
        }
        if(data==null){
            data = new ArrayList<Object[]>();
        }
        for(Field field:fields){
            if(headerEn.contains(field.getName())&&field.isAnnotationPresent(ExcelField.class)){
                ExcelField excelField = field.getAnnotation(ExcelField.class);
                header.add(excelField.title());
            }
        }
        for(T o:dataList){
            List<Object> obj = new ArrayList<Object>();
            for(Field field:fields){
                field.setAccessible(true);
                if(headerEn.contains(field.getName())&&field.isAnnotationPresent(ExcelField.class)){
                    ExcelField excelField = field.getAnnotation(ExcelField.class);
                    Map<String,String> formatMap = getFormatMap(excelField.title());
                    if(field.getType()==Date.class){
                        obj.add(DateUtil.dateToString(2, (Date) field.get(o)));
                        continue;
                    }
                    if(formatMap.size()>0){
                        if(formatMap.get(String .valueOf(field.get(o)))==null){
                            obj.add("");
                        }else {
                            obj.add(formatMap.get(String.valueOf(field.get(o))));
                        }
                    }else{
                        if(field.get(o)==null){
                            obj.add("");
                        }else {
                            obj.add(field.get(o));
                        }
                    }

                }
            }
            data.add(obj.toArray());
        }
    }

    private List<Field> getFields(Class clz,List<String> headerEn){
        List<Field> fields = new ArrayList();
        List<Field> sortFileds = new ArrayList<>();
        for(Class clazz = clz; clazz != Object.class; clazz = clazz.getSuperclass()) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
        }
        for(String en:headerEn){
            for(Field f:fields){
                if(en.equals(f.getName())){
                    sortFileds.add(f);
                    break;
                }
            }
        }
        return sortFileds;
    }

    private Map<String,String> getFormatMap(String formatStr){
        Map<String,String> map = new HashMap<String,String>();
        if("".equals(formatStr)){
            return map;
        }
        for(String f:formatStr.split(";")){
            String[] config = f.split(":");
            map.put(config[0],config[1]);

        }
        return map;
    }


}
