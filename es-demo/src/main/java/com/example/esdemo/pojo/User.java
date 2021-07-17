package com.example.esdemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author zhaolei
 * @E-mail zhaolei@dazhuanjia.com
 * @date 2021/4/28 2:42 下午 周三
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class User {

    private String name;

    private int age;
}
