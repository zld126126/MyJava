<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="keywords" content="关于我们，东宝金融网怎么样，东宝金融网优势，东宝金融网大事记，东宝金融网荣誉" />
<meta name="description" content="东宝金融网官网关于我们栏目为您解读东宝金融网优势，东宝金融网所获荣誉、发展等大事记，让您更真实的了解东宝金融网。"/>
<link href="../common/css20140521/style.css" rel="stylesheet" type="text/css" />
<title>关于东宝金融网,东宝金融网怎么样-东宝金融网,专业的互联网金融信息服务平台</title>
<script type="text/javascript" src="../common/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="../common/js/jquery.tinyscrollbar.min.js"></script>
<script type="text/javascript" src="../common/js/cufon.js"></script>
<script type="text/javascript" src="../common/js/website.js"></script>
<script type="text/javascript" src="../common/js/svg_files/raphael-min.js"></script>
<script type="text/javascript" src="../common/js/svg_files/chinaMapConfig.js"></script>
<script type="text/javascript" src="../common/js/svg_files/worldmapconfig.js"></script>
<script type="text/javascript" src="../common/js/svg_files/map.js"></script>
<script type="text/javascript" src="../common/js/includeNewSEO.js"></script>
<script type="text/javascript">
$(function(){

    // 外部控制地图
    var mapObj = {};
    var data = {"jiangsu":{"value":"30.05%","index":"1","stateInitColor":"5"},
	"henan":{"value":"19.77%","index":"1","stateInitColor":"3"},
	"anhui":{"value":"10.85%","index":"3","stateInitColor":"10"},
	"zhejiang":{"value":"10.02%","index":"4","stateInitColor":"11"},
	"liaoning":{"value":"8.46%","index":"5","stateInitColor":"9"},
	"beijing":{"value":"4.04%","index":"6","stateInitColor":"1"},
	"hubei":{"value":"3.66%","index":"7","stateInitColor":"1"},
	"jilin":{"value":"2.56%","index":"8","stateInitColor":"1"},
	"shanghai":{"value":"2.47%","index":"9","stateInitColor":"1"},
	"guangxi":{"value":"2.3%","index":"10","stateInitColor":"1"},
	"sichuan":{"value":"1.48%","index":"11","stateInitColor":"2"}
	,"guizhou":{"value":"0.99%","index":"12","stateInitColor":"2"},
	"hunan":{"value":"0.78%","index":"13","stateInitColor":"2"},
	"shandong":{"value":"0.7%","index":"14","stateInitColor":"8"},
	"guangdong":{"value":"0.44%","index":"15","stateInitColor":"8"},
	"jiangxi":{"value":"0.34%","index":"16","stateInitColor":"3"},
	"fujian":{"value":"0.27%","index":"17","stateInitColor":"3"},
	"yunnan":{"value":"0.23%","index":"18","stateInitColor":"3"},
	"hainan":{"value":"0.21%","index":"19","stateInitColor":"12"},
	"shanxi":{"value":"0.11%","index":"20","stateInitColor":"3"},
	"hebei":{"value":"0.11%","index":"21","stateInitColor":"9"},
	"neimongol":{"value":"0.04%","index":"22","stateInitColor":"4"},
	"tianjin":{"value":"0.04%","index":"23","stateInitColor":"4"},
	"gansu":{"value":"0.04%","index":"24","stateInitColor":"4"},
	"shaanxi":{"value":"0.02%","index":"25","stateInitColor":"4"},
	"macau":{"value":"0.0%","index":"26","stateInitColor":"7"},
	"hongkong":{"value":"0.0%","index":"27","stateInitColor":"7"},
	"taiwan":{"value":"0.0%","index":"28","stateInitColor":"12"},
	"qinghai":{"value":"0.0%","index":"29","stateInitColor":"7"},
	"xizang":{"value":"0.0%","index":"30","stateInitColor":"7"},
	"ningxia":{"value":"0.0%","index":"31","stateInitColor":"7"},
	"xinjiang":{"value":"0.0%","index":"32","stateInitColor":"7"},
	"heilongjiang":{"value":"0.0%","index":"33","stateInitColor":"8"},
	"chongqing":{"value":"0.0%","index":"34","stateInitColor":"7"}};
            var i = 1;
            for(k in data){
                if(i <= 12){
                    var _cls = i < 4 ? 'active' : ''; 
                    $('#MapControl .list1').append('<li name="'+k+'"><div class="mapInfo"><i class="'+_cls+'">'+(i++)+'</i><span>'+chinaMapConfig.names[k]+'</span><b>'+data[k].value+'</b></div></li>')
                }else if(i <= 24){
                    $('#MapControl .list2').append('<li name="'+k+'"><div class="mapInfo"><i>'+(i++)+'</i><span>'+chinaMapConfig.names[k]+'</span><b>'+data[k].value+'</b></div></li>')
                }else{
                    $('#MapControl .list3').append('<li name="'+k+'"><div class="mapInfo"><i>'+(i++)+'</i><span>'+chinaMapConfig.names[k]+'</span><b>'+data[k].value+'</b></div></li>')
                }
            }

            var mapObj_1 = {};
            var stateColorList = ['003399', '0058B0', '0071E1', '1C8DFF', '51A8FF', '82C0FF', 'AAD5ee', 'AAD5FF'];
            
            $('#RegionMap').SVGMap({
                external: mapObj_1,
                mapName: 'china',
                mapWidth: 600,
                mapHeight: 500,
                stateData: data,
                // stateTipWidth: 118,
                // stateTipHeight: 47,
                // stateTipX: 2,
                // stateTipY: 0,
                stateTipHtml: function (mapData, obj) {
                    var _value = mapData[obj.id].value;
                    var _idx = mapData[obj.id].index;
                    var active = '';
                    _idx < 4 ? active = 'active' : active = '';
                    // 判断省份是否含有公司
                    if (obj.name == '') {
                        $('.stateTip').remove();
                    } else {
                        var tipStr = '<div class="mapInfo"><div class="pop"><span class="arrow"></span>' + obj.name + '</div></div>';
                        //var tipStr = '<div class="mapInfo"><i class="' + active + '">' + _idx + '</i><span>' + obj.name + '</span><b>' + _value + '</b></div>';
                        return tipStr;
                    }
                }
            });
            $('#MapControl li').hover(function () {
                var thisName = $(this).attr('name');
                
                var thisHtml = $(this).html();
                $('#MapControl li').removeClass('select');
                $(this).addClass('select');
                $(document.body).append('<div id="StateTip"></div');
                $('#StateTip').css({
                    left: $(mapObj_1[thisName].node).offset().left - 50,
                    top: $(mapObj_1[thisName].node).offset().top - 40
                }).html(thisHtml).show();
                mapObj_1[thisName].attr({
                    fill: '#E99A4D'
                });
            }, function () {
                var thisName = $(this).attr('name');

                $('#StateTip').remove();
                $('#MapControl li').removeClass('select');
                mapObj_1[$(this).attr('name')].attr({
                    fill: "#" + stateColorList[data[$(this).attr('name')].stateInitColor]
                });
            });
            
            $('#MapColor').show();
    //     }
    // });


    $('#WorldMap').SVGMap({
        mapName: 'world',
        mapWidth: 600,
        mapHeight: 400
    });
});
</script>
<style type="text/css">
.stateTip {
	z-index:100003;
}
</style>
<!-- <title>关于我们,东宝金融网怎么样，-东宝金融网优势，荣誉，大事记</title> -->
</head>
<body>
<div id="header"></div>
<!--页头end-->
<!--二级导航begin-->
<div class="min_nav"> 
<a href="../index/aboutus.html" class="on"><i></i><span>公司介绍</span></a>
<!-- <a href="../index/team.html"><i class="nav_8"></i><span>管理团队</span></a>
<a href="../index/report.html"><i class="nav_9"></i><span>业绩报告</span></a> -->
<a href="../index/partners.html"><i class="nav_2"></i><span>合作伙伴</span></a>
<a href="../index/media.html"><i class="nav_3"></i><span>媒体报道</span></a>
<a href="../index/companydynamic.html"><i class="nav_4"></i><span>公司动态</span></a>
<!-- <a href="#"><i class="nav_5"></i><span>东宝金融风采</span></a> -->
<a href="../index/hire.html"><i class="nav_6"></i><span>招贤纳士</span></a>
<a href="../index/contactus.html"><i class="nav_7"></i><span>联系我们</span></a> 
</div><!--二级导航begin-->
<!-- <div class="jinxin_introduce">
  <h2>东宝金融网</h2>
  <p>成立于2013年，坐落于北京中关村互联网金融中心<br>
    是一家致力于用科技引擎推动金融创新的互联网金融信息服务平台 <br>
    践行普惠金融理念，真正做到出借人和借款人点对点资金撮合 <br>
    千万级自有资金技术研发投入</p>
