package com.test.ueditor.controller;

import com.baidu.ueditor.ActionEnter;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.State;
import com.test.ueditor.util.RandomUtils;
import com.test.ueditor.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

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
    @ResponseBody
    public String config(@RequestParam(value = "upfile", required = false) MultipartFile[] files, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        response.setHeader("Content-Type", "text/html");
        response.setContentType("application/json");
        String basePath = "./target/classes/static/upload/";
        String visitUrl = "/upload/";
        if (files != null && files.length > 0) {
            for (MultipartFile file : files) {

                String ext = StringUtils.getExt(file.getOriginalFilename());
                String fileName = String.valueOf(System.currentTimeMillis()).concat("_").concat(RandomUtils.getRandom(6)).concat(".").concat(ext);
                StringBuilder sb = new StringBuilder();
                //拼接保存路径
                sb.append(basePath).append("/").append(fileName);
                visitUrl = visitUrl.concat(fileName);
                File f = new File(sb.toString());
                if (!f.exists()) {
                    f.getParentFile().mkdirs();
                }
                OutputStream out = new FileOutputStream(f);
                FileCopyUtils.copy(file.getInputStream(), out);

                State state = new BaseState(true);
                state.putInfo("size", 0);
                state.putInfo("title", file.getSize());
                state.putInfo("url", visitUrl);
                state.putInfo("type", file.getContentType());
                state.putInfo("original", fileName);
                return state.toJSONString();
            }
        }

        String rootPath = request.getServletContext().getRealPath("/");
        String exec = new ActionEnter(request, rootPath).exec();
        return exec;
    }
}
