//点赞榜
var page_now1;
var maxPage;
var sPra={};
//最新分享
var allShare={};
var page_all;
var newMaxPage;
var sNew={};
//用户自己的分享
var sUser={};
var sUserInf ={"suserinf1":[]};
// 登录
var user = {};
var admin = {};
//推荐用户
var pageAdmin;
var newAdminMaxPage;
var recommend={};
//关键字
var userjur;
var keyword={};
var banUser={};
//搜索
var sSearch={};
var sSearchInf ={"ssearchinf1":[]};
//查看分享
var sShare={};
var comname={};
var becomname={};
//其他用户
var otherUser={};
var otherUserHead={};
var otherUserInf ={"otheruserinf1":[]};
//收藏
var colUser={};
var colShare={};
var praShare={};
//根据分享跳转
var maxPhoto=1;
var maxPhotoPath={"maxphotopath1":[]};
var rShare;
var otherUserSkip;

function rShareFunc(rShare){
	rShare.each(function(i,el){
		$(this).click(function(){
			localStorage.setItem("shareSkip",$(this).attr("data-share"));
			window.location.href="share.html";
		})
	});
}

function loadFunc(load){
	load.each(function(i,el){
		$(this).click(function(){
			localStorage.setItem("load",$(this).attr("data-load"));
			if($(this).val().match("屏蔽分享")){
			$.ajax({
				url : "http://localhost:8088/iShow//share/chageShareState.do?shareId="+$(this).attr("data-load")+"&state="+"2",
				type : "get", 
				dataType : "json",
				async : false,
				success : function(data) {
				},
				error : function() {
					alert("error");
				}
			});		
			$(this).val("解除屏蔽");
			alert("屏蔽分享成功");
			}else{
				$.ajax({
					url : "http://localhost:8088/iShow//share/chageShareState.do?shareId="+$(this).attr("data-load")+"&state="+"0",
					type : "get", 
					dataType : "json",
					async : false,
					success : function(data) {
					},
					error : function() {
						alert("error");
					}
				});		
				$(this).val("屏蔽分享");
				alert("分享解除屏蔽成功");
			}
		})
	});
}
function replyFunc(reply){
	reply.each(function(i,el){
		$(this).click(function(){
			localStorage.setItem("replySkip",$(this).attr("data-replyid"));
			document.getElementById("share_reply_top1_span").innerHTML="回复:";
			document.getElementById("share_reply_top1_a").innerHTML=$(this).attr("data-replyname");
		})
	});
}
//根据用户名跳转到其他用户界面
function otherUserSkipFunc(otherUserSkip){
	otherUserSkip.each(function(i,el){
		$(this).click(function(){
			localStorage.setItem("otheruserid",$(this).attr("data-user"));
			if(localStorage.getItem("userid")!=null&&localStorage.getItem("userid")==localStorage.getItem("otheruserid")){
				window.location.href="user_frame.html";
			}else{
				window.location.href="otheruser_frame.html";
			}
		});
	});
}
//aJax数据
function ajaxData(address,flag){
	$.ajax({
		url : "http://localhost:8088/iShow//"+address+".do?",
		type : "get",
		dataType : "json",
		async : false,
		success : function(data) {
			var temp2 = JSON.stringify(data);
			if(flag==1){
				sNew = eval('(' + temp2 + ')');
			}else if(flag==2){
				sPra = eval('(' + temp2 + ')');
				var temp4=sPra.list.length%5>0?1:0;
				maxPage=parseInt(sPra.list.length/5+temp4);	
				setPra();
			}else if(flag==3){
				banUser = eval('(' + temp2 + ')');
			}else if(flag==4){
				keyword = eval('(' + temp2 + ')');
			}else if(flag==5){
				allShare = eval('(' + temp2 + ')');
			}
		},
		error : function() {
			alert("error");
		}
	});
}
function ajaxData2(address,id,id1,flag){
	$.ajax({
		url : "http://localhost:8088/iShow//"+address+".do?"+id+"=" + id1,
		type : "get",
		dataType : "json",
		async : false,
		success : function(data) {
			var temp2 = JSON.stringify(data);
			if(flag==1){
				recommend = eval('(' + temp2 + ')');
			}else if(flag==2){
				sUser = eval('(' + temp2 + ')');
			}else if(flag==3){
				sUserInf.suserinf1.push(data);
			}else if(flag==4){
				sSearch = eval('(' + temp2 + ')');
			}else if(flag==5){
				sSearchInf.ssearchinf1.push(data);	
			}else if(flag==6){
				sShare = eval('(' + temp2 + ')');
			}else if(flag==7){
				comname = eval('(' + temp2 + ')');
			}else if(flag==8){
				becomname = eval('(' + temp2 + ')');
			}else if(flag==9){
				otherUserHead = eval('(' + temp2 + ')');
			}else if(flag==10){
				otherUser = eval('(' + temp2 + ')');
			}else if(flag==11){
				otherUserInf.otheruserinf1.push(data);
			}else if(flag==12){
				mailExist = eval('(' + temp2 + ')');
			}else if(flag==13){
				
			}else if(flag==14){
				colUser = eval('(' + temp2 + ')');
			}
		},
		error : function() {
			alert("error");
		}
	});
}
function ajaxDataLogin(address,id,id1,pwd,pwd1,flag){
	$.ajax({
		url : "http://localhost:8088/iShow//"+address+".do?"+id+"="+id1 + "&"+pwd+"=" + pwd1,
		type : "get",
		dataType : "json",
		async : false,
		success : function(data) {
			var temp2 = JSON.stringify(data);
			if(flag==1){
				user = eval('(' + temp2 + ')');
				userExist();
			}else if(flag==2){
				admin = eval('(' + temp2 + ')');
				adminExist();
			}else if(flag==3){
				user = eval('(' + temp2 + ')');
				userExist2();
			}else if(flag==4){
				admin = eval('(' + temp2 + ')');
				adminExist2();
			}else if(flag==5){
				admin = eval('(' + temp2 + ')');
			}else if(flag==6){
				user = eval('(' + temp2 + ')');
			}else if(flag==7){
				
			}
			
		},
		error : function() {
			alert("error");
		}
	});
}
//用户信息记录
function localStorageUser(){
	localStorage.setItem("userid", user.id); 
	localStorage.setItem("usernickname", user.nickname); 
	localStorage.setItem("usermailbox", user.mailbox); 
	localStorage.setItem("userhead_portrait", user.head_portrait); 
	localStorage.setItem("userjurisdiction", user.jurisdiction); 
	localStorage.setItem("usernumsOfCollectionShare", user.numsOfCollectionShare); 
	localStorage.setItem("usernumsOfCollectionUser", user.numsOfCollectionUser); 
	localStorage.setItem("usernumsOfUserShare", user.numsOfUserShare); 
	localStorage.setItem("userflag", user.flag); 
}
//管理员信息记录
function localStorageAdmin(){
	localStorage.setItem("adminid", admin.admin_id); 
	localStorage.setItem("adminnickname", admin.nickname); 
	localStorage.setItem("adminnumsOfBannedUser", admin.numsOfBannedUser); 
	localStorage.setItem("adminnumsOfBannedKeyWord", admin.numsOfBannedKeyWord); 
	localStorage.setItem("adminflag", admin.flag); 
}
//时间格式转换
function quitOrSignIn(nav_a,head_a){
	if(localStorage.getItem("userflag")=="success"){
		ajaxDataLogin("user/login","mailbox",localStorage.getItem("usermailbox"),"password",localStorage.getItem("userpassword"),6);
		localStorageUser();
		document.getElementById(nav_a).innerHTML=user.nickname;
		document.getElementById(head_a).innerHTML="退出";
		document.getElementById(nav_a).onclick=function(){
			window.location.href="user_frame.html";
		}
		document.getElementById(head_a).onclick=function(){
			localStorage.setItem("userflag", "failed"); 
			window.location.href="index.jsp";
		}
	}else if(localStorage.getItem("adminflag")=="success"){
		ajaxDataLogin("Admin/login","id",localStorage.getItem("adminid"),"pwd",localStorage.getItem("adminpassword"),5);
		localStorageAdmin(); 
		document.getElementById(nav_a).innerHTML=admin.nickname;
		document.getElementById(head_a).innerHTML="退出";
		document.getElementById(nav_a).onclick=function(){
			window.location.href="a_set.html";
		}
		document.getElementById(head_a).onclick=function(){
			localStorage.setItem("adminflag", "failed"); 
			window.location.href="index.jsp";
		}
	}else{
		document.getElementById(nav_a).innerHTML="";
		document.getElementById(head_a).innerHTML="登录";
		document.getElementById(head_a).onclick=function(){
			window.location.href="signIn.html";
		}
	}
}
function dateFormat(dateInput){
	var iM = dateInput;   //得到毫秒数
	var nDate = new Date(iM); 
	var year= nDate.getFullYear();
	var month= nDate.getMonth() + 1;
	var day= nDate.getDate();
	var hour=nDate.getHours(); //获取系统时，
	var minute=nDate.getMinutes(); //分
	var second=nDate.getSeconds(); //秒
	if(month<10){
		month="0"+month;
	}
	if(day<10){
		day="0"+day;
	}
	if(hour<10){
		hour="0"+hour;
	}
	if(minute<10){
		minute="0"+minute;
	}
	if(second<10){
		second="0"+second;
	}
	return year + "-" + month + "-" + day + " "+hour+":"+minute+":"+second;
}
//上页操作
function prePage() {
	page_now1 = parseInt(document.getElementById('page_now').innerHTML);
	page_now1 = page_now1 - 1;
	if (page_now1 < 1) {
		page_now1 = 1;
		return;
	}
	praSkip();
}
// 下页操作
function nextPage() {
	page_now1 = parseInt(document.getElementById('page_now').innerHTML);
	page_now1 = page_now1 + 1;
	if (page_now1 > maxPage) {
		page_now1 = maxPage;
		return;
	}
	praSkip();
}

