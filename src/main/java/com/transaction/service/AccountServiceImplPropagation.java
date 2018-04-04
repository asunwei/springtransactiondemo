package com.transaction.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.transaction.dao.AccountDao;
/**
 * 声明式事务演示和事务传播演示
 * @author sunwei
 *
 */
public class AccountServiceImplPropagation implements AccountServicePropagation {
    //注入DAO类
    private AccountDao accountDao;
    
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    
    //基于注解，只能注解在方法上
    //@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT)
    /*
     * doTransfer()一直存在一个事务用来演示：
     * 
     * 1.当此方法（transfer）没有事务，由于事务的传播性，doAgaintransfer标注事务，当有异常，回滚数据。
     * 
     * 2.当此方法（transfer）事务为REQUIRED，如果当前没有事务，就新建一个事务，如果已经存在一个事务中，加入到这个事务中。这是 最常见的选择。
     * 	若调用doTransfer（）,将transfer（）加入doTransfer()事务运行， 
     *  若直接掉transfer（），当前没有事务，创建一个事务运行。 
     *  
     * 3.当此方法（transfer）事务为SUPPORTS,支持当前事务以其事务运行，如果当前没有事务，就以非事务方式执行。有异常不回滚。
     *  若调用doTransfer（）,将transfer（）加入doTransfer()事务运行， 
     *  若直接掉transfer（），当前没有事务，以非事务运行。
     *  
     * 4.当此方法（transfer）事务为MANDATORY,使用当前的事务，如果当前没有事务，就抛出异常
     * 	若调用doTransfer（）,将transfer（）加入doTransfer()事务运行， 
     *  若直接掉transfer（），则抛出异常 
     *  
     * 5.当此方法（transfer）事务为REQUIRES_NEW,新建事务，如果当前存在事务，把当前事务挂起。??
     *	若调用doTransfer（）,将doTransfer()事务挂起，运行transfer（）抛出异常不会影响外部事务，内部事务结束，外部事务正常运行， 
     *  若直接掉transfer（），正常创建事务运行
     *  
     * 6.当此方法（transfer）事务为NOT_SUPPORTED,以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。 ??
     * 
     * 
     * 7.当此方法（transfer）事务为NEVER,以非事务方式执行，如果当前存在事务，则抛出异常。 ??
     * 
     * 
     * 8.当此方法（transfer）事务为NESTED,如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则执行与 PROPAGATION_REQUIRED 类似的操作。 
     */
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public void transfer( String out,  String in,  Double money) {
        accountDao.outMoney(out, money);
        accountDao.inMoney(in, money);
    	//抛出异常。
        try{
        	throw new RuntimeException();
 		  } catch(RuntimeException e){
 		    e.printStackTrace();
 		  }
    }
    
    
   @Transactional(propagation=Propagation.REQUIRED)
   //1. 当doAgaintransfer演示事务传播性，
    public void doTransfer( String out,  String in,  Double money) {
	   accountDao.outMoney(out, money);
	   accountDao.inMoney(in, money);
	   try{
		   transfer(out,in, money);
		  } catch(RuntimeException e){
			  e.printStackTrace();
		  }
    }
}