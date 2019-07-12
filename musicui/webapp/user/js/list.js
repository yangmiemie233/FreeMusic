function loadUserData(indexpage) {

    $.post(
        "http://127.0.0.1:8080/aloaduser.do?indexpage=" + indexpage,
        {},
        function (data) {


            var ul_item = $("#userList");
            ul_item.empty();


            for (var i = 0; i < data.data.data.length; i++) {
                var li_item = "<tr>" +
                    "<td tabindex=\"0\" class=\"sorting_1\">" + data.data.data[i].userName + "</td>" +
                    " <td>" + data.data.data[i].userPassword + "</td> " +
                    " <td>" + data.data.data[i].userEmail + "</td> " +
                    "<td style=\"width:200px\">" +
                    "<a href=\"javascript:void(0)\" onclick= del(" + data.data.data[i].userId + ")>删除</a>&nbsp;&nbsp;|&nbsp;&nbsp;" +
                    "<a href=\"javascript:void(0)\" onclick= modify(" + data.data.data[i].userId + ")>编辑</a>&nbsp;&nbsp;|&nbsp;&nbsp;" +
                    "<a href=\"javascript:void(0)\" onclick= checksongsheet(" + data.data.data[i].userId + ")>歌单查看</a>" +
                    "</td>" +
                    //"<td><a onclick= modify("+data.data.data[i].singerId +") >修改</a>&nbsp;&nbsp;|<a onclick= del("+data.data.data[i].singerId +") >删除</a></td>"+
                    "</tr>";

                ul_item.append(li_item);
            };





            $("#pagerutil").empty();

            var pager = " <a href=\"#\" onclick=\"loadUserData(1)\" style=\"color:#000\">首页</a>\n" +
                "            <a href=\"#\" onclick=\"loadUserData(" + (data.data.indexpage - 1) + ")\" style=\"color:#000\">上页</a>\n" +
                "            <a href=\"#\" style=\"color:#000\">" + data.data.indexpage + "/" + data.data.countpage + "</a>\n" +
                "            <a href=\"#\" onclick=\"loadUserData(" + (data.data.indexpage + 1) + ")\" style=\"color:#000\">下页</a>\n" +
                "            <a href=\"#\" onclick=\"loadUserData(" + (data.data.countpage) + ")\" style=\"color:#000\">尾页</a>";

            $("#pagerutil").append(pager);


        }
    );


}


$(function () {
    loadUserData(1);
});

function del(userId) {
    if(window.confirm("确定删除？")){
    $.post(
        "http://127.0.0.1:8080/adeluser.do?userId=" + userId,
        {},
        function (data) {
            if (data.result == false) {
                alert("删除失败！");
            } else {
                alert("删除成功！");
                window.location.reload();
            }


        }
    );
    }


}

function modify(userId) {

    window.location.href = "./modify.html?userId=" + userId;

}

function checksongsheet(userId){

    window.location.href = "../usersonglist/list.html?userId=" + userId;

}


function queryUserNames() {

    var queryUserName = document.getElementById("queryUserName").value;

    $.post(
        "http://127.0.0.1:8080/aquerybyusername.do?userName=" + queryUserName,
        {},
        function (data) {

            var ul_item = $("#userList");

            ul_item.empty();


            for (var i = 0; i < data.data.length; i++) {
                var li_item = "<tr>" +
                    "<td tabindex=\"0\" class=\"sorting_1\">" + data.data[i].userName + "</td>" +
                    " <td>" + data.data[i].userPassword + "</td> " +
                    " <td>" + data.data[i].userEmail + "</td> " +
                    "<td style=\"width:200px\">" +
                    "<a href=\"javascript:void(0)\" onclick= del(" + data.data[i].userId + ")>删除</a>&nbsp;&nbsp;|&nbsp;&nbsp;" +
                    "<a href=\"javascript:void(0)\" onclick= modify(" + data.data[i].userId + ")>编辑</a>&nbsp;&nbsp;|&nbsp;&nbsp;" +
                    "<a href=\"javascript:void(0)\" onclick= checksongsheet(" + data.data[i].userId + ")>歌单查看</a>" +
                    //"<td><a href=\"modify.html\"><button onclick= modify(data.data.data[i].albumId)>修改</button></a><button>删除</button></td>"+
                    "</tr>";

                ul_item.append(li_item);

                $("#pagerutil").empty();
            };

        }
    );

}


function add() {
    window.location.href = "./add.html";
}





