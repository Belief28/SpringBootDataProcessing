package com.dataprocess.demo.controller;


import com.dataprocess.demo.domain.vggvia.VggviaData;
import com.dataprocess.demo.utils.MultipartFileUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.*;

@RestController
public class ToCocoController {
    @PostMapping
    public String getCocoData() {
        return "ok";
    }

    @PostMapping("/fileUpload")
    //@ResponseBody
    public Collection<VggviaData> fileUpload(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        if (ObjectUtils.isEmpty(multipartFile)) {
            return null;
        }
        //String fileName = multipartFile.getOriginalFilename();
        File file = MultipartFileUtil.multipartFileToFile(multipartFile);
        FileInputStream inputStream =null;
        inputStream = new FileInputStream(file);
        ByteBuffer buffer = ByteBuffer.allocate((int) file.length());
        inputStream.getChannel().read(buffer);
        String strPnr = new String(buffer.array());
        Map<String,Map<String,Object>> map = new HashMap<>();
        System.out.println(strPnr);
        ObjectMapper mapper = new ObjectMapper();
        //List<VggviaData> demoList = mapper.readValue(strPnr,new TypeReference<List<VggviaData>>(){});
        map = mapper.readValue(strPnr, HashMap.class);
        System.out.println(map);
        //demoList.stream().forEach(a-> System.out.println(a));
        Collection<Map<String, Object>> values = map.values();
        values.forEach(value ->{
            value.keySet().removeIf(key->key.equals("file_attributes"));
        });
        String str = mapper.writeValueAsString(values);
//        System.out.println(s);
        List<VggviaData> vggviaDataList = mapper.readValue(str,new TypeReference<List<VggviaData>>(){});
        return vggviaDataList;
    }
    @PostMapping("/filesUpload")
    public List<VggviaData> filesUpload (@RequestParam("file") List<MultipartFile> multipartFiles){
        if (ObjectUtils.isEmpty(multipartFiles)) {
            return null;
        }
        List<VggviaData> vggviaDataList = new ArrayList();
        multipartFiles.forEach(multipartFile -> {
            try {
                File file = MultipartFileUtil.multipartFileToFile(multipartFile);
                FileInputStream inputStream =null;
                inputStream = new FileInputStream(file);
                ByteBuffer buffer = ByteBuffer.allocate((int) file.length());
                inputStream.getChannel().read(buffer);
                String strPnr = new String(buffer.array());
                Map<String,Map<String,Object>> map = new HashMap<>();
                System.out.println(strPnr);
                ObjectMapper mapper = new ObjectMapper();
                map = mapper.readValue(strPnr, HashMap.class);
                System.out.println(map);
                //demoList.stream().forEach(a-> System.out.println(a));
                Collection<Map<String, Object>> values = map.values();
                values.forEach(value ->{
                    value.keySet().removeIf(key->key.equals("file_attributes"));
                });
                String str = mapper.writeValueAsString(values);
                //        System.out.println(s);
                List<VggviaData> vggviaDatas = mapper.readValue(str,new TypeReference<List<VggviaData>>(){});
                vggviaDataList.addAll(vggviaDatas);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return vggviaDataList;
    }
}
