<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="renderer" content="webkit"/>
<meta http-equiv="content-type" content="text/html" charset="utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Cache-Control" content="no-cache,no-store"/>
<meta name="google-site-verification" content="oQXrGa_mTgxg7joO0himE0QuFeqOVmm-SDC1H2dzT4c"/>
<meta name="keywords" content="" />
<meta name="description" content=""/>
<link rel="stylesheet" type="text/css" href="/webPage/common/css/share.css"/>
<link rel="stylesheet" type="text/css" href="../common/css20140521/style.css"/>
<link rel="stylesheet" type="text/css" href="../common/css20140521/ingot.css"/>
<link rel="stylesheet" type="text/css" href="../common/css20140521/fullpage.css"/>
<!-- <title> 东宝金融网新手引导,平台介绍-东宝金融网-CFCA认证的互联网金融公司</title> -->
<title>东宝金融网新手引导,平台介绍-东宝金融网-CFCA认证的互联网金融公司</title>
<!-- <script type="text/javascript" src="../common/js/jquery/jquery-1.9.js"></script> -->
<script src="js/jquery-1.8.3.min.js"></script>
<script src="js/jquery-ui-1.10.3.min.js"></script>
<script type="text/javascript" src="../common/js/includeNew.js"></script>
<script type="text/javascript" src="../common/js/WinMax.js"></script> 
<script type="text/javascript" src="../common/js/trafficStatistics.js"></script>
<script type="text/javascript" src="../common/js/jquery.fullPage.min.js"></script>
<style>
/*item*/
#item0{left:-200%;}
#item1{ left:200%;}
#item2{left:-200%;}
/*img*/
#img0{left:-200%;}
#img1{left:-200%;}
#img2{ left:-200%;}
#img3{left:-200%;}
#img4{left:-200%;}
#rocket{right:477px;top:511px;}
</style>
<script type="text/javascript">
$(function(){
	var i = 0;
	var timer = null;
	$('#imgPtion').fadeIn(2000);
	//判断用户是否登录.
	if($('.head_r .logged').length){
	  $("#page5 .bn-wytz").show();
	}else{
		$("#page5 .bn-wytz").hide();	
	};
	animate = function(index){
		//处理动画函数
		if(index == 1){
			$(".headBody,.subNav").show();
			$('#imgPtion').fadeIn(2000);
		}else if(index == "1out"){
			$(".headBody,.subNav").hide();
			$('#imgPtion').fadeOut(200);
		}
		if(index == 2){
			timer = setInterval(function(){
			if(i>4){
			  i=0;
			  $('.star:eq(4)').show();
			  $('.star:eq(0)').hide();
			}else if(i==0){
			  $('.star:eq(0)').hide();
			}else{
			  $('.star:eq('+(i-1)+')').show();
			  $('.star:eq('+(i)+')').hide();
			}
			i++;
		  },500);
			$('#img5').delay(200).animate({right: '167',top:'38'
			},1000);
			$('#img6').delay(200).animate({left:'634'},1000);
			$('#img7').delay(200).animate({right:'106'},1000);
			$('#img8').delay(200).animate({right:'78'},1000);
		}else if(index == "2out"){
		  clearInterval(timer);
			$('#img5').delay().animate({right: '199',top:'78'},1000);
			$('#img6').delay().animate({left:'584'},1000);
			$('#img7').delay().animate({right:'56'},1000);
			$('#img8').delay().animate({right:'28'},1000);
		}
		if(index == 3){
			$('#item0').delay(10).animate({left: '0'});
			$('#item1').delay(10).animate({left: '0'});
			$('#item2').delay(10).animate({left: '0'});
		}else if(index == "3out"){
			$('#item0').delay(10).animate({left: '-200%'});
			$('#item1').delay(10).animate({left: '200%'});
			$('#item2').delay(10).animate({left: '-200%'});
		}
		if(index == 4){
			$('#img0').delay(100).animate({left: '108'});
			$('#img1').delay(410).animate({left: '267'});
			$('#img2').delay(610).animate({left: '418'});
			$('#img3').delay(810).animate({left: '534'});
			$('#img4').delay(1010).animate({left: '645'});
		}else if(index == "4out"){
			$('#img0').delay(100).animate({left: '-200%'},1000);
			$('#img1').delay(100).animate({left: '-200%'},1000);
			$('#img2').delay(100).animate({left: '-200%'},1000);
			$('#img3').delay(100).animate({left: '-200%'},1000);
			$('#img4').delay(100).animate({left: '-200%'},1000);
		}
		if(index == 5){
			$('.inner4').fadeIn(2000);
		}else if(index == "5out"){
			$('.inner4').fadeOut(200);
		}
	};
	$('#fullpage').fullpage({
		slidesColor: ['#F3DE7D', '#539ce7', '#56DBBE', '#56A8D2', '#CFEBF9'],
		easing: 'swing',
		navigation: true,
		navigationPosition: 'left',
		//navigationTooltips: ['fullPage.js', 'Powerful', 'Amazing', 'Simple','design'],
		menu: '#menu',
		onLeave: function(index, nextIndex, direction){
			animate(index+"out");
			animate(nextIndex);
		},
		afterLoad: function(anchorLink, index){},
		afterResize: function(){},
		afterRender: function(){}
	});
});
</script>
</head>

