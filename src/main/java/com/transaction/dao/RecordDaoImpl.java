package com.transaction.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class RecordDaoImpl implements RecordDao {

	 private JdbcTemplate jdbcTemplate;
	    
	    public void setDataSource(DataSource dataSource){
	        
	        this.jdbcTemplate = new JdbcTemplate(dataSource);
	    }

		public void outCount(String out, int count) {
			String sql = "update record set count = count - ? where name = ?";
	        this.jdbcTemplate.update(sql,count,out);
	        System.out.println("RECORD: " + out + " " + count);
			
		}

		public void inCount(String in, int count) {
			String sql = "update record set count = count + ? where name = ?";
	        this.jdbcTemplate.update(sql, count,in);
	        System.out.println("RECORD: " + in + " " + count);
		}

}


 
