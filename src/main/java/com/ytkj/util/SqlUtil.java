package com.ytkj.util;

import com.ytkj.http.SortItem;
import tk.mybatis.mapper.code.Style;
import tk.mybatis.mapper.util.StringUtil;

import javax.persistence.Column;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @Author: MarkBell
 * @Description:
 * @Date 2018/4/23
 */
public class SqlUtil {
    public static String getWhereSql(Object o, String tag){
        StringBuffer where = new StringBuffer(" where 1=1 ");
        Field[] fields = o.getClass().getDeclaredFields();
        for(Field f:fields){
            try {
                f.setAccessible(true);
                if(f.get(o)==null||"".equals(String.valueOf(f.get(o)))){
                    continue;
                }
                if(f.getType()==String.class) {
                    if("".equals(tag)||tag==null){
                        where.append(" and ").append(StringUtil.convertByStyle(f.getName(),Style.camelhumpAndUppercase)).append(" like '").append("%"+String.valueOf(f.get(o))+"%'");
                    }else{
                        where.append(" and ").append(tag).append(".").append(StringUtil.convertByStyle(f.getName(),Style.camelhumpAndUppercase)).append(" like '").append("%"+String.valueOf(f.get(o))+"%'");
                    }
                }else{
                    if("".equals(tag)||tag==null){
                        where.append(" and ").append(StringUtil.convertByStyle(f.getName(),Style.camelhumpAndUppercase)).append(" = '").append(String.valueOf(f.get(o))).append("'");
                    }else{
                        where.append(" and ").append(tag).append(".").append(StringUtil.convertByStyle(f.getName(),Style.camelhumpAndUppercase)).append(" = '").append(String.valueOf(f.get(o))).append("'");
                    }

                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return where.toString();
    }

    public static String getSortMapToStr(Object o, List<SortItem> sortMap, String tag) {
        StringBuffer sb = new StringBuffer();
        for(SortItem sortItem : sortMap){
            try {
                Field field=o.getClass().getDeclaredField(sortItem.getName());
                if(field!=null){
                    Column column=(Column) field.getAnnotation(Column.class);
                    if(!StringUtils.isEmpty(column.name())){
                        if(sb.length()<=0){
                            sb.append(column.name());
                            sb.append(" "+sortItem.getValue());
                        }else{
                            sb.append(",");
                            sb.append(column.name());
                            sb.append(" "+sortItem.getValue());
                        }
                    }
                }
            } catch (NoSuchFieldException e) {
                continue;
            }
        }

        return sb.toString();
    }


}
