package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class OneServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String item;
        //1.调用请求对象，取得请求头参数，得到用户选择商品名
        item=request.getParameter("itemname");
        //2.得到柜子。调用请求对象，索要当前用户在服务器端的私人保险柜
        HttpSession session=request.getSession();
        //3.将数据添加到用户的私人储物柜中
        Integer num=(Integer) session.getAttribute(item);
        if(num==null){
            session.setAttribute(item,1);
        }else {
            session.setAttribute(item,num+1);
        }

    }
}
