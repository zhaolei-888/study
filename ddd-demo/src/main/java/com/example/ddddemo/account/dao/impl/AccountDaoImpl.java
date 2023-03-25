package com.example.ddddemo.account.dao.impl;

import com.example.ddddemo.account.dao.AccountDao;
import com.example.ddddemo.account.entity.Account;
import com.example.ddddemo.account.entity.AccountDo;
import org.springframework.stereotype.Component;

/**
 * @Author zhaolei
 * @Date: 2023/3/25 0025 16:53
 * @Description:
 */
@Component
public class AccountDaoImpl implements AccountDao {
    @Override
    public AccountDo selectById(Long id) {
        return null;
    }

    @Override
    public AccountDo selectByNumber(Long number) {
        return null;
    }

    @Override
    public AccountDo add(AccountDo account) {
        return null;
    }

    @Override
    public AccountDo update(AccountDo account) {
        return null;
    }
}
