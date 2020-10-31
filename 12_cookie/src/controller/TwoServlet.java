package controller;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
/*
cookie的key值相同时会覆盖原有的cookie。达到更新余额的效果
*/
public class TwoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.读取请求头中的参数信息，得到食物类型
        int jiaozi_money=30;
        int gaifan_money=20;
        int noodle_money=15;
        String food=null;
        String username=null;
        int curMoney,cost=0,balance=0;
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        Cookie newCard=null;
        food=request.getParameter("food");

        //2.读取请求头中的cookie
        Cookie[] cookieArray=request.getCookies();
        //3.循环遍历数组得到每个cookie的key和value
        for (Cookie card:cookieArray){
            String key=card.getName();
            String value=card.getValue();
            if("username".equals(key)){
                username=value;
            }else if("money".equals(key)){
                curMoney=Integer.valueOf(value);
                if("饺子".equals(food)){
                    if(jiaozi_money>curMoney){
                        out.print("用户"+username+" 余额不足！");
                    }else {
                        newCard=new Cookie("money",(curMoney-jiaozi_money)+"");
                        cost=jiaozi_money;
                    }
                }else if("面条".equals(food)){
                    if (noodle_money>curMoney){
                        out.print("用户"+username+" 余额不足！");
                    }else {
                        newCard=new Cookie("money",(curMoney-noodle_money)+"");
                        cost=noodle_money;
                    }
                }else if ("盖饭".equals(food)){
                    if (gaifan_money>curMoney){
                        out.print("用户"+username+" 余额不足！");
                    }else {
                        newCard=new Cookie("money",(curMoney-gaifan_money)+"");
                        cost=gaifan_money;
                    }
                }
                balance=curMoney-cost;
            }
        }

        //4.将用户的卡返回给用户
        response.addCookie(newCard);
        //5.将消费记录写到响应体中
        out.print("用户"+username+"本次消费"+cost+"元  余额"+balance);
        /*
        cookie的key值相同时会覆盖原有的cookie。达到更新余额的效果
         */
    }
}
