
var albumId = getQueryString("albumId");

//获取歌手的id
function getQueryString(name) {
    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
}

//在页面加载前将前一次页面传过来的singerId来显示数据
$(function () {
    loadAlbumData(albumId);
});

function loadAlbumData(albumId) {

    $.post(
        "http://127.0.0.1:8080/aquerybyalbumid.do?albumId=" + albumId,
        {},
        function (data) {
            document.getElementById("albumName").value = data.data[0].albumName;

            document.getElementById("albumImg").value = data.data[0].albumImg;

            document.getElementById("albumPicLag").value = data.data[0].albumPicLag;


        }
    );

}


function modify() {

    var albumName = document.getElementById("albumName").value;

    console.log(albumName);

    var albumImg = document.getElementById("albumImg").value;

    console.log(albumImg);

    var albumPicLag = document.getElementById("albumPicLag").value;

    console.log(albumPicLag);

    $.ajax({
        type: "post",//请求类型
        url: "http://127.0.0.1:8080/amodifyalbum.do",//请求的url
        data: {
            'albumId': albumId,
            'albumName': albumName,
            'albumImg': albumImg,
            // 'albumMessage':albumMessage,
            'albumPicLag': albumPicLag
        },//请求参数
        dataType: "json",//ajax接口（请求url）返回的数据类型
        success: function (data) {//data：返回数据（json对象）
            if (data.result == false) {
                alert("修改失败！");
                //window.location.href = "./modify.html"
            } else {
                alert("修改成功！");
                window.location.reload();
            }
        },
        error: function (data) {//当访问时候，404，500 等非200的错误状态码

        }
    });
}

function back(){

    window.location.href = "./list.html";

}
