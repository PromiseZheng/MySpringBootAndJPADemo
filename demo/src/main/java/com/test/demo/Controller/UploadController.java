package com.test.demo.Controller;

import com.test.demo.Services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UploadController {
    @Autowired
    ImageService imageService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> post(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String prefix = "";
        String dateStr = "";
        //保存上传
        OutputStream out = null;
        InputStream fileInput = null;
        try {
            if (file != null) {
                String originalName = file.getOriginalFilename();
                prefix = originalName.substring(originalName.lastIndexOf(".") + 1);
                String filepath = request.getServletContext().getRealPath("/static")  + "." + prefix;
                filepath = filepath.replace("\\", "/");
                dateStr=new Date().toString();
                File files = new File(filepath);
                //打印查看上传路径
                System.out.println(filepath);
                if (!files.getParentFile().exists()) {
                    files.getParentFile().mkdirs();
                }
                file.transferTo(files);
            }
        } catch (Exception e) {
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (fileInput != null) {
                    fileInput.close();
                }
            } catch (IOException e) {
            }
        }
        Map<String, Object> map2 = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("data", map2);
        map2.put("src", "../../../static" + dateStr + "." + prefix);
        return map;
    }
}
