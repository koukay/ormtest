package org.ormtest.step040.entity;
/**
 * 实体助手工厂
 */
public class EntityHelperFactory {
    /**
     * 私有化类默认构造器
     */
    private EntityHelperFactory(){

    }

    /**
     *获取帮助
     * @param entityClass 实体类
     * @return
     */
    public static AbstractEntityHelper getEntityHelper(Class<?> entityClass){
        System.out.println("调用助手类了");
        // 这里需要全新设计,
        // 接下来就该请出 javassist 了!
        return null;
    }
}