// 跳转设置
function go() {
	var temp = parseInt(document.getElementById("page_input").value);
	if (temp < 1 || temp > maxPage || isNaN(temp)) {
		document.getElementById("page_input").value = "";
	} else {
		page_now1 = temp;
		document.getElementById("page_input").value = "";
		praSkip();
	}
}
//点赞根据页数跳转
function praSkip(){
	document.getElementById("page_now").innerHTML = page_now1;
	setPra();
}
//刷新点赞榜-重新赋值
function setPra() {
	var j = parseInt((page_now1 - 1) * 5);//排列出来的第一个数
	var k= j+1;
	var k1=k;
	var len = sPra.list.length;//总长度
	if(len-k>=5){
		k=5;
	}else{
		k=len-k+1;
	}
	for (i = 1; i <= 5; i++) {
		$("#right_center_2_p_a" + i).empty();
		$("#right_center_2_p_b" + i + "_a").empty();
		$("#right_center_2_p_c" + i).empty();
	}
	for (i = 1; i <= k; i++, j++,k1++) {
		$("#right_center_2_p_a" + i).append(k1 + ".");
		$("#right_center_2_p_b" + i + "_a").append(sPra.list[j].describe.substr(0,6));
		if(sPra.list[j].describe.length>6){
			$("#right_center_2_p_b" + i + "_a").append("...");
		}
		$("#right_center_2_p_b" + i + "_a").attr("data-share",sPra.list[j].share_id);
		$("#right_center_2_p_c" + i).append(sPra.list[j].point_of_praise);
	}
}
// 刷新今日点赞榜
function getPra_today() {
	document.getElementById("right_center_1_span").innerHTML = "今日点赞榜单";
	getSPraise("share/getTodayShareList");
}
//刷新一周点赞榜
function getPra_week() {
	document.getElementById("right_center_1_span").innerHTML = "一周点赞榜单";
	getSPraise("share/getWeekShareList");
}
//刷新全部点赞榜
function getPra_all() {
	document.getElementById("right_center_1_span").innerHTML = "全部点赞榜单";
	getSPraise("share/getAllShareList");
}
function getSPraise(sharelist){
	page_now1 = 1;
	document.getElementById("page_now").innerHTML = 1;
	ajaxData(sharelist,2);
}
//最新分享
//更新最新分享
function newShareUpdata(){
	page_all = 1;
	document.getElementById("center_2_page_now").innerHTML = 1;
	ajaxData("share/findShareByTime",1);
	var temp=sNew.shares.length%5>0?1:0;
	newMaxPage=parseInt(sNew.shares.length/5+temp);
	document.getElementById("center_2_page_all").innerHTML = newMaxPage;
	setNewShareUpdata();
	var a=sNew;
}

function setNewShareUpdata() {
	document.getElementById("center_2_page_now").innerHTML = page_all;
	var j = parseInt((page_all - 1) * 5);//排列出来的第一个数
	var k= j+1;
	var jcopy=j;
	var date1;
	var len = sNew.shares.length;//总长度
	if(len-k>=5){
		k=5;
	}else{
		k=len-k+1;
	}
	var kcopy=k;
	for (i = 1; i <= 5; i++) {
		$(".center_2_share"+i).css("display","block");
		$("#center_2_share"+i+"_top_2").empty();
		$("#center_2_share"+i+"_top_3").empty();
		$("#center_2_share"+i+"_center1_p1").empty();
		$("#center_2_share"+i+"_bottom_3").attr("value","点赞");
		$("#center_2_share"+i+"_bottom_4").attr("value","评论");
		$("#center_2_share"+i+"_center2_1").attr("src","");
		$("#center_2_share"+i+"_center2_2").attr("src","");
		$("#center_2_share"+i+"_center2_3").attr("src","");
		$("#center_2_share"+i+"_center2_4").attr("src","");
		$("#center_2_share"+i+"_center2_5").attr("src","");
		$("#center_2_share"+i+"_center2_6").attr("src","");
	}
	for (i = 1,jcopy=j,kcopy=k; i <= kcopy; i++, jcopy++) {
		date1=dateFormat(sNew.shares[jcopy].release_time);
		if(sNew.users[jcopy].head_portrait!=null&&sNew.users[jcopy].head_portrait!=""){
			$("#center_2_share"+i+"_top_img").attr("src",sNew.users[jcopy].head_portrait);
		}else{
			$("#center_2_share"+i+"_top_img").attr("src","img/user.ico");
		}
		document.getElementById("center_2_share"+i+"_top_2").innerHTML=sNew.users[jcopy].nickname;
		$("#center_2_share"+i+"_top_4").attr("data-user",sNew.users[jcopy].user_id);
		var q;
		if(sNew.pictures[jcopy]!=null){
			var z=0;
			for(q=1;q<=sNew.pictures[jcopy].length;q++,z++){
				$("#center_2_share"+i+"_center2_"+q).attr("src",sNew.pictures[jcopy][z].image_path);
			}
		}
		if(sNew.shares[jcopy].describe.length>60){
			document.getElementById("center_2_share1_center1_p"+i).innerHTML=sNew.shares[jcopy].describe.substr(0,60)+"...";
		}else{
			document.getElementById("center_2_share1_center1_p"+i).innerHTML=sNew.shares[jcopy].describe.substr(0,60);
		}
		$("#center_2_share"+i+"_top_3").append(date1);
		$("#center_2_share"+i+"_bottom_1").attr("data-user",sNew.users[jcopy].user_id);
		$("#center_2_share"+i+"_bottom_2").attr("data-share",sNew.shares[jcopy].share_id);
		$("#center_2_share"+i+"_bottom_3").attr("data-share",sNew.shares[jcopy].share_id);
		$("#center_2_share"+i+"_bottom_4").attr("data-share",sNew.shares[jcopy].share_id);
		$("#center_2_share"+i+"_bottom_3").attr("value","点赞:"+sNew.shares[jcopy].point_of_praise);
		$("#center_2_share"+i+"_bottom_4").attr("value","评论:"+sNew.shares[jcopy].comment_number);
	}
	for (i = kcopy+1; i <= 5; i++){
		$(".center_2_share"+i).css("display","none");
	}
}

//上页操作
function newPrePage() {
	page_all = parseInt(document.getElementById('center_2_page_now').innerHTML);
	page_all = page_all - 1;
	if (page_all < 1) {
		page_all = 1;
		return;
	}
	setNewShareUpdata();
}
// 下页操作
function newNextPage() {
	page_all = parseInt(document.getElementById('center_2_page_now').innerHTML);
	page_all = page_all + 1;
	if (page_all > newMaxPage) {
		page_all = newMaxPage;
		return;
	}
	setNewShareUpdata();
}

// 跳转设置
function newGo() {
	var temp = parseInt(document.getElementById("center_2_page_6").value);
	if (temp < 1 || temp > newMaxPage || isNaN(temp)) {
		document.getElementById("center_2_page_6").value = "";
	} else {
		page_all = temp;
		document.getElementById("center_2_page_6").value = "";
		setNewShareUpdata();
	}
}
//首页
function newFirstPage(){
	page_all = 1;
	setNewShareUpdata();
}
//尾页
function newLastPage(){
	page_all = newMaxPage;
	setNewShareUpdata();
}
// 登录

// 用户登录
function userLogin() {
	var temp = $("#right_top_user_name1").val();
	var temp1 = $("#right_top_user_pwd1").val();
	if (temp == "" || temp1 == "") {
		alert("输入不能为空");
		return;
	}
	ajaxDataLogin("user/login","mailbox",temp,"password",temp1,1);
}

