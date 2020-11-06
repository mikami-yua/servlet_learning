package filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class OneFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request=(HttpServletRequest) servletRequest;// ServletRequest没有getSession方法

        /*
        //HttpServletResponse response=(HttpServletResponse) servletResponse;//HttpServletResponse没有sendRedirect方法
        //拦截之后，通过请求对象向tomcat索要当前用户的httpsession
        HttpSession session=request.getSession(false);
        //判断来访用户身份的合法性
        if (session == null) {
            //response.sendRedirect("/myweb/login_error.html");
            request.getRequestDispatcher("/login_error.html").forward(servletRequest,servletResponse);
            return;
        }
        //放行
        filterChain.doFilter(servletRequest,servletResponse);

         */
        //过滤器方式
        HttpSession session=null;
        //1.调用当前请求对象读取请求包中请求行中发URI，了解用户希望访问的资源文件是谁
        String uri=request.getRequestURI();//[/网站名/资源文件名   /myweb/login or /myweb/login.html]
        //2.如果本次请求的资源文件与登录相关此时无条件放行
        if(uri.indexOf("login")!=-1){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        //3.本次请求访问其他资源文件，需要得到用户在服务器端httpSession
        session=request.getSession(false);
        if (session != null) {
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        //4.拒绝请求
        request.getRequestDispatcher("/login_error.html").forward(servletRequest,servletResponse);

    }
}
