package test.controller;




import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import test.service.picservice;


/**
 * @ClassName:testController
 * @Description:
 * @Author li
 * @Date 2020/9/14
 * @Version 1.0
 */
@RestController
public class testController {
    @Autowired
    picservice p;
    @GetMapping(value = "/hi")
    public String getHi(){
        return "hi----"+"devlop+88888888";
    }

    @ApiOperation(value = "图片上传")
    @PostMapping(value = "/updateImage")
    public String updateImage(
       @ApiParam(value = "上传图片文件",required = true) MultipartFile pic){
        return p.updateImage(pic);
    }
    @ApiOperation(value = "获取图片")
    @GetMapping(value = "/getImage")
    public String getImage(){
        return null;
    }
}
