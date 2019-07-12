var url=["loadNewSong","loadHotSong","loadAmericanSong","loadHanYuSong","loadSpeedSong"];
var elemt=["newSong","hotSong","americanSong","loadHanYuSong","loadSpeedSong"];
$(document).ready(
    //轮番图

    /*歌手推荐*/
    function getSingerDate() {
        $.post(
            "http://localhost:8080/loadsinger.do",
            {},
            function (data) {

                var str = "";
                for (var i = 0; i < 5; i++) {
                    str += "<div class='artist_item' style='display: inline-block'>" +
                        "<div class='artist'>" +
                        "<div class='pic_out' style='margin: 20px 20px 0 15px;border-radius: 50%;overflow: hidden;width: 200px;height: 200px;border: 1px solid #cccccc'>" +
                        "<a href='#' onclick='singerInfo(" + data.data.data[i].singerId + ")'>" +
                        "<img src='" + data.data.data[i].pic300 + "'style='width: 100%' />" +
                        "</a>" +
                        "</div>" +
                        "<p align='center' style='text-align: center;font-size: 18px;font-weight: bold;'>" +
                        "<a href='#' onclick='singerInfo(" + data.data.data[i].singerId + ")'>" +
                        data.data.data[i].singerName
                        +
                        " </a></p>" +
                        "<p align='center' style='margin-top: 10px'>" + data.data.data[i].songsNum + "首歌曲" + "</p></div></div>";
                }
                document.getElementById("pushSinger").innerHTML = str;
            }
        )

    },
    RankingList(url[0],1,elemt[0]),
    RankingList(url[1],1,elemt[1]),
    RankingList(url[2],1,elemt[2]),
    RankingList(url[3],1,elemt[3]),
    RankingList(url[4],1,elemt[4]),
);




/*获取新歌,热歌榜*/
function RankingList(url,indexpage,e) {
    $.post(
        "http://localhost:8080/"+url+".do?indexpage=" + indexpage,
        {},
        function (data) {
           console.log(data.data.data);
            var str = "<li data-v-e2a063a6>\n" +
                "                            <div class=\"top_img top1\" data-v-e2a063a6></div>\n" +
                "                            <div class=\"info\" data-v-e2a063a6>\n" +
                "                                <p title=\""+data.data.data[0].songName+"\" class=\"song_name\" data-v-e2a063a6><a href='#' onclick='getSongInfo("+data.data.data[0].songId+","+data.data.data[0].songAlbumId+")'> "+data.data.data[0].songName+"</a></p>\n" +
                "                                <p title=\""+data.data.data[0].singerName+"\" class=\"artist\" data-v-e2a063a6><a href='#' onclick='singerInfo("+data.data.data[0].singerId+")'>"+data.data.data[0].singerName+"</a></p>\n" +
                "                            </div>\n" +
                "                        </li>\n" +
                "                        <li data-v-e2a063a6>\n" +
                "                            <div class=\"top_img top2\" data-v-e2a063a6></div>\n" +

                "                            <div class=\"info\" data-v-e2a063a6>\n" +
                "                                <p title=\""+data.data.data[1].songName+"\" class=\"song_name\" data-v-e2a063a6><a href='#' onclick='getSongInfo("+data.data.data[1].songId+","+data.data.data[1].songAlbumId+")'> "+data.data.data[1].songName+"</a></p>\n" +
                "                                <p title=\""+data.data.data[1].singerName+"\" class=\"artist\" data-v-e2a063a6><a href='#' onclick='singerInfo("+data.data.data[1].singerId+")'>"+data.data.data[1].singerName+"</a></p>\n" +
                "                            </div>\n" +
                "                        </li>\n" +
                "                        <li data-v-e2a063a6>\n" +
                "                            <div class=\"top_img top3\" data-v-e2a063a6></div>\n" +

                "                            <div class=\"info\" data-v-e2a063a6>\n" +
                "                                <p title=\""+data.data.data[2].songName+"\" class=\"song_name\" data-v-e2a063a6><a href='#'onclick='getSongInfo("+data.data.data[2].songId+","+data.data.data[2].songAlbumId+")'> "+data.data.data[2].songName+"</a></p>\n" +
                "                                <p title=\""+data.data.data[2].singerName+"\"\n" +
                "                                   class=\"artist\" data-v-e2a063a6><a href='#' onclick='singerInfo("+data.data.data[2].singerId+")'>\n" +
                "                                   "+data.data.data[2].singerName+"</a></p>\n" +
                "                            </div>\n" +
                "                        </li>";

            for (var i = 3; i < 5; i++) {
                str += "<li data-v-e2a063a6>\n" +
                    "                            <div class=\"index\" data-v-e2a063a6>"+(i+1)+"</div>\n" +
                    "                            <!---->\n" +
                    "                            <div class=\"info\" data-v-e2a063a6>\n" +
                    "                                <p title=\"" + data.data.data[i].songName + "\" class=\"song_name\" data-v-e2a063a6><a href='#' onclick='getSongInfo("+data.data.data[i].songId+","+data.data.data[i].songAlbumId+")'>" + data.data.data[i].songName + "</a> </p>\n" +
                    "                                <p title=\"" + data.data.data[i].singerName + "\" class=\"artist\" data-v-e2a063a6><a href='#' onclick='singerInfo("+data.data.data[i].singerId+")'>" + data.data.data[i].singerName + "</a></p>\n" +
                    "                            </div>\n" +
                    "                        </li>"
            }
            document.getElementById(e).innerHTML = str;
        }
    )
}


function singerInfo(singerId) {
    window.location.href = "../src/SingerList.html?singerId=" + singerId;
}

//获取歌手id去歌曲详情页
function getSongInfo(songId,songAlbumId) {
    window.location.href = "../src/playDetail.html?songId=" + songId+"&songAlbumId="+songAlbumId;
}

