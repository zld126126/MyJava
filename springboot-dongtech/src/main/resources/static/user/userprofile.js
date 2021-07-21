function resetUserProfile(){
    //alert('重置');
    $('#editUserProfile')[0].reset();
    alert('重置成功');
}

function toEditPage(userid){
    //alert('编辑');
    $("#pagedocument").load("/menu/edituserprofile", {"userid" : ""+userid});
}

function editUserProfile(){
    //alert("编辑");
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "/userprofile/editUserProfile" ,//url
        data: $('#editUserProfile').serialize(),
        success: function (result) {
            console.log(result);//打印服务端返回的数据(调试用)
            if (result.status == 0) {
                alert(result.msg);
                pagedocument('/menu/profiledocument');
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