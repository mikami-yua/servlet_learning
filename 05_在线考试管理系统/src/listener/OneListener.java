package listener;

import util.JdbcUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *监听服务器的生死时刻
 */
public class OneListener implements ServletContextListener {

    /**
     * 在tomcat启动时预先创建20个connection，在UserDao.add方法指向时将创建好的connection交给add方法
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Map map=new HashMap();
        for (int i=0;i<20;i++){
            try {
                Connection con=JdbcUtil.getConnection();
                System.out.println("在http服务器启动时创建connection"+con);
                map.put(con,true);//true表示这个通道处于空闲状态（key设置为con的内存地址）
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        ServletContext application=sce.getServletContext();
        application.setAttribute("key1",map);
    }//map被销毁了，全局作用域对象的声明周期最长，为了一直能使用这些connection，需要保存到全局作用域对象

    /**
     * http服务器关闭的时候，将全局作用域对象的20个connection销毁
     * @param sce
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext application=sce.getServletContext();
        Map map=(Map)application.getAttribute("key1");
        Iterator it=map.keySet().iterator();
        while (it.hasNext()){
            Connection con=(Connection) it.next();
            if(con!=null){
                System.out.println("连接"+con+"已断开");

            }
        }
    }
}
