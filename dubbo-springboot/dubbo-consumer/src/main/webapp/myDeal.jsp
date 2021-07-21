<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<link rel="stylesheet" type="text/css" href="css/cashbox-share.css"/>
<link rel="stylesheet" type="text/css" href="css/fund-guanli.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/share.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base.css"/>
<title>东宝金融网 - 专业的互联网金融信息服务平台</title>
<script type="text/javascript" language="javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/trafficStatistics.js"></script>
<script type="text/javascript">
$(function(){
	$("#myCash").attr("class","on");
	$('#jiaoyijilu').addClass('on');
	
	$(".selectBox").hover(function(){
  		$("ul",this).show();							   
	},function(){
  		$("ul",this).hide();	
	});
	$(".selectBox li a").click(function(){
		var selectName=$(this).text();
		$(".select-selected h3").text(selectName);
		$(".selectBox ul").hide();
	});
	$(".select-selected").hover(function(){
  		$(this).addClass('select-hover');							   
	},function(){
  		$(this).removeClass('select-hover');	
	});
})
$(document).ready(function() {
	$("#overView").removeClass("on");
	$("#myAccount").addClass("on");
});
$(document).ready(function(){
	$("#jiaoyijilu").addClass("on");
});
</script>
</head>

<body>
<!--页头start-->
<div id="header">
<jsp:include page="commons/header.jsp"/>
</div>
<!--页头end-->

<!-- 二级导航栏start -->
<jsp:include page="commons/subNav.jsp"/>
<!-- 二级导航栏end -->