</div>
<div class="jinxin_edge">
  <div class="mainBox">
    <h2>我们的优势</h2>
    <ul class="edge_box clearfix">
      <li><a href="javascript:yyzzOpen();"></a>
        <h3>亿级注册资本</h3>
      </li>
      <li class="ensure_box"><a href="javascript:layerOpen();"></a>
        <h3>千万级风险准备金</h3>
      </li>
      <li class="institution_box"><a href="javascript:(0);"></a>
        <h3>O2O战略闭环模式</h3>
      </li>
    </ul>
  </div> -->
</div>
<div class="honour_wall">
  <div class="mainBox">
    <h2>荣誉墙</h2>
    
    <div class="wall_body clearfix" style="width: 1108px;">
     <!-- <div class="movezuo"></div>
     <div class="moveyou"></div> -->
      <div class="wall_wap" style="diplay:inline;width: 1084px;/* margin-left:76px; */">
      <ul class="clearfix"style="margin-left: -3832px;">
        <li class="honour_min" onclick="javascript:imgOpen(0);"><img src="../common/images/rongyu_1.jpg" alt="东宝金融网-2014中国互联网金融最具竞争力企业"/></li>
        <li onclick="javascript:imgOpen(1);"><img src="../common/images/rongyu_2.jpg" alt="东宝金融网-2014年度互联网金融企业社会责任“金蜘蛛”优秀企业奖"/></li>
        <li onclick="javascript:imgOpen(2);"><img src="../common/images/rongyu_3.jpg" alt="东宝金融网-2014年度互联网金融企业社会责任“金蜘蛛”互联网金融创新奖"/></li>
        <li class="honour_min" onclick="javascript:imgOpen(3);"><img src="../common/images/rongyu_4.jpg" alt="东宝金融网-2014中国互联网金融最佳风险控制企业"/></li>
        <li onclick="javascript:imgOpen(4);"><img src="../common/images/rongyu_5.jpg" alt="东宝金融网-2014年度中国互联网金融创新奖"/></li>
        <li onclick="javascript:imgOpen(5);"><img src="../common/images/rongyu_6.jpg" alt="东宝金融网-杰出互联网金融创新者"/></li>
        <li class="honour_min" onclick="javascript:imgOpen(6);"><img src="../common/images/rongyu_7.jpg" alt="东宝金融网-网上交易保障中心会员单位"/></li>
        <li onclick="javascript:imgOpen(7);"><img src="../common/images/rongyu_8.jpg" alt="东宝金融网-优秀p2p平台创新奖"/></li>
        <li onclick="javascript:imgOpen(8);"><img src="../common/images/rongyu_9.jpg" alt="东宝金融网-中国电子金融产业联盟会员单位"/></li>
        <li class="honour_min" onclick="javascript:imgOpen(9);"><img src="../common/images/rongyu_10.jpg" alt="东宝金融网-年度领军人物大奖"/></li>
        <li onclick="javascript:imgOpen(10);"><img src="../common/images/rongyu_11.jpg" alt="东宝金融网-中国金融认证中心授权书"/></li>
        <li onclick="javascript:imgOpen(11);"><img src="../common/images/rongyu_12.jpg" alt="东宝金融网-中国互联网协会互联网金融工作委员会发起单位"/></li>
        <!-- <li class="honour_min" onclick="javascript:imgOpen(12);"><img src="../common/images/rongyu_13.jpg" /></li> -->
        <li onclick="javascript:imgOpen(29);"><img src="../common/images/rongyu_29.jpg" /></li>
        <li onclick="javascript:imgOpen(13);"><img src="../common/images/rongyu_14.jpg" /></li>
        <li onclick="javascript:imgOpen(14);"><img src="../common/images/rongyu_15.jpg" /></li>
        <li class="honour_min" onclick="javascript:imgOpen(15);"><img src="../common/images/rongyu_16.jpg" /></li>
        <li onclick="javascript:imgOpen(16);"><img src="../common/images/rongyu_17.jpg" /></li>
        <li onclick="javascript:imgOpen(17);"><img src="../common/images/rongyu_18.jpg" /></li>
        <li class="honour_min" onclick="javascript:imgOpen(18);"><img src="../common/images/rongyu_19.jpg" /></li>
        <li onclick="javascript:imgOpen(19);"><img src="../common/images/rongyu_20.jpg" /></li>
        <li onclick="javascript:imgOpen(20);"><img src="../common/images/rongyu_21.jpg" /></li>
        <li onclick="javascript:imgOpen(21);"><img src="../common/images/rongyu_22.jpg" /></li>
        <li class="honour_min" onclick="javascript:imgOpen(22);"><img src="../common/images/rongyu_23.jpg" /></li>
        <li onclick="javascript:imgOpen(23);"><img src="../common/images/rongyu_24.jpg" /></li>
        <li onclick="javascript:imgOpen(24);"><img src="../common/images/rongyu_25.jpg" /></li>
        <li class="honour_min" onclick="javascript:imgOpen(25);"><img src="../common/images/rongyu_26.jpg" /></li>
        <li onclick="javascript:imgOpen(26);"><img src="../common/images/rongyu_27.jpg" /></li>
        <li onclick="javascript:imgOpen(27);"><img src="../common/images/rongyu_28.jpg" /></li>
      </ul>
      </div>
    </div>
  </div>
