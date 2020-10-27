package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 以get方式发送中文内容没问题
 * 以post方式发送中文得到乱码
 *
 * 原因：
 *      以get方式发送请求，请求参数保存在请求头中，请求协议包到服务器后第一件事是进行解码
 *          请求头中的二进制内容由tomcat负责解码，tomcat9.0默认使用utf-8
 *
 *      以post方式发送请求，请求参数保存在请求体中，请求协议包到服务器后第一件事是进行解码
 *          请求体中的二进制内容由当前的请求对象requset负责解码，request默认使用一个东欧语系。中文无法解码，得到的就是一组乱码
 *
 * 解决：
 *      post请求方式下，读取请求体内容前，应该先通知请求对象使用utf-8，对请求体内容进行重新解码
 */
public class ThreeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //通知请求对象使用utf-8，对请求体内容进行重新解码
        request.setCharacterEncoding("utf-8");
        //通过请求对象，读取【请求体】参数信息
        String username=request.getParameter("username");
        System.out.println("从请求体中得到的参数值:"+username);//从请求体中得到的参数值:how are you
        //从请求体中得到的参数值:??????é?±????????±
        //从请求体中得到的参数值:喵喵酱很可爱
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //通过请求对象，读取【请求头】参数信息
        String username=request.getParameter("username");
        System.out.println("从请求头中得到的参数值:"+username);//从请求头中得到的参数值:hello world
        //从请求头中得到的参数值:喵喵酱很可爱
    }
}
