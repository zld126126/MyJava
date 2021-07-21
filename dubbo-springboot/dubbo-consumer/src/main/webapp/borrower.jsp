<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="../common/css20140521/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/borrowers.css"/>
<title>借款人信息-东宝金融网,专业的互联网金融信息服务平台</title>
<script language="javascript" src="../common/js/jquery-1.7.2.min.js"></script>
<script language="javascript" src="../common/js/jquery/jquery.form.js"></script>
<script type="text/javascript" src="../common/js/includeNewSEO.js"></script>
<script type="text/javascript" src="../common/js/lend_apply.js"></script>
<script type="text/javascript" src="../common/js/xmlHttpAjax.js"></script>
<script type="text/javascript" src="../common/js/trafficStatistics.js"></script>
</head>
<body style="background:#fafafa;">

<div id="header"></div>
<!--Banner-begin -->
<div id="advertArea"></div>
<!--Banner-end -->
<!-- 借款人信息详情 start-->
<div class="borrowerInfoDetail">
</div>
<!-- 借款人信息详情 end-->

<div class="mainBox" id="fullbidList">
<!-- <div style="text-align:center;"><img  style="text-align:center;" src="../common/imagesNew/loading.gif" /></div> -->
</div>

<!--页脚start-->
<div id="footer"></div>
<!--页脚end-->
<script type="text/javascript">
	$(function(){
		getAjax(1);
	});
	
	function getAjax(type){
		$.ajax({
		   type: "POST",
		   url:"../../borrower/getFullBidList.do",
		   dataType: "text",
		   async: false,
		   data:"currentPage="+type,
		   success: function(data){
			   $("#fullbidList").html(data);
               loadAdvert();
		   }
		});
	}
    //加载广告图
    function loadAdvert(){
        $.post("../../image/getImage.do",{advertPosition:'借款人信息频道顶部通栏广告位'},function(data){
            if(data != null && data != ''){
                var obj = JSON.parse(data);
                if(obj.errorCode == 0){
                    $("#advertArea").css("margin-bottom","50px");
                    $("#advertArea").html("<a href=\""+obj.data[0].linkUrl+"\" target=\"_blank\" style=\"width:100%; height:260px;max-height: 260px; background:url('"+obj.data[0].imageUrl+"') no-repeat 50% 0; display:block;\" alt=\""+obj.data[0].name+"\"></a>");
                }
            }
        });
    }
	
	//分页查询事件
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
	}
</script>
</body>
</html>