</div>
<div class="aboutus">
  <div class="mainBox">
    <div class="homeWap">
      <h2>关于我们</h2>
      <p style="font-size:16px; line-height:30px; margin-top:30px; color:#444; text-indent:2em;">东宝金融网是由东宝金融金融信息服务（北京）有限公司打造的互联网金融信息服务平台，成立于2013年，坐落于北京创新腹地中关村。东宝金融网致力于用科技引擎推动金融创新，为投资方和融资方提供信息交互、撮合、资信评估等中介服务。</p>
      <p style="font-size:16px; line-height:30px;color:#444; text-indent:2em;">公司注册资本金1亿元人民币，且配备一支专业素质高、创新能力强的管理团队，成立短短一年即实现盈利。</p>
      <p style="font-size:16px; line-height:30px;color:#444; text-indent:2em;">东宝金融网坚持与中国金融深化改革同步，在『互联网+』行动计划中，不断践行高效、便捷、普惠的金融信息服务，打造独特互联网金融生态圈。</p>
      <ul class="clearfix" style="margin-top:30px;">
        <li><img src="../common/images/aboutus_1.jpg" /></li>
        <li><img src="../common/images/aboutus_2.jpg" /></li>
        <li><img src="../common/images/aboutus_3.jpg" /></li>
        <li><img src="../common/images/aboutus_4.jpg" /></li>
        <li><img src="../common/images/aboutus_5.jpg" /></li>
        <li><img src="../common/images/aboutus_6.jpg" /></li>
        <li><img src="../common/images/aboutus_7.jpg" /></li>
        <li><img src="../common/images/aboutus_8.jpg" /></li>
      </ul>
    </div>
  </div>
