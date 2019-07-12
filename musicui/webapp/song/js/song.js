
function showSong(currentPage) {
    var zz=document.getElementById('tb_song');
    zz.innerHTML='';
    var str='';
    $.ajax({
        url:'http://localhost:8080/main/ShowAllSong.do',
        type:'POST',
        data:{
            currentPage:currentPage
        },
        success:function (result) {

               for(var i=0;i<result.data.data.length;i++){
                   str+='<tr role="row" class="odd">'+
                       '<td tabindex="0" class="sorting_1">'+(i+1)+'</td>'+
                       '<td>'+result.data.data[i].songName+'</td>'+
                       '<td>'+result.data.data[i].songAlbumName+'</td>'+
                       '<td>'+result.data.data[i].songTimeMinutes+'</td>'+
                       '<td>' +
                         '<a href="javascript:void(0)" onclick="deleteSong('+result.data.data[i].songId+')">删除</a> &nbsp;&nbsp;|&nbsp;&nbsp;' +
                         '<a href="javascript:void(0)" onclick="editSong('+result.data.data[i].songId+')">编辑</a>' +
                       '</td>'+
                       '</tr>'
               }

               zz.innerHTML=str;

               var pageshow=document.getElementById('pagerutila');
               pageshow.innerHTML='' +
                   '<a href="#" onclick="showSong(1)" style="color:#000">首页</a>&nbsp;' +
                   '<a href="#" onclick="showSong('+(result.data.indexpage-1)+')" style="color:#000">上页</a>&nbsp;' +
                   '<a href="#" style="color:#000">'+result.data.indexpage+'/'+result.data.countpage+'</a>&nbsp;' +
                   '<a href="#" onclick="showSong('+(result.data.indexpage+1)+')">下页</a>&nbsp;' +
                   '<a href="#" onclick="showSong('+result.data.countpage+')" >尾页</a>&nbsp;'



        },
        error:function(){
                zz.innerHTML='后台服务错误 数据查询失败'
        }
    })
    
}

function  deleteSong(Id) {
    console.log('songId:'+Id)
    if(window.confirm('确定删除吗？')){
        $.ajax({
            url:'http://127.0.0.1:8080/main/deleteSong.do',
            type:'POST',
            data:{
                id:Id
            },
            success:function (result) {
                if(result.result==true || result.result=='true'){
                    window.confirm('删除成功')
                    location.reload();
                }else{
                    window.confirm('删除失败')
                    location.reload();
                }
            },
            error:function () {
                window.confirm('服务器访问失败')
            }
        })
    }
}

function editSong(Id) {

    window.location.href='./modify.html?id='+Id;
}

function showSongMessage() {
    var songId=getQueryString('id');
     $.ajax({
         url:'http://127.0.0.1:8080/main/editSong.do',
         type:'POST',
         data:{
            Id:songId
         },
         success:function (result) {
             document.getElementById('songName').value=result.data[0].songName
             document.getElementById('songTimeMinutes').value=result.data[0].songTimeMinutes
             document.getElementById('songRid').value=result.data[0].songRid
             document.getElementById('songDate').value=result.data[0].songDate
             document.getElementById('pic120').value=result.data[0].pic120
             document.getElementById('pic300').value=result.data[0].pic300

         },
         error:function () {
             window.confirm('查找失败')
             window.history.back();
         }
     })

}

function updateSong() {

    var songName=document.getElementById('songName').value;
    var songTimeMinutes=document.getElementById('songTimeMinutes').value;
    var songRid=document.getElementById('songRid').value;
    var songDate=document.getElementById('songDate').value;
    var pic120=document.getElementById('pic120').value;
    var pic300=document.getElementById('pic300').value;

    if(songName.length<=0){
        window.confirm('名称太短了')
    }else if(songTimeMinutes.length<=0){
        window.confirm('歌曲播放时长')
    }
    else if(songRid.length<=0){
        window.confirm('歌曲资源Id')
    }
    else if(songDate.length<=0){
        window.confirm('歌曲发布日期')
    }
    else if(pic120.length<=0){
        window.confirm('小图片链接')
    }
    else if(pic300.length<=0){
        window.confirm('大图片链接')
    }else{
        $.ajax({
            url:'http://127.0.0.1:8080/main/updateSong.do',
            type:'POST',
            data:{
                songId:getQueryString('id'),
                songName:songName,
                songTimeMinutes:songTimeMinutes,
                songRid:songRid,
                songDate:songDate,
                pic120:pic120,
                pic300:pic300
            },
            success:function (result) {
                console.log(result.result)
                if(result.result=='true' || result.result == true){
                    window.confirm('更新成功');
                   // window.location.reload();
                }else{
                    window.confirm('更新失败了')
                }
            },
            error:function () {
                window.confirm('服务器连接失败')
                //location.reload()
            }
        })
    }
}


//href属性名
function getQueryString(name) {
    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
}

function toList() {
    window.location.href="./list.html";
}

function querySong() {
    var name=$("#queryByAlbumName").val();

    if(name.length<=0){
        location.reload();
    }else {
        $.ajax({
            url:'http://127.0.0.1:8080/main/findSong.do',
            type:'POST',
            data:{
                name:name
            },
            success:function (result) {
                console.log(result)
                if(result.result==false || result.result=='false'){
                    window.confirm('查询失败')
                }else{

                    var zz=document.getElementById('tb_song');
                    zz.innerHTML='';
                    var str='';

                    for(var i=0;i<result.data.length;i++){

                        str+='<tr role="row" class="odd">'+
                            '<td tabindex="0" class="sorting_1">'+(i+1)+'</td>'+
                            '<td>'+result.data[i].songName+'</td>'+
                            '<td>'+result.data[i].songAlbumName+'</td>'+
                            '<td>'+result.data[i].songTimeMinutes+'</td>'+
                            '<td>' +
                            '<a href="javascript:void(0)" onclick="deleteSong('+result.data[i].songId+')">删除</a> &nbsp;&nbsp;|&nbsp;&nbsp;' +
                            '<a href="javascript:void(0)" onclick="editSong('+result.data[i].songId+')">编辑</a>' +
                            '</td>'+
                            '</tr>'
                    }

                    zz.innerHTML=str;

                    document.getElementById('pagerutila').innerHTML=''

                }
            },
            error:function () {
                window.confirm("服务器连接失败")
            }
        })
    }
}