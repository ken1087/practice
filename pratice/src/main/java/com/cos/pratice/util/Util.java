package com.cos.pratice.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.cos.pratice.domain.Users;


public class Util {
		
	
	public List<HashMap<String,Object>> hash(List<Users> users){
		
	
		List<HashMap<String,Object>> user = new ArrayList<HashMap<String,Object>>();
		
		for(int i = 0; i<users.size(); i++) {
			LinkedHashMap<String, Object> map = new LinkedHashMap<>();
			map.put("num", users.get(i).getUserpk().getNum());
			map.put("userid", users.get(i).getUserpk().getUserid());
			map.put("stdnum", users.get(i).getUserpk().getStdnum());
			map.put("mynum", users.get(i).getUserpk().getMynum());
			map.put("phone", users.get(i).getUserpk().getPhone());
			
			
			
			map.put("name", users.get(i).getName());
			map.put("password", users.get(i).getPassword());
			map.put("title", users.get(i).getTitle());
			map.put("content", users.get(i).getContent());
			map.put("addr", users.get(i).getAddr());
			map.put("email", users.get(i).getEmail());
			map.put("gender", users.get(i).getGender());
			map.put("country", users.get(i).getCountry());
			map.put("createDate", users.get(i).getCreateDate());
			map.put("updateDate", users.get(i).getUpdateDate());
			user.add(map);	//Key , Value 値で格納
		}
		return user;	
	}
}