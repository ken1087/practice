package com.cos.pratice.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.pratice.dao.UserDAO;
import com.cos.pratice.domain.Users;
import com.cos.pratice.domain.UsersPk;
import com.cos.pratice.util.Util;



@Controller
public class UserListController {

	@Autowired
	UserDAO userDAO;
	
	String tag = "UsersListController : ";
	
	//User select*
	@GetMapping("/homework") //http://localhost:8000/homeworkの場合
	public String home(Model model){
		List<HashMap<String,String[]>> user = new ArrayList<HashMap<String,String[]>>();
		
		user = userDAO.findAllList();

		model.addAttribute("users", user);	// List UsersをJspFileに渡すためにmodelに格納する
		return "homework";	 	//　homework.jspにreturnする
		/////////////////////////////////////////////////////////////////( /homework )//////////////////////////////////////////////
	}
	
	@GetMapping("/search")	// http://localhost:8000/searchの場合
	public String search(Model model, String num,String userid,String stdnum,String mynum,String phone){	//5個のPrimary Keyのデータを要請
		
		List<HashMap<String, String[]>> user 		= new ArrayList<HashMap<String,String[]>>();	//DBのデータを全部格納するMap
		List<HashMap<String,String[]>> search 	= new ArrayList<HashMap<String,String[]>>();	//検索したデータを格納するMap
		List<HashMap<String,String[]>> search2 	= new ArrayList<HashMap<String,String[]>>();	//検索したデータの中でチェックして格納するMap
		user = userDAO.findAllList();	//userのデータを呼び出す
		
		if(num.equals("")) 		{num = null;}//Numが””の場合、Nullにする。
		if(userid.equals("")) 	{userid = null;}//Useridが””の場合、Nullにする。
		if(stdnum.equals("")) 	{stdnum = null;}//Stdnumが””の場合、Nullにする。
		if(mynum.equals("")) 	{mynum = null;}//Mynumが””の場合、Nullにする。
		if(phone.equals("")) 	{phone = null;}//Phoneが””の場合、Nullにする。

		String[] numarr 		= 		new String[user.size()];	//Numの配列変数宣言　（String)
		String[] useridarr 	= 		new String[user.size()];	//Useridの配列変数宣言　（String)
		String[] stdnumarr 	= 		new String[user.size()];	//Stdnumの配列変数宣言　（String)
		String[] mynumarr 	= 		new String[user.size()];	//MyNumの配列変数宣言　（String)
		String[] phonearr  	= 		new String[user.size()];	//Phoneの配列変数宣言　（String)
		
		for(int i =0; i<user.size(); i++) {		//MapのKeyを取る
			String[] primary 		= 		null;		//String配列変数宣言
			String primaryKey 	= 		null;		//MapのKeyの変数
			Set set 					= 		user.get(i).keySet();	//UserのIndexのKeyを呼び出す  SetにKeyのObjectが存在してる
			Iterator iterator 		= 		set.iterator();	//IteratorのInterfaceで探す準備
			while(iterator.hasNext()){					//順番で探す
			  primaryKey = (String)iterator.next();	//探したKeyをString 変数に入れる
			}

			primary 			= 		primaryKey.split("\\|");	//入れたKeyを”｜”で分ける
			numarr[i] 		=		primary[0];	//Index 0はNum　Numを宣言した配列に格納
			useridarr[i] 	= 		primary[1];//Index 1はUserid　Useridを宣言した配列に格納
			stdnumarr[i] 	= 		primary[2];//Index 2はStdNum　Stdnumを宣言した配列に格納
			mynumarr[i] 	= 		primary[3];//Index 3はMyNum　MyNumを宣言した配列に格納
			phonearr[i] 	= 		primary[4];//Index 4はPhone　Phoneを宣言した配列に格納
		}
		
		/////////////////////////////////////////////////(     MAPからデータを格納     )/////////////////////////////////////////////////////////////
			
		for(int i = 0; i<user.size(); i++) {		
			
			if(numarr[i].equals(num) && num != null) {		//Num配列と要請したNumの値が同じの場合、要請したNumがある場合
				search.add(user.get(i));							//あってる場合、宣言したsearch変数に格納する
			}															
			if(useridarr[i].equals(userid) && userid != null) {	//Userid配列と要請したUseridの値が同じの場合、要請したUseridがある場合
				search.add(user.get(i));								//あってる場合、宣言したsearch変数に格納する
				
			}
			if(stdnumarr[i].equals(stdnum) && stdnum != null) {	//Stdnum配列と要請したStdnumの値が同じの場合、要請したStdnumがある場合
				search.add(user.get(i));										//あってる場合、宣言したsearch変数に格納する					
			}
			if(mynumarr[i].equals(mynum) && mynum != null) {		//Mynum配列と要請したMynumの値が同じの場合、要請したMynumがある場合
				search.add(user.get(i));										//あってる場合、宣言したsearch変数に格納する				
			}
			if(phonearr[i].equals(phone) && phone != null) {		//Phone配列と要請したPhoneの値が同じの場合、要請したPhoneがある場合			
				search.add(user.get(i));									//あってる場合、宣言したsearch変数に格納する
			}
			
		}
			
		/////////////////////////////////////////////////( 重複チェック　）/////////////////////////////////////////////
		for(HashMap<String, String[]> list : search) {
			if(search2.contains(list) == false) {
				search2.add(list);											//全部格納したListを重複検査後、Search2に格納する
			}
		}
		/////////////////////////////////////////////////( 　　検索した値がないListを削除      )//////////////////////////////////////////////////
		
		Util util = new Util();	//UtilClassを使うために宣言
		int ck;
		if(num != null) {
			for(int k = search2.size()-1; k>=0; k--) {//And結果になるVelueではない場合、後ろから削除
																		//前からするとHashMapのIndexが変わるため
				ck = util.check(search2.get(k), num);		//メソッドに入れてチェックする
				if(ck != 1) {			//チェックが1の場合
					search2.remove(k);	//Search2のKのIndexは削除する		
				}																	
			}
		}
		if(userid != null) {
			for(int k = search2.size()-1; k>=0; k--) {
				ck = util.check(search2.get(k), userid);	//メソッドに入れてチェックする
				if(ck != 2) {			//チェックが2の場合
					search2.remove(k);	//Search2のKのIndexは削除する
				}																	
			}
		}
		if(stdnum != null) {
			for(int k = search2.size()-1; k>=0; k--) {
				ck = util.check(search2.get(k), stdnum);	//メソッドに入れてチェックする
				if(ck != 3) {			//チェックが3の場合
					search2.remove(k);	//Search2のKのIndexは削除する
				}
			}
		}
		if(mynum != null) {
			for(int k = search2.size()-1; k>=0; k--) {
				ck = util.check(search2.get(k), mynum);	//メソッドに入れてチェックする
				if(ck != 4) {			//チェックが4の場合
					search2.remove(k);	//Search2のKのIndexは削除する
				}
			}
		}
		if(phone != null) {
			for(int k = search2.size()-1; k>=0; k--) {
				ck = util.check(search2.get(k), phone);	//メソッドに入れてチェックする
				if(ck != 5) {			//チェックが5の場合
					search2.remove(k);	//Search2のKのIndexは削除する
				}
			}
		}
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//　検索欄に入力したデータ
		model.addAttribute("num", num);			//検索欄に検索した値を入れる
		model.addAttribute("userid", userid);		//検索欄に検索した値を入れる
		model.addAttribute("stdnum", stdnum);	//検索欄に検索した値を入れる
		model.addAttribute("mynum", mynum);	//検索欄に検索した値を入れる
		model.addAttribute("phone", phone);		//検索欄に検索した値を入れる
		model.addAttribute("search", search2);	//全ての検査が終わったらModelに格納してJsp fileに渡す
		return "search";
	}

