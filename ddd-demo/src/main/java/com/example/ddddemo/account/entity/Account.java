package com.example.ddddemo.account.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author zhaolei
 * @Date: 2023/3/25 0025 16:24
 * @Description:
 * 充血模型：将实体，以及影起实体状态（实体中的属性）发生变化的方法写在一起
 */
@Data
public class Account {
    private Long id;
    private Long accountNumber;
    private BigDecimal available;

    public void withdraw(BigDecimal money) {
        //转入操作
        available = available.add(money);
    }

    public void deposit(BigDecimal money) {
        //转出操作
        if (available.compareTo(money) < 0) {
            //业务异常抛出
        }
        available = available.subtract(money);
    }
    //提现...
}
