package com.dataprocess.demo.controller;


import com.dataprocess.demo.utils.MultipartFileUtil;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.List;

@RestController
public class ToCocoController {
    @PostMapping
    public String getCocoData() {
        return "ok";
    }

    @PostMapping("/fileUpload")
    //@ResponseBody
    public String fileUpload(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        if (ObjectUtils.isEmpty(multipartFile)) {
            return "false";
        }
        String fileName = multipartFile.getOriginalFilename();
        File file = MultipartFileUtil.multipartFileToFile(multipartFile);
        FileInputStream inputStream =null;
        inputStream = new FileInputStream(file);
        ByteBuffer buffer = ByteBuffer.allocate((int) file.length());
        inputStream.getChannel().read(buffer);
        String strPnr = new String(buffer.array());
        JSONArray objar = new JSONArray(strPnr);
        return strPnr;
    }

}