</div>
<div class="mainBox memorabilia">
<h2>大事记</h2>
  <div class="chronicle_body">
  <div class="chronicle_2016">2016年 东宝金融网再创新高</div>
   <p>2016年，东宝金融网将继续坚持产品和业务模式创新，<br />不忘普惠金融初衷，<br />打造中国具有创造力的互联网金融平台。</p>
   <div class="chronicle_chakan"><a href="javascript:chronicleOpen3();">查看</a></div>
   <div class="chronicle_line"></div>
   <div class="chronicle_2015">2015年 东宝金融网力争上游</div>
   <p>2015年，东宝金融网将坚持普惠金融的初衷，<br />进一步整合资源，在完善现有产品基础上突破创新。</p>
   <div class="chronicle_chakan"><a href="javascript:chronicleOpen();">查看</a></div>
   <div class="chronicle_line"></div>
   <div class="chronicle_2014">2014年 东宝金融网快速成长</div>
   <p>2014年，东宝金融网从无到有，发展迅猛。屡获行业各类殊荣，<br />并一举登陆纽约时代广场，载誉归来。</p>
   <div class="chronicle_chakan"><a href="javascript:chronicleOpen2();">查看</a></div>
   <div class="chronicle_line"></div>
   <div class="chronicle_2013">2013年 东宝金融网成立啦！</div>
   <p>2013年12月31日 <br />东宝金融财富网络科技（北京）有限公司正式成立</p>
  </div>
