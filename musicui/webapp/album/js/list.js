function loadAlbumData(indexpage) {

    $.post(
        "http://127.0.0.1:8080/aloadalbum.do?indexpage=" + indexpage,
        {},
        function (data) {


            var ul_item = $("#albumList");
            ul_item.empty();


            for (var i = 0; i < data.data.data[0].length; i++) {
                var li_item = "<tr>" +
                    "<td tabindex=\"0\" class=\"sorting_1\">" + data.data.data[0][i].albumName + "</td>" +
                    " <td>" + data.data.data[0][i].albumSingerName + "</td>" +
                    // " <td><textarea style=\"width: 500px\">"+data.data.data[i].albumMessage+"</textarea></td> " +
                    " <td>" + data.data.data[0][i].albumDate + "</td> " +
                    " <td>" + data.data.data[0][i].albumPicLag + "</td> " +
                    "<td style=\"width:150px\">" +
                    "<a href=\"javascript:void(0)\" onclick= del(" + data.data.data[0][i].albumId + ")>删除</a>&nbsp;&nbsp;|&nbsp;&nbsp;" +
                    "<a href=\"javascript:void(0)\" onclick= modify(" + data.data.data[0][i].albumId + ")>编辑</a>&nbsp;&nbsp;" +
                    "</td>" +
                    //"<td><a href=\"modify.html\"><button onclick= modify(data.data.data[i].albumId)>修改</button></a><button>删除</button></td>"+
                    "</tr>";

                ul_item.append(li_item);
            };





            $("#pagerutil").empty();

            var pager = " <a href=\"#\" onclick=\"loadAlbumData(1)\" style=\"color:#000\">首页</a>\n" +
                "            <a href=\"#\" onclick=\"loadAlbumData(" + (data.data.indexpage - 1) + ")\" style=\"color:#000\">上页</a>\n" +
                "            <a href=\"#\" style=\"color:#000\">" + data.data.indexpage + "/" + data.data.countpage + "</a>\n" +
                "            <a href=\"#\" onclick=\"loadAlbumData(" + (data.data.indexpage + 1) + ")\" style=\"color:#000\">下页</a>\n" +
                "            <a href=\"#\" onclick=\"loadAlbumData(" + (data.data.countpage) + ")\" style=\"color:#000\">尾页</a>";

            $("#pagerutil").append(pager);


        }
    );


}


$(function () {
    loadAlbumData(1);
});

function modify(albumId) {
    $.post(
        "http://127.0.0.1:8080/aloadalbum.do?modifyid=" + albumId,
        {},
        function (data) {
        }
    );
}


function del(albumId) {
    if(window.confirm("确定删除？")){
    $.post(
        "http://127.0.0.1:8080/adelalbum.do?albumId=" + albumId,
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

function modify(albumId) {

    window.location.href = "./modify.html?albumId=" + albumId;

}

function queryByAlbumNames() {

    var queryByAlbumName = document.getElementById("queryByAlbumName").value;

    $.post(
        "http://127.0.0.1:8080/aquerybyalbumname.do?albumName=" + queryByAlbumName,
        {},
        function (data) {

            var ul_item = $("#albumList");

            ul_item.empty();


            for (var i = 0; i < data.data.length; i++) {
                var li_item = "<tr>" +
                    "<td tabindex=\"0\" class=\"sorting_1\">" + data.data[i].albumName + "</td>" +
                    " <td>" + data.data[i].albumSingerName + "</td>" +
                    // " <td><textarea style=\"width: 500px\">"+data.data.data[i].albumMessage+"</textarea></td> " +
                    " <td>" + data.data[i].albumDate + "</td> " +
                    " <td>" + data.data[i].albumPicLag + "</td> " +
                    "<td style=\"width:200px\">" +
                    "<a href=\"javascript:void(0)\" onclick= del(" + data.data[i].albumId + ")>删除</a>&nbsp;&nbsp;|&nbsp;&nbsp;" +
                    "<a href=\"javascript:void(0)\" onclick= modify(" + data.data[i].albumId + ")>编辑</a>&nbsp;&nbsp;" +
                    "</td>" +
                    //"<td><a href=\"modify.html\"><button onclick= modify(data.data.data[i].albumId)>修改</button></a><button>删除</button></td>"+
                    "</tr>";

                ul_item.append(li_item);

                $("#pagerutil").empty();
            };



        }
    );





}
