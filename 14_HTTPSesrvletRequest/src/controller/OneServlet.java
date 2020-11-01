package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OneServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.将数据添加到【请求作用域对象】的attribute属性
        request.setAttribute("key1","helloworld");
        //2.代替浏览器向tomcat索要twoServlet，完成剩余认为
        request.getRequestDispatcher("/two").forward(request,response);

    }
}
