package com.transaction.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
	
	//private Logger logger = LoggerFactory.getLogger(getClass());
	protected transient Log logger = LogFactory.getLog(getClass());
	//boolean debugEnabled = logger.isDebugEnabled();
	
	
	String in = "bbb";
	String out = "aaa";

	@Test
	public void testMethodB() {
		logger.info("==============================");
		logger.debug("debug");
		accountService.methodB(in, out, 100d);
		logger.info("==============================");
	}
	
	@Test
	public void testMethodA() {
		logger.info("==============================");
		accountService.methodA(in, out, 100d);
		logger.info("==============================");
	}
}