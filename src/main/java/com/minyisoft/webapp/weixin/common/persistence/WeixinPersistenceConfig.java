package com.minyisoft.webapp.weixin.common.persistence;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author qingyong_ou 注册weixinUserTraceDao
 */
@Configuration
public class WeixinPersistenceConfig {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Bean
	public WeixinUserTraceDao weixinUserTraceDao() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
		return sessionTemplate.getMapper(WeixinUserTraceDao.class);
	}

	@Bean
	public WeixinUserDao weixinUserDao() throws Exception {
		SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
		return sessionTemplate.getMapper(WeixinUserDao.class);
	}
}
