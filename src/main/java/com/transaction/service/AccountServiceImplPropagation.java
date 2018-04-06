package com.transaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.transaction.dao.AccountDao;
import com.transaction.dao.RecordDao;

import javax.annotation.PostConstruct;
/**
 * 声明式事务演示和事务传播演示
 * @author sunwei
 *
 */
public class AccountServiceImplPropagation implements AccountServicePropagation {
    //注入 Account DAO类
    private AccountDao accountDao;
    public void setAccountDao(AccountDao accountDao) { this.accountDao = accountDao;}
    
    //注入 Record Dao类
    private RecordDao recordDao;
    public void setRecordDao(RecordDao recordDao) { this.recordDao = recordDao;}
    
    //在一个类处理两个方法的 事务类型不同。
    @Autowired  //注入上下文  
    private ApplicationContext context;  
     
    //表示代理对象，不是目标对象  
    private AccountServicePropagation proxySelf; 
    @PostConstruct  //初始化方法  
    private void setSelf() {  
        //从上下文获取代理对象（如果通过proxtSelf=this是不对的，this是目标对象）  
        //此种方法不适合于prototype Bean，因为每次getBean返回一个新的Bean  
    	proxySelf = context.getBean(AccountServicePropagation.class);   
    }
    
    private void doAccount(String out,  String in,  Double money) {
    	accountDao.outMoney(out, money);
        accountDao.inMoney(in, money);
    }
    
    private void doRecord(String out, String in, int count) {
    	recordDao.outCount(out, count);
    	recordDao.inCount(in, count);
    }
    
    //基于注解，只能注解在方法上
    //@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT)
    /*
     * methodA()一直存在一个事务用来演示：
     * 
     * 0.当此方法（transfer）没有事务，由于事务的传播性，doAgaintransfer标注事务，当有异常，回滚数据。
     * 
     * 1. Propagation.REQUIRED：如果当前存在事务，使用当期事务，如果不存在，创建一个事务运行。
     * Test调用methodB， 当前不存在事务， 创建一个新事务运行。有错误回滚。
     * Test调用methodA， 当前存在事务，以A事务运行。
     * 
     * 2. Propagation.REQUIRES_NEW：如果当前存在事务，当前事务挂起，创建一个新事务运行。
     * Test调用methodB， 当前不存在事务， 创建一个新事务运行。有错误回滚
     * Test调用methodA， 当前存在事务，将事务A挂起，在methodB抛出运行异常，结果：methodB数据回滚，methodA方法数据提交。
     * Test调用methodA， 当前存在事务，将事务A挂起，在methodA抛出运行异常,methodB运行结束，结果：因为事务A被挂起，methodA数据回滚，methodB方法数据提交。
     * 
     * 3. Propagation.SUPPORTS：如果当前存在事务，使用当前事务，如果不存在，以非事务运行。
     * Test调用methodB， 当前不存在事务， 以非事务运行。有错误不回滚。
     * Test调用methodA， 当前存在事务，以A事务运行。
     * 
     * 4. Propagation.NOT_SUPPORTED：如果当前存在事务，将当前事务挂起，以非事务运行。总是以非事务运行。
     * Test调用methodB， 当前不存在事务， 以非事务运行。有错误不回滚。
     * Test调用methodA， 当前存在事务，将事务A挂起，以非事务运行。
     * 
     * 5. Propagation.MANDATORY：当前存在事务，使用当前事务，如果不存在事务，则抛异常，总是以事务运行。
     * Test调用methodB， 当前不存在事务， 抛出异常
     * Test调用methodA， 当前存在事务，以事务A运行。
     * 
     * 6. Propagation.NEVER：如果当前存在事务，则抛异常，总是以非事务运行
     * Test调用methodB， 当前不存在事务， 以非事务运行。
     * Test调用methodA， 当前存在事务， 抛出异常
     * 
     * 7、Propagation.NESTED如果当前存在事务，则运行在一个嵌套的事务中. 如果没有事务, 
     *   则按TransactionDefinition.PROPAGATION_REQUIRED 属性执行。需要JDBC3.0以上支持。
     *    
     *    事务A
     *    methodA（）{
     *    	methodB();
     *    }
     *    
     *    事务B
     *    methodB(){}
     */
    
   
    @Transactional(propagation=Propagation.NEVER)
    public void methodB( String out,  String in,  Double money) {
        doAccount(out, in, money);
    	//抛出异常。注意，SpringAop只能捕获未处理运行异常或者throw new RuntimeException();
        //int i = 1/0;
    }
    
    
    @Transactional(propagation=Propagation.REQUIRED)
    public void methodA( String out,  String in, Double money) {
    	int count = (int) (money/100);
		doRecord(out, in, count);
		proxySelf.methodB(out,in, money);
		// int i = 1/0;
		
		//只有演示 Propagation.REQUIRED 和 Propagation.REQUIRES_NEW情况下使用try catch，
		//由于要保证methodA没有抛错，and methodB抛错的场景
    	try{
    		//proxySelf.methodB(out,in, money);
		  } catch(RuntimeException e){
			  e.printStackTrace();
		  }
    }
   
}