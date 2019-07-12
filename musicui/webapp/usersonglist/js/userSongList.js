

function showAll() {

    var userId = getQueryString('userId');
    var ad_usl= document.getElementById('ad_usl');
    var str = '';

    function recom(zz){
        if(zz == '0'){
            return '未推荐';
        }else if(zz == '1'){
            return '推荐歌单';
        }else{
            return '未知状态';
        }
    }

    function recomName(zz){
        if(zz == '0'){
            return '设为推荐';
        }else if(zz == '1'){
            return '取消推荐';
        }else{
            return '操作异常';
        }
    }


    $.ajax({
        url:'http://127.0.0.1:8080/main/queryAllByUserId.do',
        type:'POST',
        data:{
            Id:userId
        },
        success:function (result) {
            if(result.result == false || result.result == 'false'){
                ad_usl.innerHTML = '暂无数据'
            }else{

                for(var i =0 ; i < result.data.length ; i++ ){
                    str +='<tr role="row" class="odd">' +
                            '<td tabindex="0" class="sorting_1">'+(i+1)+'</td>' +
                            '<td>'+result.data[i].songListName+'</td>' +
                            '<td>'+result.data[i].userId+'</td>' +
                            '<td>'+recom(result.data[i].recommend)+'</td>' +
                            '<td style="width: 200px;">' +
                            '   <a href="javascript:void(0)" onclick="deleteUSL('+result.data[i].songListId+')">删除</a> &nbsp;&nbsp;|&nbsp;&nbsp;' +
                            '   <a href="javascript:void(0)" onclick="edit('+result.data[i].songListId+',\' '+result.data[i].songListName+' \')">编辑</a> &nbsp;&nbsp;|&nbsp;&nbsp;' +
                            '   <a href="javascript:void(0)" onclick="setRecommed('+result.data[i].songListId+','+result.data[i].recommend+')">'+recomName(result.data[i].recommend)+'</a> '+
                            '</td>'+
                           '</tr>'
                }

                ad_usl.innerHTML = str ;


            }
        },
        error:function () {


            ad_usl.innerHTML = '暂无数据 服务器连接失败'
        }
    })

}

/**
 *
 * @param Id
 */
function deleteUSL(Id) {
    if(window.confirm("确定删除吗？")){
        $.ajax({
            type:'POST',
            url:'http://127.0.0.1:8080/main/deleteUserSongList.do',
            data:{
                Id:Id
            },
            success:function (result) {
                window.confirm(result.msg)
                location.reload();
            },
            error:function () {
                window.confirm("服务器连接失败")
            }
        })
    }
}

function setRecommed(Id,status){
    var szz=0;
    if(status == 0){
        szz = 1;
    }else if(status == 1){
        szz = 0;
    }


    $.ajax({
        url:'http://127.0.0.1:8080/main/changeRecommend.do',
        type:'POST',
        data:{
            Id:Id,
            recommend:szz
        },
        success:function (result) {

            window.confirm(result.msg)
            location.reload();

        },
        error:function () {
            window.confirm('服务器连接失败')
        }
    })

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

function returnUSLList() {

    window.location.href='./list.html?userId='+getQueryString('userId');

}

function edit(sulId,suln) {
    window.confirm("功能开发ing")
   // window.location.href = './modify.html?userId='+getQueryString('userId')+'&sulId='+sulId+'&suln='+suln;
}


function showEdit() {
    console.log(getQueryString('userId'))
    console.log(getQueryString('sulId'))
    console.log(getQueryString('suln'));

    document.getElementById('usl_name').value=getQueryString('suln')

}

