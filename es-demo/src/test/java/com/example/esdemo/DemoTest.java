package com.example.esdemo;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Comparator;
import java.util.List;
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

    @Test
    public void sortByLength(){
        List<String> list = Lists.newArrayList("he see a ball".split(" "));
        System.out.println("排序前" + list);
        list.sort(Comparator.comparing(String :: length));
        System.out.println("排序后" + list);
    }
}
