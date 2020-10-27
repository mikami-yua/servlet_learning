package util;

import java.sql.*;

/**
 * JDBC工具类简化JDBC编程
 */
public class JdbcUtil {

    /**
     * 工具类的构造方法一般都是私有的
     * 因为工具类中的方法都是静态的，不需要new对象
     */
    private JdbcUtil(){}

    //静态代码块在类加载时执行，只执行一次
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");//注册驱动
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接对象
     * @return 连接对象
     * @throws SQLException 调用函数中有try catch
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/learningtest","root","333");
    }

    /**
     * 关闭资源
     * @param conn 连接对象
     * @param ps    数据库操作对象
     * @param rs    结果集
     */
    public static void close(Connection conn, Statement ps, ResultSet rs){//Statement面向抽象编程
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
