package controller;

import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username,password;
        UserDao dao=new UserDao();
        int result=0;
        //1.调用请求对象，对请求体中的内容使用utf-8字符集进行重新编写
        request.setCharacterEncoding("utf-8");

        //2.调用请求对象读取请求体中的信息
        username=request.getParameter("username");
        password=request.getParameter("password");
        //System.out.println("11111");
        //3.调用dao，将查询验证信息推送到sql服务器
        result=dao.login(username,password);
        //System.out.println(result);
        //4.调用响应对象，根据验证结果把不同的资源文件地址写到响应头，交给浏览器
        if(result==1){
            response.sendRedirect("index.html");
        }else {
            response.sendRedirect("login_error.html");
        }
    }

}
