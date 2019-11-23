package com.zben.shop.common.mybatis;

import com.zben.shop.common.utils.ReflectionUtils;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * @DESC:
 * @author: zhouben
 * @date: 2019/11/22 0022 18:43
 */
public class BaseProvider {

    public String save(Map<String, Object> map) {
        // 实体类
        Object oj = map.get("obj");
        // 表名称
        String table = (String) map.get("table");
        // 生成添加的sql语句。 使用反射机制
        // 步驟：使用反射机制加载这个类所有属性
        // INSERT INTO `mb_user` (username,password,phone,email,created,updated)
        // VALUES ('yushengjun2', 'e10adc3949ba59abbe56e057f20f883e',
        // '15527339673', 'aa1@a', '2015-04-06 17:03:55', '2015-04-06
        // 17:03:55');
        SQL sql = new SQL() {
            {

                INSERT_INTO(table);
                VALUES(ReflectionUtils.fatherAndSonField(oj), ReflectionUtils.fatherAndSonFieldValue(oj));

            }
        };

        return sql.toString();
    }
}