<body style="background:#fafafa;">

<!--header begin-->
<div class="header" id="header">
</div>
<!--header end-->

<!--二级导航start-->
<div class="subNav">
  <div class="mainBox">
    <ul>
      <li><a href="yindao.html" class="on">平台介绍</a></li>
      <li><a href="helpcenter.html">帮助中心</a></li>
    </ul>
  </div>
</div>
<!--二级导航end-->
<!--新手指引start-->
<div id="fullpage" class="new-guidelines">
  <div id="page1" class="section flash-box" style="background:#F3DE7D;">
    <div class="flash-inner inner0" style=" top:10px;background: url(images/img-33.png) no-repeat center bottom;"> <img id="imgPtion" style="display: none;" src="images/img-34.png" class="inner-img inner-img-34" /> </div>
  </div>
  <div id="page2" class="section flash-box" style=" background:#539ce7;">
      <div class="flash-inner inner1" style="top:17%; margin-top:0px;"> 
      <img src="images/img-1.png" class="inner-img inner-img-1" alt="东宝金融网介绍"/>
      <img src="images/img-2.png" class="inner-img inner-img-2" alt="东宝金融网简介"/>
      <img src="images/img-3.png" class="inner-img inner-img-3" style = "left:584px;" id = "img6"/> 
      <img src="images/img-4.png" class="inner-img inner-img-4 tmove3-a"/> 
      <img src="images/img-5.png" class="inner-img inner-img-5 tmove3-a delay-1"/> 
      <img src="images/img-7.png" class="inner-img inner-img-7"/> 
      <img src="images/img-6.png" class="inner-img inner-img-6"/> 
      <img src="images/img-8.png" class="inner-img inner-img-8" style = "right:199px; top:78px;" id = "img5" alt="东宝金融网快速发展"/>
      <img src="images/img-9.png" class="inner-img inner-img-9" style = "right:56px;" id = "img7"/> 
      <img src="images/img-10.png" class="inner-img inner-img-10" style = "right:28px;" id = "img8"/> 
      <img src="images/img-11.png" class="inner-img inner-img-11 star" /> 
      <img src="images/img-12.png" class="inner-img inner-img-12 star" /> 
      <img src="images/img-13.png" class="inner-img inner-img-13 star" /> 
      <img src="images/img-14.png" class="inner-img inner-img-14 star" /> 
      <img src="images/img-15.png" class="inner-img inner-img-15 star" /> 
      <img src="images/img-16.png" class="inner-img inner-img-16" /> 
      </div>
  </div>
  <div id="page3" class="section flash-box" style=" background:#56DBBE;">
    <div class="flash-inner inner2" style="height:470px; top:13%;"> 
    <img src="images/img-17.png" class="inner-img inner-img-17 a-auto-1 inner-img-17-a" alt="东宝金融模式"/>
    <img id='item0' src="images/img-18.png" class="inner-img inner-img-18 a-auto-1 inner-img-18-a" alt="如何为您提供满意的投资项目"/> 
    <img id='item1' src="images/img-19.png" class="inner-img inner-img-19 a-auto-1 inner-img-19-a" alt="如何 成为最安全的投资平台"/> 
    <img id='item2' src="images/img-20.png" class="inner-img inner-img-20 a-auto-1 inner-img-20-a" alt="如何提供最优质的服务"/> 
    </div>
  </div>
  <div id="page4" class="section flash-box" style=" background:#56A8D2 url(images/img-22.png) no-repeat center bottom;"> 
  <img src="images/img-23.png" class="inner-img inner-img-23" /> 
  <img src="images/img-24.png" class="inner-img inner-img-24" />
    <div class="flash-inner inner3" style="top:12%; height:600px; padding-top:0px; margin-top:0px;"> 
    <img src="images/img-21.png" class="inner-img inner-img-21" alt="如何投资"/> 
    <img src="images/img-25.png" class="inner-img inner-img-25" /> 
    <img src="images/img-26.png" class="inner-img inner-img-26" id = "img0" /> 
    <img src="images/img-27.png" class="inner-img inner-img-27" id = "img1" /> 
    <img src="images/img-28.png" class="inner-img inner-img-28" id = "img2" /> 
    <img src="images/img-29.png" class="inner-img inner-img-29" id = "img3" /> 
    <img src="images/img-30.png" class="inner-img inner-img-30" id = "img4" /> 
    </div>
  </div>
  <div id="page5" class="section flash-box" style=" background:#CFEBF9 url(images/img-31.png) no-repeat center top;">
    <div class="flash-inner inner4" style="height:400px; top:23%; padding:0px; margin-top:0px; display:none;"> 
	    <img src="images/img-32.png" width="192" height="209" class="inner-img inner-img-32" style="top:0%;" alt="选东宝金融 多重好礼"/>
	    <a href="../ssl/Register.html" class="bn-wyzc" style="top:70%">我要注册</a>
	    <a href="../invest/wytz.html" class="bn-wyzc bn-wytz" style="top:70%;display:none;">我要投资</a>
	  </div>
  </div>
</div>
<!--新手指引end-->
<!--引导页 end-->
<!--footer begin-->
<div id="footer"></div>
<!--footer end-->
</body>
</html>
