package org.ormtest.step030;

import org.ormtest.step030.entity.UserEntity;
import org.ormtest.step030.entity.XxxEntity_Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 主应用程序
 */
public class App030 {
    /**
     * 应用程序主函数
     *
     * @param argvArray 参数数组
     * @throws Exception
     */
    public static void main(String[] argvArray) throws Exception {
        (new App030()).start();
    }
    /**
     * 测试开始
     */
    private static void start() throws Exception {
        // 加载 Mysql 驱动
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        // 数据库连接地址
        String dbConnStr = "jdbc:mysql://localhost:3306/ormtest?serverTimezone=UTC&user=root&password=123456";
        // 创建数据库连接
        Connection conn = DriverManager.getConnection(dbConnStr);
        // 简历陈述对象
        Statement stmt = conn.createStatement();
        // 创建 SQL 查询
        // ormtest 数据库中有个 t_user 数据表,
        // t_user 数据表包括三个字段: user_id、user_name、password,
        // t_user 数据表有 20 万条数据
        String sql = "select * from t_user limit 200000";

        // 执行查询
        ResultSet rs = stmt.executeQuery(sql);

        //创建助手类
        XxxEntity_Helper userEntity_helper=new XxxEntity_Helper();
        // 获取开始时间
        long t0 = System.currentTimeMillis();
        while (rs.next()) {
            // 创建新的实体对象,
            UserEntity ue = userEntity_helper.create(UserEntity.class,rs);


        }
        // 获取结束时间
        long t1 = System.currentTimeMillis();

        // 关闭数据库连接
        stmt.close();
        conn.close();

        // 打印实例化花费时间
        System.out.println("实例化花费时间 = " + (t1 - t0) + "ms");
    }
}
