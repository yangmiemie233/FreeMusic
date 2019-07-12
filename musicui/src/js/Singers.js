/*查询歌手数据*/
function getSingerDate(indexpage) {
    $.post(
        "http://localhost:8080/loadsinger.do?indexpage=" + indexpage,
        {},
        function (data) {
            console.log(data.data.data);
            var str = "";
            for (var i = 0; i < data.data.data.length; i++) {
                str += "<div class='artist_item' style='display: inline-block'>" +
                    "<div class='artist'>" +
                    "<div class='pic_out' style='margin: 20px 20px 0 15px;border-radius: 50%;overflow: hidden;width: 200px;height: 200px;border: 1px solid #cccccc'>" +
                    "<a href='#' onclick='singerInfo(" + data.data.data[i].singerId + ")'>" +
                    "<img src='" + data.data.data[i].pic300 + "'style='width: 100%' />" +
                    "</a>" +
                    "</div>" +
                    "<p align='center' style='text-align: center;font-size: 18px;font-weight: bold;'>" +
                    "<a href='#'>" +
                    data.data.data[i].singerName
                    +
                    " </a></p>" +
                    "<p align='center' style='margin-top: 10px'>" + data.data.data[i].songsNum + "首歌曲" + "</p></div></div>";

            }

            document.getElementById("pushSinger").innerHTML = str;
            var page = " <ul>\n" +
                "                <li><a href=\"#\" onclick=\"getSingerDate(1)\">首页</a></li>\n" +
                "                <li><a href=\"#\" onclick=\"getSingerDate(" + (data.data.indexpage - 1) + ")\"><span class=\"iconfont\">&#xe659;</span></a></li>\n" +
                "                <li><a href=\"#\">" + data.data.indexpage + "/" + data.data.countpage + "</a></li>\n" +
                "                <li><a href=\"#\" onclick=\"getSingerDate(" + (data.data.indexpage + 1) + ")\"><span class=\"iconfont\">&#xe65b;</span></a></li>\n" +
                "                <li><a href=\"#\" onclick=\"getSingerDate(" + (data.data.countpage) + ")\">尾页</a></li>\n" +
                "            </ul>";
            document.getElementById("pageSize").innerHTML = page;

        }
    )

}

$(function () {
    getSingerDate(1);
});

//歌手链接
function singerInfo(singerId) {
    window.location.href = "../src/SingerList.html?singerId=" + singerId;
}
//通过种类获取歌手数据
function getSingerByType(type, indexpage) {
    console.log(type);
    $.post(
        "http://localhost:8080/loadsingerInfo/getSingerByType.do?type=" + type + "&indexpage=" + indexpage,
        {},
        function (data) {
            console.log(data.data.data);
            var str = "";
            for (var i = 0; i < data.data.data.length; i++) {
                str += "<div class='artist_item' style='display: inline-block'>" +
                    "<div class='artist'>" +
                    "<div class='pic_out' style='margin: 20px 20px 0 15px;border-radius: 50%;overflow: hidden;width: 200px;height: 200px;border: 1px solid #cccccc'>" +
                    "<a href='#' onclick='singerInfo(" + data.data.data[i].singerId + ")'>" +
                    "<img src='" + data.data.data[i].pic300 + "'style='width: 100%' />" +
                    "</a>" +
                    "</div>" +
                    "<p align='center' style='text-align: center;font-size: 18px;font-weight: bold;'>" +
                    "<a href='#'>" +
                    data.data.data[i].singerName
                    +
                    " </a></p>" +
                    "<p align='center' style='margin-top: 10px'>" + data.data.data[i].songsNum + "首歌曲" + "</p></div></div>";

            }

            document.getElementById("pushSinger").innerHTML = str;
            var page = " <ul>\n" +
                "                <li><a href=\"#\" onclick=\"getSingerByType(" + type + ",1)\">首页</a></li>\n" +
                "                <li><a href=\"#\" onclick=\"getSingerByType(" + type + "," + (data.data.indexpage - 1) + ")\"><span class=\"iconfont\">&#xe659;</span></a></li>\n" +
                "                <li><a href=\"#\">" + data.data.indexpage + "/" + data.data.countpage + "</a></li>\n" +
                "                <li><a href=\"#\" onclick=\"getSingerByType(" + type + "," + (data.data.indexpage + 1) + ")\"><span class=\"iconfont\">&#xe65b;</span></a></li>\n" +
                "                <li><a href=\"#\" onclick=\"getSingerByType(" + type + "," + (data.data.countpage) + ")\">尾页</a></li>\n" +
                "            </ul>";
            document.getElementById("pageSize").innerHTML = page;

        }
    )
}

