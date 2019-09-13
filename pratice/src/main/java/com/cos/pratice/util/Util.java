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
		
	
	public List<HashMap<String,String[]>> hash(List<Users> users){		//DAOから来たUserList

		List<HashMap<String,String[]>> user = new ArrayList<HashMap<String,String[]>>();
		
		for(int i = 0; i<users.size(); i++) {
			
			
			HashMap<String, String[]> map = new HashMap<>();		//HashMap　宣言
			
			String primary = users.get(i).getUserpk().getNum()		+"|"+		
				users.get(i).getUserpk().getUserid()						+"|"+
				users.get(i).getUserpk().getStdnum()						+"|"+
				users.get(i).getUserpk().getMynum()						+"|"+
				users.get(i).getUserpk().getPhone();									//PrimaryKeyを区切り文字”｜”で指定してString変数に入れる

			String name 			= 		users.get(i).getName();					//String宣言してUsersの項目を入れる
			String password 		= 		users.get(i).getPassword();				//String宣言してUsersの項目を入れる
			String addr				= 		users.get(i).getAddr();						//String宣言してUsersの項目を入れる
			String email 			= 		users.get(i).getEmail();					//String宣言してUsersの項目を入れる
			String title 				= 		users.get(i).getTitle();						//String宣言してUsersの項目を入れる
			String content 		= 		users.get(i).getContent();					//String宣言してUsersの項目を入れる
			String country 		= 		users.get(i).getCountry(); 				//String宣言してUsersの項目を入れる
			String gender 			= 		users.get(i).getGender();					//String宣言してUsersの項目を入れる
			String createDate 	= 		users.get(i).getCreateDate()		+"";	//String宣言してUsersの項目を入れる
			String updateDate 	= 		users.get(i).getUpdateDate()	+"";	//StringではないデータはStringで変換する
			
			if(name == null) 		{name = "";}				//　nameがNullの場合、nameは””にする　（デフォルト)
			if(password == null) 	{password = "";}			//　passwordがNullの場合、passwordは””にする　（デフォルト)
			if(addr == null) 			{addr = "";}				//　addrがNullの場合、addrは””にする　（デフォルト)
			if(email == null) 		{email = "";}				//　emailがNullの場合、emailは””にする　（デフォルト)
			if(title == null) 			{title = "";}				//　titleがNullの場合、titleは””にする　（デフォルト)
			if(content == null) 		{content = "";}			//　contentがNullの場合、contentは””にする　（デフォルト)
			if(country == null) 		{country = "";}			//　countryがNullの場合、countryは””にする　（デフォルト)
			if(gender == null) 		{gender = "";}			//　genderがNullの場合、genderは””にする　（デフォルト)
			if(createDate == null) {createDate = "";}		//　createDateがNullの場合、createDateは””にする　（デフォルト)
			if(updateDate == null){updateDate = "";}		//　updateDateがNullの場合、updateDateは””にする　（デフォルト)
			
			//残り10項目をString配列にいれる
			String[] arr 	= 		{name,password,addr,email,title, content, country,gender,createDate,updateDate};
			
			map.put(primary, arr);	//Mapに　Key：PrimaryKey（　区切り”｜”　）　Value：String配列変数
			user.add(map);	//MapをUserListに格納
		}
		
		return user;	//userListをreturnする
	}
	
	
	public int check(HashMap<String, String[]> search2, String param) {	//チェックするsearch2.get(Index)とパラメータ
		
		String[] primary 		= 		null;		//String配列変数宣言
		String primaryKey 	= 		null;		//MapのKeyの変数
		Set set 					= 		search2.keySet();	//UserのKeyを呼び出す
		Iterator iterator 		= 		set.iterator();			//IteratorのInterfaceで探す準備
		while(iterator.hasNext()){							//順番で探す
		  primaryKey 			= 		(String)iterator.next();		//探したKeyをString 変数に入れる
		}
		primary 					= 		primaryKey.split("\\|");		//入れたKeyを”｜”で分ける
		if(param != null) {						//入ったパラメータのデータがある場合
			if(primary[0].equals(param)) {	//primary配列のIndex0（num）とパラメータのnumが一致する場合
				return 1;							//returnは１にする
			}
			if(primary[1].equals(param)) {	//primary配列のIndex1（userid）とパラメータのuseridが一致する場合
				return 2;							//returnは2にする
			}
			if(primary[2].equals(param)) {	//primary配列のIndex2（num）とパラメータのstdnumが一致する場合
				return 3;							//returnは3にする
			}
			if(primary[3].equals(param)) {	//primary配列のIndex3（num）とパラメータのmynumが一致する場合
				return 4;							//returnは4にする
			}
			if(primary[4].equals(param)) {	//primary配列のIndex4（num）とパラメータのphoneが一致する場合
				return 5;							//returnは5にする
			}
		}
		return 0;	//何もない場合はreturnは０にする
	}
}
