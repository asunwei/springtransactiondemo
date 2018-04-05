package com.transaction.dao;

/**
 * 测试事务临时伪表
 * @author sunwei
 *
 */
public interface RecordDao {
	/**
      * @param out    :转出账号
     * @param count :数量
     */
    public void outCount(String out, int count);
    /**
     * 
     * @param in    :转入账号
     * @param count :数量
     */
    public void inCount(String in, int count);
}
