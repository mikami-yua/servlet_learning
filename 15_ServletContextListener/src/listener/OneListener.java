package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class OneListener implements ServletContextListener {
    /**
     * 全局作用域对象被初始化的时候调用
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("全局作用域对象初始化完成...");
    }

    /**
     *全局作用域对象被销毁时调用
     * @param sce
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("全局作用域对象即将被销毁...");
    }
}
