package com.transaction.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 编程式事务测试--使用TransactionTemplate实现
 * @author sunwei
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContextBCTemp.xml")
public class SpringTestBCTemp {
	@Autowired
	@Qualifier(value="accountService")
    private AccountService accountService;

    @Test
    public void demo1(){
        accountService.transfer("aaa", "bbb", 200d);
    }
}
