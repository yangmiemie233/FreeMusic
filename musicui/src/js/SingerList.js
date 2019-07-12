var singerId = getQueryString("singerId");
window.onload=function (ev) {
    getSinger();
    getSingerSong(1)
  };


//获取歌手的id
function getQueryString(name) {
    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
}
function getSinger() {
    $.post(
        "http://localhost:8080/loadsingerInfo.do?singerId=" + singerId,
        {},
        function (data) {
            var data = data.data[0];
            console.log(data);
            document.getElementById("singerImage").src = data.pic300;
            document.getElementById("singername").innerText = deleteHtmlTag(data.singerName);
            document.getElementById("songsNum").innerText = data.songsNum;
            document.getElementById("albumNum").innerText = data.albumsNum;
            document.getElementById("fansNum").innerText = (data.fansNum / 10000).toFixed(2) + "W";
            document.getElementById("englishName").innerText = deleteHtmlTag(data.singerEnglishName);
            document.getElementById("nation").innerText = data.singerNation;
            document.getElementById("language").innerText = data.singerLanguage;
            document.getElementById("area").innerText = data.singerArea;
            document.getElementById("xingzuo").innerText = data.constellation;
        }
    )
}
function deleteHtmlTag(str){
    str = str.replace(/<[^>]+>|&[^>]+;/g,"").trim();//去掉所有的html标签和&nbsp;之类的特殊符合
    return str;
}

function getOneSingerSong(indexpage) {
    $.post(
        "http://localhost:8080/loadsingerInfo/singerSongs.do?singerId=" + singerId + "&indexpage=" + indexpage,
        {},
        function (data) {
            var a=data.data.data[0].songRid;
            getmusic(a)
        }

    )
}
function getSingerSong(indexpage) {

    $.post(
        "http://localhost:8080/loadsingerInfo/singerSongs.do?singerId=" + singerId + "&indexpage=" + indexpage,
        {},
        function (data) {
            var str = "";
            console.log(data.data.data);
            for (var i = 0; i < data.data.data.length; i++) {
                str += "<li class=\"song_item flex_c\">\n" +
                    "                        <div class=\"song_rank flex_c\"><!---->\n" +
                    "                            <div class=\"rank_num\">\n" +
                    "                                <span class=\"playing\" style=\"display: none;\">\n" +
                    "                                    <span class=\"side1 pause\"></span> <span class=\"side3 pause\"></span></span>\n" +
                    "                                <span>" + (i + 1) + "</span></div> <!---->\n" +
                    "                            <img alt=\"\" class=\"cover\"\n" +
                    "                                 data-src=\"http://img4.kuwo.cn/star/albumcover/120/63/49/990007113.jpg\"\n" +
                    "                                 src=\"" + data.data.data[i].pic120 + "\"\n" +
                    "                                 lazy=\"loaded\"></div>\n" +
                    "                        <div class=\"song_name flex_c\">\n" +
                    "                            <div title=\"\" class=\"name\" style='display: block;position: absolute'><a href='#' style='color: black;text-decoration: none' onclick='getSongInfo("+data.data.data[i].songId+","+data.data.data[i].songAlbumId+")'> " + data.data.data[i].songName + "</a></div>\n"
                    + "<a class='iconfont' style='display: block;font-size:18px;cursor: pointer;float: right;margin-left: 150px;text-decoration: none;color: #ffd128'href='#' onclick='getmusic(" + data.data.data[i].songRid + ")' >&#xe624;</a>" +
                    "                        </div>\n" +
                    "\n" +
                    "                        <div class=\"song_album\"><span title=\"\"><a href='#' style='text-decoration: none;color: rgba(59,56,64,0.75)' onclick='getAlbumInfo("+data.data.data[i].songAlbumId+")'>" + data.data.data[i].songAlbumName + "</a> </span></div>\n" +
                    "                        <div class=\"song_time\"><span>" + data.data.data[i].songTimeMinutes + "</span></div>\n" +
                    "                    </li>"
            }


            var page = " <ul>\n" +
                "                <li><a href=\"#\" onclick=\"getSingerSong(1)\">首页</a></li>\n" +
                "                <li><a href=\"#\" onclick=\"getSingerSong(" + (data.data.indexpage - 1) + ")\"><span class=\"iconfont\">&#xe659;</span></a></li>\n" +
                "                <li><a href=\"#\">" + data.data.indexpage + "/" + data.data.countpage + "</a></li>\n" +
                "                <li><a href=\"#\" onclick=\"getSingerSong(" + (data.data.indexpage + 1) + ")\"><span class=\"iconfont\">&#xe65b;</span></a></li>\n" +
                "                <li><a href=\"#\" onclick=\"getSingerSong(" + (data.data.countpage) + ")\">尾页</a></li>\n" +
                "            </ul>";

            //$("#singerInner").val(str);
            document.getElementById("singerUl").style.display="";
            document.getElementById("singerSongs").innerHTML = str;
            document.getElementById("pageSize").innerHTML = page;


        }
    )
}

