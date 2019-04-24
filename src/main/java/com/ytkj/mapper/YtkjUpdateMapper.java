package com.ytkj.mapper;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.UpdateProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.base.BaseUpdateMapper;
import tk.mybatis.mapper.provider.base.BaseUpdateProvider;

/**
 * @Author: MarkBell
 * @Description:
 * @Date 2018/4/26
 */
@RegisterMapper
public interface YtkjUpdateMapper<T> extends BaseUpdateMapper<T> {
    @UpdateProvider(
            type = BaseUpdateProvider.class,
            method = "dynamicSQL"
    )
    @Options(useGeneratedKeys = false)
    int updateByPrimaryKey(T var1);

    @UpdateProvider(
            type = BaseUpdateProvider.class,
            method = "dynamicSQL"
    )
    @Options(useGeneratedKeys = false)
    int updateByPrimaryKeySelective(T var1);
}
