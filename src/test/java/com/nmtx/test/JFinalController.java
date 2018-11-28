package com.nmtx.test;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.POST;

/**
 * JFinal
 * 
 * @author lianghao
 *
 *         2017年3月23日
 */
public class JFinalController extends Controller {

    /**
     * 我是测试接口1
     * 
     * @param username|用户名|Integer|必填
     * @title 测试接口1
     * @respParam username|用户名|Integer|必填
     * @respBody {username:23234}
     */
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
    @Before(POST.class)
    public void test2() {

    }
}
