package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class OneServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result="Hello World";//执行结果

        //通过响应对象将结果写到响应体中---------start

        //1.通过响应对象向tomcat索要输出流
        PrintWriter out=response.getWriter();
        //2.通过输出流，将结果以二进制形式写到响应体中
        out.write(result);//不需要关闭，是借的输出流

        //通过响应对象将结果写到响应体中---------end
    }
}
