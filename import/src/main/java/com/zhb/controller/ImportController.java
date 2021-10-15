package com.zhb.controller;

import com.zhb.service.GlobalApi;
import com.zhb.service.ImportService;
import com.zhb.service.LocalApi;
import com.zhb.service.PsmsApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

/**
 * @author: curry
 * @Date: 2018/8/16
 */
@Controller
public class ImportController {

    @Autowired
    private ImportService importService;
    @Resource
    GlobalApi globalApi;
    @Resource
    LocalApi localApi;
    @Resource
    PsmsApi psmsApi;


    @PostMapping(value = "/upload")
    @ResponseBody
    public String uploadExcel(HttpServletRequest request) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        MultipartFile file = multipartRequest.getFile("filename");
        if (file.isEmpty()) {
            return "文件不能为空";
        }
        InputStream inputStream = file.getInputStream();
        List<List<Object>> list = importService.getBankListByExcel(inputStream, file.getOriginalFilename());
        inputStream.close();
        HashMap keys = new HashMap();
        for (int i = 0; i < list.size(); i++) {
            List<Object> lo = list.get(i);
            //TODO 随意发挥
         //   System.out.println(lo.size()+"每行大小");
            for (int j=0; j<lo.size();j++)
            {String k;
if(lo.get(j)==null) {
    k = "cc";
}
else {
    k = lo.get(j).toString();
}
                System.out.println(i+"c"+j+","+k);
                keys.put(i+"c"+j,k);


            }

            //   System.out.println(lo);
         //String cc =  demoApi.getDate(lo.toString());
         //   System.out.println(cc+i);
        }
        if(keys.get("2c16").equals("Global Incident Number")){   // 三种不同模板三种不同处理方式
            System.out.println("这是Global");

            return  globalApi.getsDate(keys)+"\t\t\t这是======"+keys.get("2c8").toString();
        }
        else if(keys.get("2c9").equals("Issue Number"))
        {
            System.out.println("这是Local");

            return localApi.getsDate(keys)+"\t\t\t这是====="+keys.get("2c15").toString();
        }
        else if(keys.get("3c9").equals("Global Incident Number"))
        {
            System.out.println("这是 Psms");

            return  psmsApi.getsDate(keys)+"\t\t\t这是==="+keys.get("2c15").toString();
        }
        else {
            return "模板未识别 请重新上传";
        }
//       String ccy=file.getName();
//        demoApi.getsDate(keys);
        //System.out.println(keys.size()+"大小");

//        return "上传成功";
    }

}
