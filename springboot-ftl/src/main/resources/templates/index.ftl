<Html>
<head>
    <title>springboot-ftl</title>
</head>
<body>
1.list 例子
<table border="1" cellspacing="0" cellpadding="20">
    <!--1. list-->
			<#list childrenList as children>
			   <tr>
                   <td>${children.name}</td><td>${children.age}</td><td>${children.gender}</td>
               </tr>
            </#list>
</table>     <br>

2.if 例子<br>
        <#if flag==1>
           result:flag==1
        </#if><br><br>

3.if elseif else 例子<br>
        <#if condition==1>
            result:condition==1
        <#elseif condition==2>
            result:condition==2
        <#elseif condition==3>
            result:condition==3
        <#else>
            result:condition=${condition}
        </#if>
<br><br>

4.converter lowercase to uppercase 转换大小写例子<br>
<!--3.  ?代替为.  后面接内建函数,将字符串转为大写-->
result:${home?upper_case}<br><br>

5.map 例子<br>
<!--4.迭代map中的key/value对-->
        <#list userMap?keys as key>
            ${key}--${userMap[key]!("default value")}<br>
        </#list>

<body>
</html>