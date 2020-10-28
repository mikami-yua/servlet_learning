package controller;

import dao.UserDao;
import entity.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserAddServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName,password,sex,email;
        UserDao dao=new UserDao();
        Users user=null;
        int result=0;
        PrintWriter out=null;
        //1.【调用请求对象】读取【请求头】参数信息，得到用户信息
        ///myweb/user/add?userName=mike&password=123&sex=%E7%94%B7&email=mike%40123.com
        userName=request.getParameter("userName");
        password=request.getParameter("password");
        sex=request.getParameter("sex");
        email=request.getParameter("email");
        //所有的参数拿到了

        //2。调用【UserDao】将用户信息填充到insert命令并借助JDBC规范发送到数据库服务器
        user=new Users(null,userName,password,sex,email);
        result=dao.add(user);

        //3.【调用响应对象】将处理结果以二进制形式写到响应体中
        response.setContentType("text/html;charset=utf-8");
        out=response.getWriter();
        if(result==1){
            out.print("<font style='color:red;font-size:40'>用户信息注册成功</font>");
        }else {
            out.print("<font style='color:red;font-size:40'>用户信息注册失败</font>");
        }
    }
    //doGet执行之后，tomact负责销魂响应对象和请求对象
    //Tomcat 负责将http响应协议包推送到发起请求的浏览器上
    //浏览器根据响应头content-type指定编译器对响应体二进制内容编辑
    //浏览器将编辑后结果在窗口展示给用户
}
