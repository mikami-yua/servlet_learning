package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TwoServlet extends HttpServlet {
    public TwoServlet() {
        System.out.println("TwoServlet类被创建实例对象");
        /*
        Connected to server
        [2020-10-26 03:25:33,703] Artifact 02_servlet生命周期:war exploded: Artifact is being deployed, please wait...
        TwoServlet类被创建实例对象
        [2020-10-26 03:25:34,949] Artifact 02_servlet生命周期:war exploded: Artifact is deployed successfully
        [2020-10-26 03:25:34,949] Artifact 02_servlet生命周期:war exploded: Deploy took 1,246 milliseconds
         */
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("two diGet() function is running");
    }
}
