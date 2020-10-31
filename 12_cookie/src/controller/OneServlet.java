package controller;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OneServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username,money;
        //1.调用请求对象。读取请求头中的参数信息
        username=request.getParameter("username");
        money=request.getParameter("money");
        //2.将cookie写到响应头，交给浏览器
        Cookie card1=new Cookie("username",username);
        Cookie card2=new Cookie("money",money);
        /*
        追加card2在硬盘上存活1min
         */
        card2.setMaxAge(60);//此时card1在浏览器的内存中，card2在硬盘中

        //3.将cookie写到响应头中交给浏览器
        response.addCookie(card1);
        response.addCookie(card2);
        //4.通知浏览器，将点餐页面写到响应体中交给浏览器（请求转发）
         request.getRequestDispatcher("order.html").forward(request,response);

    }
}
