package org.ormtest.step020.entity;


import java.lang.reflect.Field;
import java.sql.ResultSet;

/**
 * 用户实体助手类
 */
public class UserEntity_Helper {
    /**
     * 将数据集装换为实体对象
     *
     * @param rs 数据集
     * @return
     * @throws Exception
     */
    public UserEntity create(ResultSet rs) throws Exception {
        if (null==rs)return null;
        // 有了反射,
        // 这下就不怕实体类的修改了...
        // 实体类你随便改!
        // 我们还能再优化一步, 将这个 UserEntity_Handler 改的更通用!
        // 跳到 XxxEntity_Helper 类看看!

        //创建新的实体类对象
        UserEntity userEntity=new UserEntity();
        //获取类的字段数组
        Field[] fields = userEntity.getClass().getFields();
        for (Field field : fields) {
            //获取字段上的注解
            Column annotation = field.getAnnotation(Column.class);
            // 如果注解为空,
            // 则直接跳过...
            if (null==annotation)continue;
            //获取列名
            String name = annotation.name();
            //从数据库中获取值
            Object object = rs.getObject(name);
            // 如果列值为空,
            // 则直接跳过...
            if (null==object)continue;
            //设置字段值
            field.set(userEntity, object);
        }
        return userEntity;
    }
}
