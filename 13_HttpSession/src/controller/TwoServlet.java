package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

public class TwoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.得到柜子。调用请求对象，索要当前用户在服务器端的私人保险柜
        HttpSession session=request.getSession();
        //2.将session中所有的key读取出来，存放到一个枚举对象中
        Enumeration itemNames=session.getAttributeNames();
        while (itemNames.hasMoreElements()){
            String itemName=(String) itemNames.nextElement();
            int itemNum=(int)session.getAttribute(itemName);//这里没有空指针异常
            System.out.println("商品名称:"+itemName+"  商品数量:"+itemNum);
        }

    }
}
