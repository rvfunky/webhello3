package com.raghu;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import com.modullib.exposed.*;
import com.modullib.hidden.*;

import java.io.*;

public class HelloWorldServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        ExposedCls exposedCls = new ExposedCls();
        Hiddencls hiddencls = new Hiddencls();
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Hello, World from module!" + exposedCls.getData() + ":" + hiddencls.getData() + "</h1>");
        out.println("</body></html>");
    }
}
