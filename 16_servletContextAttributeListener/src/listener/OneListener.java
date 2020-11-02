package listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

public class OneListener implements ServletContextAttributeListener {
    /**
     * 全局作用域对象添加数据时触发
     * @param scae
     */
    @Override
    public void attributeAdded(ServletContextAttributeEvent scae) {
        System.out.println("新增共享数据");
    }

    /**
     * 全局作用域对象删除数据时触发
     * @param scae
     */
    @Override
    public void attributeRemoved(ServletContextAttributeEvent scae) {
        System.out.println("删除共享数据");
    }

    /**
     * 全局作用域对象修改数据时触发
     * @param scae
     */
    @Override
    public void attributeReplaced(ServletContextAttributeEvent scae) {
        System.out.println("修改共享数据");
    }
}
