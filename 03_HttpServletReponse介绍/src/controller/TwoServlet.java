package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class TwoServlet extends HttpServlet {
    /*
    问题：浏览器接受到2，不是50
    原因：
        out.write();【可以写入字符，字符串，ASCII码】写到响应体中

        2对应的ASCII码就是50
        write方法认为放入的不是int而是ASCII码50
    解决:实际开发中都是用print方法
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int money=50;//执行结果

        PrintWriter out=response.getWriter();
        //out.write(money);
        out.print(money);
    }
}
