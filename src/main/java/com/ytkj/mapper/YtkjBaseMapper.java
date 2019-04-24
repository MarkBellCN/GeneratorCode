package com.ytkj.mapper;

import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.base.BaseDeleteMapper;
import tk.mybatis.mapper.common.base.BaseSelectMapper;

/**
 * @Author: MarkBell
 * @Description:
 * @Date 2018/4/26
 */
@RegisterMapper
public interface YtkjBaseMapper<T> extends BaseSelectMapper<T>,BaseDeleteMapper<T>,YtkjInsertMapper<T>,YtkjUpdateMapper<T> {
}
