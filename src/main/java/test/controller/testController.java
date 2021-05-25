package test.controller;




import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import test.service.picservice;

import java.util.logging.Logger;


/**
 * @ClassName:testController
 * @Description:
 * @Author li
 * @Date 2020/9/14
 * @Version 1.0
 */
@RestController
public class testController {
    @Value("${test.canshi}")
    private String test;
    @Autowired
    picservice p;
    @GetMapping(value = "/hi")
    public String getHi(){
        return "hi----"+"devlop+11111111";
    }

    @ApiOperation(value = "图片上传222222")
    @PostMapping(value = "/updateImage")
    public String updateImage(
       @ApiParam(value = "上传图片文件",required = true)@RequestParam() MultipartFile pic){
        String a =  p.updateImage(pic);
        Logger.getLogger("updateImage").info("收到消息" + a);
        return a;
    }
    @Autowired
    private JedisPool jedisPool;

    @ApiOperation(value = "测试redis")
    @GetMapping(value = "/getRedis")
    public String getRedis(){
        Jedis jedis = jedisPool.getResource();
        jedis.setex("name",60,"kkkkk");
        return jedis.get("name");
    }

    @ApiOperation(value = "测试配置文件")
    @GetMapping(value = "/test")
    public String getTest(){
        return test;
    }


}