</div>
<div class="aboutus_bottom">
  <div class="reg_jinxin"><a href="../ssl/Register.html">立即注册东宝金融网</a></div>
  <p>暂时不，回<a href="">首页</a>逛逛</p>
</div>
<!--页脚start-->
<div id="footer"></div>
<!--页脚end-->
<div class="dialog-overlay" id="dialog_id" style="display: none;"></div>
<!--遮罩层-->
<!--亿级注册资本begin-->
<div class="layer-body certiAll" id="layer2" style="display: none;"><a class="layer-close certi" href="javascript:layerClose();"></a><img src="../common/images/yyzz2.jpg" alt="东宝金融网-营业执照"/></div>
<!--亿级注册资本end-->
<!--百万级风险保障金begin-->
<div class="layer-body certiAll" id="layer" style="display: none;"><a class="layer-close certi" href="javascript:layerClose();"></a><a href="../common/images/certi-big.jpg" target="_blank" ><img src="../common/images/certi.png" title="请点击查看大图"></a></div>
<!--百万级风险保障金end-->

<!--荣誉墙begin-->
<!-- <div class="layer-body honour_layer" id="layer4" style="display: none;"> <a class="layer-close" href="javascript:layerClose();"></a>
<div class="moveleft"></div>
<div class="moveright"></div>
 <div class="honour_body">
  <div class="honour_wap">
   <div class="honour_box"><img src="../common/images/rongyu_1.jpg" alt="东宝金融网-2014中国互联网金融最具竞争力企业"/></div>
   <div class="honour_box" style="padding-top:118px;"><img src="../common/images/rongyu_2.jpg" alt="东宝金融网-2014年度互联网金融企业社会责任“金蜘蛛”优秀企业奖"/></div>
   <div class="honour_box" style="padding-top:118px;"><img src="../common/images/rongyu_3.jpg" alt="东宝金融网-2014年度互联网金融企业社会责任“金蜘蛛”互联网金融创新奖"/></div>
   <div class="honour_box"><img src="../common/images/rongyu_4.jpg" alt="东宝金融网-2014中国互联网金融最佳风险控制企业"/></div>
   <div class="honour_box" style="padding-top:118px;"><img src="../common/images/rongyu_5.jpg" alt="东宝金融网-2014年度中国互联网金融创新奖"/></div>
   <div class="honour_box" style="padding-top:118px;"><img src="../common/images/rongyu_6.jpg" alt="东宝金融网-杰出互联网金融创新者"/></div>
   <div class="honour_box"><img src="../common/images/rongyu_7.jpg" alt="东宝金融网-网上交易保障中心会员单位"/></div>
   <div class="honour_box" style="padding-top:118px;"><img src="../common/images/rongyu_8.jpg" alt="东宝金融网-优秀p2p平台创新奖"/></div>
   <div class="honour_box" style="padding-top:118px;"><img src="../common/images/rongyu_9.jpg" alt="东宝金融网-中国电子金融产业联盟会员单位"/></div>
   <div class="honour_box"><img src="../common/images/rongyu_10.jpg" alt="东宝金融网-年度领军人物大奖"/></div>
   <div class="honour_box" style="padding-top:118px;"><img src="../common/images/rongyu_11.jpg" alt="东宝金融网-中国金融认证中心授权书"/></div>
   <div class="honour_box" style="padding-top:118px;"><img src="../common/images/rongyu_12.jpg" alt="东宝金融网-中国互联网协会互联网金融工作委员会发起单位"/></div>
   <div class="honour_box"><img src="../common/images/rongyu_13.jpg" /></div>
   <div class="honour_box" style="padding-top:118px;"><img src="../common/images/rongyu_14.jpg" /></div>
   <div class="honour_box" style="padding-top:118px;"><img src="../common/images/rongyu_15.jpg" /></div> 
   <div class="honour_box"><img src="../common/images/rongyu_16.jpg" /></div>
   <div class="honour_box" style="padding-top:118px;"><img src="../common/images/rongyu_17.jpg" /></div>
   <div class="honour_box" style="padding-top:118px;"><img src="../common/images/rongyu_18.jpg" /></div> 
   <div class="honour_box"><img src="../common/images/rongyu_19.jpg" /></div>
   <div class="honour_box" style="padding-top:118px;"><img src="../common/images/rongyu_20.jpg" /></div>
   <div class="honour_box" style="padding-top:118px;"><img src="../common/images/rongyu_21.jpg" /></div>
   <div class="honour_box" style="padding-top:118px;"><img src="../common/images/rongyu_22.jpg" /></div>
   <div class="honour_box"><img src="../common/images/rongyu_23.jpg" /></div>
   <div class="honour_box" style="padding-top:118px;"><img src="../common/images/rongyu_24.jpg" /></div>
   <div class="honour_box" style="padding-top:118px;"><img src="../common/images/rongyu_25.jpg" /></div>
   <div class="honour_box"><img src="../common/images/rongyu_26.jpg" /></div>
   <div class="honour_box" style="padding-top:118px;"><img src="../common/images/rongyu_27.jpg" /></div>
   <div class="honour_box" style="padding-top:118px;"><img src="../common/images/rongyu_28.jpg" /></div>    
  </div>
 </div>
