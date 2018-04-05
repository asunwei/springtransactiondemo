package com.transaction.service;

public interface AccountServicePropagation {
	/**
	 * 
	 * 演示事务传播
	 * 
	 * @param out  : 转出账号
	 * @param in   ： 转入账号
	 * @param money： 转账金额
	 */
	public void methodA(String out, String in, Double money);
	public void methodB(String out, String in, Double money);
}