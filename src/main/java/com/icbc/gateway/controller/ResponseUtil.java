package com.icbc.gateway.controller;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class ResponseUtil {
    public static void write(HttpServletResponse response, Object o)throws Exception{
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        out.println(o.toString());
        out.flush();
        out.close();
    }

//---------------------
//    作者：debug-steadyjack
//    来源：CSDN
//    原文：https://blog.csdn.net/u013871100/article/details/52039089
//    版权声明：本文为博主原创文章，转载请附上博文链接！
}