</div> -->
<!--荣誉墙end-->
<!--大事记2016年begin-->
<div class="layer-body chronicle_layer" id="chronicle_2016" style="height: 0px; overflow: hidden;display: block;"> <a class="layer-close" href="javascript:layerClose();"></a>
<div class="chronicle_wap" id="scrollbar5">
<div class="scrollbar"><div class="track"><div class="thumb"></div></div></div><!--模拟滚动条,改变span的top值来改变滚动条位置-->
<div class="viewport">
 <div class="overview">
 <div class="chronicle_cnt">
 <div class="memorabilia_body" style="height:216px;">
    <div class="year year2016">2016</div>
    <div class="memorabilia_box" style="top:30px;"> <i class="dian"></i><i class="arr"></i>
      <p>2016年1月东宝金融网正式接入央行旗下中国支付清算协会的互联网金融风险信息共享系统</p>
    </div>
    <div class="memorabilia_box right_memorabilia" style="top:100px;"> <i class="dian"></i><i class="arr"></i>
      <p>2016年2月7-10日，东宝金融网以“金梦想 信中国”之大气形象登陆纽约时代广场，与世界人民一起共贺新春</p>
    </div>
  </div>
 </div>
 </div>
</div>
</div>
</div>
<!--大事记2016年end-->
<!--大事记2015年begin-->
<div class="layer-body chronicle_layer" id="chronicle" style="height:0px; overflow:hidden;display: block;"> <a class="layer-close" href="javascript:layerClose();"></a>
<div class="chronicle_wap" id="scrollbar1">
<div class="scrollbar"><div class="track"><div class="thumb"></div></div></div><!--模拟滚动条,改变span的top值来改变滚动条位置-->
<div class="viewport">
 <div class="overview">
 <div class="chronicle_cnt">
 <div class="memorabilia_body" style="height:750px;">
    <div class="year year_2015">2015</div>
    <div class="memorabilia_box" style="top:30px;"> <i class="dian"></i><i class="arr"></i>
      <p>2015年12月，推出两周年用户感恩回馈系列活动，跨界营销持续升级，东宝金融网品牌深入人心</p>
    </div>
    <div class="memorabilia_box right_memorabilia" style="top:100px;"> <i class="dian"></i><i class="arr"></i>
      <p>2015年11月18日，策划“金粉节”活动，跨界合作再度深入，东宝金融网的公众知名度再次提升</p>
    </div>
    <div class="memorabilia_box" style="top:170px;"> <i class="dian"></i><i class="arr"></i>
      <p>2015年10月，累计投资服务规模突破100亿元</p>
    </div>
