package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OneServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address="http://www.baidu.com";
        response.sendRedirect(address);//写入响应头的location中
        /*
        tomcat在推送响应包之前，看到响应体中是空的，响应头的location中有个地址
        tomcat会将302的状态码写到状态行中
        浏览器接受到响应包后，因为302状态码，浏览器不会读取响应体的内容，自动根据响应头中location的地址，发起第二次的请求
         */
    }
}
