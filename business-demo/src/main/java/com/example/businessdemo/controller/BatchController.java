package com.example.businessdemo.controller;

import com.example.businessdemo.utils.MyThread;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;

@RestController(value = "/batch")
public class BatchController {


    @GetMapping("/add")
    public void bactchAdd(){
        //手动创建一个线程池，数量和开启线程的数量一样
//        ExecutorService executor = Executors.newFixedThreadPool(3);
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();

        //Common Thread Pool
        ExecutorService pool = new ThreadPoolExecutor(5, 200,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        //pool.execute(()-> System.out.println(Thread.currentThread().getName()));
        //pool.shutdown();//gracefully shutdown


        //线程类
        MyThread mythead1 = new MyThread(16000, 30000);
        MyThread mythead2 = new MyThread(30000, 60000);
        MyThread mythead3 = new MyThread(60000, 100000);
        MyThread mythead4 = new MyThread(100000, 150000);
        MyThread mythead5 = new MyThread(150000, 200000);
        MyThread mythead6 = new MyThread(200000, 250000);
        MyThread mythead7 = new MyThread(250000, 300000);
        pool.execute(mythead1);
        pool.execute(mythead2);
        pool.execute(mythead3);
        pool.execute(mythead4);
        pool.execute(mythead5);
        pool.execute(mythead6);
        pool.execute(mythead7);
        //执行完关闭线程池
        pool.shutdown();
    }


}
