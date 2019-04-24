package com.ytkj.util;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: MarkBell
 * @Description:通用的service提供通用的方法
 * @Date 2018/4/13
 */
public class YtkjCommonService <M extends YtkjCommonMapper<T>,T extends Serializable> {
    @Autowired
    private M m;

    public T queryById(Object id){
        return this.m.selectByPrimaryKey(id);
    }

    public Page<T> pageQuery(T t, int pageNum, int pageSize, String orderBy){
        Page<T> page = PageHelper.startPage(pageNum,pageSize,orderBy);
        this.m.select(t);
        return page;
    }

    public T update(T t){
        this.m.updateByPrimaryKeySelective(t);
        return t;
    }

    public void deleteById(String id){
        this.m.deleteByPrimaryKey(id);
    }

    public List<T> queryAll(T t){
        return this.m.select(t);
    }

    public List<T> queryAll(T t,String orderBy){
        Example example = new Example(t.getClass());
        example.setOrderByClause(orderBy);
        Example.Criteria c = example.createCriteria();
        Field[] fields = getAllFields(t);
        List<Field> sortFileds = new ArrayList<>();

        for(Field f:fields){
            try {
                f.setAccessible(true);
                if(f.get(t)==null||"".equals(String.valueOf(f.get(t)))){
                    continue;
                }
                c.andEqualTo(f.getName(),f.get(t));
            } catch (IllegalAccessException e) {
                continue;
            }
        }
        return this.m.selectByExample(example);
    }

    public T save(T t){
        this.m.insertSelective(t);
        return t;
    }

    public Page<T> pageQueryLike(T t,int pageNum,int pageSize,String orderBy){
        Page<T> page = PageHelper.startPage(pageNum,pageSize,orderBy);
        Example example =queryLikeGetExample(t,null,orderBy);
        m.selectByExample(example);
        return page;
    }
    public Page<T> pageQueryLikeWithOutFields(T t,List<String> withFields,int pageNum,int pageSize,String orderBy){
        Page<T> page = PageHelper.startPage(pageNum,pageSize,orderBy);
        Example example =queryLikeGetExample(t,withFields,orderBy);
        m.selectByExample(example);
        return page;
    }

    /**
     * 根据类反射生成Example
     */
    protected Example queryLikeGetExample(Object o,List<String> withFields,String orderBy){
        Example example = new Example(o.getClass(),false);
        if(!StringUtils.isEmpty(orderBy)){
            example.setOrderByClause(orderBy);
        }
        Example.Criteria criteria = example.createCriteria();
        queryLikeGetCriteriaWithOutFields(o,withFields,criteria);
        return example;
    }
    /**
     * 根据类反射给Criteria添加查询参数
     */
    private void queryLikeGetCriteriaWithOutFields(Object o,List<String> withFields,Example.Criteria criteria){
        Field[] fields = getAllFields(o);
        for(Field f:fields){
            Boolean skipThis=false;
            try {
                f.setAccessible(true);
                if(f.get(o)==null ||"".equals(String.valueOf(f.get(o)))){
                    continue;
                }
                if(f.getType()==String.class) {
                    skipThis=false;
                    if(withFields!=null){
                        for (int i = 0; i <withFields.size() ; i++) {
                            if(withFields.get(i).equals(f.getName())){
                                skipThis=true;
                                break;
                            }
                        }
                    }
                    if(skipThis){
                        continue;
                    }
                    criteria.andLike(f.getName(),"%"+String.valueOf(f.get(o))+"%");
                }else{
                    skipThis=false;
                    if(withFields!=null){
                        for (int i = 0; i <withFields.size() ; i++) {
                            if(withFields.get(i).equals(f.getName())){
                                skipThis=true;
                                break;
                            }
                        }
                    }
                    if(skipThis){
                        continue;
                    }
                    criteria.andEqualTo(f.getName(),f.get(o));
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 获取定义的所有字段
     */
    private Field[] getAllFields(Object object){
        Class clazz = object.getClass();
        List<Field> fieldList = new ArrayList<>();
        while (clazz != Object.class){
            fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        fieldList.toArray(fields);
        return fields;
    }


}
