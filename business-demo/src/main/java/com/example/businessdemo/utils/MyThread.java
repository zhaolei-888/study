package com.example.businessdemo.utils;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.businessdemo.mapper.UserMapper;
import com.example.businessdemo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

@Slf4j
public class MyThread implements Runnable {

    int start;

    int end;

    //创建个构造函数初始化
    public MyThread(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        log.info(Thread.currentThread().getName() + "========开始执行========");

        for (int i = start; i < end; i++) {
            //这里还要说一下，，由于在实质项目中，当处理的数据存在等待超时和出错会使线程一直处于等待状态
            //这里只是处理简单的，
            //分批 批量插入
            add();
        }
        log.info(Thread.currentThread().getName() + "========执行完成========");
//        try {
//
//        } catch (Exception e) {
//            log.error("批量新增错误:{}", e);
//        } finally {
//
//        }
    }

    private void add(){
        UserMapper userMapper = SpringBeanUtil.getBean(UserMapper.class);
        Integer count = userMapper.selectCount(Wrappers.<User>lambdaQuery());
        User user = new User();
        user.setName("用户" + count + 1);
        user.setPwd("888888");
        userMapper.insert(user);
        log.info("{}新增完成！", user.getName());
    }
}