<!--     <div class="memorabilia_box right_memorabilia" style="top:240px;"> <i class="dian"></i><i class="arr"></i> -->
<!--       <p>2015年7月22日，东宝金融网接受央行条法司司长张涛调研，积极为互联网金融监管新政建言献策</p> -->
<!--     </div> -->
     <div class="memorabilia_box right_memorabilia" style="top:240px;"> <i class="dian"></i><i class="arr"></i>
      <p>2015年7月18日，《互联网金融指导意见》出台，东宝金融网作为唯一一家企业代表，接受央视新闻采访谈新政</p>
    </div>
    <div class="memorabilia_box " style="top:310px;"> <i class="dian"></i><i class="arr"></i>
      <p>2015年6月17日，东宝金融网运营主体名称正式由“东宝金融财富网络科技（北京）有限公司”变更为“东宝金融金融信息服务（北京）有限公司”
      </p>
    </div>
<!--     <div class="memorabilia_box" style="top:450px;"> <i class="dian"></i><i class="arr"></i> -->
<!--       <p>2015年6月12日，中关村管委会主任郭洪莅临东宝金融网，畅谈互联网金融2.0创新</p> -->
<!--     </div> -->
    <div class="memorabilia_box right_memorabilia" style="top:380px;"> <i class="dian"></i><i class="arr"></i>
      <p>2015年4月28日，东宝金融网携手中国政法大学金融创新与互联网金融法制研究中心建立互联网金融风控实验室</p>
    </div>
    <div class="memorabilia_box" style="top:450px;"> <i class="dian"></i><i class="arr"></i>
      <p>2015年4月22日，东宝金融网成为中关村互联网金融服务中心登记备案企业</p>
    </div>
    <div class="memorabilia_box right_memorabilia" style="top:520px;"> <i class="dian"></i><i class="arr"></i>
      <p>2015年4月13日-15日，东宝金融网创始人、COO安丹方受邀出席在美国纽约举行的全球网贷行业盛会Lendit 2015峰会，参与圆桌对话</p>
    </div>
    <div class="memorabilia_box" style="top:590px;"> <i class="dian"></i><i class="arr"></i>
      <p>2015年3月30日，东宝金融网创始人、COO安丹方受邀成为“中国互联网金融三十人论坛（CIF30）”成员兼中关村互联网金融研究院研究专家</p>
    </div>  
<!--     <div class="memorabilia_box right_memorabilia" style="top:800px;"> <i class="dian"></i><i class="arr"></i> -->
<!--       <p>2015年1月15日，央行研究局局长陆磊带队深入海淀区互联网金融中心，就互联网金融发展进行调研，东宝金融网作为四家代表企业之一接受调研并参与行业座谈</p> -->
<!--     </div> -->
  </div>
 </div>
 </div>
</div>
</div>
</div>
<!--大事记end-->
<!--大事记2014年begin-->
<div class="layer-body chronicle_layer" id="chronicle_2014" style="height: 0px; overflow: hidden;display: block;"> <a class="layer-close" href="javascript:layerClose();"></a>
<div class="chronicle_wap" id="scrollbar4">
<div class="scrollbar"><div class="track"><div class="thumb"></div></div></div><!--模拟滚动条,改变span的top值来改变滚动条位置-->
<div class="viewport">
 <div class="overview">
 <div class="chronicle_cnt">
 <div class="memorabilia_body" style="height:690px;">
    <div class="year year2014">2014</div>
    <div class="memorabilia_box" style="top:30px;"> <i class="dian"></i><i class="arr"></i>
      <p>2014年9月19日受邀参加互联网金融企业社会责任峰会，斩获 “优秀企业奖”和“互联网金融创新奖”</p>
    </div>
    <div class="memorabilia_box right_memorabilia" style="top:100px;"> <i class="dian"></i><i class="arr"></i>
      <p>2014年8月26日东宝金融网COO出席2014中国互联网大会·互联网金融中国峰会，提出建立P2P行业标准</p>
    </div>
    <div class="memorabilia_box" style="top:170px;"> <i class="dian"></i><i class="arr"></i>
      <p>2014年8月23日东宝金融网协办清华五道口金融学院主办中国互联网金融季谈，同政府、行业领导探讨趋势与风控：中国P2P发展之路</p>
    </div>
    <div class="memorabilia_box right_memorabilia" style="top:240px;"> <i class="dian"></i><i class="arr"></i>
      <p>2014年8月7日受邀海淀金融办，参加中关村网络借贷（P2P）联盟成立筹备会议，成为首批发起单位</p>
    </div>
    <div class="memorabilia_box" style="top:310px;"> <i class="dian"></i><i class="arr"></i>
      <p>2014年7月25日 成为中国互联网协会互联网金融工作委员会会员单位</p>
    </div>
    <div class="memorabilia_box right_memorabilia" style="top:380px;"> <i class="dian"></i><i class="arr"></i>
      <p>2014年 6月18日2014海峡两岸P2P网贷行业高峰论坛，斩获“年度领军人物大奖”和“年度成长价值”两项大奖</p>
    </div>
    <div class="memorabilia_box" style="top:450px;"> <i class="dian"></i><i class="arr"></i>
      <p>2014年5月10日东宝金融网作为互联网金融新锐唯一代表与清华大学全球金融论坛共谋改革-发展新征程</p>
    </div>