function userExist() {
	var str = user.flag;
	var temp3 = $("#right_top_user_pwd1").val();
	if (str.match("failed")) {
		alert("请输入正确的邮箱或密码");
		$("#right_top_user_name1").attr("value","");
		$("#right_top_user_pwd1").attr("value","");
	} else {
		$("#right_top").load('index_signIn_user.html',function() {
			alert("登录成功！");
			if (user.head_portrait != null&&user.head_portrait != "") {
				$("#user_signin_top_img2").attr("src",user.head_portrait);
			}else{
				$("#user_signin_top_img2").attr("src","img/user.ico");
			}
			localStorageUser();
			localStorage.setItem("userpassword", temp3);
			document.getElementById("user_signin_center_id").innerHTML = user.nickname;
			document.getElementById("user_signin_bottom1_user_a_s").innerHTML = user.numsOfCollectionUser;
			document.getElementById("user_signin_bottom1_ushare_a_s").innerHTML = user.numsOfCollectionShare;
			document.getElementById("user_signin_bottom1_share_a_s").innerHTML = user.numsOfUserShare;
			$("#header_ul_li_signin").css("display","none");
		});
	}
}
// 管理员登录-点击登录
function adminLogin() {
	var temp = $("#right_top_admin_name1").val();
	var temp1 = $("#right_top_admin_pwd1").val();
	if (temp == "" || temp1 == "") {
		alert("输入不能为空");
		return;
	}else if(isNaN(temp)){
		alert("请输入正确的账户或密码");
		return;
	}else{
		ajaxDataLogin("Admin/login","id",temp,"pwd",temp1,2);
	}
}
function adminExist() {
	var str = admin.flag;
	var temp3 = $("#right_top_admin_pwd1").val();
	if (str.match("failed")) {
		alert("请输入正确的账户或密码");
		$("#right_top_admin_name1").attr("value","");
		$("#right_top_admin_pwd1").attr("value","");
	} else {
		$("#right_top").load('index_signIn_a.html',function() {
			alert("登录成功！");
			localStorageAdmin(); 
			localStorage.setItem("adminpassword", temp3); 
			document.getElementById("a_signin_center_id").innerHTML=localStorage.getItem("adminnickname") ;
			document.getElementById("a_signin_bottom1_userf_span").innerHTML = admin.numsOfBannedUser;
			document.getElementById("a_signin_bottom1_keyb_span").innerHTML = admin.numsOfBannedKeyWord;
			$("#header_ul_li_signin").css("display","none");
		});
	}
}
// 用户登录成功后退出登录
function userQuit() {
	user.flag = "failed";
	localStorage.setItem("userflag", "failed"); 
	$("#user_signin").load("index.jsp #right_top");
	$("#header_ul_li_signin").css("display","block");
};
//用户资料设置
function userData(){
	$("#user_signin").load("user_set.html",function(){
		if(localStorage.getItem("userhead_portrait")!=null&&localStorage.getItem("userhead_portrait")!="null"){
			$("#uset_form1_1_img").attr("src",localStorage.getItem("userhead_portrait"));
		}else{
			$("#uset_form1_1_img").attr("src","img/user.ico");
		}
		var s=$("#uset_form1_1_img").attr("src");
		$("#uset_nickname_input").val(localStorage.getItem("usernickname"));
		localStorage.setItem("usetphoto", "");
	});
}
//用户取消设置资料并返回
function usetBack(){
	$("#uset_main").load("index_signIn_user.html",function(){
		ajaxDataLogin("user/login","mailbox",localStorage.getItem("usermailbox"),"password",localStorage.getItem("userpassword"),6);
		localStorageUser();
		if (user.head_portrait != null&&user.head_portrait != "") {
			$("#user_signin_top_img2").attr("src",user.head_portrait);
		}else{
			$("#user_signin_top_img2").attr("src","img/user.ico");
		}
		document.getElementById("user_signin_center_id").innerHTML = user.nickname;
		document.getElementById("user_signin_bottom1_user_a_s").innerHTML = user.numsOfCollectionUser;
		document.getElementById("user_signin_bottom1_ushare_a_s").innerHTML = user.numsOfCollectionShare;
		document.getElementById("user_signin_bottom1_share_a_s").innerHTML = user.numsOfUserShare;

	});
}
//管理员登录成功后退出登录
function adminQuit() {
	admin.flag = "failed";
	localStorage.setItem("adminflag", "failed"); 
	$("#a_signin").load("index.jsp #right_top");
	$("#header_ul_li_signin").css("display","block");
};
//signIn.html
//登录操作
function userLogin2() {
	var temp = $("#signin_user").val();
	var temp1 = $("#signin_user_pwd").val();
	if (temp == "" || temp1 == "") {
		alert("输入不能为空");
		return;
	}
	ajaxDataLogin("user/login","mailbox",temp,"password",temp1,3);
}
function userExist2() {
	var str = user.flag;
	var temp3 = $("#signin_user_pwd").val();
	if (str.match("failed")) {
		alert("请输入正确的邮箱或密码");
		$("#signin_user").attr("value","");
		$("#signin_user_pwd").attr("value","");
	} else {
		localStorageUser();
		localStorage.setItem("userpassword", temp3); 
		window.location.href="index.jsp";
	}
}
//管理员登录
//管理员登录-点击登录
function adminLogin2() {
	var temp = $("#signin_admin").val();
	var temp1 = $("#signin_admin_pwd").val();
	if (temp == "" || temp1 == "") {
		alert("输入不能为空");
		return;
	}else if(isNaN(temp)){
		alert("请输入正确的账户和密码");
		return;
	}else{
		ajaxDataLogin("Admin/login","id",temp,"pwd",temp1,4);
	}
}
function adminExist2() {
	var str = admin.flag;
	var temp3 = $("#signin_admin_pwd").val();
	if (str.match("failed")) {
		alert("请输入正确的账户或密码");
		$("#signin_admin").attr("value","");
		$("#signin_admin_pwd").attr("value","");
	} else {
		window.location.href="index.jsp";
		localStorage.setItem("adminpassword", temp3); 
		localStorageAdmin();
		document.getElementById("a_signin_bottom1_userf_span").innerHTML = admin.numsOfBannedUser;
		document.getElementById("a_signin_bottom1_keyb_span").innerHTML = admin.numsOfBannedKeyWord;
		$("#header_ul_li_signin").css("display","none");
	}
}
// 切换至用户登录界面
function switch_user() {
	$("#right_top_header_signIn_user").css("color", "black");
	$("#right_top_header_signIn_a").css("color", "darkgray");
	$("#right_top_a").hide();
	$("#right_top_user").show();
}
// 切换至管理员登录界面
function switch_a() {
	$("#right_top_header_signIn_user").css("color", "darkgray");
	$("#right_top_header_signIn_a").css("color", "black");
	$("#right_top_user").hide();
	$("#right_top_a").show();
}
//user_set.html
//设置头像的操作
function setUSerHead() {
	var docObj = document.getElementById("usetpath");
	var panduan = docObj.files[0].name.substring(docObj.files[0].name.lastIndexOf(".") + 1).toLowerCase();
	var a = ["png", "jpg", "jpeg", "gif"];
	var temp = 0;
	for(var j = 0; j < a.length; j++) {
		if(panduan.match(a[j])) {
			temp = 1;
			break;
		}
	}
	if(temp == 0) {
		alert("请选择正确的图片格式");
		return false;
	}else if(docObj.files[0].name.indexOf("&")>-1){
		alert("该图片名含有非法字符'&'");
		return false;
	}
	var fileList = docObj.files;
	var imgObjPreview = document.getElementById("uset_form1_1_img");
	if(docObj.files && docObj.files[0]) {
		imgObjPreview.style.display = 'block';
		imgObjPreview.style.width = '100px';
		imgObjPreview.style.height = '100px';
		imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
	}
	localStorage.setItem("usetphoto", docObj.files[0].name);
	return true;
}
//user_set保存设置
function uSetSava(){
	if($("#uset_nickname_input").val()==""){
		alert("昵称不能为空");
		return false;
	}
	ajaxDataLogin("user/updateNickname","id",localStorage.getItem("userid"),"nickname",$("#uset_nickname_input").val(),6);
	if(localStorage.getItem("usetphoto")!=null&&localStorage.getItem("usetphoto")!=""){
		$.ajax({
			url : "http://localhost:8088/iShow//picture/saveUserPhoto.do?paths=upload/"+localStorage.getItem("usetphoto")+"&userid="+localStorage.getItem("userid"),
			type : "get",
			dataType : "json",
			async : false,
			success : function(data) {
			},
			error : function() {
				alert("error");
			}
		});
	}

	//	alert("保存成功");
//	usetBack();
	return true;
}
//register.html
//主函数
function mainRegister(){
	if(localStorage.getItem("userflag")=="success"||localStorage.getItem("adminflag")=="success"){
		document.getElementById("header_a13").innerHTML="";
		
	}else{
		document.getElementById("header_a13").innerHTML="登录";
		document.getElementById("header_a13").onclick=function(){
			window.location.href="signIn.html";
		}
	}
}
//检查输入
function check_register() {
	var mail = $("#mail1").val();
	var nickname =$("#nickname1").val();
	var password1 =$("#password1").val();
	var password2 =$("#password2").val();
	var filter = /^([a-zA-Z0-9_-])+\@([a-zA-Z0-9])+(\.com)$/;
	if (mail == "" || (!filter.test(mail))) {
		alert("邮箱格式输入不正确");
		return;
	}else if(nickname==""){
		alert("昵称输入不能为空");
		return;
	}else if(password1==""||password2==""||password1!=password2){
		alert("密码输入为空或两次密码输入不相同");
		return;
	}else{
		mailExist={};
		ajaxData2("user/findMailBox","mailbox",mail,12);
	
		if("true".match(mailExist.flag)){
			$("#mail1").val("");
			alert("该邮箱已经注册过");
			return;
		}else{
			$.ajax({
				url : "http://localhost:8088/iShow//user/addUser.do?",
				type : "get",
				data:{
					"nickname":nickname,
					"password":password1,
					"mailbox":mail,
					"jurisdiction":0,
					"head_portrait":"img/user.ico"
				},
				dataType : "json",
				async : false,
				success : function(data) {
				},
				error : function() {
					alert("error");
				}
			});
			$("#mail1").val("");
			$("#nickname1").val("");
			$("#password1").val("");
			$("#password2").val("");
			alert("注册成功");
		}
	}
}

