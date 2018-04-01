package com.transaction.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/**
 * 声明式事务 -  基于AspectJ的XML方式
 * @author sunwei
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext3.xml")
public class SpringTest3 {
	/**
	 * 注入代理类
	 */
	@Autowired
	@Qualifier(value = "accountService")
	private AccountService accountService;

	@Test
	public void demo() {
		accountService.transfer("aaa", "bbb", 200d);
	}
}