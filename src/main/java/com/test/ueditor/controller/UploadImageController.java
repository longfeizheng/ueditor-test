package com.test.ueditor.controller;

import com.baidu.ueditor.ActionEnter;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.State;
import com.test.ueditor.util.LoadPropertiesDataUtils;
import com.test.ueditor.util.RandomUtils;
import com.test.ueditor.util.StringUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created on 2017/3/16 0016.
 *
 * @author zlf
 * @since 1.0
 */
@RestController
public class UploadImageController {
    /**
     * ueditor上传文件
     *
     * @param files   文件列表
     * @param request {@link HttpServletRequest}
     * @return 上传结果
     * @throws IOException 文件上传错误
     */
    @RequestMapping( value = "uploadimage" ,consumes = "multipart/form-data")
    public String upload(@RequestParam(value = "upfile", required = false) MultipartFile[] files,
                         HttpServletRequest request,HttpServletResponse response) throws IOException {

        String basePath = LoadPropertiesDataUtils.getValue("lyz.uploading.url");
        String visitUrl = LoadPropertiesDataUtils.getValue("lyz.visit.url");
        if(basePath == null || "".equals(basePath)){
            basePath = "d:/test";  //与properties文件中lyz.uploading.url相同，未读取到文件数据时为basePath赋默认值
        }
        if(visitUrl == null || "".equals(visitUrl)){
            visitUrl = "/upload/"; //与properties文件中lyz.visit.url相同，未读取到文件数据时为visitUrl赋默认值
        }

        if (files != null && files.length > 0) {
            for (MultipartFile file : files) {

                String ext = StringUtils.getExt(file.getOriginalFilename());
                String fileName = String.valueOf(System.currentTimeMillis()).concat("_").concat(RandomUtils.getRandom(6)).concat(".").concat(ext);
                StringBuilder sb = new StringBuilder();
                //拼接保存路径
                sb.append(basePath).append("/").append(fileName);
                visitUrl = visitUrl.concat(fileName);
                File f = new File(sb.toString());
                if(!f.exists()){
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
        return new ActionEnter(request, getDownloadPath(request)).exec();
    }

    public String getDownloadPath(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        return "/" + ("".equals(contextPath) ? "" : contextPath + "/");
    }
}
