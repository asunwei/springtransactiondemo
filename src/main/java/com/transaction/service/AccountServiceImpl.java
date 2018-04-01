package com.transaction.service;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.transaction.dao.AccountDao;
/**
 * 声明式事务演示。代码不许改动
 * @author sunwei
 *
 */
public class AccountServiceImpl implements AccountService {
    //注入DAO类
    private AccountDao accountDao;
    
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    
  //基于注解--4，只能注解在方法上
    @Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT)
    public void transfer( String out,  String in,  Double money) {
        accountDao.outMoney(out, money);
        //int i = 1/0;
        accountDao.inMoney(in, money);
    }
}