// 分享
// 检验主界面上传分享的字数
function checkLen(obj) {
	var maxChars = 300; // 最多字符数
	if(obj.value.match("\n")){
		obj.value = obj.value.substring(0, obj.value.length-1);
	}else if (obj.value.length > maxChars){
		obj.value = obj.value.substring(0, maxChars);
	}
	var curr = maxChars - obj.value.length;
	document.getElementById("center_1_top_count").innerHTML = curr.toString();
}
// 用户点击图片按钮的操作
function setImagePreviews() {
	if (maxPhoto == 7) {
		alert("最多只能选择6张图片");
		return false;
	}
	var docObj = document.getElementById("path"+maxPhoto);
	var panduan = docObj.files[0].name.substring(docObj.files[0].name.lastIndexOf(".") + 1).toLowerCase();
	var a = [ "png", "jpg", "jpeg", "gif" ];
	var temp = 0;
	for (var j = 0; j < a.length; j++) {
		if (panduan.match(a[j])) {
			temp = 1;
			break;
		}
	}
	if (temp == 0) {
		alert("该图片不符合格式要求");
		return false;
	}else if(docObj.files[0].name.indexOf("&")>-1){
		alert("该图片名含有非法字符'&'");
		return false;
	}
	var dd = document.getElementById("center_1_bottom" + maxPhoto);
	var data=[{"photo":docObj.files[0].name}];
	maxPhotoPath.maxphotopath1.push(data);
	if (docObj.files && docObj.files[0]) {
		dd.src = window.URL.createObjectURL(docObj.files[0]);
	}
	$("#path"+maxPhoto).attr("name","files");
	maxPhoto++;
	if(maxPhoto==7){
		return false;
	}
	$("#photo_select").attr("onclick","path"+maxPhoto+".click()");
	return true;
}
// 用户点击清空按钮的操作
function clear1() {
	maxPhoto = 1;
	for (var i = 1; i < 7; i++) {
		$("#center_1_bottom" + i).attr("src","");
	}
	maxPhotoPath={"maxphotopath1":[]};
	for(var i=1;i<7;i++){
		$("#path"+i).attr("name","");
	}
	$("#photo_select").attr("onclick","path1.click()");
}
function savaShare(){
	if(localStorage.getItem("userflag")==null||localStorage.getItem("userflag")=="failed"){
		alert("请先进行用户登录");
		return false;
	}else if(localStorage.getItem("userjurisdiction")=="1"){
		alert("您已被管理员禁止进行分享活动");
		return false;
	}
	if(maxPhoto == 1){
		alert("请至少选择一张图片");
		return false;
	}else if(($("#center_1_center_area1").val().replace(/(^\s*)|(\s*$)/g, "")=="") ||$("#center_1_center_area1").val()==""){
		alert("描述不能为空");
		return false;
	}else{
		ajaxData("keyword/getAllKeyWord",4);
		if(keyword.keywords!=null&&keyword.keywords.length>0){
			for(var i=0;i<keyword.keywords.length;i++){
				if($("#center_1_center_area1").val().indexOf(keyword.keywords[i].key) > -1 ){
					alert("描述中包含非法字符:"+keyword.keywords[i].key);
					return false;
				}
			}
		}
		$.ajax({
			url : "http://localhost:8088/iShow//share/addShare.do?",
			type : "get",
			data:{
				"publisher_id":user.id,
				"describe":$("#center_1_center_area1").val(),
				"state":0
			},
			dataType : "json",
			async : false,
			success : function(data) {
			},
			error : function() {
				alert("error");
			}
		});
		allShare={};
		ajaxData("share/findShareByTime",5);
		for(var i=0;i<=maxPhoto-2;i++){
			$.ajax({
				url : "http://localhost:8088/iShow//picture/savePaths.do?paths=upload/"+maxPhotoPath.maxphotopath1[i][0].photo+"&shareId="+allShare.shares[0].share_id,
				type : "get",
				dataType : "json",
				async : false,
				success : function(data) {
				},
				error : function() {
					alert("error");
				}
			});
		}
		return true;
	}
}
//用户推荐
function change_1(){
	ajaxData2("user/recommend","id","6",1);
	for(var i=1;i<=recommend.list.length;i++){
		document.getElementById("right_bottom_2_1_"+i).innerHTML=recommend.list[i-1].nickname;
		$("#right_bottom_2_1_"+i).attr("data-user",recommend.list[i-1].user_id);		
	}
}
//a_set.html
//添加关键字
function addkeyword(){
	var key=$("#aset_change1_add_1").val();
	if(key==""){
		alert("关键字不能为空");
	}else{
		ajaxData("keyword/getAllKeyWord",4);
		for(var i=0;i<keyword.keywords.length;i++){
			if(keyword.keywords[i].key==key){
				alert("关键字已存在");
				return;
			}
		}
		ajaxData2("keyword/addKeyWord","wd",key,13);
		$("#aset_change1_add_1").val("");
		alert("插入成功");
		switch_fset();
	}
}
function userjurFunc(userjur){
	userjur.each(function(i,el){
		$(this).click(function(){
			if($(this).text().match("允许")){
				ajaxData2("Admin/banUser","userId",$(this).attr("data-userjur"),13);
				alert("该用户已被禁止进行分享活动");
				switch_uset();
			}else if($(this).text().match("禁止")){
				ajaxData2("Admin/allowUser","userId",$(this).attr("data-userjur"),13);
				alert("该用户已被允许进行分享活动");
				switch_uset();
			}
		})
	});
}
function deletekeywordFunc(deletekeyword){
	deletekeyword.each(function(i,el){
		$(this).click(function(){
			ajaxData2("keyword/deleteKeyWord","wd",$(this).attr("data-keyword"),13);
			alert("关键字删除成功");
			switch_fset();
		})
	});
}
//管理员退出
function aSetQuit1(){
	window.location.href="index.jsp";
	localStorage.setItem("adminflag", "failed");
}
function mainAdminSet(){
	ajaxDataLogin("Admin/login","id",localStorage.getItem("adminid"),"pwd",localStorage.getItem("adminpassword"),5);
	localStorageAdmin();
	document.getElementById("aset_top_a1").innerHTML=localStorage.getItem("adminnickname");
	switch_uset();
}
//a_set.html切换用户设置和关键字设置
function switch_uset() {
	$("#aset_nav_a1").css("color", "black");
	$("#aset_nav_a2").css("color", "gray");
	$(".aset_change1").hide();
	$(".aset_change").show();
	ajaxData("Admin/getAllUsers",3);
	pageAdmin=1;
	document.getElementById("aset_bottom_page_now").innerHTML=1;
	var temp4=banUser.users.length%10>0?1:0;
	newAdminMaxPage=parseInt(banUser.users.length/10+temp4);
	document.getElementById("aset_bottom_page_all").innerHTML=newAdminMaxPage;
	switch_uset_updata();
}
function switch_uset_updata(){
	var txt="";
	var i;
	var j;
	var k;
	if(document.getElementById("aset_nav_a1").style.color=="black"){		
		if(banUser!=null&&banUser.users.length>0){
			k=(pageAdmin-1)*10+1;
			$("#aset_text1").empty();
			for(i=k,j=1;j<=10&&i<=banUser.users.length;j++,i++){
				txt=txt+"<div class='aset_text1'>"+
					"<span>"+banUser.users[i-1].user_id+"</span>"+
					"</div><div class='aset_text2'>"+
					"<span>"+banUser.users[i-1].nickname+"</span></div>"+
					"<div class='aset_text3'><a href='#' class='userjur1' data-userjur="+banUser.users[i-1].user_id+">";
				if(banUser.users[i-1].jurisdiction=="0"){
					txt=txt+"允许</a></div>";
				}else{
					txt=txt+"禁止</a></div>";
				}
			}
		}
		$("#aset_text1").append(txt);
		userjur =$('.userjur1');
		userjurFunc(userjur);
	}else{
		if(keyword!=null&&keyword.keywords.length>0){
			k=(pageAdmin-1)*9+1;
			$("#aset_text_3").empty();
			for(i=k,j=1;j<=9&&i<=keyword.keywords.length;j++,i++){
				txt=txt+"<div class='aset_text_2_1'>"+
					"<span>"+keyword.keywords[i-1].key+"</span></div>"+
					"<div class='aset_text_2_1'>"+
					"<a href='#' data-keyword="+keyword.keywords[i-1].key+" class='keywordDelete')'>删除</a></div>";			
			}
		}
		$("#aset_text_3").append(txt);
		var deletekeyword =$('.keywordDelete');
		deletekeywordFunc(deletekeyword);
	}
}
function switch_fset() {
	$("#aset_nav_a1").css("color", "gray");
	$("#aset_nav_a2").css("color", "black");
	$(".aset_change").hide();
	$(".aset_change1").show();
	ajaxData("keyword/getAllKeyWord",4);
	pageAdmin=1;
	document.getElementById("aset_bottom_page_now").innerHTML=1;
	var temp4=keyword.keywords.length%9>0?1:0;
	newAdminMaxPage=parseInt(keyword.keywords.length/9+temp4);
	document.getElementById("aset_bottom_page_all").innerHTML=newAdminMaxPage;
	switch_uset_updata();
}
function switch_prePage(){
	pageAdmin = parseInt(document.getElementById('aset_bottom_page_now').innerHTML);
	pageAdmin = pageAdmin - 1;
	if (pageAdmin < 1) {
		pageAdmin = 1;
		return;
	}
	document.getElementById("aset_bottom_page_now").innerHTML = pageAdmin;
	switch_uset_updata();
}
function switch_nextPage(){
	pageAdmin = parseInt(document.getElementById('aset_bottom_page_now').innerHTML);
	pageAdmin = pageAdmin + 1;
	if (pageAdmin > newAdminMaxPage) {
		pageAdmin = newAdminMaxPage;
		return;
	}
	document.getElementById("aset_bottom_page_now").innerHTML = pageAdmin;
	switch_uset_updata();
}
function switch_firstPage(){
	pageAdmin = 1;
	document.getElementById("aset_bottom_page_now").innerHTML = 1;
	switch_uset_updata();
}
function switch_lastPage(){
	pageAdmin = newAdminMaxPage;
	document.getElementById("aset_bottom_page_now").innerHTML = pageAdmin;
	switch_uset_updata();
}
function switch_Go(){
	var temp = parseInt(document.getElementById("aset_bottom_page_6").value);
	if (temp < 1 || temp > newAdminMaxPage || isNaN(temp)) {
		document.getElementById("aset_bottom_page_6").value = "";
	} else {
		pageAdmin = temp;
		document.getElementById("aset_bottom_page_6").value = "";
		document.getElementById("aset_bottom_page_now").innerHTML = pageAdmin;
		switch_uset_updata();
	}
}
//userFrame.html
//用户已经登录
function userFrameIdExist(){
	if(user.flag=="success"){
		window.location.href="user_frame.html";
	}else{
		alert("请先进行用户登录");
	}
}
//退出
function userFrameQuit(){
	window.location.href="index.jsp";
	localStorage.setItem("userflag", "failed");
}
function mainUserFrame(){ 
	ajaxDataLogin("user/login","mailbox",localStorage.getItem("usermailbox"),"password",localStorage.getItem("userpassword"),6);
	localStorageUser();
	if(user.head_portrait!=null&&user.head_portrait!=""){
		$("#userf_data_head1").attr("src",user.head_portrait);
	}else{
		$("#userf_data_head1").attr("src","img/user.ico");
	}
	document.getElementById("userf_data_name1").innerHTML=user.nickname;
	switch_uf1();
}
function switch_ufdeal1(){
	$("#userf_body1").empty();
	ajaxData2("share/findUserSharesById","id",user.id,2);
	sUserInf ={"suserinf1":[]};
	for(var i=1;i<=sUser.shares.length;i++){
		ajaxData2("share/findShareById","id",sUser.shares[i-1].share_id,3);
	}
	var txt="";
	for(var i=1;i<=user.numsOfUserShare;i++){
		var date1=dateFormat(sUser.shares[i-1].release_time);
		txt=txt+"<div class='userf_top'><div class='userf_top_img'>";
		if(sUserInf.suserinf1[i-1].user.head_portrait!=null&&sUserInf.suserinf1[i-1].user.head_portrait!=""){
			txt=txt + "<img class='userf_top_img1' src="+sUserInf.suserinf1[i-1].user.head_portrait+" />";
		}else{
			txt=txt + "<img src='img/user.ico' class='userf_top_img1' />";
		}
		txt=txt+ "</div><span class='userf_top_a1' style='cursor:default;'>"+sUserInf.suserinf1[i-1].user.nickname+"</span>"+
			"<span class='userf_top_span'>"+date1+"</span></div>"+
			"<p class='userf_text'>"+sUserInf.suserinf1[i-1].share.describe+"</p>"+
			"<p class='userf_photo'>";
		for(var j=0;sUserInf.suserinf1[i-1].pictures!=null&&j<sUserInf.suserinf1[i-1].pictures.length;j++){
			txt=txt+"<img class='userf_photo1' src="+sUserInf.suserinf1[i-1].pictures[j].image_path+" />";
		}
		txt=txt+"</p><div class='userf_button'>";
		if(sUserInf.suserinf1[i-1].share.state=="0"){
			txt=txt+"<input type='button' class='userf_button_1 loadSkip_link' data-load="+sUserInf.suserinf1[i-1].share.share_id+" value='屏蔽分享' />";
		}else{
			txt=txt+"<input type='button' class='userf_button_1' value='解除屏蔽' onclick='userfBan()'/>";
		}
		txt=txt+"<input type='button' id='userf_button_2_set' class='userf_button_2 praiseSkip_link' value='点&nbsp;赞' data-share="+sUserInf.suserinf1[i-1].share.share_id+" />"+
			"<input type='button' id='userf_button_3_set' class='userf_button_3 praiseSkip_link' value='评&nbsp;论' data-share="+sUserInf.suserinf1[i-1].share.share_id+" />"+
			"</div>"
	}
	$(".userf_body").append(txt);
	rShare =$('.praiseSkip_link');
	rShareFunc(rShare);
	var loadS =$('.loadSkip_link');
	loadFunc(loadS);
}

