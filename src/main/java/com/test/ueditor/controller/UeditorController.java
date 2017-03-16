package com.test.ueditor.controller;

import com.baidu.ueditor.ActionEnter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created on 2017/3/16 0016.
 *
 * @author zlf
 * @since 1.0
 */
@RequestMapping("ueditor")
@Controller
public class UeditorController {
    @RequestMapping("config")
    public void config(HttpServletRequest request, HttpServletResponse response){
        try{
            request.setCharacterEncoding( "utf-8" );
            response.setHeader("Content-Type" , "text/html");
            response.setContentType("application/json");

            String rootPath = request.getServletContext().getRealPath( "/" );
            String exec = new ActionEnter(request, rootPath).exec();
            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();

        }catch (Exception e){

        }
    }
}
