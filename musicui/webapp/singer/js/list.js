function loadSingerData(indexpage) {

    $.post(
        "http://127.0.0.1:8080/aloadsinger.do?indexpage=" + indexpage,
        {},
        function (data) {


            var ul_item = $("#singerList");
            ul_item.empty();


            for (var i = 0; i < data.data.data.length; i++) {
                var li_item = "<tr>" +
                    "<td tabindex=\"0\" class=\"sorting_1\">" + data.data.data[i].singerName + "</td>" +
                    " <td>" + data.data.data[i].sex + "</td>" +
                    " <td>" + data.data.data[i].singerNation + "</td> " +
                    " <td>" + data.data.data[i].singerBirthday + "</td> " +
                    " <td>" + data.data.data[i].fansNum + "</td> " +
                    "<td style=\"width:150px\">" +
                    "<a href=\"javascript:void(0)\" onclick= del(" + data.data.data[i].singerId + ")>删除</a>&nbsp;&nbsp;|&nbsp;&nbsp;" +
                    "<a href=\"javascript:void(0)\" onclick= modify(" + data.data.data[i].singerId + ")>编辑</a>&nbsp;&nbsp;" +
                    "</td>" +
                    //"<td><a onclick= modify("+data.data.data[i].singerId +") >修改</a>&nbsp;&nbsp;|<a onclick= del("+data.data.data[i].singerId +") >删除</a></td>"+
                    "</tr>";

                ul_item.append(li_item);
            };





            $("#pagerutil").empty();

            var pager = " <a href=\"#\" onclick=\"loadSingerData(1)\" style=\"color:#000\">首页</a>\n" +
                "            <a href=\"#\" onclick=\"loadSingerData(" + (data.data.indexpage - 1) + ")\" style=\"color:#000\">上页</a>\n" +
                "            <a href=\"#\" style=\"color:#000\">" + data.data.indexpage + "/" + data.data.countpage + "</a>\n" +
                "            <a href=\"#\" onclick=\"loadSingerData(" + (data.data.indexpage + 1) + ")\" style=\"color:#000\">下页</a>\n" +
                "            <a href=\"#\" onclick=\"loadSingerData(" + (data.data.countpage) + ")\" style=\"color:#000\">尾页</a>";

            $("#pagerutil").append(pager);


        }
    );


}


$(function () {
    loadSingerData(1);
});

function del(singerId) {

    if(window.confirm("确定删除？")){
    $.post(
        "http://127.0.0.1:8080/adelsinger.do?singerId=" + singerId,
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

function modify(singerId) {

    window.location.href = "./modify.html?singerId=" + singerId;

}


function querySingerNames() {

    var querySingerName = document.getElementById("querySingerName").value;

    $.post(
        "http://127.0.0.1:8080/aquerybysingername.do?singerName=" + querySingerName,
        {},
        function (data) {

            var ul_item = $("#singerList");

            ul_item.empty();


            for (var i = 0; i < data.data.length; i++) {
                var li_item = "<tr>" +
                    "<td tabindex=\"0\" class=\"sorting_1\">" + data.data[i].singerName + "</td>" +
                    " <td>" + data.data[i].sex + "</td>" +
                    // " <td><textarea style=\"width: 500px\">"+data.data.data[i].albumMessage+"</textarea></td> " +
                    " <td>" + data.data[i].singerNation + "</td> " +
                    " <td>" + data.data[i].singerBirthday + "</td> " +
                    " <td>" + data.data[i].fansNum + "</td> " +
                    "<td style=\"width:200px\">" +
                    "<a href=\"javascript:void(0)\" onclick= del(" + data.data[i].singerId + ")>删除</a>&nbsp;&nbsp;|&nbsp;&nbsp;" +
                    "<a href=\"javascript:void(0)\" onclick= modify(" + data.data[i].singerId + ")>编辑</a>&nbsp;&nbsp;" +
                    "</td>" +
                    //"<td><a href=\"modify.html\"><button onclick= modify(data.data.data[i].albumId)>修改</button></a><button>删除</button></td>"+
                    "</tr>";

                ul_item.append(li_item);

                $("#pagerutil").empty();
            };



        }
    );





}