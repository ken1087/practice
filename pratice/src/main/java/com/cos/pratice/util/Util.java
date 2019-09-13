package com.cos.pratice.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import com.cos.pratice.domain.Users;


public class Util {
		
	
	public List<HashMap<String,String[]>> hash(List<Users> users){

		List<HashMap<String,String[]>> user = new ArrayList<HashMap<String,String[]>>();
		
		for(int i = 0; i<users.size(); i++) {
			HashMap<String, String[]> map = new HashMap<>();
			String primary = users.get(i).getUserpk().getNum()		+"|"+
				users.get(i).getUserpk().getUserid()						+"|"+
				users.get(i).getUserpk().getStdnum()						+"|"+
				users.get(i).getUserpk().getMynum()						+"|"+
				users.get(i).getUserpk().getPhone();
			
			String name 			= 		users.get(i).getName();
			String password 		= 		users.get(i).getPassword();
			String addr				= 		users.get(i).getAddr();
			String email 			= 		users.get(i).getEmail();
			String title 				= 		users.get(i).getTitle();
			String content 		= 		users.get(i).getContent();
			String country 		= 		users.get(i).getCountry(); 
			String gender 			= 		users.get(i).getGender();
			String createDate 	= 		users.get(i).getCreateDate()		+"";
			String updateDate 	= 		users.get(i).getUpdateDate()	+"";
			String[] arr 			= {name,password,addr,email,title, content, country,gender,createDate,updateDate};
			
			map.put(primary, arr);
			
			user.add(map);	//Key , Value 値で格納
			
			
			
			Set set 				= 		user.get(i).keySet();
			Iterator iterator 	= 		set.iterator();
			while(iterator.hasNext()){
			  String key 		= (String)iterator.next();
			  System.out.println("hashMap Key : " + key);
			}	
		}
		
		return user;	
	}
	
	
	public int parsing(HashMap<String, String[]> search2, String param) {
		
			String[] primary 		= 		null;
			String primaryKey 	= 		null;
			Set set 					= 		search2.keySet();
			Iterator iterator 		= 		set.iterator();
			while(iterator.hasNext()){
			  primaryKey 			= 		(String)iterator.next();
			}
			primary 					= 		primaryKey.split("\\|");
			if(param != null) {
				if(primary[0].equals(param)		||
						primary[1].equals(param)	||
						primary[2].equals(param)	||
						primary[3].equals(param)	||
						primary[4].equals(param)
				) {
					return 1;
				}
			}
		return 0;
	}
}
