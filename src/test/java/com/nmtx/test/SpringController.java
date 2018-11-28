package com.nmtx.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring接口
 * @author lianghao
 *
 * 2017年3月24日
 */

@Controller
@RestController
@RequestMapping("/spring")
public class SpringController {

    /**
     * 我是测试接口1
     * 
     * @param username|用户名|Integer|必填
     * @title 测试接口1
     * @respParam username|用户名|Integer|必填
     * @respBody {username:23234}
     */
    @RequestMapping("/test1")
    public void test1() {

    }

    /**
     * 我是测试接2
     * 
     * @param username|用户名|Integer|必填
     * @title 测试接口2
     * @respParam username|用户名|Integer|必填
     * @respBody {username:23234}
     */
    @RequestMapping("/test2")
    public void test2() {

    }
}
