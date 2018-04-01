package com.transaction.service;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.transaction.dao.AccountDao;

/** 
 * 编程式事务演示 - 基于 TransactionTemplate 的编程式事务管理
 * @author sunwei
 *
 */
public class AccountServiceImplBCTemp implements AccountService {
    //注入DAO类
    private AccountDao accountDao;
    //注入事务管理模板--1
    private TransactionTemplate transactionTemplate;
    
    
    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
       this.transactionTemplate = transactionTemplate;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    //编程式事务
    public void transfer(final String out, final String in, final Double money) {
    	transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				accountDao.outMoney(out, money);
		        //int i = 1/0;
		        accountDao.inMoney(in, money);
			}
		});
    } 
    
}