$(function () {
    var searchWords = decodeURI(getQueryString("searchContent"), "utf-8");
    searchSongs(searchWords);
});

function getQueryString(name) {
    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
}

function searchSongs(searchWords) {
    $("#search").val(searchWords);
    var indexpage=1;
    var str="";
    $.post(
        "http://localhost:8080/searchSongs.do?indexpage"+indexpage,
        {'searchWords':searchWords},
        function (data) {
            if(data!=null){
                var searchData=data.data.data;
                var str ="<div class=\"list_head head_name_search\" style=\"\" data-v-edb94b72>\n" +
                    "                                        <ul class=\"flex_c\" data-v-edb94b72>\n" +
                    "                                            <li class=\"head_num\" data-v-edb94b72>序号</li>\n" +
                    "                                            <li class=\"head_name\" data-v-edb94b72>歌曲</li>\n" +
                    "                                            <li class=\"head_artist\" data-v-edb94b72>歌手</li>\n" +
                    "                                            <li class=\"head_album\" data-v-edb94b72>专辑</li>\n" +
                    "                                            <li class=\"head_time\" data-v-edb94b72>时长</li>\n" +
                    "                                        </ul>\n" +
                    "                                    </div>\n" +
                    "                                    <ul id=\"singer_list\" class=\"singer_list\">";
                for (var i=0;i<searchData.length;i++){
                    str+="<li class=\"song_item flex_c\">\n" +
                        "                                            <div class=\"song_rank flex_c\">\n" +
                        "                                                <!---->\n" +
                        "                                                <div class=\"rank_num\">\n" +
                        "                                                    <span>"+(i+1)+"</span>\n" +
                        "                                                </div>\n" +
                        "                                                <!---->\n" +
                        "                                                <img alt=\"\" class=\"cover\" data-src=\"http://img4.kuwo.cn/star/albumcover/120/63/49/990007113.jpg\" src=\""+searchData[i].pic120+"\" lazy=\"loaded\"></div>\n" +
                        "                                            <div class=\"song_name flex_c\">\n" +
                        "                                                <span title=\"来自天堂的魔鬼\" class=\"name\">"+searchData[i].songName+"</span>\n" +
                        "                                            </div>\n" +
                        "                                            <div class=\"song_artist\"><span title=\"邓紫棋\">"+searchData[i].singerName+"</span></div>\n" +
                        "                                            <div class=\"song_album\"><span title=\"新的心跳\">"+searchData[i].songAlbumName+"</span></div>\n" +
                        "                                            <div class=\"song_time\"><span>"+searchData[i].songTimeMinutes+"</span></div>\n" +
                        "                                        </li>"
                }
                str+="</ul>"
                document.getElementById("searchInfo").innerHTML = str;
            }else {
                document.getElementById("searchInfo").innerHTML = "<p>抱歉，没有搜索到相关歌曲</p>";
            }

            /*            var page = " <ul>\n" +
                            "                <li><a href=\"#\" onclick=\"searchSongs(searchWords,1)\">首页</a></li>\n" +
                            "                <li><a href=\"#\" onclick=\"getSingerDate(" + (data.data.indexpage - 1) + ")\"><span class=\"iconfont\">&#xe659;</span></a></li>\n" +
                            "                <li><a href=\"#\">" + data.data.indexpage + "/" + data.data.countpage + "</a></li>\n" +
                            "                <li><a href=\"#\" onclick=\"getSingerDate(" + (data.data.indexpage + 1) + ")\"><span class=\"iconfont\">&#xe65b;</span></a></li>\n" +
                            "                <li><a href=\"#\" onclick=\"getSingerDate(" + (data.data.countpage) + ")\">尾页</a></li>\n" +
                            "            </ul>";
                        document.getElementById("pageSize").innerHTML = page;*/
        }
    )
}

function searchAlbum() {
    var searchWords = $("#search").val();
    var indexpage = 1;
    $.post(
        "http://localhost:8080/searchAlbum.do?indexpage"+indexpage,
        {'searchWords':searchWords},
        function (data) {
            console.log(data.data.data);
            var searchData = data.data.data;
            var str="<div class=\"rec_list\">";
            for(var j=0;j<searchData.length;j++){

                str+="\t<div class=\"item\">\n" +
                    "\t\t<div class=\"pic_out\">\n" +
                    "\t\t\t<div class=\"cover\">\n" +
                    "\t\t\t\t<span class=\"play icon_play\">\n" +
                    "\t\t\t\t\t<i class=\"iconfont icon-icon_play_1\"></i>\n" +
                    "\t\t\t\t</span>\n" +
                    "\t\t\t</div>\n" +
                    "\t\t\t<img src=\""+searchData[j].albumImg+"\" class=\"pic\">\n" +
                    "\t\t</div>\n" +
                    "\t\t<p class=\"name\"><span title=\"热爱就一起\">"+searchData[j].albumName+"</span></p>\n" +
                    "\t\t<p class=\"count\" style=\"display:;\">"+searchData[j].albumDate+"</p>\n" +
                    "\t</div>"
            }
            str+="</div>";
            document.getElementById("searchInfo").innerHTML = str;
        }
    )
}

function searchSinger() {
    var searchWords = $("#search").val();
    var indexpage = 1;
    $.post(
        "http://localhost:8080/searchSinger.do?indexpage"+indexpage,
        {'searchWords':searchWords},
        function (data) {
            var searchData = data.data.data;
            var str = "<div class=\"rec_list\">";
            for(var j=0;j<searchData.length;j++){
                str+="<div class=\"artist_item\" style=\"display: inline-block\">\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t<div class=\"artist\">\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"pick_out\">\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t<img src=\""+searchData[j].pic120+"\">\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t<p align=\"center\">\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t<span>"+searchData[j].singerName+"</span>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t</p>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t<p align=\"center\">"+searchData[j].songsNum+"</p>\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
                    "\t\t\t\t\t\t\t\t\t\t</div>"
            }
            str+="</div>";
            document.getElementById("searchInfo").innerHTML = str;
        }
    )
}
