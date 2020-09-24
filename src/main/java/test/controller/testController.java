package test.controller;


import com.google.gson.Gson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import test.domain.test;


/**
 * @ClassName:testController
 * @Description:
 * @Author li
 * @Date 2020/9/14
 * @Version 1.0
 */
@RestController
public class testController {
    @GetMapping(value = "/hi")
    public String getHi(){
        return "hi----"+"lililililili";
    }

}