function switch_ufdeal2(){
	$("#userf_body1").empty();
	ajaxData2("comment/getAllCommentsByUserId","id",user.id,2);
	sUserInf ={"suserinf1":[]};
	for(var i=1;i<=sUser.shares.length;i++){
		ajaxData2("share/findShareById","id",sUser.shares[i-1].share_id,3);
	}
	var txt="";
	for(var i=1;i<=sUserInf.suserinf1.length;i++){
		var date1=dateFormat(sUser.shares[i-1].release_time);
		txt=txt+"<div class='userf_top'><div class='userf_top_img'>";
		if(sUserInf.suserinf1[i-1].user.head_portrait!=null&&sUserInf.suserinf1[i-1].user.head_portrait!=""){
			txt=txt + "<img class='userf_top_img1' src="+sUserInf.suserinf1[i-1].user.head_portrait+" />";
		}else{
			txt=txt + "<img src='img/user.ico' class='userf_top_img1' />";
		}
		txt=txt + "</div><a href='#' class='userf_top_a1 userSkip_link' data-user="+sUserInf.suserinf1[i-1].user.user_id+" >"+sUserInf.suserinf1[i-1].user.nickname+"</a>"+
			"<span class='userf_top_span'>"+date1+"</span></div>"+
			"<p class='userf_text'>"+sUserInf.suserinf1[i-1].share.describe+"</p>"+
			"<p class='userf_photo'>";
		for(var j=0;sUserInf.suserinf1[i-1].pictures!=null&&j<sUserInf.suserinf1[i-1].pictures.length;j++){
			txt=txt+"<img class='userf_photo1' src="+sUserInf.suserinf1[i-1].pictures[j].image_path+" />";
		}
		txt=txt+"</p><div class='userf_button'>"+
			"<input type='button' id='userf_button_1_set' class='userf_button_1 praiseSkip_link' value='收&nbsp;藏' data-share="+sUserInf.suserinf1[i-1].share.share_id+" />"+
			"<input type='button' id='userf_button_2_set' class='userf_button_2 praiseSkip_link' value='点&nbsp;赞' data-share="+sUserInf.suserinf1[i-1].share.share_id+" />"+
			"<input type='button' id='userf_button_3_set' class='userf_button_3 praiseSkip_link' value='评&nbsp;论' data-share="+sUserInf.suserinf1[i-1].share.share_id+" />"+
			"</div>"
	}
	$(".userf_body").append(txt);
	otherUserSkip =$('.userSkip_link');
	otherUserSkipFunc(otherUserSkip);
	rShare =$('.praiseSkip_link');
	rShareFunc(rShare);
}
function switch_ufdeal3(){
	$("#userf_body1").empty();
	ajaxData2("PointPraise/getPointpraiseByUserId","id",user.id,2);
	sUserInf ={"suserinf1":[]};
	for(var i=1;i<=sUser.shares.length;i++){
		ajaxData2("share/findShareById","id",sUser.shares[i-1].share_id,3);
	}
	var txt="";
	for(var i=1;i<=sUserInf.suserinf1.length;i++){
		var date1=dateFormat(sUser.shares[i-1].release_time);
		txt=txt+"<div class='userf_top'><div class='userf_top_img'>";
		if(sUserInf.suserinf1[i-1].user.head_portrait!=null&&sUserInf.suserinf1[i-1].user.head_portrait!=""){
			txt=txt + "<img class='userf_top_img1' src="+sUserInf.suserinf1[i-1].user.head_portrait+" />";
		}else{
			txt=txt + "<img src='img/user.ico' class='userf_top_img1' />";
		}
		txt=txt + "</div><a href='#' class='userf_top_a1 userSkip_link' data-user="+sUserInf.suserinf1[i-1].user.user_id+" >"+sUserInf.suserinf1[i-1].user.nickname+"</a>"+
			"<span class='userf_top_span'>"+date1+"</span></div>"+
			"<p class='userf_text'>"+sUserInf.suserinf1[i-1].share.describe+"</p>"+
			"<p class='userf_photo'>";
		for(var j=0;sUserInf.suserinf1[i-1].pictures!=null&&j<sUserInf.suserinf1[i-1].pictures.length;j++){
			txt=txt+"<img class='userf_photo1' src="+sUserInf.suserinf1[i-1].pictures[j].image_path+" />";
		}
		txt=txt+"</p><div class='userf_button'>"+
			"<input type='button' id='userf_button_1_set' class='userf_button_1 praiseSkip_link' value='收&nbsp;藏' data-share="+sUserInf.suserinf1[i-1].share.share_id+" />"+
			"<input type='button' id='userf_button_2_set' class='userf_button_2 praiseSkip_link' value='点&nbsp;赞' data-share="+sUserInf.suserinf1[i-1].share.share_id+" />"+
			"<input type='button' id='userf_button_3_set' class='userf_button_3 praiseSkip_link' value='评&nbsp;论' data-share="+sUserInf.suserinf1[i-1].share.share_id+" />"+
			"</div>"
	}
	$(".userf_body").append(txt);
	otherUserSkip =$('.userSkip_link');
	otherUserSkipFunc(otherUserSkip);
	rShare =$('.praiseSkip_link');
	rShareFunc(rShare);
}
function switch_ufdeal4(){
	$("#userf_body1").empty();
	ajaxData2("CollectionShare/getCollectionShare","id",user.id,2);
	sUserInf ={"suserinf1":[]};
	for(var i=1;i<=sUser.shares.length;i++){
		ajaxData2("share/findShareById","id",sUser.shares[i-1].share_id,3);
	}
	var txt="";
	for(var i=1;i<=sUserInf.suserinf1.length;i++){
		var date1=dateFormat(sUser.shares[i-1].release_time);
		txt=txt+"<div class='userf_top'><div class='userf_top_img'>";
		if(sUserInf.suserinf1[i-1].user.head_portrait!=null&&sUserInf.suserinf1[i-1].user.head_portrait!=""){
			txt=txt + "<img class='userf_top_img1' src="+sUserInf.suserinf1[i-1].user.head_portrait+" />";
		}else{
			txt=txt + "<img src='img/user.ico' class='userf_top_img1' />";
		}
		txt=txt + "</div><a href='#' class='userf_top_a1 userSkip_link' data-user="+sUserInf.suserinf1[i-1].user.user_id+">"+sUserInf.suserinf1[i-1].user.nickname+"</a>"+
			"<span class='userf_top_span'>"+date1+"</span></div>"+
			"<p class='userf_text'>"+sUserInf.suserinf1[i-1].share.describe+"</p>"+
			"<p class='userf_photo'>";
		for(var j=0;sUserInf.suserinf1[i-1].pictures!=null&&j<sUserInf.suserinf1[i-1].pictures.length;j++){
			txt=txt+"<img class='userf_photo1' src="+sUserInf.suserinf1[i-1].pictures[j].image_path+" />";
		}
		txt=txt+"</p><div class='userf_button'>"+
			"<input type='button' id='userf_button_1_set' class='userf_button_4' value='收&nbsp;藏' disabled='disabled'/>"+
			"<input type='button' id='userf_button_2_set' class='userf_button_2 praiseSkip_link' value='点&nbsp;赞' data-share="+sUserInf.suserinf1[i-1].share.share_id+" />"+
			"<input type='button' id='userf_button_3_set' class='userf_button_3 praiseSkip_link' value='评&nbsp;论' data-share="+sUserInf.suserinf1[i-1].share.share_id+" />"+
			"</div>"
	}
	
	$(".userf_body").append(txt);
	otherUserSkip =$('.userSkip_link');
	otherUserSkipFunc(otherUserSkip);
	rShare =$('.praiseSkip_link');
	rShareFunc(rShare);
}
function switch_ufdeal5(){
	ajaxData2("CollectionUser/getCollectionUser","id",user.id,2);
	var txt="";
	$("#userf_body2_p1").empty();
	for(var i=1;i<=sUser.users.length;i++){
		txt=txt+"<a href='#' class='userf_body2_p_a userSkip_link' data-user="+sUser.users[i-1].user_id+">"+sUser.users[i-1].nickname+"</a>";
	}
	$("#userf_body2_p1").append(txt);
	otherUserSkip =$('.userSkip_link');
	otherUserSkipFunc(otherUserSkip);
}
//user_frame.html切换分享或用户
function switch_uf1() {
	$("#userf_main").css("height", "");
	$("#userf_menu1").css("color", "black");
	$("#userf_menu2").css("color", "gray");
	$("#userf_menu3").css("color", "gray");
	$("#userf_menu4").css("color", "gray");
	$("#userf_menu5").css("color", "gray");
	$(".userf_body2").hide();
	$(".userf_body").show();
	switch_ufdeal1();
	if($(document).height() < "770") {
		$("#userf_main").css("height", "770px");
	} 
}