<!--     <div class="memorabilia_box right_memorabilia" style="top:520px;"> <i class="dian"></i><i class="arr"></i> -->
<!--       <p>2014年5月9日东宝金融网正式加入“中国互联网金融行业协会”</p> -->
<!--     </div> -->
    <div class="memorabilia_box right_memorabilia" style="top:520px;"> <i class="dian"></i><i class="arr"></i>
      <p>2014年5月6日东宝金融网深受关注凤凰财经、网易新闻、央视网、中国日报网等知名媒体竞相报道</p>
    </div>
    <div class="memorabilia_box" style="top:590px;"> <i class="dian"></i><i class="arr"></i>
      <p>2014年4月30日清华五道口金融学院特邀东宝金融网代表行业共商互联网金融与财富管理变革</p>
    </div>
  </div>
 </div>
 </div>
</div>
</div>
</div>
<!--大事记end-->
<script type="text/javascript" src="../common/js/trafficStatistics.js"></script>
<script type="text/javascript" src="../common/js/move.js"></script>
<script type="text/javascript">
//左右切换
var pid=5,i=0,j=0,x=0;
var otime=null;
$(".aboutus li").hover(function(){
  $(this).addClass('hover_img');
},function(){
  $(this).removeClass('hover_img');
	});

//关闭界面
function layerClose(){
	document.getElementById("dialog_id").style.display = 'none';
	document.getElementById("layer").style.display = 'none';	
	document.getElementById("layer2").style.display = 'none';
	//document.getElementById("layer3").style.display = 'none';	
	//document.getElementById("layer4").style.display = 'none';
	document.getElementById("chronicle").style.height = '0px';
	document.getElementById("chronicle_2014").style.height = '0px';
	document.getElementById("chronicle_2016").style.height = '0px';
	

}
//弹出界面
function layerOpen(){
	document.getElementById("dialog_id").style.display = 'block';
	document.getElementById("layer").style.display = 'block';	
}
function yyzzOpen(){
	document.getElementById("dialog_id").style.display = 'block';
	document.getElementById("layer2").style.display = 'block';	
}
function mapOpen(){
	//document.getElementById("dialog_id").style.display = 'block';
	//document.getElementById("layer3").style.display = 'block';	
}
function imgOpen(j){
	/* i=j;
	if(j!=0){
		$(".moveleft").removeClass('moveleft-not')
	}else{
		$(".moveleft").addClass('moveleft-not')
	}
	if(j!=($(".anquan-box,.honour_box").length-1)){
		$(".moveright").removeClass('moveright-not')
	}else{
		$(".moveright").addClass('moveright-not')		
	}
  	$(".honour_wap").css({marginLeft:-425*i})
	document.getElementById("dialog_id").style.display = 'block';
	document.getElementById("layer4").style.display = 'block';	 */
}
initDisplay();//
//wall();//荣誉墙

//大事记弹窗
function chronicleOpen(){
 document.getElementById("dialog_id").style.display = 'block';
 document.getElementById("chronicle").style.height = '600px';
}
function chronicleOpen2(){
	 document.getElementById("dialog_id").style.display = 'block';
	 document.getElementById("chronicle_2014").style.height = '600px';
	}
function chronicleOpen3(){
	 document.getElementById("dialog_id").style.display = 'block';
	 document.getElementById("chronicle_2016").style.height = '600px';
	}
</script>
</body>
</html>
