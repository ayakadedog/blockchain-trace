package com.project.bookstore.controller;


import com.project.bookstore.pojo.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 图片上传方法
 */

@RestController
@RequestMapping("/api")
public class CommonController {
    @ResponseBody
    @PostMapping("/upload")
    public R up(String nickname, MultipartFile photo, String uploadpath) throws IOException {
        System.out.println(nickname);
        // 获取图片的原始名称
        System.out.println(photo.getOriginalFilename());
        // 获取文件类型
        System.out.println(photo.getContentType());

        // 获取服务器对应的路径，web服务器运行目录（动态变化，部署到其他服务器则路径改变，动态获取）
        //String path=request.getServletContext().getRealPath("/upload/");

        // 服务器存储图片位置
        String path=("C:\\Users\\11540\\Desktop\\毕业设计材料\\源码\\后台\\bookstore\\src\\main\\resources\\static/upload/");
        System.out.println(path);
        saveFile(photo,path);
        //图片在服务器中进行资源映射的地址
        String res="http://localhost:8080/api/upload/"+photo.getOriginalFilename();
        System.out.println(res);

        return R.SUCCESS(res,"成功");
    }

    /**
     * 图片保存方法
     * @param photo
     * @param path
     * @throws IOException
     */
    public void saveFile(MultipartFile photo,String path) throws  IOException{
//        判断存储的目录是否存在，如果不存在则创建
//        创建File对象，将路径传到该对象进行判断
        File dir=new File(path);
        if(!dir.exists()){
//            创建目录
            dir.mkdir();
        }
//        最终存储的文件
        File file = new File(path+photo.getOriginalFilename());
        photo.transferTo(file);

    }
}
