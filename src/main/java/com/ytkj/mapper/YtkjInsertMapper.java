package com.ytkj.mapper;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.base.BaseInsertMapper;
import tk.mybatis.mapper.common.base.insert.InsertSelectiveMapper;
import tk.mybatis.mapper.provider.base.BaseInsertProvider;

/**
 * @Author: MarkBell
 * @Description:
 * @Date 2018/4/26
 */
@RegisterMapper
public interface YtkjInsertMapper<T> extends BaseInsertMapper<T>,InsertSelectiveMapper<T> {
    @InsertProvider(
            type = BaseInsertProvider.class,
            method = "dynamicSQL"
    )
    @Options(useGeneratedKeys = false)
    int insert(T var1);

    @InsertProvider(
            type = BaseInsertProvider.class,
            method = "dynamicSQL"
    )
    @Options(useGeneratedKeys = false)
    int insertSelective(T var1);

}
