package com.ztx.math.controller;

import com.ztx.math.util.Constant;
import com.ztx.math.util.DateAndTimeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Iterator;
import java.util.UUID;

@Controller
public class FileController {

    /**
     * 文件上传，以今日日期YYYYMMDD为父文件夹
     * tomcat配置虚拟路径访问
     */
    @RequestMapping("/uploadfile_v1")
    @ResponseBody
    public String uploadfile_v1(HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        CommonsMultipartResolver cmr = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        String path = "";
        String fileName = "";
        String savepath = DateAndTimeUtil.getCurrentDate(DateAndTimeUtil.DATEFORM_1);
        if (cmr.isMultipart(request)) {
            MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) (request);
            Iterator<String> files = mRequest.getFileNames();
            while (files.hasNext()) {
                MultipartFile mFile = mRequest.getFile(files.next());
                if (mFile != null) {
                    fileName = UUID.randomUUID()
                            + mFile.getOriginalFilename();
                    String directoryPath = Constant.UPLOAD_PATH + savepath;
                    File directory = new File(directoryPath);
                    if (!directory.exists()) {
                        directory.mkdirs();
                    }
                    path = directoryPath + "/" + fileName;
                    File localFile = new File(path);
                    localFile.createNewFile();
                    mFile.transferTo(localFile);
                }
            }
        }
        return Constant.VIRTUAL_UPLOAD_PATH + savepath + "/" + fileName;
    }

}
