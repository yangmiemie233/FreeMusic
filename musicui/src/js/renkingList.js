var url=["loadNewSong","loadHotSong","loadAmericanSong","loadHanYuSong","loadSpeedSong"];

window.onload=function (ev) {
    getSongListSpeed(1)
};
function getSongListSpeed(indexpage) {

   $.post(
       "http://localhost:8080/loadSpeedSong.do?indexpage=" + indexpage,
       {},
       function (data) {
           console.log(data.data.data)
           var a=data.data.data;
           var str="";
           for (var i=0;i<a.length;i++){
               str+="      <li class=\"song_item rankFlex_c\">\n" +
                   "                                            <div class=\"song_rank rankFlex_c\">\n" +
                   "                                                <div class=\"rank_num \">"+(i+1)+"</div>\n" +
                   "                                                <div class=\"status\">\n" +
                   "                                                    <span>\n" +
                   "                                                        <i class=\"is_new iconfont icon_icon_new_\">&#xe6d5;</i>\n" +
                   "                                                    </span>\n" +
                   "                                                </div>\n" +
                   "                                                <img class=\"cover\"\n" +
                   "                                                     src=\""+a[i].pic120+"\">\n" +
                   "                                            </div>\n" +
                   "                                            <div class=\"song_name rankFlex_c\">\n" +
                   "                                                <span title=\""+a[i].songName+"\" class=\"name\"><a href='#' onclick='getSongInfo("+a[i].songId+","+a[i].songAlbumId+")'>"+a[i].songName+"</a></span>\n" +
                   "                                                <i class=\"type iconfont icon-tag_wusun\">&#xe696;</i>\n" +
                   "                                           <span class='iconfont' ><a href='#' onclick='getmusic("+a[i].songRid+")' style='color: rgba(255,209,40,0.69);font-size: 18px;padding-left: 20px'>&#xe624;</a></span>"+
                   "                                       <span class='iconfont'><a href='#' style='margin-left: 10px;color: red'>&#xe6a8;</a> </span>"+
                   "                                            </div>\n" +
                   "                                            <div class=\"song_artist\">\n" +
                   "                                                <span title=\""+a[i].singerId+"\"><a href='#' onclick='singerInfo("+a[i].singerId+")'>"+a[i].singerName+"</a> </span>\n" +
                   "                                            </div>\n" +
                   "                                            <div class=\"song_album\">\n" +
                   "                                                <span title=\""+a[i].songAlbumName+"\"><a href='#' onclick='getAlbumInfo("+a[i].songAlbumId+")'> "+a[i].songAlbumName+"</a></span>\n" +
                   "                                            </div>\n" +
                   "                                            <div class=\"song_time\">\n" +
                   "                                                <span>"+a[i].songTimeMinutes+"</span>\n" +
                   "                                            </div>\n" +
                   "                                        </li>"
           }
           document.getElementById("songInner").innerHTML=str;

           var page=" <ul>\n" +
               "                <li><a href=\"#\" onclick='getSongListSpeed(1)'>首页</a></li>\n" +
               "                <li><a href=\"#\" onclick='getSongListSpeed("+(data.data.indexpage-1)+")'><span class=\"iconfont\">&#xe659;</span></a></li>\n" +
               "                <li><a href=\"#\">"+data.data.indexpage+"/"+(data.data.countpage)+"</a></li>\n" +
               "                <li><a href=\"#\" onclick='getSongListSpeed("+(data.data.indexpage+1)+")'><span class=\"iconfont\">&#xe65b;</span></a></li>\n" +
               "                <li><a href=\"#\" onclick='getSongListSpeed("+(data.data.countpage)+")'>尾页</a></li>\n" +
               "            </ul>"

           document.getElementById("pageSize").innerHTML = page;document.getElementById("title").innerText="阿狸飙升榜"
       }
   )
}
function getSongListNew(indexpage) {

    $.post(
        "http://localhost:8080/loadNewSong.do?indexpage=" + indexpage,
        {},
        function (data) {
            console.log(data.data.data)
            var a=data.data.data;
            var str="";
            for (var i=0;i<a.length;i++){
                str+="      <li class=\"song_item rankFlex_c\">\n" +
                    "                                            <div class=\"song_rank rankFlex_c\">\n" +
                    "                                                <div class=\"rank_num \">"+(i+1)+"</div>\n" +
                    "                                                <div class=\"status\">\n" +
                    "                                                    <span>\n" +
                    "                                                        <i class=\"is_new iconfont icon_icon_new_\">&#xe6d5;</i>\n" +
                    "                                                    </span>\n" +
                    "                                                </div>\n" +
                    "                                                <img class=\"cover\"\n" +
                    "                                                     src=\""+a[i].pic120+"\">\n" +
                    "                                            </div>\n" +
                    "                                            <div class=\"song_name rankFlex_c\">\n" +
                    "                                                <span title=\""+a[i].songName+"\" class=\"name\"><a href='#' onclick='getSongInfo("+a[i].songId+","+a[i].songAlbumId+")'>"+a[i].songName+"</a></span>\n" +
                    "                                                <i class=\"type iconfont icon-tag_wusun\">&#xe696;</i>\n" +
                    "                                           <span class='iconfont' ><a href='#' onclick='getmusic("+a[i].songRid+")' style='color: rgba(255,209,40,0.69);font-size: 18px;padding-left: 20px'>&#xe624;</a></span>"+
                    "                                       <span class='iconfont'><a href='#' style='margin-left: 10px;color: red'>&#xe6a8;</a> </span>"+
                    "                                            </div>\n" +
                    "                                            <div class=\"song_artist\">\n" +
                    "                                                <span title=\""+a[i].singerId+"\"><a href='#' onclick='singerInfo("+a[i].singerId+")'> "+a[i].singerName+"</a></span>\n" +
                    "                                            </div>\n" +
                    "                                            <div class=\"song_album\">\n" +
                    "                                                <span title=\""+a[i].songAlbumName+"\"><a href='#' onclick='getAlbumInfo("+a[i].songAlbumId+")'> "+a[i].songAlbumName+"</a></span>\n" +
                    "                                            </div>\n" +
                    "                                            <div class=\"song_time\">\n" +
                    "                                                <span>"+a[i].songTimeMinutes+"</span>\n" +
                    "                                            </div>\n" +
                    "                                        </li>"
            }
            document.getElementById("songInner").innerHTML=str;
            document.getElementById("pageSize").innerHTML = page;document.getElementById("title").innerText="阿狸新歌榜"


            var page=" <ul>\n" +
                "                <li><a href=\"#\" onclick='getSongListNew(1)'>首页</a></li>\n" +
                "                <li><a href=\"#\" onclick='getSongListNew("+(data.data.indexpage-1)+")'><span class=\"iconfont\">&#xe659;</span></a></li>\n" +
                "                <li><a href=\"#\">"+data.data.indexpage+"/"+(data.data.countpage)+"</a></li>\n" +
                "                <li><a href=\"#\" onclick='getSongListNew("+(data.data.indexpage+1)+")'><span class=\"iconfont\">&#xe65b;</span></a></li>\n" +
                "                <li><a href=\"#\" onclick='getSongListNew("+(data.data.countpage)+")'>尾页</a></li>\n" +
                "            </ul>"

            document.getElementById("pageSize").innerHTML = page;
        }
    )
}
function getSongListHot(indexpage) {

    $.post(
        "http://localhost:8080/loadHotSong.do?indexpage=" + indexpage,
        {},
        function (data) {
            console.log(data.data.data)
            var a=data.data.data;
            var str="";
            for (var i=0;i<a.length;i++){
                str+="      <li class=\"song_item rankFlex_c\">\n" +
                    "                                            <div class=\"song_rank rankFlex_c\">\n" +
                    "                                                <div class=\"rank_num \">"+(i+1)+"</div>\n" +
                    "                                                <div class=\"status\">\n" +
                    "                                                    <span>\n" +
                    "                                                        <i class=\"is_new iconfont icon_icon_new_\">&#xe6d5;</i>\n" +
                    "                                                    </span>\n" +
                    "                                                </div>\n" +
                    "                                                <img class=\"cover\"\n" +
                    "                                                     src=\""+a[i].pic120+"\">\n" +
                    "                                            </div>\n" +
                    "                                            <div class=\"song_name rankFlex_c\">\n" +
                    "                                                <span title=\""+a[i].songName+"\" class=\"name\"><a href='#' onclick='getSongInfo("+a[i].songId+","+a[i].songAlbumId+")'>"+a[i].songName+"</a></span>\n" +
                    "                                                <i class=\"type iconfont icon-tag_wusun\">&#xe696;</i>\n" +
                    "                                           <span class='iconfont' ><a href='#' onclick='getmusic("+a[i].songRid+")' style='color: rgba(255,209,40,0.69);font-size: 18px;padding-left: 20px'>&#xe624;</a></span>"+
                    "                                       <span class='iconfont'><a href='#' style='margin-left: 10px;color: red'>&#xe6a8;</a> </span>"+

                    "                                            </div>\n" +

                    "                                            <div class=\"song_artist\">\n" +
                    "                                                <span title=\""+a[i].singerId+"\"><a href='#' onclick='singerInfo("+a[i].singerId+")'> "+a[i].singerName+"</a></span>\n" +
                    "                                            </div>\n" +
                    "                                            <div class=\"song_album\">\n" +
                    "                                                <span title=\""+a[i].songAlbumName+"\"><a href='#' onclick='getAlbumInfo("+a[i].songAlbumId+")'> "+a[i].songAlbumName+"</a></span>\n" +
                    "                                            </div>\n" +
                    "                                            <div class=\"song_time\">\n" +
                    "                                                <span>"+a[i].songTimeMinutes+"</span>\n" +
                    "                                            </div>\n" +
                    "                                        </li>"
            }
            document.getElementById("songInner").innerHTML=str;
            document.getElementById("pageSize").innerHTML = page;document.getElementById("title").innerText="阿狸热歌榜"

            var page=" <ul>\n" +
                "                <li><a href=\"#\" onclick='getSongListHot(1)'>首页</a></li>\n" +
                "                <li><a href=\"#\" onclick='getSongListHot("+(data.data.indexpage-1)+")'><span class=\"iconfont\">&#xe659;</span></a></li>\n" +
                "                <li><a href=\"#\">"+data.data.indexpage+"/"+(data.data.countpage)+"</a></li>\n" +
                "                <li><a href=\"#\" onclick='getSongListHot("+(data.data.indexpage+1)+")'><span class=\"iconfont\">&#xe65b;</span></a></li>\n" +
                "                <li><a href=\"#\" onclick='getSongListHot("+(data.data.countpage)+")'>尾页</a></li>\n" +
                "            </ul>"

            document.getElementById("pageSize").innerHTML = page;
        }
    )
}
function getSongListAmericanSong(indexpage) {

    $.post(
        "http://localhost:8080/loadAmericanSong.do?indexpage=" + indexpage,
        {},
        function (data) {
            console.log(data.data.data)
            var a=data.data.data;
            var str="";
            for (var i=0;i<a.length;i++){
                str+="      <li class=\"song_item rankFlex_c\">\n" +
                    "                                            <div class=\"song_rank rankFlex_c\">\n" +
                    "                                                <div class=\"rank_num \">"+(i+1)+"</div>\n" +
                    "                                                <div class=\"status\">\n" +
                    "                                                    <span>\n" +
                    "                                                        <i class=\"is_new iconfont icon_icon_new_\">&#xe6d5;</i>\n" +
                    "                                                    </span>\n" +
                    "                                                </div>\n" +
                    "                                                <img class=\"cover\"\n" +
                    "                                                     src=\""+a[i].pic120+"\">\n" +
                    "                                            </div>\n" +
                    "                                            <div class=\"song_name rankFlex_c\">\n" +
                    "                                                <span title=\""+a[i].songName+"\" class=\"name\"><a href='#' onclick='getSongInfo("+a[i].songId+","+a[i].songAlbumId+")'>"+a[i].songName+"</a></span>\n" +
                    "                                                <i class=\"type iconfont icon-tag_wusun\">&#xe696;</i>\n" +
                    "                                           <span class='iconfont' ><a href='#' onclick='getmusic("+a[i].songRid+")' style='color: rgba(255,209,40,0.69);font-size: 18px;padding-left: 20px'>&#xe624;</a></span>"+
                    "                                       <span class='iconfont'><a href='#' style='margin-left: 10px;color: red'>&#xe6a8;</a> </span>"+
                    "                                            </div>\n" +
                    "                                            <div class=\"song_artist\">\n" +
                    "                                                <span title=\""+a[i].singerId+"\"><a href='#' onclick='singerInfo("+a[i].singerId+")'> "+a[i].singerName+"</a></span>\n" +
                    "                                            </div>\n" +
                    "                                            <div class=\"song_album\">\n" +
                    "                                                <span title=\""+a[i].songAlbumName+"\"><a href='#' onclick='getAlbumInfo("+a[i].songAlbumId+")'> "+a[i].songAlbumName+"</a></span>\n" +
                    "                                            </div>\n" +
                    "                                            <div class=\"song_time\">\n" +
                    "                                                <span>"+a[i].songTimeMinutes+"</span>\n" +
                    "                                            </div>\n" +
                    "                                        </li>"
            }
            document.getElementById("songInner").innerHTML=str;

            var page=" <ul>\n" +
                "                <li><a href=\"#\" onclick='getSongListAmericanSong(1)'>首页</a></li>\n" +
                "                <li><a href=\"#\" onclick='getSongListAmericanSong("+(data.data.indexpage-1)+")'><span class=\"iconfont\">&#xe659;</span></a></li>\n" +
                "                <li><a href=\"#\">"+data.data.indexpage+"/"+(data.data.countpage)+"</a></li>\n" +
                "                <li><a href=\"#\" onclick='getSongListAmericanSong("+(data.data.indexpage+1)+")'><span class=\"iconfont\">&#xe65b;</span></a></li>\n" +
                "                <li><a href=\"#\" onclick='getSongListAmericanSong("+(data.data.countpage)+")'>尾页</a></li>\n" +
                "            </ul>"

            document.getElementById("pageSize").innerHTML = page;
            document.getElementById("pageSize").innerHTML = page;document.getElementById("title").innerText="阿狸欧美榜"

        }
    )
}
function getSongListHan(indexpage) {

    $.post(
        "http://localhost:8080/loadHanYuSong.do?indexpage=" + indexpage,
        {},
        function (data) {
            console.log(data.data.data)
            var a=data.data.data;
            var str="";
            for (var i=0;i<a.length;i++){
                str+="      <li class=\"song_item rankFlex_c\">\n" +
                    "                                            <div class=\"song_rank rankFlex_c\">\n" +
                    "                                                <div class=\"rank_num \">"+(i+1)+"</div>\n" +
                    "                                                <div class=\"status\">\n" +
                    "                                                    <span>\n" +
                    "                                                        <i class=\"is_new iconfont icon_icon_new_\">&#xe6d5;</i>\n" +
                    "                                                    </span>\n" +
                    "                                                </div>\n" +
                    "                                                <img class=\"cover\"\n" +
                    "                                                     src=\""+a[i].pic120+"\">\n" +
                    "                                            </div>\n" +
                    "                                            <div class=\"song_name rankFlex_c\">\n" +
                    "                                                <span title=\""+a[i].songName+"\" class=\"name\"><a href='#' onclick='getSongInfo("+a[i].songId+","+a[i].songAlbumId+")'>"+a[i].songName+"</a></span>\n" +
                    "                                                <i class=\"type iconfont icon-tag_wusun\">&#xe696;</i>\n" +
                    "                                           <span class='iconfont' ><a href='#' onclick='getmusic("+a[i].songRid+")' style='color: rgba(255,209,40,0.69);font-size: 18px;padding-left: 20px'>&#xe624;</a></span>"+
                    "                                       <span class='iconfont'><a href='#' style='margin-left: 10px;color: red'>&#xe6a8;</a> </span>"+
                    "                                            </div>\n" +
                    "                                            <div class=\"song_artist\">\n" +
                    "                                                <span title=\""+a[i].singerName+"\"><a href='#' onclick='singerInfo("+a[i].singerId+")'> "+a[i].singerName+"</a></span>\n" +
                    "                                            </div>\n" +
                    "                                            <div class=\"song_album\">\n" +
                    "                                                <span title=\""+a[i].songAlbumName+"\"><a href='#' onclick='getAlbumInfo("+a[i].songAlbumId+")'> "+a[i].songAlbumName+"</a></span>\n" +
                    "                                            </div>\n" +
                    "                                            <div class=\"song_time\">\n" +
                    "                                                <span>"+a[i].songTimeMinutes+"</span>\n" +
                    "                                            </div>\n" +
                    "                                        </li>"
            }
            document.getElementById("songInner").innerHTML=str;
            document.getElementById("pageSize").innerHTML = page;document.getElementById("title").innerText="阿狸韩语榜"


            var page=" <ul>\n" +
                "                <li><a href=\"#\" onclick='getSongListHan(1)'>首页</a></li>\n" +
                "                <li><a href=\"#\" onclick='getSongListHan("+(data.data.indexpage-1)+")'><span class=\"iconfont\">&#xe659;</span></a></li>\n" +
                "                <li><a href=\"#\">"+data.data.indexpage+"/"+(data.data.countpage)+"</a></li>\n" +
                "                <li><a href=\"#\" onclick='getSongListHan("+(data.data.indexpage+1)+")'><span class=\"iconfont\">&#xe65b;</span></a></li>\n" +
                "                <li><a href=\"#\" onclick='getSongListHan("+(data.data.countpage)+")'>尾页</a></li>\n" +
                "            </ul>"

            document.getElementById("pageSize").innerHTML = page;
        }
    )
}
//歌手链接
function singerInfo(singerId) {
    window.location.href = "../src/SingerList.html?singerId=" + singerId;
}

//歌曲链接
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
function getAlbumInfo(songAlbumId) {
    window.location.href = "../src/AlbumList.html?songAlbumId="+songAlbumId;
}
//获取歌手id去歌曲详情页
function getSongInfo(songId,songAlbumId) {
    window.location.href = "../src/playDetail.html?songId=" + songId+"&songAlbumId="+songAlbumId;
}