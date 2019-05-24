$(function () {
    getCategoryList();//加载分类浏览
    //getRankWeekly();//加载本周排行 
    //getComingToEndProjectList();//加载即将结束
});

//搜索按钮跳转search页
$(".s-btn").click(function () {
    //如果选择了分类
    var categoryId = $('#searchCategory').attr('categoryId');
    if (categoryId == '' || categoryId == undefined) {
        window.open("/index/search?pageNo=1&keyword=" + $(".s-ipt").val());
        return;
    }
    window.open('/index/search?pageNo=1&categoryId=' + categoryId + '&keyword=' + $(".s-ipt").val());
});

//搜索按钮点回车跳转search页
$(".s-ipt").keydown(function (event) {
    if (event.keyCode == 13) {
        window.open("/index/search?pageNo=1&keyword=" + $(".s-ipt").val());
    }
});

//点击"最新上线"TAB页
$("#latestProject").on("click", function () {
    $('#loadMore').attr('href', '/bigger/search.html?sort=zxsx');
});

//点击"即将结束"TAB页
$("#comingToEnd").on("click", function () {
    $('#loadMore').attr('href', '/bigger/search.html?sort=jjjs&status=2');
});

//加载分类浏览
function getCategoryList() {
    var url = "//" + window.location.host + '/getIndexCategoryList.action';
    $.ajax({
        type: "get",
        dataType: "json",
        url: url,
        success: function (data) {
            if (data == null || data.length == 0) {
                return;
            }

            var html = '';
            for (var i = 0; i < data.length; i++) {
                var categoryId = data[i]["categoryId"];
                var categoryName = data[i]["categoryName"];
                html += '<li><a target="_blank" href="/bigger/search.html?categoryId=' + categoryId + '">' + categoryName + '</a></li>';
            }
            $('#categorylist').append(html);
            $('.browse').show();
        },
        error: function (e) {
            return;
        }
    });
}


//加载本周排行
function getRankWeekly() {
    var url = "//" + window.location.host + '/getRankWeekly.action';
    $.ajax({
        type: "get",
        dataType: "json",
        url: url,
        success: function (data) {
            if (data == null || data.length == 0) {
                return;
            }

            var html = '';
            var weekInfo = '';
            var index = 66;//埋点
            var imgIndex = 71;//图片埋点
            for (var i = 0; i < data.length; i++) {
                var projectId = data[i].projectId;
                var projectName = data[i].projectName;
                var projectAdWord = data[i].projectAdWord;
                var image = data[i].sceneProjectImg.replace("http:", "");
                image = image.replace("https:", "");
                var supports = getFormatNumber(data[i].supports);//支持人数
                var progress = formatProgress(data[i].progress);//进度
                var projectUrl = '/project/details/' + projectId + '.html';

                //左侧数据
                html += '<li>';
                html += '<a target="_blank" href="' + projectUrl + '" clstag="pageclick|keycount|sceneIndex_201602246|' + index + '">';
                html += '<p>' + projectName + '</p>';
                if (projectAdWord != '' && projectAdWord != null) {
                    html += '<p class="detail">' + projectAdWord + '</p>';
                }
                html += '<div class="order">0' + (i + 1) + '</div>';
                html += '</a>';
                html += '</li>';

                //右侧图片信息
                weekInfo += '<div class="w-r"';
                if (i == 0) {
                    weekInfo += 'style="display: block"';
                }

                weekInfo += '>';
                weekInfo += '<a target="_blank" href="' + projectUrl + '" clstag="pageclick|keycount|sceneIndex_201602246|' + imgIndex + '"><img src="' + image + '" alt=""/></a>';
                weekInfo += '<a href="' + projectUrl + '" target="_blank" clstag="pageclick|keycount|sceneIndex_201602246|' + imgIndex + '">';
                weekInfo += '<div class="progress">';
                weekInfo += '<p class="pt36">支持人数</p>';
                weekInfo += '<p class="num">' + supports + '<span>人</span></p>';
                weekInfo += '<div class="bar">';
                weekInfo += '<div style="width: ' + formatProgress(progress) + '% "></div>';
                weekInfo += '</div>';
                weekInfo += '<p>已完成' + formatProgress(progress) + '%</p>';
                weekInfo += '</div></a></div>';

                index++;
                imgIndex++;

            }
            $('#topList ul').append(html);
            $('#weekTopInfo').append(weekInfo);
            $('.week-top-new').show();

            //绑定HOVER效果
            seajs.use([
                    'project/topList'
                ],

                function (topList) {
                    //解决图片加载不出来的问题
                    topList.topListInit();

                });
        },
        error: function (e) {
            return;
        }
    });
}

//加载即将结束
/*function getComingToEndProjectList() {
 var url = '/getComingToEndProjectList.action';
 $.ajax({
 type: "post",
 dataType: "json",
 url: url,
 success: function (data) {
 if (data == null || data.length == 0) {
 return;
 }

 var html = '';
 for (var i = 0; i < data.length; i++) {
 var detailUrl = data[i].detailUrl;//单品页链接
 var projectImg = data[i].itemImg;//单品页图片
 var projectName = data[i].itemName;//项目名称
 var projectSummary = data[i].itemDes;//详情
 var progress = data[i].progress;//进度
 var amount = parseInt(data[i].amount);//目标金额
 var collectedAmount = parseInt(data[i].collectedAmount);//已筹金额

 html += '<li class="item-li">';
 html += '<a href="' + detailUrl + '">';
 html += '<img class="item-img" src="' + projectImg + '"/>';
 html += '</a>';
 html += '<h3 class="item-title">';
 html += '<a href="' + detailUrl + '">' + projectName + '</a>';
 html += '</h3>';
 html += '<p class="item-detail"><a href="' + detailUrl + '">' + projectSummary + '</a></p>';
 html += '<div class="p-outer"><div class="p-bar">';
 html += '<div style="width: ' + progress + '%"></div>';
 html += '</div></div>';
 html += '<div class="p-items">';
 html += '<div class="p-target"><span class="p-title">目标</span>';
 html += '<span class="num">￥' + amount + '</span>';
 html += '<span class="line"></span>';
 html += '</div>';
 html += '<div class="p-extra"><span class="p-title">已筹</span>';
 html += '<span class="num">￥' + collectedAmount + '</span>';
 html += '</div>';
 html += '</div>';
 html += '</li>';
 }
 $('#comingToEndItems').append(html);
 },
 error: function (e) {
 return;
 }
 });
 }*/

//格式式金额（超过一百万显示万字）
function getFormatNumber(number) {
    var result = "";
    var desNumber = 1000000;
    if (parseInt(number) >= parseInt(desNumber)) {
        result = parseInt(number / 10000) + "<span>万</span>";
    } else {
        result = number;
    }

    return result;
}

//格式式金额（超过亿显示万字）
function getFormatNumber2(number) {
    var result = "";
    var desNumber = 100000000;
    if (parseInt(number) >= parseInt(desNumber)) {
        result = parseInt(number / 10000) + "<span>万</span>";
    } else {
        result = number;
    }

    return result;
}
