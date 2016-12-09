<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<title>iShow</title>
		<link rel="stylesheet" type="text/css" href="css/style.css"/>
		<script type="text/javascript" src="js/jquery-1.12.4.js" ></script>
	</head>

	<body onload = mainfunction()>
		<div class="main">
			<div class="header">
				<div class="header_1">
					<a href="index.jsp"><img src="img/ishow.png" class="header_logo"/></a>
					<input type="text" maxlength="23" spellcheck="false" placeholder="大家都在搜：铃兰丶物语" class="header_input"/>
					<img src="img/search.png" onclick="searchShare()" class="header_search cursor_pointer"/>
					<ul class="header_ul">
						<li class="header_ul_li"><a href="index.jsp" class="header_a a_color a_textdecoration"><img src="img/home.ico"/>首页</a></li>
						<li class="header_ul_li"><a href="#" class="header_a a_color a_textdecoration"><img src="img/heart.ico"/>点赞榜</a>
							<ul class="header_ul_2">
								<li class="header_ul_2_li"><a href="#" class="header_a a_color a_textdecoration" onclick="getPra_today()">今日点赞榜</a></li>
								<li class="header_ul_2_li"><a href="#" class="header_a a_color a_textdecoration" onclick="getPra_week()">一周点赞榜</a></li>
								<li class="header_ul_2_li"><a href="#" class="header_a a_color a_textdecoration" onclick="getPra_all()">全部点赞榜</a></li>
							</ul>
						</li>
						<li class="header_ul_li"><a href="#" onclick="newShareUpdata()" class="header_a a_color a_textdecoration"><img src="img/new.ico">最新分享</a></li>
						<li class="header_ul_li header_ul_li_3"><a href="register.html" class="header_a a_color a_textdecoration">注册</a></li>
						<li class="header_ul_li header_ul_li_2">|</li>
						<li class="header_ul_li header_ul_li_2" id="header_ul_li_signin"><a href="signIn.html" class="header_a a_color a_textdecoration">登录</a></li>
					</ul>
				</div>
			</div>
			<div class="content">
				<div class="left">
					<ul class="left_ul">
						<li class="left_ul_li left_ul_li2"><a href="#center_1_center_area1"><img src="img/glyphicons-416-disk-open.png">发布分享</a></li>
						<li class="left_ul_li"><a href="#"><img src="img/glyphicons-28-search.png">查看分享</a>
							<ul class="left_ul_2">
								<li class="left_ul_li"><a href="#" onclick="userFrameIdExist()"><img src="img/glyphicons-4-user.png">自己的分享</a></li>
								<li class="left_ul_li"><a href="#" onclick="userFrameIdExist()"><img src="img/glyphicons-13-heart.png">点赞的分享</a></li>
								<li class="left_ul_li"><a href="#" onclick="userFrameIdExist()"><img src="img/glyphicons-151-edit.png">评论的分享</a></li>
							</ul>
						</li>
						<li class="left_ul_li"><a href="#"><img src="img/glyphicons-31-pencil.png">收藏记录</a>
							<ul class="left_ul_2">
								<li class="left_ul_li"><a href="#" onclick="userFrameIdExist()"><img src="img/glyphicons-89-address-book.png">收藏的用户</a></li>
								<li class="left_ul_li"><a href="#" onclick="userFrameIdExist()"><img src="img/glyphicons-88-log-book.png">收藏的分享</a></li>
							</ul>
						</li>
						<li class="left_ul_li"><a href="#" onclick="userFrameIdExist()"><img src="img/glyphicons-505-alert.png">屏蔽分享</a></li>
						
					</ul>
				</div>
				<div class="center">
					<div class="center_1">
						<div class="center_1_top">
							<p>
								<span id="center_1_top_left" class="cursor_default">有什么新鲜事想告诉大家？</span>
								<span class="center_1_top_right cursor_default">
								还可以输入
								<span id="center_1_top_count" class="cursor_default">300</span>									
								字
								</span>
							</p>
						</div>
						<div class="center_1_center">
							<textarea placeholder="输入描述" spellcheck="false" class="center_1_center_area" id="center_1_center_area1" onpaste="return false" onkeyup="checkLen(this)"></textarea>
						</div>
						<div class="center_1_bottom">
							<div class="center_1_bottom_div"><img src="" id="center_1_bottom1"/></div>
							<div class="center_1_bottom_div"><img src="" id="center_1_bottom2"/></div>
							<div class="center_1_bottom_div"><img src="" id="center_1_bottom3"/></div>
							<div class="center_1_bottom_div"><img src="" id="center_1_bottom4"/></div>
							<div class="center_1_bottom_div"><img src="" id="center_1_bottom5"/></div>
							<div class="center_1_bottom_div"><img src="" id="center_1_bottom6"/></div>
							<form method="post" onsubmit="return savaShare()" action="picture/upload.do" enctype="multipart/form-data">										
				
								<input type="button" id="photo_select" class="cursor_pointer" value="图片" onclick="path1.click()" >
								<input type="file" id="path1" class="ima" style="display:none" onchange="setImagePreviews()" accept="image/*">
								<input type="file" id="path2" class="ima" style="display:none" onchange="setImagePreviews()" accept="image/*">
								<input type="file" id="path3" class="ima" style="display:none" onchange="setImagePreviews()" accept="image/*">
								<input type="file" id="path4" class="ima" style="display:none" onchange="setImagePreviews()" accept="image/*">
								<input type="file" id="path5" class="ima" style="display:none" onchange="setImagePreviews()" accept="image/*">
								<input type="file" id="path6" class="ima" style="display:none" onchange="setImagePreviews()" accept="image/*">
								<input type="button" id="photo_clear" class="cursor_pointer" value="清空" onclick="clear1()"/>
								<input type="submit" id="photo_submit" class="cursor_pointer" value="发&nbsp;布" />
							</form>
						</div>
					</div>
					<div class="center_2">
						<div class="center_2_share">
							<div class="center_2_share1">
								<div class="center_2_share1_top">
									<div class="center_2_share1_top_img1">
										<img src="img/user.ico" id="center_2_share1_top_img"/>
									</div>
									<a href="#" class="a_color a_textdecoration userSkip_link" id="center_2_share1_top_4"><span id="center_2_share1_top_2" ></span></a>
									<span id="center_2_share1_top_3" class="cursor_default" ></span>
								</div>
								<div class="center_2_share1_center1">
									<p class="center_2_share1_center1_p" id="center_2_share1_center1_p1"></p>
								</div>
								<div class="center_2_share1_center2">
									<img src="" id="center_2_share1_center2_1"/>
									<img src="" id="center_2_share1_center2_2"/>
									<img src="" id="center_2_share1_center2_3"/>
									<img src="" id="center_2_share1_center2_4"/>
									<img src="" id="center_2_share1_center2_5"/>
									<img src="" id="center_2_share1_center2_6"/>
								</div>
								<div class="center_2_share1_bottom">
									<input type="button" id="center_2_share1_bottom_1" class="cursor_pointer userSkip_link" value="收藏用户" />
									<input type="button" id="center_2_share1_bottom_2" class="cursor_pointer praiseSkip_link" value="收藏分享" />
									<input type="button" id="center_2_share1_bottom_3" class="cursor_pointer praiseSkip_link" value="点赞" />
									<input type="button" id="center_2_share1_bottom_4" class="cursor_pointer praiseSkip_link" value="评论" />
								</div>
							</div>
							<div class="center_2_share2">
								<div class="center_2_share1_top">
									<div class="center_2_share2_top_img1">
										<img src="img/user.ico" id="center_2_share2_top_img"/>
									</div>
									<a href="#" class="a_color a_textdecoration userSkip_link" id="center_2_share2_top_4"><span id="center_2_share2_top_2"></span></a>
									<span id="center_2_share2_top_3"  class="cursor_default"></span>
								</div>
								<div class="center_2_share2_center1">
									<p class="center_2_share1_center1_p" id="center_2_share1_center1_p2"></p>
								</div>
								<div class="center_2_share2_center2">
									<img src="" id="center_2_share2_center2_1"/>
									<img src="" id="center_2_share2_center2_2"/>
									<img src="" id="center_2_share2_center2_3"/>
									<img src="" id="center_2_share2_center2_4"/>
									<img src="" id="center_2_share2_center2_5"/>
									<img src="" id="center_2_share2_center2_6"/>
								</div>
								<div class="center_2_share2_bottom">
									<input type="button" id="center_2_share2_bottom_1" class="cursor_pointer userSkip_link" value="收藏用户" />
									<input type="button" id="center_2_share2_bottom_2" class="cursor_pointer praiseSkip_link" value="收藏分享" />
									<input type="button" id="center_2_share2_bottom_3" class="cursor_pointer praiseSkip_link" value="点赞" />
									<input type="button" id="center_2_share2_bottom_4" class="cursor_pointer praiseSkip_link" value="评论" />
								</div>
							</div>
							<div class="center_2_share3">
								<div class="center_2_share3_top">
									<div class="center_2_share3_top_img1">
										<img src="img/user.ico" id="center_2_share3_top_img"/>
									</div>
									<a href="#" class="a_color a_textdecoration userSkip_link" id="center_2_share3_top_4"><span id="center_2_share3_top_2"></span></a>
									<span id="center_2_share3_top_3"  class="cursor_default"></span>
								</div>
								<div class="center_2_share3_center1">
									<p class="center_2_share1_center1_p" id="center_2_share1_center1_p3"></p>
								</div>
								<div class="center_2_share3_center2">
									<img src="" id="center_2_share3_center2_1"/>
									<img src="" id="center_2_share3_center2_2"/>
									<img src="" id="center_2_share3_center2_3"/>
									<img src="" id="center_2_share3_center2_4"/>
									<img src="" id="center_2_share3_center2_5"/>
									<img src="" id="center_2_share3_center2_6"/>
								</div>
								<div class="center_2_share3_bottom">
									<input type="button" id="center_2_share3_bottom_1" class="userSkip_link cursor_pointer" value="收藏用户" />
									<input type="button" id="center_2_share3_bottom_2" class="cursor_pointer praiseSkip_link" value="收藏分享" />
									<input type="button" id="center_2_share3_bottom_3" class="cursor_pointer praiseSkip_link" value="点赞" />
									<input type="button" id="center_2_share3_bottom_4" class="cursor_pointer praiseSkip_link" value="评论" />
								</div>
							</div>
							<div class="center_2_share4">
								<div class="center_2_share4_top">
									<div class="center_2_share4_top_img1">
										<img src="img/user.ico" id="center_2_share4_top_img"/>
									</div>
									<a href="#" class="a_color a_textdecoration userSkip_link" id="center_2_share4_top_4"><span id="center_2_share4_top_2"></span></a>
									<span id="center_2_share4_top_3"  class="cursor_default"></span>
								</div>
								<div class="center_2_share4_center1">
									<p class="center_2_share1_center1_p" id="center_2_share1_center1_p4"></p>
								</div>
								<div class="center_2_share4_center2">
									<img src="" id="center_2_share4_center2_1"/>
									<img src="" id="center_2_share4_center2_2"/>
									<img src="" id="center_2_share4_center2_3"/>
									<img src="" id="center_2_share4_center2_4"/>
									<img src="" id="center_2_share4_center2_5"/>
									<img src="" id="center_2_share4_center2_6"/>
								</div>
								<div class="center_2_share4_bottom">
									<input type="button" id="center_2_share4_bottom_1" class="cursor_pointer userSkip_link" value="收藏用户" />
									<input type="button" id="center_2_share4_bottom_2" class="cursor_pointer praiseSkip_link" value="收藏分享" />
									<input type="button" id="center_2_share4_bottom_3" class="cursor_pointer praiseSkip_link" value="点赞" />
									<input type="button" id="center_2_share4_bottom_4" class="cursor_pointer praiseSkip_link" value="评论" />
								</div>
							</div>
							<div class="center_2_share5">
								<div class="center_2_share5_top">
									<div class="center_2_share5_top_img1">
										<img src="img/user.ico" id="center_2_share5_top_img"/>
									</div>
									<a href="#" class="a_color a_textdecoration userSkip_link" id="center_2_share5_top_4"><span id="center_2_share5_top_2"></span></a>
									<span id="center_2_share5_top_3" class="cursor_default" ></span>
								</div>
								<div class="center_2_share5_center1">
									<p class="center_2_share1_center1_p" id="center_2_share1_center1_p5"></p>
								</div>
								<div class="center_2_share5_center2">
									<img src="" id="center_2_share5_center2_1"/>
									<img src="" id="center_2_share5_center2_2"/>
									<img src="" id="center_2_share5_center2_3"/>
									<img src="" id="center_2_share5_center2_4"/>
									<img src="" id="center_2_share5_center2_5"/>
									<img src="" id="center_2_share5_center2_6"/>
								</div>
								<div class="center_2_share5_bottom">
									<input type="button" id="center_2_share5_bottom_1" class="cursor_pointer userSkip_link" value="收藏用户" />
									<input type="button" id="center_2_share5_bottom_2" class="cursor_pointer praiseSkip_link" value="收藏分享" />
									<input type="button" id="center_2_share5_bottom_3" class="cursor_pointer praiseSkip_link" value="点赞" />
									<input type="button" id="center_2_share5_bottom_4" class="cursor_pointer praiseSkip_link" value="评论" />
								</div>
							</div>
						</div>
						<div class="center_2_page">
							<input type="button" id="center_2_page_1" class="cursor_pointer" onclick="newFirstPage()" value="首页"/>
							<input type="button" id="center_2_page_2" class="cursor_pointer" onclick="newPrePage()" value="上页"/>
							<input type="button" id="center_2_page_3" class="cursor_pointer" onclick="newNextPage()" value="下页"/>
							<input type="button" id="center_2_page_4" class="cursor_pointer" onclick="newLastPage()" value="尾页"/>
							<span class="center_2_page_span cursor_default">第</span>
							<span id="center_2_page_now" class="cursor_default">1</span>
							<span class="center_2_page_span cursor_default">页</span>
							<span class="center_2_page_span cursor_default">/</span>
							<span class="center_2_page_span cursor_default">共</span>
							<span id="center_2_page_all" class=" cursor_default">1</span>
							<span class="center_2_page_span cursor_default">页</span>
							<input type="text" id="center_2_page_6" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" placeholder="输入页数"/>
							<input type="button" id="center_2_page_5" class="cursor_pointer" onclick="newGo()" value="跳转"/>
						</div>
					</div>
				</div>
				<div class="right">
					<div id="right_top">
						<div class="right_top_header_signIn">
							<a id="right_top_header_signIn_user" class="a_color a_textdecoration" title="切换至用户登录" onclick="switch_user()" href="#">用户登录</a>
							<span>|</span>
							<a id="right_top_header_signIn_a" class="a_textdecoration" title="切换至管理员登录" onclick="switch_a()" href="#">管理员登录</a>
						</div>
						<form id="right_top_user">
							<div class="right_top_2">
								<input type="text" maxlength="30" id="right_top_user_name1" class="right_top_user_name" onkeyup="this.value=this.value.replace(/^ +| +$/g,'')" onpaste="return false" spellcheck="false" placeholder="请输入用户邮箱"/>
								<img src="img/user.ico" class="right_top_2_name"/>		
							</div>
							<div class="right_top_3">
								<input type="password"	maxlength="20" id="right_top_user_pwd1" class="right_top_user_pwd" onpaste="return false" placeholder="请输入用户密码"/><br />	
								<img src="img/lock.ico" class="right_top_3_lock"/>
							</div>
							<input type="checkbox" id="ck1" class="right_top_remember_me" checked="checked"/><label for="ck1" id="ck3" class="cursor_pointer">记住我</label>
							<a class="right_top_find_pwd a_color" href="#">忘记密码？</a>
							<input type="button" value="登&nbsp录" class="right_top_user_submit cursor_pointer" onclick="userLogin()"/>	
							<a class="right_top_register" href="register.html">还没账号？立即注册</a>
						</form>
						<form id="right_top_a" >
							<div class="right_top_2">
								<input type="text" maxlength="5" onkeyup="this.value=this.value.replace(/\D/g,'')" onpaste="return false"  id="right_top_admin_name1" class="right_top_user_name" placeholder="请输入管理员账号"/>
								<img src="img/user.ico" class="right_top_2_name"/>								
							</div>
							<div class="right_top_3">
								<input type="password"	maxlength="20" id="right_top_admin_pwd1" class="right_top_user_pwd" onpaste="return false" placeholder="请输入管理员密码"/><br />	
								<img src="img/lock.ico" class="right_top_3_lock"/>
							</div>
							<input type="checkbox" class="right_top_remember_me2 cursor_no_drop" disabled="disabled"/><label id="ck2" class="cursor_no_drop"><s>记住我</s></label>
							<a class="right_top_find_pwd a_color" href="#">忘记密码？</a>
							<input type="button" value="登&nbsp录" class="right_top_a_submit cursor_pointer" onclick="adminLogin()"/>	
							<a class="right_top_register2 cursor_no_drop" href="#">还没账号？立即注册</a>
						</form>
					</div>
					<div class="right_center">
						<div class="right_center_1"><span id="right_center_1_span" class="cursor_default">点赞榜单</span></div>
						<hr />
						<div class="right_center_2">
							<p id="right_center_2_p_a1" class="cursor_default"></p><p id="right_center_2_p_b1"><a id="right_center_2_p_b1_a" class="praiseSkip_link" href="#"></a></p><p id="right_center_2_p_c1" class="cursor_default"></p>
							<p id="right_center_2_p_a2" class="cursor_default"></p><p id="right_center_2_p_b2"><a id="right_center_2_p_b2_a" class="praiseSkip_link" href="#"></a></p><p id="right_center_2_p_c2" class="cursor_default"></p>
							<p id="right_center_2_p_a3" class="cursor_default"></p><p id="right_center_2_p_b3"><a id="right_center_2_p_b3_a" class="praiseSkip_link" href="#"></a></p><p id="right_center_2_p_c3" class="cursor_default"></p>
							<p id="right_center_2_p_a4" class="cursor_default"></p><p id="right_center_2_p_b4"><a id="right_center_2_p_b4_a" class="praiseSkip_link" href="#"></a></p><p id="right_center_2_p_c4" class="cursor_default"></p>
							<p id="right_center_2_p_a5" class="cursor_default"></p><p id="right_center_2_p_b5"><a id="right_center_2_p_b5_a" class="praiseSkip_link" href="#"></a></p><p id="right_center_2_p_c5" class="cursor_default"></p>
						</div>
						<div class="right_center_3">
							<a href="#" id="page_pre" class="a_color" onclick="prePage()">上页</a>
							<span class="right_center_3_point cursor_default">第</span>
							<span id="page_now" class="cursor_default">1</span>
							<span class="right_center_3_point cursor_default">页</span>
							<a href="#" id="page_next" class="a_color" onclick="nextPage()">下页</a>
							<input type="text" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" id="page_input" placeholder="页数"/>
							<a href="#" id="page_go" class="a_color" onclick="go()">Go</a>
						</div>
					</div>
					<div class="right_bottom">
						<div class="right_bottom_1">
							<span class="cursor_default">推荐用户</span>
							<a class="a_color" href="#" onclick="change_1()">换一批</a>
						</div>
						<hr />
						<div class="right_bottom_2">
							<div id="right_bottom_2_1">
								<a href="#" class="a_color userSkip_link" id="right_bottom_2_1_1"></a>
							</div>
							<div id="right_bottom_2_2">
								<a href="#" class="a_color userSkip_link" id="right_bottom_2_1_2"></a>		
							</div>
							<div id="right_bottom_2_3">
								<a href="#" class="a_color userSkip_link" id="right_bottom_2_1_3"></a>					
							</div>
							<div id="right_bottom_2_4">
								<a href="#" class="a_color userSkip_link" id="right_bottom_2_1_4"></a>
							</div>
							<div id="right_bottom_2_5">
								<a href="#" class="a_color userSkip_link" id="right_bottom_2_1_5"></a>							
							</div>
							<div id="right_bottom_2_6">
								<a href="#" class="a_color userSkip_link" id="right_bottom_2_1_6"></a>		
							</div>
						</div>
					</div>
				</div>
				<div style="clear:both"></div>
			</div>
		</div>
		<script type="text/javascript" src="js/main.js"></script>
	</body>

</html>