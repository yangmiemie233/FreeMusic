
function insertAd(){
    var title=$("#title").val();
    var ad_link=$("#ad_link").val();
    var file=document.getElementById('ad_file');
    //获取上传文件的扩展名
    var filevalue = file.value;
    var index = filevalue.lastIndexOf('.');
    var filetype=filevalue.substring(index);

    if(title.length<=0){
        window.confirm('请输入运营商名')
    }else if(file.files[0] == undefined){
        window.confirm('请选择上传的文件')
    }else if(filetype!='png' && filetype !='.png' && filetype !='.jpg' && filetype != '.jpg'){
        window.confirm('请输入正确的格式')
    }else{
        var datas = new FormData();
        datas.append('title',title);
        datas.append('link',ad_link);
        datas.append('file',$('#ad_file').prop('files')[0])

        $.ajax({
            type: 'POST',
            url:'http://127.0.0.1:8080/main/insertAdvertise.do',
            data: datas,
            cache: false,
            processData: false,
            contentType: false,
            success:function (result) {
                console.log(result)
                if(result.result==true){
                    window.confirm('添加成功');
                    location.reload();
                }else{
                    window.confirm(result.msg)
                }
            },
            error:function () {
                window.confirm('程序出错')
            }
        })
    }


}

function returnList() {
    window.location.href='./list.html';
}



    function showAllUse() {

        var zz=document.getElementById('tbody_ad');
        var showAdvertise='';

        $.ajax({
            url:'http://localhost:8080/main/showUseAd.do',
            type:'POST',
            success:function (result) {


                if(result.result==true ){



                    var date=new Date();


                    for(var i=0;i<result.data.length;i++){
                        showAdvertise+='' +
                            '<tr role=/"row/" class=/"odd/">'+
                            '<td tabindex=/"0/" class=/"sorting_'+i+'/">'+(i+1)+'</td>' +
                            '<td>'+result.data[i].title+'</td>' +
                            '<td>'+result.data[i].creatTime+'</td>' +
                            '<td>'+result.data[i].openTime+'</td>' +
                            '<td>已启用</td>'+
                            '<td>\n' +

                            '     <a href="javascript:void(0)" onclick="deleteAd('+result.data[i].advertisementId+')">删除</a>&nbsp;&nbsp;|\n' +
                            '     <a href="javascript:void(0)" onclick="update()">编辑</a>&nbsp;&nbsp;|\n' +
                            '     <a href="javascript:void(0)" onclick="changeStatus('+result.data[i].advertisementId+',1)">取消启用</a>\n' +
                            '</td>'+

                            '</tr>'


                    }
                    showAdvertise+='<tr><td ></td></tr><tr><td></td></tr>'
                    zz.innerHTML=showAdvertise;
                }
            },
            error:function () {

            }
        })



        $.ajax({
            url:'http://localhost:8080/main/showUnuseAd.do',
            type:'POST',
            success:function (result) {


                if(result.result==true ){



                    var date=new Date();


                    for(var i=0;i<result.data.length;i++){
                        showAdvertise+='' +
                            '<tr role=/"row/" class=/"odd/">'+
                            '<td tabindex=/"0/" class=/"sorting_'+i+'/">'+(i+1)+'</td>' +
                            '<td>'+result.data[i].title+'</td>' +
                            '<td>'+result.data[i].creatTime+'</td>' +
                            '<td>无</td>' +
                            '<td>未启用</td>'+
                            '<td>\n' +

                            '        <a href="javascript:void(0)" onclick="deleteAd('+result.data[i].advertisementId+')">删除</a>&nbsp;&nbsp;|\n' +
                            '        <a href="javascript:void(0)" onclick="update()">编辑</a>&nbsp;&nbsp;|\n' +
                            '        <a href="javascript:void(0)" onclick="changeStatus('+result.data[i].advertisementId+',0)">设为启用</a>\n' +
                            '</td>'+

                            '</tr>'


                    }
                    if(showAdvertise.length<0){
                        zz.innerHTML='暂无数据'
                    }else{
                        zz.innerHTML=showAdvertise;
                    }

                }
            },
            error:function () {

            }
        })



    }

    function deleteAd(Id) {

        if(window.confirm('确定删除吗？')){
            $.ajax({
                url:'http://localhost:8080/main/delteAd.do',
                type:'POST',
                data:{
                    adId:Id
                },
                success:function (result) {

                    if(result.result==true || result.result=='true'){
                        window.confirm(result.msg);
                        location.reload()
                    }else{
                        window.confirm(result.msg)
                    }
                }


            })
        }

    }

    function update() {
        window.confirm('功能开发ing')
    }

    function changeStatus(id,status) {

        var szz=0;

        if(status == 0){
            szz=1;
        }else{
            status == 0;
        }

        $.ajax({
            type:'POST',
            url:'http://127.0.0.1:8080/main/changStatu.do',
            data:{
                Id:id,
                status:szz
            },
            success:function (result) {
                window.confirm(result.msg)
                location.reload()
            },

            error:function () {
                window.confirm('服务器异常')
            }
        })
    }