		//    																	 data insert page
	@GetMapping("/insert") //http://localhost:8000/insertの場合
	public String insert() {
		return "insert"; //insert.jspのページに移動する
	}
	
		//    																		 data insert
	 @PostMapping("/datainsert")	//http://localhost:8000/datainsertの場合 　
	 public String datainsert(Users user, UsersPk userspk) {
		 
		 userDAO.sava(user, userspk);	//入力した内容をinsertする
		 return "redirect:homework"; //入力するとhttp://localhost:8000/homeworkに戻る
	 
	 }
 																				//data 重複チェック
	@PostMapping("/checkid")
	public @ResponseBody int checkid(@RequestBody UsersPk userpk) {		//データをreturnするためにResponseBodeyを使う
																										//RequestBody：Jsonデータを取るため
		List<HashMap<String, String[]>> user = new ArrayList<HashMap<String,String[]>>();	//HashMap　宣言
		
		user = userDAO.findAllList();	//要請したUserPrimaryKeyと比較するために呼び出し
	
		int check = 1;		//Check宣言
		String insert	=		userpk.getNum()		+"|"+
									userpk.getUserid()	+"|"+
									userpk.getStdnum()	+"|"+
									userpk.getMynum()	+"|"+
									userpk.getPhone();				//取ったUesrspkの値を”｜”と合わせて変数に入れる
		
		if(user.size() != 0) {								//Userのデータがない場合、データ入力可能、Userのデータがある場合はチェックする
			for(int i =0; i<user.size(); i++) {			//UserのデータのSizeでチェック
				if( user.get(i).get(insert) != null){		//MapのKeyの中で宣言したInsertがある場合はCheck＝０にする
					//　5個のPrimaryKeyが全部同じの場合、
					check = 0;		//Checkは０
					break;
				}
			}
		}else {
			check = 1;	//Userのデータが一つもない場合はInsertできる
		}
		return check;	//	呼び出したデータの中で要請した値がない場合、Checkは１　*　Insertができる
	}
	
	
	@GetMapping("/view/{primarykey}")	//view/{ } <-URLに変数の値を取る
	public String view(Model model ,@PathVariable String primarykey) {	//＠PathVariable宣言後、String変数は値になる
		
		List<HashMap<String, String[]>> users = new ArrayList<HashMap<String,String[]>>();	//Mapからの全てのデータ
		HashMap<String, String[]> user = new HashMap<String,String[]>();	//全てのデータの中で要請した値で探す
		users = userDAO.findAllList();	//DBから呼び出してMapに格納したデータをUsersに入れる
		
		for(int i = 0; i<users.size(); i++) {				//全てのデータ比較する
			if(users.get(i).get(primarykey) != null) {	//Index番号と要請したPrimaryKeyとあってる場合
				user = users.get(i);	//Userにいれる
				break;	//データは一つだからいれると終了
			}
		}
		model.addAttribute("user", user);	//UserをJSPに渡す
		return "view";
	}
	
}

