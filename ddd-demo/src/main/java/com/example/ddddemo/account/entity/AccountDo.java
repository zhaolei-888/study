package com.example.ddddemo.account.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author zhaolei
 * @Date: 2023/3/25 0025 17:08
 * @Description: 数据库的字段（直接与数据库交互的），其实就是数据的表
 */
@Data
public class AccountDo {

    private Long id;

    private Long accountNumber;

    private BigDecimal available;
}
