package com.transaction.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
/**
 * 声明式编程  - 注解的方式和事务传播测试
 * @author sunwei
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContextPropagation.xml")
public class SpringTestPropagation {
	/**
	 * 注入代理类
	 */
	@Autowired
	@Qualifier(value="accountService")
	private AccountServicePropagation accountService;
	
	String in = "bbb";
	String out = "aaa";

	@Test
	public void testTransfer() {
		accountService.transfer(in, out, 100d);
	}
	
	@Test
	public void testDoTransfer() {
		accountService.doTransfer(in, out, 100d);
	}
}