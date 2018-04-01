package com.transaction.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * DAO层接口实现类
 * 这里使用的是JdbcTemplate
 *
 */
public class AccountDaoImpl implements AccountDao {
    
    private JdbcTemplate jdbcTemplate;
    
    public void setDataSource(DataSource dataSource){
        
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void outMoney(String out, Double money) {
        String sql = "update account set money = money - ? where name = ?";
        this.jdbcTemplate.update(sql,money,out);
        System.out.println(out + "取出金额：" + money);
    }

    public void inMoney(String in, Double money) {
        String sql = "update account set money = money + ? where name = ?";
        this.jdbcTemplate.update(sql, money,in);
        System.out.println(in + "存入金额：" + money);
    }

}