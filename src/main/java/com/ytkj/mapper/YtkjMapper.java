package com.ytkj.mapper;

import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.ExampleMapper;
import tk.mybatis.mapper.common.Marker;
import tk.mybatis.mapper.common.RowBoundsMapper;

/**
 * @Author: MarkBell
 * @Description:
 * @Date 2018/4/26
 */
@RegisterMapper
public interface YtkjMapper<T> extends YtkjBaseMapper<T>, ExampleMapper<T>, RowBoundsMapper<T>, Marker {
}
