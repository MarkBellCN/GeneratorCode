package com.ytkj.util;

import com.ytkj.mapper.YtkjMapper;
import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Author: MarkBell
 * @Description:
 * @Date 2018/4/12
 */
@RegisterMapper
public interface YtkjCommonMapper<T> extends YtkjMapper<T>,MySqlMapper<T> {

}
