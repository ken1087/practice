package com.cos.pratice.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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



@Controller
public class UserListController {

	@Autowired
	UserDAO userDAO;
	
	String tag = "UsersListController : ";
	//User select*
	@GetMapping("/homework") //http://localhost:8000/homeworkの場合
	public String home(Model model){
		List<HashMap<String,Object>> user = new ArrayList<HashMap<String,Object>>();
	
		user = userDAO.findAllList();
		
		model.addAttribute("users", user);	// List UsersをJspFileに渡すためにmodelに格納する
		return "homework";	 	//　homework.jspにreturnする
		
	}
	
	
	
	//검색기능
	@GetMapping("/search")	// http://localhost:8000/searchの場合、
	public String search(Model model, Integer num,String userid,Integer stdnum,Integer mynum,Integer phone){	//5個のPrimary Keyのデータを要請
		
		List<HashMap<String, Object>> user = new ArrayList<HashMap<String,Object>>();
		List<HashMap<String,Object>> search = new ArrayList<HashMap<String,Object>>();
		List<HashMap<String,Object>> search2 = new ArrayList<HashMap<String,Object>>();	//HashMap 宣言
		user = userDAO.findAllList();	//userのデータを呼び出す
		
		if(userid.equals("")) {userid = null;}//useridが””の場合、Nullにする。
		
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if(num != null || userid != null || stdnum != null|| mynum != null|| phone != null) {	//データ値がある場合、
			for(int i = 0; i<user.size(); i++) {							
				if(user.get(i).get("|").equals(num)) {
					search.add(user.get(i));						//呼び出したuserのKeyと要請したValueを比較
				}															//あってる場合、宣言したsearch変数に格納する
				if(user.get(i).get("|").equals(userid)) {
					search.add(user.get(i));
				}
				if(user.get(i).get("|").equals(stdnum)) {
					search.add(user.get(i));
				}
				if(user.get(i).get("|").equals(mynum)) {
					search.add(user.get(i));
				}
				if(user.get(i).get("|").equals(phone)) {
					search.add(user.get(i));
				}
				
			}
		}
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		for(HashMap<String, Object> list : search) {
			if(search2.contains(list) == false) {
				search2.add(list);											//全部格納したListを重複検査後、Search2に格納する
			}
		}
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if(num != null) {
			for(int k = search2.size()-1; k>=0; k--) {
				if(!search2.get(k).get("|").equals(num)) {
					search2.remove(k);										//And結果になるためにVelueではない場合、後ろから削除
				}																	//前からするとHashMapのIndexが変わるため
			}
		}
		if(userid != null) {
			for(int k = search2.size()-1; k>=0; k--) {
				if(!search2.get(k).get("|").equals(userid)) {
					search2.remove(k);
				}																	
			}
		}
		if(stdnum != null) {
			for(int k = search2.size()-1; k>=0; k--) {
				if(!search2.get(k).get("|").equals(stdnum)) {
					search2.remove(k);
				}
			}
		}
		if(mynum != null) {
			for(int k = search2.size()-1; k>=0; k--) {
				if(!search2.get(k).get("|").equals(mynum)) {
					search2.remove(k);
				}
			}
		}
		if(phone != null) {
			for(int k = search2.size()-1; k>=0; k--) {
				if(!search2.get(k).get("|").equals(phone)) {
					search2.remove(k);
				}
			}
		}
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		//　検索欄に入力したデータ
		model.addAttribute("num", num);
		model.addAttribute("userid", userid);
		model.addAttribute("stdnum", stdnum);
		model.addAttribute("mynum", mynum);
		model.addAttribute("phone", phone);
			
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
	public @ResponseBody int checkid(@RequestBody UsersPk userpk) {
		List<HashMap<String, Object>> user = new ArrayList<HashMap<String,Object>>();
		
		user = userDAO.findAllList();	//要請したUserPrimaryKeyと比較するために呼び出し
	
		int check = 1;
		
		
		if(user.size() != 0) {
			
			for(int i =0; i<user.size(); i++) {
				
				
				if( user.get(i).get("num").equals(userpk.getNum())&&
					user.get(i).get("userid").equals(userpk.getUserid())&&
					user.get(i).get("stdnum").equals(userpk.getStdnum())&&
					user.get(i).get("mynum").equals(userpk.getMynum())&&
					user.get(i).get("phone").equals(userpk.getPhone())) {
					//　5個の値が全部同じの場合、
					check = 0;		//Checkは０
					break;
				}
			}
		}else {
			check = 0;
		}
		return check;	//	呼び出したデータの中で要請した値がない場合、Checkは１　*　Insertができる
	}
	
	
	@GetMapping("/view/{num}/{userid}/{stdnum}/{mynum}/{phone}")
	public String view(Model model ,@PathVariable int num, 
			@PathVariable String userid, 
			@PathVariable int stdnum, 
			@PathVariable int mynum, 
			@PathVariable int phone) {
		
		List<HashMap<String, Object>> user = new ArrayList<HashMap<String,Object>>();
		List<HashMap<String, Object>> users = new ArrayList<HashMap<String,Object>>();
		users = userDAO.findAllList();
		
		for(int i = 0; i<users.size(); i++) {
			if(users.get(i).get("num").equals(num)&&
				users.get(i).get("userid").equals(userid)&&
				users.get(i).get("stdnum").equals(stdnum)&&
				users.get(i).get("mynum").equals(mynum)&&
				users.get(i).get("phone").equals(phone)
			) {
				user.add(users.get(i));
				break;
			}
		}
		
		model.addAttribute("user", user);
		return "view";
	}
	
}
