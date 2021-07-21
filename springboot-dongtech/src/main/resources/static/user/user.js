function changeWorkStatus(userid) {
    //alert("改变工作状态");
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "/userinfo/changeWorkStatus" ,//url
        data: {'userid':userid},
        success: function (result) {
            console.log(result);//打印服务端返回的数据(调试用)
            if (result.status == 0) {
                alert(result.msg);
                pagedocument('/menu/userdocument');
            }else{
                alert(result.msg);
            }
            ;
        },
        error : function() {
            alert("服务器繁忙,请稍后重试");
        }
    });
}

function resetUserInfo(){
    //alert('重置');
    $('#addUserInfo')[0].reset();
    alert('重置成功');
}

function addUseInfo(){
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "/userinfo/addUserInfo" ,//url
        data: $('#addUserInfo').serialize(),
        success: function (result) {
            console.log(result);//打印服务端返回的数据(调试用)
            if (result.status == 0) {
                alert(result.msg);
                pagedocument('/menu/userdocument');
            }else{
                alert(result.msg);
            }
            ;
        },
        error : function() {
            alert("服务器繁忙,请稍后重试");
        }
    });
}

function resetEditUserInfo(){
    //alert('重置');
    $('#editUserInfo')[0].reset();
    alert('重置成功');
}

function editUseInfo(){
    //alert('提交被编辑用户信息');
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "/userprofile/editUserProfile" ,//url
        data: $('#editUserInfo').serialize(),
        success: function (result) {
            console.log(result);//打印服务端返回的数据(调试用)
            if (result.status == 0) {
                alert(result.msg);
                pagedocument('/menu/userdocument');
            }else{
                alert(result.msg);
            }
            ;
        },
        error : function() {
            alert("服务器繁忙,请稍后重试");
        }
    });
}

function toEditPage(userid) {
    //alert('跳转到编辑页面'+userId);
    $("#pagedocument").load("/menu/edituserinfo", {"userid" : ""+userid})
}

function deleteUserInfo(userid) {
    //alert('删除操作'+userid);
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "/userinfo/deleteUserInfo" ,//url
        data: {'userid':userid},
        success: function (result) {
            console.log(result);//打印服务端返回的数据(调试用)
            if (result.status == 0) {
                alert(result.msg);
                pagedocument('/menu/userdocument');
            }else{
                alert(result.msg);
            }
            ;
        },
        error : function() {
            alert("服务器繁忙,请稍后重试");
        }
    });
}
