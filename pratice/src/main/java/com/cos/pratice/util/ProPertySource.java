package com.cos.pratice.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource(value = {"classpath:static/properties/db.properties"})	//propertiesファイルがあるパス
public class ProPertySource{
	
	@Autowired
	Environment env;	//propertiesファイルを読み込む

	@Bean
	public MyQuery myquery() {
		MyQuery qry = new MyQuery();
		qry.setSelectall(env.getProperty("selectall"));	//MyQueryObjectに保存
		return qry;
	}
	
}