package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "TwoServlet")
public class TwoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.通过请求对象获得【请求头】中【所有请求参数】
        Enumeration paramNames = request.getParameterNames();//将所有请求参数名称保存到一个枚举对象进行返回
        while (paramNames.hasMoreElements()){
            String paramName = (String) paramNames.nextElement();
            System.out.println("请求参数名称 ： "+paramName);
            /*
            请求参数名称 ： username
            请求参数名称 ： password
             */
            //2.读取参数的值
            String value=request.getParameter(paramName);
            System.out.println("请求参数值 ： "+value);
            /*
            请求参数名称 ： username
            请求参数值 ： mike
            请求参数名称 ： password
            请求参数值 ： 123
             */
        }
    }
}
