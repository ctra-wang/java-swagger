package com.ctra.controller;

import com.ctra.dao.UserMapper;
import com.ctra.pojo.User;
import com.ctra.utils.MybatisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.session.SqlSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jws.soap.SOAPBinding;

@Api(tags = "hello接口")
@RestController
public class HelloController {
    @GetMapping(value = "/hello")
    @ApiOperation("hello控制类")
    public String hello() {
        return "hello";
    }

    // 只要我们的接口中，返回值中存在实体类，他就会被扫描到 swagger
    @PostMapping(value = "/user")
    @ApiOperation("user控制类")
    public User user() {

        SqlSession sqlSession = MybatisUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User userModel = mapper.getUserById(2);
        System.out.println(userModel);


        sqlSession.close();
        return userModel;
    }

    //  operation 接口，不是放在类上的，是方法
    @ApiOperation("hello2控制类")
    @PostMapping(value = "/hello2")

    public String hello2(@ApiParam("用户名") String name) {
        return "hello" + name;
    }

}
