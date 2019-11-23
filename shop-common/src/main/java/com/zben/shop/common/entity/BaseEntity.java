package com.zben.shop.common.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * @DESC:
 * @author: zhouben
 * @date: 2019/11/22 0022 18:45
 */
@Getter
@Setter
public class BaseEntity {

    /**
     * 主键
     */
    private Long id;
    /**
     * 创建时间
     */
    private Timestamp created;
    /**
     * 修改时间
     */
    private Timestamp updated;

}
