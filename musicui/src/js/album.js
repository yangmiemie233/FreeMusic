
var songAlbumId = getQueryString("songAlbumId");


//获取歌曲
function getQueryString(name) {

    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
}
window.onload=function (ev) {
    getAlbumSongs(songAlbumId,1);
    getAlbumInfos(songAlbumId);
};
function getAlbumInfos(songAlbumId) {
    $.post(
        "http://localhost:8080/loadSongAblum.do?albumId="+songAlbumId,
        {},
        function (data) {
            console.log(data.data[0]);
            var dataAlbum=data.data[0];
            document.getElementById("albumImg").src=dataAlbum.albumImg;
            document.getElementById("introduce").innerText=deleteHtmlTag(dataAlbum.albumMessage);
            document.getElementById("songtitle").innerText=deleteHtmlTag(dataAlbum.albumName);
            document.getElementById("songnames").innerText=deleteHtmlTag(dataAlbum.albumSingerName);
            document.getElementById("publishTime").innerText=dataAlbum.albumDate;
        }
    )
}
function deleteHtmlTag(str){
    str = str.replace(/<[^>]+>|&[^>]+;/g,"").trim();//去掉所有的html标签和&nbsp;之类的特殊符合
    return str;
}

function getAlbumSongs(songAlbumId,indexpage) {
    $.post(
        "http://localhost:8080/getAlbumAllSongs.do?songAlbumId="+songAlbumId+"&indexpage="+indexpage,
        {},
        function (data) {
            console.log(data.data.data);
            var songs=data.data.data;
            var str="";
            for (var i=0;i<songs.length;i++){
              str+="<li class=\"song_item flex_c current\">\n" +
                  "                    <div class=\"song_rank flex_c\">\n" +
                  "                        <span class=\"rank_num\">"+(i+1)+"</span>\n" +
                  "                       </div>\n" +
                  "                    <div class=\"song_name flex_c\">\n" +
                  "                        <span title=\"热爱就一起\" class=\"name\"><a href='#' onclick='getSongInfo("+songs[i].songId+","+songs[i].songAlbumId+")'>"+songs[i].songName+"</a> </span>\n" +
                  "<a href='#' onclick='getmusic("+songs[i].songRid+")' style='color: rgba(255,209,40,0.75);position: absolute;margin-left: 200px'><span class='iconfont' style='font-size: 22px;'>&#xe624;</span></a>"+
                  "<a href='#' onclick='#' style='color: rgba(212,69,97,0.75);position: absolute;margin-left: 250px'><span class='iconfont' style='font-size: 22px;'>&#xe6a8;</span></a>"+
                  "                    </div>\n" +
                  "                    <div class=\"song_artist\"><span title=\"\"><a href='#' onclick='singerInfo("+songs[i].singerId+")'> "+  deleteHtmlTag(songs[i].singerName)+"</a></span></div>\n" +
                  "                   \n" +
                  "                    <div class=\"song_time\"><span>"+songs[i].songTimeMinutes+"</span></div>\n" +
                  "                </li>"
            }
            document.getElementById("albums").innerHTML=str;
            var page=" <ul>\n" +
                "                <li><a href=\"#\" onclick='getAlbumSongs(1)'>首页</a></li>\n" +
                "                <li><a href=\"#\" onclick='getAlbumSongs("+songAlbumId+","+(data.data.indexpage-1)+")'><span class=\"iconfont\">&#xe659;</span></a></li>\n" +
                "                <li><a href=\"#\">"+data.data.indexpage+"/"+(data.data.countpage)+"</a></li>\n" +
                "                <li><a href=\"#\" onclick='getAlbumSongs("+songAlbumId+","+(data.data.indexpage+1)+")'><span class=\"iconfont\">&#xe65b;</span></a></li>\n" +
                "                <li><a href=\"#\" onclick='getAlbumSongs("+songAlbumId+","+(data.data.countpage)+")'>尾页</a></li>\n" +
                "            </ul>"

            document.getElementById("pageSize").innerHTML = page;


        }
    )
}

//获取歌手id去歌曲详情页
function getSongInfo(songId,songAlbumId) {
    window.location.href = "../src/playDetail.html?songId=" + songId+"&songAlbumId="+songAlbumId;
}
//歌手链接
function singerInfo(singerId) {
    window.location.href = "../src/SingerList.html?singerId=" + singerId;}
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


