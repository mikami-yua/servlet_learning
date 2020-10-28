package dao;

import entity.Users;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserDao {

    //private JdbcUtil util=new JdbcUtil();

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
}
