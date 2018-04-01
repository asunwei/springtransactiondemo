package com.transaction.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/**
 * 声明式编程  - 注解的方式
 * @author sunwei
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext4.xml")
public class SpringTest4 {
	/**
	 * 注入代理类
	 */
	@Autowired
	@Qualifier(value="accountService")
	private AccountService accountService;

	@Test
	public void demo() {
		accountService.transfer("bbb", "aaa", 200d);
	}
}