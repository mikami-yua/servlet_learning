package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 浏览器在接收到响应包之后，如果发现响应头中存在location属性。将自动通过地址栏，向localtion指向的网站发送请求
 */
public class FourServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result="https://www.baidu.com";

        //通过响应对象，将地址赋值给响应头中的location属性
        response.sendRedirect(result);
    }
}
