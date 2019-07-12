var singerId = getQueryString("singerId");

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
    loadSingerData(singerId);
});

function loadSingerData(singerId) {

    $.post(
        "http://127.0.0.1:8080/aloadsingerInfo.do?singerId=" + singerId,
        {},
        function (data) {
            document.getElementById("singerName").value = data.data[0].singerName;

            document.getElementById("sex").value = data.data[0].sex;

            document.getElementById("singerNation").value = data.data[0].singerNation;

            document.getElementById("pic120").value = data.data[0].pic120;

            document.getElementById("pic300").value = data.data[0].pic300;

            document.getElementById("fansNum").value = data.data[0].fansNum;

            document.getElementById("songsNum").value = data.data[0].songsNum;

            document.getElementById("albumsNum").value = data.data[0].albumsNum;

            document.getElementById("singerBirthday").value = data.data[0].singerBirthday;

            document.getElementById("prefix").value = data.data[0].prefix;
        }
    );

}


function modify() {

    var singerName = document.getElementById("singerName").value;

    console.log(singerName);

    var sex = document.getElementById("sex").value;

    console.log(sex);

    var singerNation = document.getElementById("singerNation").value;

    console.log(singerNation);

    var pic120 = document.getElementById("pic120").value;

    console.log(pic120);

    var pic300 = document.getElementById("pic300").value;

    console.log(pic300);

    var fansNum = document.getElementById("fansNum").value;

    console.log(fansNum);

    var songsNum = document.getElementById("songsNum").value;

    console.log(songsNum);

    var albumsNum = document.getElementById("albumsNum").value;

    console.log(albumsNum);

    var singerBirthday = document.getElementById("singerBirthday").value;

    console.log(singerBirthday);

    var prefix = document.getElementById("prefix").value;

    console.log(prefix);


    $.ajax({
        type: "post",//请求类型
        url: "http://127.0.0.1:8080/amodifysinger.do",//请求的url
        data: {
            'singerId': singerId,
            "singerName": singerName,
            "sex": sex,
            "singerNation": singerNation,
            "pic120": pic120,
            "pic300": pic300,
            "fansNum": fansNum,
            "songsNum": songsNum,
            "albumsNum": albumsNum,
            "singerBirthday": singerBirthday,
            "prefix": prefix
        },//请求参数
        dataType: "json",//ajax接口（请求url）返回的数据类型
        success: function (data) {//data：返回数据（json对象）
            if (data.result == false) {
                alert("修改失败！");
                window.location.href = "./modify.html"
            } else {
                alert("修改成功！");
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
