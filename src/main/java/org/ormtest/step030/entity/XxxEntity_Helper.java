package org.ormtest.step030.entity;

import org.ormtest.step010.entity.UserEntity;

import java.lang.reflect.Field;
import java.sql.ResultSet;

/**
 * 实体助手类, 这个更通用
 */
public class XxxEntity_Helper {
    /**
     * 将数据集装换为实体对象
     *
     * @param rs 数据集
     * @return
     * @throws Exception
     */
    public <TEntity>TEntity create(Class<TEntity> entityClass,ResultSet rs) throws Exception {
        if (null==rs)return null;
        // 更通用的助手类,
        // 甭管实体类是哪个, 也甭管实体类有多少属性,
        // 全灭!
        // 但是,
        // 就是性能太差了...

        //创建新的实体类对象
        Object tEntity = entityClass.newInstance();
        //获取字段的数组
        Field[] fields = tEntity.getClass().getFields();
        for (Field field : fields) {
            //获取字段上的注解
            Column annotation = field.getAnnotation(Column.class);
            // 如果注解为空,
            // 则直接跳过...
            if (null==annotation)continue;
            //获取列名
            String name = annotation.name();
            //从数据库中获取列值
            Object object = rs.getObject(name);
            // 如果列值为空,
            // 则直接跳过...
            if (null==object)continue;
            field.set(tEntity, object);
        }
        return (TEntity) tEntity;
    }
}
