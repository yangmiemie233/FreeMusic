var songId = getQueryString("songId");
var songAlbumId = getQueryString("songAlbumId");


//获取歌曲
function getQueryString(name) {

    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
};
window.onload = function (ev) {
    getSong(songId),
        getsongAlbum(songAlbumId)
};
var songRid;

function getSong(songId) {

    $.post(
        "http://localhost:8080/getOneSongInfo.do?songId=" + songId,
        {},
        function (data) {
            var a = data.data[0];
            songRid = a.songRid;
            var songWords = data.data[0].songWords.split("/");
            console.log(songWords);
            document.getElementById("songBtn").innerHTML = "" +
                "<button id=\"playBtn\" style=\"width: 150px;color: #333;\" class=\"btn btn-warning\" id=\"play\" onclick='getmusic("+a.songRid+")' >立即播放</button>\n" +
                "                    <button style=\"width: 113px\" class=\"btn btn-secondary\" id=\"likeBtn\">收藏</button>"
            document.getElementById("songNameH").innerText = a.songName;
            document.getElementById("songSinger").innerText = a.singerName;
            document.getElementById("songdate").innerText = a.songDate;
            document.getElementById("songSinger").innerText = a.singerName;
            var str = "";
            for (var i = 0; i < songWords.length; i++) {
                str += "<span style=\"font-size: 14px;color: #6e6e6e\">" + songWords[i] + "</span><br>"
            }
            document.getElementById("lyricContent").innerHTML = str

        }
    )
}

function getsongAlbum(songAlbumId) {
    $.post(
        "http://localhost:8080/loadSongAblum.do?albumId=" + songAlbumId,
        {},
        function (data) {
            console.log(data.data[0])
            var b = data.data[0];
            document.getElementById("albumName").innerText = b.albumName;
            document.getElementById("albumMessage").innerText = b.albumMessage;
            document.getElementById("pic300").src = b.albumImg;
        }
    )
}




    function getmusic(songRid) {
        $.post(
            "http://127.0.0.1:8080/getMusic.do",
            {"songRid": songRid},
            function (data) {
                var playm = $("#musicplay")[0];
                playm.src = data.data;
                playm.play();
            }
        );

    }