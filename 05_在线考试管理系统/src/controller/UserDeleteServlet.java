package controller;

import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserDeleteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userid;
        UserDao dao=new UserDao();
        int result=0;
        PrintWriter out=null;
        //1.调用请求对象，读取请求头中的参数（用户编号）
        userid=request.getParameter("userid");
        int id = Integer.parseInt(userid);
        //2.调用dao，将用户编号填充到delete命令并发送到sql服务器
        result=dao.delete(id);
        //3.调用响应对象，将执行结果以二进制写到响应体中，交给浏览器
        response.setContentType("text/html;charset=utf-8");
        out=response.getWriter();
        if(result==1){
            out.print("<font style='color:red;font-size:40'>用户信息删除成功</font>");
        }else {
            out.print("<font style='color:red;font-size:40'>用户信息删除失败</font>");
        }
    }

}
