package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OneServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.通过请求对象，读取请求行中发url信息
        String url= request.getRequestURL().toString();
        //2.通过请求对象，读取请求行中【method】信息
        String method= request.getMethod();
        //读取
        System.out.println("URL:"+url+" method:"+method);//URL:http://localhost:8080/myweb4/one method:GET

        //3.读取请求行中的uri信息
        /*
        URI:资源文件精准定位地址
            请求行中没有URI这个属性
            实际上是从URL中截取的一个字符串
            格式：“/网站名/资源文件名”
            用于http服务器对被访问的资源文件进行定位
         */
        String uri=request.getRequestURI();
        System.out.println("uri:"+uri);//uri:/myweb4/one
    }
}
