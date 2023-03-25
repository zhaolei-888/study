package com.example.ddddemo.account.repository;

import com.example.ddddemo.account.entity.Account;

/**
 * @Author zhaolei
 * @Date: 2023/3/25 0025 16:47
 * @Description:
 */
public interface AccountRepository {

    Account findById(Long id);

    Account findByNumber(Long accountNUmber);

    Account save(Account account);
}
