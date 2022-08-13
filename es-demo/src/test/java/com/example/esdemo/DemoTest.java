package com.example.esdemo;

import com.google.common.collect.Sets;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class DemoTest {

    @Test
    public void testSet(){
        Set<Integer> set = Sets.newHashSet();
        set.add(2);
        set.add(6);
        set.add(4);
        set.add(4);
        System.out.println(set);
    }
}
