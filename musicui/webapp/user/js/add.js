var userId = getQueryString("userId");

//获取歌手的id
function getQueryString(name) {
    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
}



function add() {

    var userName = document.getElementById("userName").value;

    console.log(userName);


    var userPassword = document.getElementById("userPassword").value;

    console.log(userPassword);

    var userEmail = document.getElementById("userEmail").value;

    console.log(userEmail);



    $.ajax({
        type: "post",//请求类型
        url: "http://127.0.0.1:8080/aadduser.do",//请求的url
        data: {
            'userId': userId,
            'userName': userName,
            'userPassword': userPassword,
            'userEmail': userEmail,
        },//请求参数
        dataType: "json",//ajax接口（请求url）返回的数据类型
        success: function (data) {//data：返回数据（json对象）
            if (data.result == false) {
                alert("添加失败！");
                window.location.href = "./modify.html"
            } else {
                alert("添加成功！");
                window.location.reload();
                // window.history.back(-1);
            }
        },
        error: function (data) {//当访问时候，404，500 等非200的错误状态码

        }
    });



}

function back(){

    window.location.href = "./list.html";

}
