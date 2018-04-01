package com.transaction.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/**
 * 声明式事务 - 基于TransactionProxyFactoryBean的方式
 * @author sunwei
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext2.xml")
public class SpringTest2 {
	/**
     * 注入代理类
     */
	@Autowired
	@Qualifier(value="accountServiceProxy")
    private AccountService accountService;
    
    @Test
    public void demo(){
    	//期望出错，由于配置readOnly
        accountService.transfer("aaa", "bbb", 200d);
    }
}
