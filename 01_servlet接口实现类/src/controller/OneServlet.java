package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 子类继承父类，父类实现A接口
 * 子类也是A接口的实现类
 *
 * 抽象类的作用：降低接口实现类实现过程的难度
 *              将接口中不需要使用的抽象方法交给抽象类完成
 *              实现类只需要对需要的方法进行重写
 *
 * servlet接口：
 *      init
 *      getServletConfig
 *      service-------------------仅用得上这个
 *      getServletInfo
 *      destroy
 *
 * tomcat根据servlet规范调用servlet接口实现类规则：
 *      1.tomcat有权创建servlet接口实现类实例对象
 *          Servlet oneServlet=new OneServlet();
 *      2.tomcat根据实例对象调用service方法处理当前请求
 *          oneServlet.service();
 *
 *              extends            extends                                implements
 * oneServlet--------->HttpServlet---------->GenericServlet（实现4个方法）------------->Servlet接口
 *
 * 通过父类决定在何种情况下调用子类的方法---------【设计模式】模板设计模式
 *      父类的service决定何时调用doGet和doPost
 */
public class OneServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("oneServlet类针对浏览器发送的get请求进行处理");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("oneServlet类针对浏览器发送的post请求进行处理");
    }

}
