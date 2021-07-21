<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="keywords" content="公司动态，大事记，发展" />
<meta name="description" content=""/>
<link href="../common/css20140521/style.css" rel="stylesheet" type="text/css" />
<title>东宝金融网 - 专业的互联网金融信息服务平台</title>
<script type="text/javascript" src="../common/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="../common/js/includeNewSEO.js"></script>
</head>

<body>
<div id="header"></div>
<!-- <div class="mainBox">
<div class="homeBorder homeBody clearfix">
左侧树形导航start
<div id="menu" class="sideNav">
</div>
左侧树形导航end
右侧start
<div class="rightBody">
<div class="title"><h3>公司动态</h3></div>
<div id="list" class="media_reports">
 
</div>
</div>
右侧end
</div>
</div> -->
<!--二级导航begin-->
<div class="min_nav"> 
<a href="../index/aboutus.html"><i></i><span>公司介绍</span></a>
<!--     <a href="../index/team.html"><i class="nav_8"></i><span>管理团队</span></a> -->
<!--     <a href="../index/report.html"><i class="nav_9"></i><span>业绩报告</span></a> -->
<a href="../index/partners.html"><i class="nav_2"></i><span>合作伙伴</span></a> 
<a href="../index/media.html"><i class="nav_3"></i><span>媒体报道</span></a>
<a href="../index/companydynamic.html" class="on"><i class="nav_4"></i><span>公司动态</span></a>
<!-- <a href="#"><i class="nav_5"></i><span>东宝风采</span></a> -->
<a href="../index/hire.html"><i class="nav_6"></i><span>招贤纳士</span></a>
<a href="../index/contactus.html"><i class="nav_7"></i><span>联系我们</span></a> 
</div>
<!--二级导航begin-->
<div class="mainBox memorabilia events_body">
<div class="memorabilia_body" style="height:1150px;" id = "list"><!--再出十个，增加高度  1030px-->
    <div class="year year2016">2016</div>
    <div class="more_events"><a href="javascript:showMore();">更多</a></div>
  </div>
</div>

<!--页脚start-->
<div id="footer"></div>
<!--页脚end-->
<script type="text/javascript" src="../common/js/trafficStatistics.js"></script>
<script type="text/javascript">
var lists = new Array();
var count = 0;
var height = 1150;
$(function(){
getAjax(1);
});
function getAjax(type){
/* $.ajax({
	   type: "POST",
	   url:"../../index/getArticInfoList.do",
	   dataType: "text",
	   async: false,
	   data:"org=wzgg",
	   success: function(data){
		    $("#list").html(data);
	   }
	}); */
$.post("../../index/getArticInfoList.do?org=wzgg","",function(data){
	 lists = eval(data);
	 for(var i = count;i < count + 5;i++){
		 if(lists[0][i] != undefined){
			 var left = 35 + i * 195;
			 var leftContent = lists[0][i][0].content.substring(0,50) + "...";
			 var str = '<div class="memorabilia_wap" style="top:' + left + 'px;">';
				 str += '<div class="events_time">' + lists[0][i][0].source + '<i></i></div>'
				 str += '<div class="memorabilia_box"> <i class="dian"></i><i class="arr"></i>';
				 str += '<h3><a href="../newsMain/info.jsp?id=' + lists[0][i][0].id + '">' + lists[0][i][0].title +'</a></h3>';
				 str += '<p>' + leftContent + '</p>';
				 str += '</div>';
			     str += '</div>';
			     if(lists[0][i][1] != undefined){
			    	 var right = 130 + i * 195;
			    	 var rightContent = lists[0][i][1].content.substring(0,50) + "...";
			    	 str += '<div class="memorabilia_wap events_right" style="top:' + right + 'px;">';
				     str += '<div class="events_time">' + lists[0][i][1].source + '<i></i></div>';
	                 str += '<div class="memorabilia_box right_memorabilia"> <i class="dian"></i><i class="arr"></i>';
	                 str += '<h3><a href="../newsMain/info.jsp?id=' + lists[0][i][1].id + '">' + lists[0][i][1].title +'</a></h3>';
	                 str += '<p>' + rightContent + '</p>';
	                 str += '</div>';
	                 str += '</div>';
			     }
                 $("#list").append(str);
		 }else{
			 break;
		 }
	 }
	 count = count + 5;
	},'json');
}

function showMore(){
	for(var i = count;i < count + 5;i++){
		 if(lists[0][i] != undefined){
			 var left = 35 + i * 195;
			 var leftContent = lists[0][i][0].content.substring(0,45) + "...";
			 var str = '<div class="memorabilia_wap" style="top:' + left + 'px;">';
				 str += '<div class="events_time">' + lists[0][i][0].source + '<i></i></div>'
				 str += '<div class="memorabilia_box"> <i class="dian"></i><i class="arr"></i>';
				 str += '<h3><a href="../newsMain/info.jsp?id=' + lists[0][i][0].id + '">' + lists[0][i][0].title +'</a></h3>';
				 str += '<p>' + leftContent + '</p>';
				 str += '</div>';
			     str += '</div>';
			     height = height + 97;
			 	 $('#list').css('height',height);
			     if(lists[0][i][1] != undefined){
			    	 var right = 130 + i * 195;
			    	 var rightContent = lists[0][i][1].content.substring(0,35) + "...";
			    	 str += '<div class="memorabilia_wap events_right" style="top:' + right + 'px;">';
				     str += '<div class="events_time">' + lists[0][i][1].source + '<i></i></div>';
	                 str += '<div class="memorabilia_box right_memorabilia"> <i class="dian"></i><i class="arr"></i>';
	                 str += '<h3><a href="../newsMain/info.jsp?id=' + lists[0][i][1].id + '">' + lists[0][i][1].title +'</a></h3>';
	                 str += '<p>' + rightContent + '</p>';
	                 str += '</div>';
	                 str += '</div>';
	                 height = height + 97;
				 	 $('#list').css('height',height);
			     }
                $("#list").append(str);
		 }else{
			 break;
		 }
	 }
	 count = count + 5;
}
/* //分页查询事件
function SwitchPage(allpage,currentPage){
 if(currentPage=="Jump"){ //输入
	var number=$("#pageIdN").val();
	if(number!=""){
	  	number=number.replace(/\s+/g,"");  //去除所有空格
		if(isNaN(number)){ //验证是否为数字
			alert("请输入合法的数字");
			return;
		}else{
			if(number>allpage||number<=0){
				alert("没有此页面");
				return;
			}
		}
	}else{
		alert("请输入您要跳转的页面");
		return;
	}
	currentPage=number;//赋值跳转的页面
 }else{
	 if(currentPage>allpage){
		alert("已经是最后一页");
		return;
	}else if(currentPage<=0){
		alert("已经是第一页");
		return;
	}
 }
getAjax(currentPage);	
} */
/* getLeftMenuxmlHttpAjax("menu","company"); */
</script>
</body>
</html>