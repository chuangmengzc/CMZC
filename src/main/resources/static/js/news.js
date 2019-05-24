//众筹干货,资讯

$(function () {
	var isInformationHidden = $('#isInformationHidden').val();
	if(isInformationHidden == 'false'){
		getInformationListByChannelId(9);//干货
		getInformationListByChannelId(8);//资讯
	}
});

//获取
function getInformationListByChannelId(channelId) {
    $.ajax({
        //url: "http://sq.jr.jd.com/notice/getList?callback=?",
    	url:"//"+window.location.host+"/getNewsList.action",
        type: "get",
        async: false,
        dataType: "text",
        data: {"key": "1000", "channelId": channelId, "pageSize": 3, "temp": Math.random()},
        timeout: 5000,
        success: function (data) {
            if (data == null || data.length == 0) {
                $('.znews').hide();//隐藏该模块
                return;
            }

            var maidian = 0;
            if(channelId == 9){
                //干货
                maidian = 88;
            }else{
                //资讯
                maidian = 92;
            }

            var html = "";
            if (data != null && data) {
            	var data = eval(data);
                for (var i = 0; i < data.length; i++) {
                    var imgUrl = data[i]["noticeImageUrl"].replace("http:","");
                    var linkType = data[i]["linkType"];
                    var noticeTitle = data[i]["noticeTitle"];

                    var linkUrl = '';
                    if (linkType == 1) {
                        linkUrl = '//sq.jr.jd.com/notice?noticeId="' + data[i]["noticeId"];
                    } else if (linkType == 2) {
                        linkUrl = data[i]["linkUrl"].replace("http:","");
                    } else {
                        linkUrl = 'javascript:;';
                    }

                    //设置首屏图片
                    if (i == 0) {
                        if (imgUrl != '') {
                            $("#channel_img_" + channelId).attr("src", imgUrl);
                        }

                        if (linkType == 1) {
                            $("#channel_img_" + channelId).parent("a").attr("href", linkUrl);
                        } else if (linkType == 2) {
                            $("#channel_img_" + channelId).parent("a").attr("href", linkUrl);
                        }
                    }

                    //设置各标题和图片
                    if (imgUrl != '') {
                        html += '<li data-src="' + imgUrl + '"><a target="_blank" href="' + linkUrl + '" clstag="pageclick|keycount|sceneIndex_201602246|' + maidian + '">' + noticeTitle + '</a></li>';
                    }
                    maidian ++;
                }
            }
            $("#channel_" + channelId).empty().html(html);

            //更新图片的链接地址
            seajs.use([
                    'project/imgSrc'
                ],
                function(imgSrc) {
                    //众筹干货，图片及链接跟随li变化
                    imgSrc.znewsImgSrc();
                });

        },
        error: function () {
            $('.znews').hide();//隐藏该模块
        },complete:function(){
            if($('#channel_8 > li').length != 0 && $('#channel_9  > li').length != 0){
                $('.znews').show();//展示该模块
            }else{
                $('.znews').hide();//隐藏该模块
            }
        }
    });
}