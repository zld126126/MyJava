function resetUserRole(){
    $('#addUserRole')[0].reset();
    alert('重置成功');
}

function resetEditUserRole() {
    $('#editUserRole')[0].reset();
    alert('重置成功');
}

function addUserRole(){
    //alert(userid+permissionid);
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "/userrole/addUserRole" ,//url
        data:$('#addUserRole').serialize(),
        success: function (result) {
            console.log(result);//打印服务端返回的数据(调试用)
            if (result.status == 0) {
                alert(result.msg);
                pagedocument('/menu/roledocument');
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

function toEditPage(roleid) {
    //alert('编辑');
    $("#pagedocument").load("/menu/edituserrole", {"roleid" : ""+roleid});
}

function deleteUserInfo(roleid) {
    alert("删除");
}

function editUserRole() {
    //alert('保存');
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "/userrole/editUserRole" ,//url
        data: $('#editUserRole').serialize(),
        success: function (result) {
            console.log(result);//打印服务端返回的数据(调试用)
            if (result.status == 0) {
                alert(result.msg);
                pagedocument('/menu/roledocument');
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