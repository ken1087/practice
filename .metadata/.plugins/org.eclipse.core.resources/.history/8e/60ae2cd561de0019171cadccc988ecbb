package com.cos.pratice.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Repository;
import com.cos.pratice.domain.Users;
import com.cos.pratice.domain.UsersPk;
import com.cos.pratice.util.MyQuery;
import com.cos.pratice.util.ProPertySource;
import com.cos.pratice.util.Util;

@Repository
public class UserDAO{
	
	public void sava(Users user, UsersPk userpk){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");    //META-INFにあるPersistence.xml読み込む
		EntityManager em = emf.createEntityManager();    //EntityManager　宣言
		EntityTransaction tx = em.getTransaction();    //Transaction　宣言

		tx.begin();    //Transactionが始まる
		try {
			Users u = new Users();    //User　Object生成
			u.setUserpk(userpk);
			u.setName(user.getName());
			u.setPassword(user.getPassword());
			u.setEmail(user.getEmail());
			u.setAddr(user.getAddr());//										
			u.setTitle(user.getTitle());
			u.setContent(user.getContent());
			u.setCountry(user.getCountry());
			u.setGender(user.getGender());
			u.setCreateDate(user.getCreateDate());
			u.setUpdateDate(user.getUpdateDate());    //要請した値をSETする
			
			em.persist(u);    	//Save
			tx.commit();    //Insert後、Commit
			
		}catch (Exception e) {
			
		}finally {
			em.close();	    //EntityManagerをClose
		}
		emf.close();    //EntityManagerFactoryをClose
	}

	//																				Find 	All
	public List<HashMap<String, String[]>> findAllList(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");    //META-INFにあるPersistence.xml読み込む
		EntityManager em = emf.createEntityManager();    //EntityManager　宣言
		@SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(ProPertySource.class);    //SQL文があるPropertiesファイルを読み込んでるClassを呼び出す
		MyQuery qry = context.getBean("myquery", MyQuery.class);    //SQL文を保存してるObjectを呼び出す
		String jpql = qry.getSelectall();    //MyQueryにSelect u FROM Users uが書いてる変数を呼び出してStringに格納
		
		try {
			List<HashMap<String, String[]>> hashlist = new ArrayList<HashMap<String,String[]>>();
			TypedQuery<Users> query =  em.createQuery(jpql, Users.class);    //createQueryにSQL文とUsersObjectを入れる
				List<Users> list = (List<Users>)query.getResultList();    //入れるとSQL文が実行、getResultList() <-　実行したSQL文のList
				Util util = new Util();
				hashlist = util.hash(list);    //HashMapに格納するためのメソッド
				
			return hashlist;    //HashMapに格納後、return
			
		}catch (Exception e) {
			
		}finally {
			em.close();    //EntityManagerをClose
		}
		emf.close();    //EntityManagerFactoryをClose
		
		return null;	
	}
	
}