function switch_uf2() {
	$("#userf_main").css("height", "");
	$("#userf_menu2").css("color", "black");
	$("#userf_menu1").css("color", "gray");
	$("#userf_menu3").css("color", "gray");
	$("#userf_menu4").css("color", "gray");
	$("#userf_menu5").css("color", "gray");
	$(".userf_body2").hide();
	$(".userf_body").show();
	switch_ufdeal2();
	if($(document).height() < "770") {
		$("#userf_main").css("height", "770px");
	} 
}

function switch_uf3() {
	$("#userf_main").css("height", "");
	$("#userf_menu3").css("color", "black");
	$("#userf_menu1").css("color", "gray");
	$("#userf_menu2").css("color", "gray");
	$("#userf_menu4").css("color", "gray");
	$("#userf_menu5").css("color", "gray");
	$(".userf_body2").hide();
	$(".userf_body").show();
	switch_ufdeal3();
	if($(document).height() < "770") {
		$("#userf_main").css("height", "770px");
	} 
}
function switch_uf4() {
	$("#userf_main").css("height", "");
	$("#userf_menu4").css("color", "black");
	$("#userf_menu1").css("color", "gray");
	$("#userf_menu2").css("color", "gray");
	$("#userf_menu3").css("color", "gray");
	$("#userf_menu5").css("color", "gray");
	$(".userf_body2").hide();
	$(".userf_body").show();
	switch_ufdeal4();
	if($(document).height() < "770") {
		$("#userf_main").css("height", "770px");
	} 
}
function switch_uf5() {
	$("#userf_main").css("height", "");
	$("#userf_menu5").css("color", "black");
	$("#userf_menu1").css("color", "gray");
	$("#userf_menu2").css("color", "gray");
	$("#userf_menu3").css("color", "gray");
	$("#userf_menu4").css("color", "gray");
	$(".userf_body").hide();
	$(".userf_body2").show();
	switch_ufdeal5();
	if($(document).height() < "770") {
		$("#userf_main").css("height", "770px");
	} 
}
//主界面搜索分享
function searchShare(){
	var temp=$(".header_input").val();
	if(temp==""||temp.replace(/(^\s*)|(\s*$)/g, "")==""){
		alert("输入内容不能为空");
		return;
	}else{
		temp=temp.replace(/(^\s*)|(\s*$)/g, "");
		localStorage.setItem("search",temp);
		window.location.href="search.html";
	}
}
//查询窗口加载好之后
function mainSearch(){
	ajaxData2("share/findShareByKey","key",localStorage.getItem("search"),4);
	quitOrSignIn("search_nav_a1","header_ul_li_4");
	if(sSearch.list==null||sSearch.list.length==0){
		$("#search_allbody").empty();
		var txt="<p style='text-align:center;font-size:23px'><b>未找到相关内容</b></p>";
		$("#search_allbody").append(txt);
	}else{
		sSearchInf ={"ssearchinf1":[]};
		for(var i=1;i<=sSearch.list.length;i++){
			ajaxData2("share/findShareById","id",sSearch.list[i-1].share_id,5);								
		}
		mainSearch1();
	}
	if($("#search_main").height() < "770") {
		$("#search_main").css("height", "770px");
	}
}
function mainSearch1(){
	var txt="";
	$("#search_body1").empty();
	for(var i=1;i<=sSearch.list.length;i++){
		var date1=dateFormat(sSearchInf.ssearchinf1[i-1].share.release_time);
		txt=txt+"<div class='search_top'><div class='search_top_img'>";
		if(sSearchInf.ssearchinf1[i-1].user.head_portrait!=null&&sSearchInf.ssearchinf1[i-1].user.head_portrait!=""){
			txt=txt+"<img src="+sSearchInf.ssearchinf1[i-1].user.head_portrait+" class='search_top_img1'/>";
		}else{
			txt=txt+"<img src='img/user.ico' class='search_top_img1'/>";
		}
		txt=txt+"</div><a href='#' class='search_top_a1 userSkip_link' id=search_top_a1_set"+i+" data-user="+sSearchInf.ssearchinf1[i-1].user.user_id+">"+sSearchInf.ssearchinf1[i-1].user.nickname+"</a>"+
			"<span class='search_top_span'>"+date1+"</span></div>"+
			"<p class='search_text'>"+sSearchInf.ssearchinf1[i-1].share.describe+"</p>"+
			"<p class='search_photo'>";
		if(sSearchInf.ssearchinf1[i-1].pictures!=null&&sSearchInf.ssearchinf1[i-1].pictures>0){
			for(var j=1;j<=sSearchInf.ssearchinf1[i-1].pictures.length;j++){
				txt=txt+"<img src="+sSearchInf.ssearchinf1[i-1].pictures[j].image_path+"class='search_photo1' />";
			}
		}
		txt=txt+"</p><div class='search_button'>";
		txt=txt+"<input type='button' class='search_button_1 praiseSkip_link' data-share="+sSearchInf.ssearchinf1[i-1].share.share_id+" value='收&nbsp;藏' />";
		txt=txt+"<input type='button' class='search_button_2 praiseSkip_link' data-share="+sSearchInf.ssearchinf1[i-1].share.share_id+" value='点&nbsp;赞' />";
		txt=txt+"<input type='button' class='search_button_3 praiseSkip_link' data-share="+sSearchInf.ssearchinf1[i-1].share.share_id+" value='评&nbsp;论' />";
		txt=txt+"</div>";
	}
	$("#search_body1").append(txt);
	otherUserSkip =$('.userSkip_link');
	otherUserSkipFunc(otherUserSkip);
	rShare =$('.praiseSkip_link');
	rShareFunc(rShare);
}
//share.html 分享设计
function mainShareFunction(){
	localStorage.setItem("replySkip","100000");
	ajaxData2("share/findShareById","id",localStorage.getItem("shareSkip"),6);
	quitOrSignIn("share_nav_a1","header_ul_li_5");
	if(sShare.flag=="true"){
		mainShareUpdata();
	}
	if($(document).height() < "770") {
		$("#share_main").css("height", "770px");
	}
}
function mainShareUpdata(){
	var txt="";
	$("#share_body1").empty();
	txt=txt+"<div class='share_top'><div class='share_top_img'>";
	if(sShare.user.head_portrait!=null&&sShare.user.head_portrait!=""){
		txt=txt+"<img src="+sShare.user.head_portrait+" id='share_top_img1'/>";
	}else{
		txt=txt+"<img src='img/user.ico' id='share_top_img1'/>";
	}
	var date1=dateFormat(sShare.share.release_time);
	txt=txt+"</div><a href='#' id='share_top_a1' class='userSkip_link share_comment_context1' data-user="+sShare.user.user_id+" >"+sShare.user.nickname+"</a>"+
		"<span id='share_top_span'>"+date1+"</span></div>"+
		"<p class='share_text'>"+sShare.share.describe+"</p>"+
		"<p class='share_photo'>";
	if(sShare.pictures!=null&&sShare.pictures.length>0){
		for(var i=0;i<sShare.pictures.length;i++){
			txt=txt+"<img src="+sShare.pictures[i].image_path+" id='share_photo1' />";
		}
	}
	txt=txt+"</p><div class='share_heart'>"+
		"<img src='img/heart.ico' id='share_heart_img' />"+
		"<p class='share_heart1'>";
	if(sShare.pointPraiseUsers!=null&&sShare.pointPraiseUsers.length>0){
		var j=0;
		for(j=0;j<sShare.pointPraiseUsers.length-1;j++){
			txt=txt+"<a href='#' id=share_pointPraiseUsers"+j+" class='userSkip_link share_comment_context' data-user="+sShare.pointPraiseUsers[j].user_id+">"+sShare.pointPraiseUsers[j].nickname+"</a>,";
		}
		txt=txt+"<a href='#' id=share_pointPraiseUsers"+j+" class='userSkip_link share_comment_context' data-user="+sShare.pointPraiseUsers[j].user_id+">"+sShare.pointPraiseUsers[j].nickname+"</a>";
	}
	txt=txt+"</p></div><div class='share_button'>"+
	"<input type='button' id='share_button_1' value='收&nbsp;藏' onclick='collectionShare1()' />"+
	"<input type='button' id='share_button_2' value='点&nbsp;赞' onclick='praiseShare1()' />"+
	"<input type='button' id='share_button_3' value='评&nbsp;论' onclick='click_scroll()' />"+
	"</div><p class='share_comment'>";
	if(sShare.comments!=null&&sShare.comments.length>0){
		for(var k=0;k<sShare.comments.length;k++){
			if(sShare.comments[k].be_commented_id!=null&&sShare.comments[k].be_commented_id!="100000"){
				ajaxData2("user/getUserById","id",sShare.comments[k].user_id,7);
				ajaxData2("user/getUserById","id",sShare.comments[k].be_commented_id,8);
				txt=txt+"<a href='#' data-user="+comname.user.user_id+" class='userSkip_link share_comment_context1' id=share_comment_a_1"+k+">"+comname.user.nickname+"</a>"+
					"<span>&nbsp;回复&nbsp;</span>"+
					"<a href='#' data-user="+becomname.user.user_id+" class='userSkip_link share_comment_context1' id=share_comment_a_2"+k+">"+becomname.user.nickname+"</a>"+
					"<span>:</span><br/>"+
					"<a href='#' data-replyid="+comname.user.user_id+" data-replyname="+comname.user.nickname+" class='share_comment_context replySkip_link' id=share_comment_a_3"+k+">&nbsp;"+sShare.comments[k].comment_content+"</a>"+
					"<br/>";
			}else{
				ajaxData2("user/getUserById","id",sShare.comments[k].user_id,7);
				txt=txt+"<a href='#' data-user="+comname.user.user_id+" class='userSkip_link share_comment_context1' id=share_comment_a_1"+k+">"+comname.user.nickname+"</a>"+
					"<span>&nbsp;评论</span>"+
					"<span>:</span><br/>"+
					"<a data-replyid="+comname.user.user_id+" data-replyname="+comname.user.nickname+" href='#' class='share_comment_context replySkip_link' id=share_comment_a_3"+k+">&nbsp;"+sShare.comments[k].comment_content+"</a>"+
					"<br/>";
			}
		}
	}
	txt=txt+"</p>";
	$("#share_body1").append(txt);
	beCollectionShare1();
	bePraiseShare1();
	bePraiseShare1();
	var reply =$('.replySkip_link');
	replyFunc(reply);
	otherUserSkip =$('.userSkip_link');
	otherUserSkipFunc(otherUserSkip);
}
function click_scroll() {
	document.getElementById("anchor_scroll").click();
}
function beCollectionShare1(){
	if(localStorage.getItem("userflag")!=null&&localStorage.getItem("userflag")=="success"){
		$.ajax({
			url : "http://localhost:8088/iShow//CollectionShare/getCollectionShare.do?id="+localStorage.getItem("userid"),
			type : "get",
			dataType : "json",
			async : false,
			success : function(data) {
				var temp2 = JSON.stringify(data);
				colShare = eval('(' + temp2 + ')');
			
			},
			error : function() {
				alert("error");
			}
		});
		if(colShare.shares!=null&&colShare.shares.length>0){
			for(var i=0;i<colShare.shares.length;i++){
				if(colShare.shares[i].share_id==localStorage.getItem("shareSkip")){
					$("#share_button_1").attr("disabled","disabled");
					$("#share_button_1").attr("id","share_button_4");
					return;
				}
			}
		}
	}
}
function bePraiseShare1(){
	if(localStorage.getItem("userflag")!=null&&localStorage.getItem("userflag")=="success"){
		if(sShare.pointPraiseUsers!=null&&sShare.pointPraiseUsers.length>0){
			for(var i=0;i<sShare.pointPraiseUsers.length;i++){
				if(sShare.pointPraiseUsers[i].user_id==localStorage.getItem("userid")){
					$("#share_button_2").attr("disabled","disabled");
					$("#share_button_2").attr("id","share_button_4");
					return;
				}
			}
		}
	}
}
function collectionShare1(){
	if(localStorage.getItem("userflag")==null||localStorage.getItem("userflag")=="failed"){
		alert("请先进行用户登录");
		return;
	}else if(localStorage.getItem("userjurisdiction")=="1"){
		alert("您已被管理员禁止进行分享活动");
		return;
	}else{
		$.ajax({
			url : "http://localhost:8088/iShow//CollectionShare/collectShare.do?user_id="+localStorage.getItem("userid")+"&share_id="+localStorage.getItem("shareSkip"),
			type : "get",
			dataType : "json",
			async : false,
			success : function(data) {
			},
			error : function() {
				alert("error");
			}
		});
		$("#share_button_1").attr("disabled","disabled");
		$("#share_button_1").attr("id","share_button_4");
		alert("分享收藏成功");
		mainShareUpdata();
	}
}
function praiseShare1(){
	if(localStorage.getItem("userflag")==null||localStorage.getItem("userflag")=="failed"){
		alert("请先进行用户登录");
		return;
	}else if(localStorage.getItem("userjurisdiction")=="1"){
		alert("您已被管理员禁止进行分享活动");
		return;
	}else{
		$.ajax({
			url : "http://localhost:8088/iShow//PointPraise/addPointPraise.do?userId="+localStorage.getItem("userid")+"&shareId="+localStorage.getItem("shareSkip"),
			type : "get",
			dataType : "json",
			async : false,
			success : function(data) {
			},
			error : function() {
				alert("error");
			}
		});
		$("#share_button_2").attr("disabled","disabled");
		$("#share_button_2").attr("id","share_button_4");
		mainShareFunction();
	}
}
function savaComment(){
	if(localStorage.getItem("userflag")==null||localStorage.getItem("userflag")=="failed"){
		alert("请先进行用户登录");
		return;
	}else if(localStorage.getItem("userjurisdiction")=="1"){
		alert("您已被管理员禁止进行分享活动");
		return;
	}
	if($("#share_reply_textarea1").val().replace(/(^\s*)|(\s*$)/g, "")==""){
		alert("评论内容不能为空");
		return;
	}else{
//		var date1=new Date().getTime();
		$.ajax({
			url : "http://localhost:8088/iShow/comment/addComment.do?",
			type : "get",
			data:{
				"share_id":localStorage.getItem("shareSkip"),
				"user_id":localStorage.getItem("userid"),
				"be_commented_id":localStorage.getItem("replySkip"),
				"comment_content":$("#share_reply_textarea1").val()
				//"comment_time":date1
			},
			dataType : "json",
			async : false,
			success : function(data) {
			},
			error : function() {
				alert("error");
			}
		});
		alert("评论成功");
		mainShareFunction();
	}
}
//share.html判断字符数
function checkLen1(obj) {
	var maxChars = 100; //最多字符数 
	if(obj.value.match("\n")){
		obj.value = obj.value.substring(0, obj.value.length-1);
	}else if (obj.value.length > maxChars){
		obj.value = obj.value.substring(0, maxChars);
	}
	var curr = maxChars - obj.value.length;
	document.getElementById("share_reply_top1_count").innerHTML = curr.toString();
}
//其他用户界面设置
function mainOtherUserFunc(){
	quitOrSignIn("otheru_nav_a1","header_a12");
	ajaxData2("user/getUserById","id",localStorage.getItem("otheruserid"),9);
	if(otherUserHead.user.head_portrait!=null&&otherUserHead.user.head_portrait!=""){
		$("#otheru_data_head1").attr("src",otherUserHead.user.head_portrait);
	}else{
		$("#otheru_data_head1").attr("src","img/user.ico");
	}
	document.getElementById("otheru_data_name1").innerHTML=otherUserHead.user.nickname;
	ajaxData2("share/findUserSharesById","id",localStorage.getItem("otheruserid"),10);
	var txt="";
	$("#otheru_body1").empty();
	otherUserInf ={"otheruserinf1":[]};
	if(otherUser.shares!=null&&otherUser.shares.length>0){
		for(var i=0;i<otherUser.shares.length;i++){
			ajaxData2("share/findShareById","id",otherUser.shares[i].share_id,11);
			txt=txt+"<div class='otheru_top'><div class='otheru_top_img'>";
			if(otherUserHead.user.head_portrait!=null&&otherUserHead.user.head_portrait!=""){
				txt=txt+"<img src="+otherUserHead.user.head_portrait+" class='otheru_top_img1'/>";
			}else{
				txt=txt+"<img src='img/user.ico' class='otheru_top_img1'/>";
			}
			txt=txt+"</div><a href='#' class='otheru_top_a1'>"+otherUserHead.user.nickname+"</a><span class='otheru_top_span'";
			var date1=dateFormat(otherUser.shares[i].release_time);
			txt=txt+date1+"</span></div>"+
				"<p class='otheru_text'>"+otherUser.shares[i].describe+"</p>"+
				"<p class='otheru_photo'>";
			if(otherUserInf.otheruserinf1[i].pictures!=null&&otherUserInf.otheruserinf1[i].pictures.length>0){
				for(var j=0;j<otherUserInf.otheruserinf1[i].pictures.length;j++){
					txt=txt+"<img src="+ otherUserInf.otheruserinf1[i].pictures[j].image_path+" class='otheru_photo1' />";
				}
			}
			txt=txt+"</p><div class='otheru_button'>"+
				"<input type='button' class='otheru_button_1 praiseSkip_link' value='收&nbsp;藏' data-share="+otherUser.shares[i].share_id+" />"+
				"<input type='button' class='otheru_button_2 praiseSkip_link' value='点&nbsp;赞' data-share="+otherUser.shares[i].share_id+" />"+
				"<input type='button' class='otheru_button_3 praiseSkip_link' value='评&nbsp;论' data-share="+otherUser.shares[i].share_id+" />"+
				"</div>";
		}
	}
	$("#otheru_body1").append(txt);
	otherUserCollection();
	rShare =$('.praiseSkip_link');
	rShareFunc(rShare);
	if($(document).height() < "770") {
		$("#otheru_main").css("height", "770px");
	}
}
//判断是否关注过
function otherUserCollection(){
	ajaxData2("CollectionUser/getCollectionUser","id",localStorage.getItem("userid"),14);
	if(colUser.users==null||colUser.users.length==0){
		return;
	}else{
		for(var i=0;i<colUser.users.length;i++){
			if(localStorage.getItem("otheruserid")==colUser.users[i].user_id){
				$("#otheru_data_but1").attr("disabled","disabled");
				$("#otheru_data_but1").attr("id","otheru_data_but2");
				return;
			}
		}
		return;
	}
}
function addUserCollection(){
	$.ajax({
		url : "http://localhost:8088/iShow//CollectionUser/collectUser.do?",
		type : "get",
		data :{
			"user_id":localStorage.getItem("userid"),
			"collect_id":localStorage.getItem("otheruserid"),
		},
		dataType : "json",
		async : false,
		success : function(data) {
		},
		error : function() {
			alert("error");
		}
	});
	alert("用户已加入收藏列表");
	otherUserCollection();
}
//在主界面加载好后的操作
function mainfunction() {
	change_1();
	if(localStorage.getItem("userflag")=="success"){
		ajaxDataLogin("user/login","mailbox",localStorage.getItem("usermailbox"),"password",localStorage.getItem("userpassword"),6);
		localStorageUser();
		$("#right_top").load('index_signIn_user.html',function() {
			if (user.head_portrait != null&&user.head_portrait != "") {
				$("#user_signin_top_img2").attr("src", localStorage.getItem("userhead_portrait"));
			}else{
				$("#user_signin_top_img2").attr("src","img/user.ico");
			}
			document.getElementById("user_signin_center_id").innerHTML = localStorage.getItem("usernickname");
			document.getElementById("user_signin_bottom1_user_a_s").innerHTML =localStorage.getItem("usernumsOfCollectionUser");
			document.getElementById("user_signin_bottom1_ushare_a_s").innerHTML = localStorage.getItem("usernumsOfCollectionShare");
			document.getElementById("user_signin_bottom1_share_a_s").innerHTML =localStorage.getItem("usernumsOfUserShare") ;
			$("#header_ul_li_signin").css("display","none");
		});
	}
	if(localStorage.getItem("adminflag")=="success"){
		ajaxDataLogin("Admin/login","id",localStorage.getItem("adminid"),"pwd",localStorage.getItem("adminpassword"),5);
		localStorageAdmin(); 
		$("#right_top").load('index_signIn_a.html',function() {
			document.getElementById("a_signin_center_id").innerHTML=localStorage.getItem("adminnickname") ;
			document.getElementById("a_signin_bottom1_userf_span").innerHTML =localStorage.getItem("adminnumsOfBannedUser") ;
			document.getElementById("a_signin_bottom1_keyb_span").innerHTML =localStorage.getItem("adminnumsOfBannedKeyWord") ;
			$("#header_ul_li_signin").css("display","none");
		});
	}
	newShareUpdata();
	otherUserSkip =$('.userSkip_link');
	otherUserSkipFunc(otherUserSkip);
	rShare =$('.praiseSkip_link');
	rShareFunc(rShare);
}