function getSingerAlbum(indexpage) {
    document.getElementById("getSingerSong").className = "a";
    document.getElementById("getSingerAlbum").className = "active";
    $.post(
        "http://localhost:8080/loadsingerInfo/singerAlbums.do?singerId=" + singerId + "&indexpage=" + indexpage,
        {},
        function (data) {
            var a = data.data.data;
            console.log(a);
            var str = "";
            for (var i = 0; i < a.length; i++) {
                str += "<div class=\"album_item\">\n" +
                    "                      <div class=\"album_item_img\">\n" +
                    "                          <a href='#' onclick='getAlbumInfo("+a[i].albumId+")'><img src=\"" + a[i].albumImg + "\"></a>\n" +
                    "                      </div>\n" +
                    "                      <div class=\"album_item_text\">\n" +
                    "                          <p class=\"album_name\"><a href='#' onclick='getAlbumInfo("+a[i].albumId+")'>" + a[i].albumName + "</a></p>\n" +
                    "                          <p class=\"album_time\">" + a[i].albumDate + "</p>\n" +
                    "                      </div>\n" +
                    "                  </div>"
            }
            document.getElementById("singerSongs").innerHTML = str;
            document.getElementById("singerUl").style.display="none";
            var page = " <ul>\n" +
                "                <li><a href=\"#\" onclick=\"getAlbumInfo(1)\">首页</a></li>\n" +
                "                <li><a href=\"#\" onclick=\"getAlbumInfo(" + (data.data.indexpage - 1) + ")\"><span class=\"iconfont\">&#xe659;</span></a></li>\n" +
                "                <li><a href=\"#\">" + data.data.indexpage + "/" + data.data.countpage + "</a></li>\n" +
                "                <li><a href=\"#\" onclick=\"getAlbumInfo(" + (data.data.indexpage + 1) + ")\"><span class=\"iconfont\">&#xe65b;</span></a></li>\n" +
                "                <li><a href=\"#\" onclick=\"getAlbumInfo(" + (data.data.countpage) + ")\">尾页</a></li>\n" +
                "            </ul>";

            document.getElementById("pageSize").innerHTML = page;
        }
    )

}
function getAlbumInfo(songAlbumId) {
    window.location.href = "../src/AlbumList.html?songAlbumId="+songAlbumId;
}
function getmusic(songRid) {
    $.post(
        "http://127.0.0.1:8080/getMusic.do",
        {"songRid":songRid},
        function(data){
            var playm = $("#musicplay")[0];
            playm.src = data.data;
            playm.play();
        }
    );

}
function getSongInfo(songId,songAlbumId) {
    window.location.href = "../src/playDetail.html?songId=" + songId+"&songAlbumId="+songAlbumId;
}



