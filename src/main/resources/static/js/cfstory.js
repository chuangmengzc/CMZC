$(function () {
    //加载众筹故事
    getCfStory();
});

//众筹故事-换一组
$(".jchange").click(function () {
    getAnotherCfStory();
});

//加载众筹故事
function getCfStory() {
    var url = "//"+window.location.host+'/getCfStory.action';
    $.ajax({
        type: "get",
        dataType: "json",
        url: url,
        success: function (data) {
            if (data == null) {
                return;
            }

            var totalCount = data.totalCount;
            if (totalCount < 4) {
                return;
            }

            var storyList = data.cfStoryList;
            var html = '';
            var storyIds = '';
            var index = 82;

            for (var i = 0; i < storyList.length; i++) {
                var storyUrl = getNoHttpUrl(storyList[i]["storyUrl"]);
                var storyImg = getNoHttpUrl(storyList[i]["storyImg"]);
                var storyName = storyList[i]["storyName"];
                var storyDesc = storyList[i]["storyDesc"];
                var storyId = storyList[i]["storyId"];
                html += '<li class="item-li" clstag="pageclick|keycount|sceneIndex_201602246|' + index + '">';
                html += '<a target="_blank" href="' + storyUrl + '" class="item-a"><img class="item-img" data-original="' + storyImg + '"/></a>';
                html += '<h3 class="item-title"><a target="_blank" href="' + storyUrl + '">' + storyName + '</a></h3>';
                html += '<p class="item-detail"><a target="_blank" href="' + storyUrl + '">' + storyDesc + '</a></p>';
                html += '</li>';
                storyIds += storyId + '|';
                index++;
            }

            if (totalCount >= 8) {
                //展示"换一组"
                $('.jchange').show();
            } else {
                $('.jchange').hide();
            }

            $('#cfStory').append(html);
            $('#cfStory').attr('data-story', storyIds.substring(0, storyIds.length - 1));
            $('.zstory').show();

            seajs.use([
                    'project/imgSrc'
                ],
                function(imgSrc) {
                    //解决图片加载不出来的问题
                    imgSrc.zstoryImgLazyLoad();
                });
        },
        error: function (e) {
            return;
        }
    });
}

//众筹故事-换一组
function getAnotherCfStory() {
    var url = "//"+window.location.host+'/getCfStory.action';
    var storyIds = $('#cfStory').attr('data-story');
    $.ajax({
        type: "get",
        dataType: "json",
        data: {
            storyIds: storyIds,
            rnd: Math.random()
        },
        url: url,
        success: function (data) {
            if (data == null) {
                return;
            }

            var totalCount = data.totalCount;
            if (totalCount == null || totalCount == 0) {
                return;
            }

            if (totalCount >= 8) {
                //展示"换一组"
                $('.jchange').show();
            } else {
                $('.jchange').hide();
            }

            var storyList = data.cfStoryList;
            var html = '';
            var storyIds = '';
            var index = 82;

            for (var i = 0; i < storyList.length; i++) {
                var storyUrl = getNoHttpUrl(storyList[i]["storyUrl"]);
                var storyImg = getNoHttpUrl(storyList[i]["storyImg"]);
                var storyName = storyList[i]["storyName"];
                var storyDesc = storyList[i]["storyDesc"];
                var storyId = storyList[i]["storyId"];
                html += '<li class="item-li" clstag="pageclick|keycount|sceneIndex_201602246|' + index + '">';
                html += '<a target="_blank" class="item-a" href="' + storyUrl + '"><img class="item-img" src="' + storyImg + '"/></a>';
                html += '<h3 class="item-title"><a target="_blank" href="' + storyUrl + '">' + storyName + '</a></h3>';
                html += '<p class="item-detail"><a target="_blank" href="' + storyUrl + '">' + storyDesc + '</a></p>';
                html += '</li>';
                storyIds += storyId + '|';
                index++;
            }
            $('#cfStory').empty();
            $('#cfStory').append(html);
            $('#cfStory').attr('data-story', storyIds.substring(0, storyIds.length - 1));

        },
        error: function (e) {
            return;
        }
    });
}

//过滤http:
function getNoHttpUrl(url){
	var returnUrl = "";
	if(url != null && url != undefined){
		returnUrl = url.replace("http:","").replace("https:","");
	}else{
		returnUrl = url;
	}
	return returnUrl;
}