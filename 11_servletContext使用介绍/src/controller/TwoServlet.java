package controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TwoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //先向tomcat索要全局作用域对象
        ServletContext application=request.getServletContext();
        //全局作用域对象得到指定关键词对应的数据
        Integer money=(Integer) application.getAttribute("key1");//防止空指针异常
        System.out.println(money);
    }
}
