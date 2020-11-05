package util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.Iterator;
import java.util.Map;

/**
 * JDBC工具类简化JDBC编程
 */
public class JdbcUtil {

    /**
     * 工具类的构造方法一般都是私有的
     * 因为工具类中的方法都是静态的，不需要new对象
     */
    public JdbcUtil(){}

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

    //-------------------------通过全局作用域对象得到connect---------------------------------------------start
    public static Connection getConnection(HttpServletRequest request){
        Connection conn=null;

        //1.通过请求对象，得到全局作用域对象
        ServletContext application=request.getServletContext();

        //2.从全局作用域对象得到map
        Map map=(Map) application.getAttribute("key1");

        //3.从map中得到处于空闲状态的connection
        Iterator it=map.keySet().iterator();
        while (it.hasNext()){
            conn=(Connection) it.next();
            boolean flag=(boolean)map.get(conn);
            if(flag==true){
                map.put(conn,false);
                break;
            }
        }
        return conn;
    }

    public  PreparedStatement createStatement(String sql,Connection conn){
        PreparedStatement ps=null;
        try {
            ps=conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }

    public void close(Connection conn, Statement ps, ResultSet rs,HttpServletRequest request){
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
        ServletContext application=request.getServletContext();
        Map map=(Map)application.getAttribute("key1");
        map.put(conn,true);
    }

    //--------------------------------------------------------------------------------------------------end

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
