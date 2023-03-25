package com.example.ddddemo.account.repository.impl;

import com.example.ddddemo.account.builder.AccountBuilder;
import com.example.ddddemo.account.dao.AccountDao;
import com.example.ddddemo.account.entity.Account;
import com.example.ddddemo.account.entity.AccountDo;
import com.example.ddddemo.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author zhaolei
 * @Date: 2023/3/25 0025 16:47
 * @Description:
 */
@Component
public class JpaAccountRepositoryImpl implements AccountRepository {

    /**
     * 如果要变换持久层框架可以新建一个实现类(MyBatisAccountRepositoryImpl)，
     * 将accountDao换成要变换的持久层框架如accountMapper
     */
    //如果要变换持久层框架可以新建一个实现类，
    // 将accountDao换成要变换的持久层框架如accountMapper
    @Autowired
    private AccountDao accountDao;

    @Autowired
    private AccountBuilder accountBuilder;

    @Override
    public Account findById(Long id) {
        AccountDo accountDo = accountDao.selectById(id);
        return accountBuilder.toAccount(accountDo);
    }

    @Override
    public Account findByNumber(Long accountNUmber) {
        AccountDo accountDo = accountDao.selectByNumber(accountNUmber);
        return accountBuilder.toAccount(accountDo);
    }

    @Override
    public Account save(Account account) {
        AccountDo accountDo = accountBuilder.formatAccount(account);
        if(accountDo.getId() == null){
            accountDo = accountDao.add(accountDo);
        }else {
            accountDo = accountDao.update(accountDo);
        }
        return accountBuilder.toAccount(accountDo);
    }
}
