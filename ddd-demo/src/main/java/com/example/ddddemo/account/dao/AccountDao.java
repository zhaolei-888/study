package com.example.ddddemo.account.dao;

import com.example.ddddemo.account.entity.Account;
import com.example.ddddemo.account.entity.AccountDo;

/**
 * @Author zhaolei
 * @Date: 2023/3/25 0025 16:49
 * @Description: 这一般是MyBatis层，这力为了演示，代码暂时写死
 */
public interface AccountDao {

    AccountDo selectById(Long id);

    AccountDo selectByNumber(Long number);

    AccountDo add(AccountDo account);

    AccountDo update(AccountDo account);
}
