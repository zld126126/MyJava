function resetUserProfile(){
    //alert('重置');
    $('#editUserProfile')[0].reset();
    alert('重置成功');
}

function toEditPage(pid) {
    //alert("编辑"+pid);
    $("#pagedocument").load("/menu/editpermission", {"pid" : ""+pid});
}