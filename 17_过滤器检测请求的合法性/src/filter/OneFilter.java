package filter;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * http://localhost:8080/myweb17/gl.jpg?age=17
 */
public class OneFilter implements Filter {
    /**
     *
     * @param servletRequest 请求对象，HTTP服务器接到浏览器的请求后为当前请求生成一共请求对象和一共响应对象
     * @param servletResponse 当前响应对象
     * @param filterChain 负责在判断当前请求合法时，将请求对象和响应对象还给tomcat（称为放行）
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //1.通过拦截请求对象得到请求包中的参数信息，从而得到来访用户的真实年龄
        String age=servletRequest.getParameter("age");
        //2.根据年龄，帮助http服务器判断请求的合法性
        if(Integer.valueOf(age)>=18){//合法请求
            // 将请求对象和响应对象还给tomcat，由tomcat继续调用资源文件
            filterChain.doFilter(servletRequest,servletResponse);//方行
        }else {//不合法
            //过滤器代替http服务器拒绝本次请求
            servletResponse.setContentType("text/html;charset=utf-8");
            PrintWriter out=servletResponse.getWriter();
            out.print("<center><font style='color:red;font-size:30px'>未成年禁止观看</font></center>");
        }
    }
}
