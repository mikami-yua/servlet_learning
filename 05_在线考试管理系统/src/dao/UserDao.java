package dao;

import entity.Users;
import util.JdbcUtil;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserDao {

    //private JdbcUtil util=new JdbcUtil();

    //jdbc规范中connection的创建与销毁最耗费时间，connection本质是个IO流接口。
    //解决办法是，在创建用户时不创建和销毁连接-----没有connection就不能创建ps
    //预先创建一些连接，在tomcat启动时创建连接：ServletContextListener接口，通过这个接口合法检测全局作用域对象被初始化时刻，以及被销毁时刻
    //这个接口中的方法：Public void contextInitLized():在全局作用域回想被http服务器初始化时调用。得知服务器什么时候创建，并创建一些连接
    //Public void contextDestory():在全局作用域对象被HTTP服务器销毁时触发调用。这时销毁这些连接

    /**
     * 用户注册
     * @param user 用户信息类，存放用户信息
     * @return 返回1说明增加成功
     */
    public int add(Users user){
        String sql ="insert into users(userName,password,sex,email)"+"values(?,?,?,?)";
        Connection conn=null;
        PreparedStatement ps=null;
        int result=0;
        try {
            conn=JdbcUtil.getConnection();
            ps=conn.prepareStatement(sql);
            ps.setString(1,user.getUserName());
            ps.setString(2,user.getPassWord());
            ps.setString(3,user.getSex());
            ps.setString(4,user.getEmail());
            result=ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn,ps,null);
        }
        return result;
    }

    /**
     * add方法重载以适应连接池
     * @param user
     * @return
     */
    public int add(Users user, HttpServletRequest request){
        String sql ="insert into users(userName,password,sex,email)"+"values(?,?,?,?)";
        JdbcUtil util=new JdbcUtil();
        Connection conn=null;
        conn=JdbcUtil.getConnection(request);
        PreparedStatement ps=util.createStatement(sql,conn);
        int result=0;
        try {
            ps.setString(1,user.getUserName());
            ps.setString(2,user.getPassWord());
            ps.setString(3,user.getSex());
            ps.setString(4,user.getEmail());
            result=ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(conn,ps,null,request);
        }
        return result;
    }

    /**
     * 查询所有用户信息
     * @return 一个list作为结果的集合，list中的元素是User对象
     */
    public List finaAll(){
        String sql="select userid,userName,sex,email from users";
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<Users> usersList=new ArrayList<>();

        try {
            conn=JdbcUtil.getConnection();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()){
                Integer userId=rs.getInt("userId");
                String userName=rs.getString("userName");
                String sex=rs.getString("sex");
                String email=rs.getString("email");
                Users user=new Users(userId,userName,sex,email);
                usersList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn,ps,rs);
        }
        return usersList;
    }

    /**
     * 根据用户编号删除用户信息
     * @param userid 需要删除的用户id
     * @return 返回1说明删除成功
     */
    public int delete(int userid){
        String sql="delete from users where userid=?";
        Connection conn=null;
        PreparedStatement ps=null;
        int result=0;

        try {
            conn=JdbcUtil.getConnection();
            ps=conn.prepareStatement(sql);
            ps.setInt(1,userid);
            result=ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn,ps,null);
        }
        return result;
    }

    /**
     * 登录验证
     * @param username
     * @param password
     * @return
     */
    public int login(String username,String password){
        String sql="select count(*) from users where username=? and password=?";
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        int result=0;

        try {
            conn=JdbcUtil.getConnection();
            ps=conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            rs=ps.executeQuery();
            while (rs.next()){
                result=rs.getInt("count(*)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn,ps,rs);
        }
        return result;
    }
}
