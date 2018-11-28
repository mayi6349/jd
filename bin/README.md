#JDoc
JAVA 接口文档生成利器 JDoc

JDoc是基于JAVA开发的，针对主流web框架做的接口文档生成利器，只需极简配置就可以实现文档的生成，且对项目零入侵，主要目的是解决协作开发中接口文档及时更新

JDoc有如下主要特点

1.简单易用，配置极简

2.对项目零入侵，在不改变项目本身代码结构上加上JDoc的格式注解就可以快速生成文档，只对Controller层解析

JDoc jar包依赖，再配置JAVA_HOME目录到jdkhome中

maven包依赖
```
<dependency>
    <groupId>com.nmtx</groupId>
    <artifactId>jdoc</artifactId>
    <version>1.0</version>
</dependency>

```
JDoc配置文件,参考配置如下，配置文件放在resource文件夹中，名字为jdoc.properties

#指定java文件路径
java.source=src/test/java

#指定需要生成文档的包路径
package.name=com.nmtx.test

#指定采用的框架类型,目前支持jfinal,springmvc两种框架
parser.name=jfinal

#指定文档输出路径
out.path=test.html

#指定模版生成器，目前仅支持html
parser.formater=html

#是否启用分析器，默认为false,直接生成文档
use.analysis=true

#分析器访问接口地址，默认为http://localhost:8080
analysis.prefix.url=http://localhost:8080

#接口前缀
api.prefix=/api

Controller配置如下

```
package com.nmtx.jfinal.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.POST;
import com.nmtx.jfinal.common.MessageResp;
import com.nmtx.jfinal.model.User;

/**
 * 用户模块
 * 
 * @author lianghao
 *
 *         2017年3月27日
 */
public class UserController extends Controller {

    /**
     * 用于添加用户功能
     * @title 新增用户
     * @param username|用户名|String|必填
     * @param password|密码|String|必填
     * @param createTime|创建时间|Date|必填 
     */
    @Before(POST.class)
    public void add() {
        MessageResp<String> resp = new MessageResp<String>();
        resp.setData("");
        resp.setCode("100000");
        resp.setMessage("新增成功");
        renderJson(resp);
    }
    
    
    /**
     * 用于删除用户功能
     * @title 删除用户
     * @param id|用户id|Intger|必填
     * @respBody {"code":"100000","data":"","message":"新增成功"}
     */
    public void delete(){
        MessageResp<String> resp = new MessageResp<String>();
        resp.setData("");
        resp.setCode("100000");
        resp.setMessage("删除成功");
        renderJson(resp);
    }
    
    
    /**
     * 通过用户id查询用户功能
     * @title 查询ID查用户
     * @respParam username|用户名|String|必填
     * @respParam password|密码|String|必填
     * @respBody {"code":"100000","data":{"password":"123456","username":"13811111111"},"message":"删除成功"}
     */
    public void getUserById(){
        MessageResp<User> resp = new MessageResp<User>();
        User user = new User();
        user.put("username","13811111111");
        user.put("password","123456");
        resp.setData(user);
        resp.setCode("100000");
        resp.setMessage("获取成功");
        renderJson(resp);
    }
}

```


@param为请求参数 可设置多个 参数名 描述 请求类型 是否必填 

@title 接口标题 

@respParam返回参数 参数名 描述 请求类型 是否必填  可设置多个

@respBody返回demo 



 **JFinal框架中使用，因JFinal本身架构的原因，外部无法拿到对应根映射，JDoc提供两种方法解决** 

1.第一种配置，清除后缀方法，比如说TestController，默认访问链接为/test
```
	/**
	 * 配置访问路由
	 */
	@Override
	public void configRoute(Routes me) {
		//TODO 配置路由
                new JFinalApiDocConfig().setClearSuffix("Controller").start();
	}
 

```
2.第二种配置，与路由配置一致，调用add方法把路由加进去
```
new JFinalApiDocConfig().setUseClearSuffix(false).add("/jfinal", JFinalController.class).start();
```

 **SpringMVC，SpringBoot框架中使用如下** 

```
package com.nmtx.springmvc.doc.config;

import com.nmtx.doc.core.api.springmvc.SpringMVCApiDocConfig;

public class SpringDocBuilder {

    public static void main(String[] args) {
        SpringMVCApiDocConfig doc = new SpringMVCApiDocConfig();
        doc.setConfigFilePath("jdoc.properties");
        doc.start();
    }
}

```



SpringMVC demo地址 https://git.oschina.net/lianghao2016/springmvc-jdoc-demo

JFinal demo地址 https://git.oschina.net/lianghao2016/jdoc-jfinal-demo

SpringBoot demo地址 https://git.oschina.net/lianghao2016/jdoc-springboot-demo.git
api文档截图
![输入图片说明](http://git.oschina.net/uploads/images/2017/0411/104151_5456c000_383641.png "在这里输入图片标题")
![输入图片说明](http://git.oschina.net/uploads/images/2017/0411/103340_b8cd1992_383641.png "在这里输入图片标题")