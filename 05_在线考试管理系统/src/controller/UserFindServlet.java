package controller;

import dao.UserDao;
import entity.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class UserFindServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao dao=new UserDao();
        PrintWriter out=null;
        //1.通过【dao】将查询命令推送到sql服务器，得到所有用户信息【List】
        List<Users> userList=dao.finaAll();

        //2.调用【响应对象】将用户信息结合<table>标签命令以二进制的形式写到响应体中
        int count=0;
        response.setContentType("text/html;charset=utf-8");
        out=response.getWriter();
        out.print("<table border='2' align='center'>");
        out.print("<tr>");
        out.print("<td>用户编号</td>");
        out.print("<td>用户姓名</td>");
        out.print("<td>用户性别</td>");
        out.print("<td>用户邮箱</td>");
        out.print("<td>操作</td>");
        out.print("</tr>");
        for(Users user:userList){
            out.print("<tr>");
            out.print("<td>"+user.getUserId()+"</td>");
            out.print("<td>"+user.getUserName()+"</td>");
            out.print("<td>"+user.getSex()+"</td>");
            out.print("<td>"+user.getEmail()+"</td>");
            out.print("<td><a href='delete?userid="+user.getUserId()+"'>删除用户</a></td>");//在一个包下写web/user/delete反而会找不到
            out.print("</tr>");
            count++;
        }
        out.print("</table>");
        out.print("<font style='color:red;font-size:18'>共计"+count+"条数据</font>");
    }
}
