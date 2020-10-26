package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * out.print(result);//java<br />Mysql<br />Html
 * 没有根据html命令换行
 *
 * 原因：
 *      浏览器接受到响应包，根据响应头【content-type】属性的值，来采用【编辑器】对【响应体中的二进制】内容进行编译
 *
 *      默认情况下【content-type】属性值text  content-type=“text”
 *      浏览器使用文本编译器对响应体二进制进行编译
 *
 * 解决：
 *      一定在【得到输出流之前】，通过响应对象响应头中的content-type属性进行一次重新的赋值。指定正确的编译器
 *
 * 默认字符集：charset=ISO-8859-1
 */
public class ThreeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result="java<br />Mysql<br />Html<br />";//既有文字信息，又有html标签命令
        String result2="张猛<br/>王建<br/>李元元";
        //设置响应头
        response.setContentType("text/html;charset=utf-8");

        PrintWriter out=response.getWriter();
        out.print(result);//java<br />Mysql<br />Html
        out.print(result2);
    }
}
