package com.zben.shop.common.mybatis;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;

/**
 * @DESC:
 * @AUTHOR: zhouben
 * @DATE: 2019/11/22 0022 18:41
 */
public interface BaseDao {

    @InsertProvider(type = BaseProvider.class, method = "save")
    public void save(@Param("obj") Object obj, @Param("table") String table);
}