<!--页中start-->
<div class="mainBox">
 <div class="homeWap">
  <div class="fund-guanli clearfix">
   <div class="left-nav">
   	<jsp:include page="commons/leftNav.jsp"/>
   </div>
   <div class="right-body">
    <div class="right-wap">
     <div class="deal-data">
      <dl>
       <dt><span class="deal-time">交易时间</span><span class="deal-name">交易对象</span><span class="deal-type" style="width:120px">类型</span><span class="deal-money">收支(元)</span><span class="deal-balance">账户可用余额(元)</span></dt>
       
	       
		       
			       <dd>
				       <div class="deal-time">2017年02月14日 23:24:29</div>
				       <div class="deal-name">
					       	
						        
						       		招商银行6225****4942
						        
					            
					        
				       </div>
				       <div class="deal-type" style="width:120px">
					       
						        
						       		提现
						        
					            
					       
				       </div>
				       <div class="deal-money">
					       
						        
						       		-308.55
						        
						        
						        
					            
					       
				       </div>
				       <div class="deal-balance">0.00</div>
			       </dd>
		       
			       <dd>
				       <div class="deal-time">2017年02月14日 23:24:27</div>
				       <div class="deal-name">
					       	
						        
						       		招商银行6225****4942
						        
					            
					        
				       </div>
				       <div class="deal-type" style="width:120px">
					       
						        
						       		提现手续费
						        
					            
					       
				       </div>
				       <div class="deal-money">
					       
						        
						       		-2.00
						        
						        
						        
					            
					       
				       </div>
				       <div class="deal-balance">308.55</div>
			       </dd>
		       
			       <dd>
				       <div class="deal-time">2017年01月31日 06:35:14</div>
				       <div class="deal-name">
					       	
						        
						       		季度金161031-01
						        
					            
					        
				       </div>
				       <div class="deal-type" style="width:120px">
					       
						        
						       		本息返还
						        
					            
					       
				       </div>
				       <div class="deal-money">
					       
						        
						        
						        
					            
					          		+101.75
					            
					       
				       </div>
				       <div class="deal-balance">310.55</div>
			       </dd>
		       
			       <dd>
				       <div class="deal-time">2017年01月13日 01:23:03</div>
				       <div class="deal-name">
					       	
						        
						       		季度金161012-01
						        
					            
					        
				       </div>
				       <div class="deal-type" style="width:120px">
					       
						        
						       		本息返还
						        
					            
					       
				       </div>
				       <div class="deal-money">
					       
						        
						        
						        
					            
					          		+203.50
					            
					       
				       </div>
				       <div class="deal-balance">208.80</div>
			       </dd>
		       
			       <dd>
				       <div class="deal-time">2017年01月13日 01:18:34</div>
				       <div class="deal-name">
					       	
						        
						       		金秋现金红包
						        
					            
					        
				       </div>
				       <div class="deal-type" style="width:120px">
					       
						        
						       		红包充值
						        
					            
					       
				       </div>
				       <div class="deal-money">
					       
						        
						        
						        
					            
					          		+0.20
					            
					       
				       </div>
				       <div class="deal-balance">5.30</div>
			       </dd>
		       
			       <dd>
				       <div class="deal-time">2016年12月13日 08:50:06</div>
				       <div class="deal-name">
					       	
						        
						       		快钱支付
						        
					            
					        
				       </div>
				       <div class="deal-type" style="width:120px">
					       
						        
						       		充值
						        
					            
					       
				       </div>
				       <div class="deal-money">
					       
						        
						        
						        
					            
					          		+1.00
					            
					       
				       </div>
				       <div class="deal-balance">5.10</div>
			       </dd>
		       
			       <dd>
				       <div class="deal-time">2016年12月08日 14:55:23</div>
				       <div class="deal-name">
					       	
						        
						       		快钱支付
						        
					            
					        
				       </div>
				       <div class="deal-type" style="width:120px">
					       
						        
						       		充值
						        
					            
					       
				       </div>
				       <div class="deal-money">
					       
						        
						        
						        
					            
					          		+1.00
					            
					       
				       </div>
				       <div class="deal-balance">4.10</div>
			       </dd>
		       
			       <dd>
				       <div class="deal-time">2016年11月19日 01:05:20</div>
				       <div class="deal-name">
					       	
						        
						       		息迎11.11红包
						        
					            
					        
				       </div>
				       <div class="deal-type" style="width:120px">
					       
						        
						       		红包充值
						        
					            
					       
				       </div>
				       <div class="deal-money">
					       
						        
						        
						        
					            
					          		+0.10
					            
					       
				       </div>
				       <div class="deal-balance">3.10</div>
			       </dd>
		       
			       <dd>
				       <div class="deal-time">2016年11月06日 19:42:13</div>
				       <div class="deal-name">
					       	
						        
						       		快钱支付
						        
					            
					        
				       </div>
				       <div class="deal-type" style="width:120px">
					       
						        
						       		充值
						        
					            
					       
				       </div>
				       <div class="deal-money">
					       
						        
						        
						        
					            
					          		+1.00
					            
					       
				       </div>
				       <div class="deal-balance">3.00</div>
			       </dd>
		       
			       <dd>
				       <div class="deal-time">2016年10月31日 19:01:32</div>
				       <div class="deal-name">
					       	
						        
						       		季度金161031-01
						        
					            
					        
				       </div>
				       <div class="deal-type" style="width:120px">
					       
						        
						       		投资
						        
					            
					       
				       </div>
				       <div class="deal-money">
					       
						        
						       		-100.00
						        
						        
						        
					            
					       
				       </div>
				       <div class="deal-balance">2.00</div>
			       </dd>
		       
		       &nbsp;
	  		   

<script type="text/javascript">
function _doPostBack(lnk,page){
	var toPage = parseInt("1") + page;
	if(lnk == "lnkFirst"){
		$("#currentPage").val(1);
	}else if(lnk == "lnkPre"){
		$("#currentPage").val(toPage);
	}else if(lnk == "lnkNext"){
		$("#currentPage").val(toPage);
	}else if(lnk == "lnkLast"){
		$("#currentPage").val("8");
	}
	$("#form").submit();
}
</script>
&nbsp;
<div class="touzi_fenye" style="width: 100%;text-align: center;">共79条8页　当前第1页　
	
		
		
			首页
		
	
	
		
			<a id="lnkNext" href="javascript:_doPostBack('lnkNext',1);">下一页</a>
		
		
	
	
		
		
			上一页
		
	
	
		
			<a id="lnkLast" href="javascript:_doPostBack('lnkLast',8)">尾页</a>
		
		
	
	<input type="hidden" id="currentPage" name="pageUtil.currentPage" value="1" />
	<input type="hidden" id="totalPage" name="pageUtil.totalPage" value="8" />
	<input type="hidden" id="pageSize" name="pageUtil.pageSize" value="10" />
</div>
       
      </dl>
     </div>
    </div>
   </div>
  </div>      
 </div>
</div>
<!--页中end-->   
<!--页脚start-->
<jsp:include page="commons/footer.jsp"/>
<!--页脚end-->
</body>
</html>