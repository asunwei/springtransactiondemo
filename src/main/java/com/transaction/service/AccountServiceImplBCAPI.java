package com.transaction.service;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.transaction.dao.AccountDao;

/** 
 * 编程式事务演示 - 基于底层 API 的编程式事务管理
 * @author sunwei
 *
 */
public class AccountServiceImplBCAPI implements AccountService {
    //注入DAO类
    private AccountDao accountDao;
    
    //
    private TransactionDefinition txDefinition;
    //
    private PlatformTransactionManager txManager;
    
    
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

	public void setTxDefinition(TransactionDefinition txDefinition) {
		this.txDefinition = txDefinition;
	}

	public void setTxManager(PlatformTransactionManager txManager) {
		this.txManager = txManager;
	}

	//编程式事务
    public void transfer(final String out, final String in, final Double money) {
    	TransactionStatus txStatus = txManager.getTransaction(txDefinition);
    	try {
    		accountDao.outMoney(out, money);
	        int i = 1/0;
	        accountDao.inMoney(in, money);
	        txManager.commit(txStatus);
    	} catch(Exception e) {
    		e.printStackTrace();
    		txManager.rollback(txStatus);
    		System.out.println("数据已回滚完成！");
    	}
				
    } 
    
}