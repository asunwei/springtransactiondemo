package com.transaction.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 编程式事务测试--基于底层 API 的编程式事务管理
 * @author sunwei
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContextBCAPI.xml")
public class SpringTestBCAPI {
	@Autowired
	@Qualifier(value="accountService")
    private AccountService accountService;

    @Test
    public void demo1(){
        accountService.transfer("bbb", "aaa", 200d);
    }
}
