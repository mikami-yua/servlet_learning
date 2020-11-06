package filter;

import javax.servlet.*;
import java.io.IOException;

public class Onefilter implements Filter {

    /**
     * 通知拦截请求对象，使用utf-8对当前请求体信息进行重新编辑
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //增强功能
        servletRequest.setCharacterEncoding("utf-8");
        //放行
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
