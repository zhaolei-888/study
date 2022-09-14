package com.example.businessdemo;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.businessdemo.mapper.UserGroupItemMapper;
import com.example.businessdemo.mapper.UserGroupMapper;
import com.example.businessdemo.mapper.UserMapper;
import com.example.businessdemo.model.User;
import com.example.businessdemo.model.UserGroup;
import com.example.businessdemo.utils.MyThread;
import com.example.businessdemo.utils.TaskExcutor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserGroupMapper userGroupMapper;

    @Autowired
    UserGroupItemMapper itemMapper;


    @Test
    public void queryAll(){

        List<User> users = userMapper.selectList(Wrappers.<User>lambdaQuery());
        System.out.println(JSON.toJSON(users));
        UserGroup group = new UserGroup();
        group.setName("测试分组001");
        userGroupMapper.insert(group);
    }

    @Test
    public void batchInsert(){
        //创建一个线程池，数量和开启线程的数量一样
        ExecutorService executor = Executors.newFixedThreadPool(3);

        //线程类
        MyThread mythead1 = new MyThread(6000, 10000);
        MyThread mythead2 = new MyThread(10000, 12000);
        MyThread mythead3 = new MyThread(12000, 16000);
        executor.execute(mythead1);
        executor.execute(mythead2);
        executor.execute(mythead3);
        //执行完关闭线程池
        executor.shutdown();
    }



}
