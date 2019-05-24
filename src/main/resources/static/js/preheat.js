//即将上架
$(function () {
    getPreheatList();//加载即将上架

    //绑定"预约按钮"的事件
    $(".item-btn").unbind("click");
    $("#preheatList").delegate('.item-order-wrap .item-btn', 'click', function () {
        var phone = $(this).prev().val();
        var projectId = $(this).attr("projectId");
        var ele = $(this).parent().parent().parent();
        var _this = $(this);
        var regPhone = /^1[0-9]{10}$/;
        if (regPhone.test(phone)) {
            jQuery.ajax({
                url: "//"+window.location.host+'/insertAdvance.action',
                type: 'post',
                data: {
                    phone: phone,
                    projectId: projectId,
                    temp: Math.random()
                },
                dataType: 'json',
                success: function (result) {
                    initDate(result.Msg, ele, _this);
                },
                error: function () {
                    initDate(4, ele, _this);
                }
            });
        } else {
            initDate(4, ele, _this);
        }
    });
});

//校验预约结果
function initDate(data, ele, _this) {
    if (ele.find(".temp-empty").length > 0) {
        if (data == 1) {
            ele.find(".temp-empty").attr("class", "order-tag-success temp-empty");
            ele.find(".temp-empty").html("您已成功订阅");
            /* ele.('<div class="order-tag-success temp-empty">您已成功订阅</div>'); */
        } else if (data == 2) {
            ele.find(".temp-empty").attr("class", "order-tag-get temp-empty");
            ele.find(".temp-empty").html("您已订阅过此商品");
        } else if (data == 3) {
            ele.find(".temp-empty").attr("class", "order-tag-error temp-empty");
            ele.find(".temp-empty").html("您提交太频繁了");
        } else {
            ele.find(".temp-empty").attr("class", "order-tag-error temp-empty");
            ele.find(".temp-empty").html("请输入正确的手机号");
        }
    } else {
        //order-tag-success
        if (data == 1) {
            ele.find(".temp-empty").removeClass("order-tag-success");
            ele.append('<div class="order-tag-success temp-empty">您已成功订阅</div>');
        } else if (data == 2) {
            ele.find(".temp-empty").removeClass("order-tag-get");
            ele.append('<div class="order-tag-get temp-empty">您已订阅过此商品</div>');
        } else if (data == 3) {
            ele.find(".temp-empty").removeClass("order-tag-error");
            ele.append('<div class="order-tag-error temp-empty">您提交太频繁了</div>');
        } else {
            ele.find(".temp-empty").removeClass("order-tag-error");
            ele.append('<div class="order-tag-error temp-empty">请输入正确的手机号</div>');
        }
    }
    ele.find(".temp-empty").show();
    setTimeout(function () {
        ele.find(".temp-empty").hide();
        _this.prev().val("");
    }, 1500)
}

//加载即将上架
function getPreheatList() {
    var url = "//"+window.location.host+'/getPreheatList.action';
    $.ajax({
        type: "get",
        dataType: "json",
        url: url,
        success: function (data) {
            if (data == null || data.length == 0) {
                return;
            }

            var html = '';
            var index = 76;//埋点
            for (var i = 0; i < data.length; i++) {
                var projectImg = getNoHttpUrl(data[i].itemImg);//单品页图片
                var projectName = data[i].itemName;//项目名称
                var projectSummary = data[i].itemDes;//详情
                var progress = formatProgress(data[i].progress);//进度
                var amount = getFormatNumber2(parseInt(data[i].amount));//目标金额
                var type = parseInt(data[i].type);//类型
                var preheatEndType = parseInt(data[i].preheatEndType);//距上架时间的类型
                var preheatEndDays = parseInt(data[i].preheatEndDays);//距上架天数
                var preheatEndHour = parseInt(data[i].preheatEndHour);//距上架小时
                var itemId = parseInt(data[i].itemId);//项目编号
                var evaluateUrl = getNoHttpUrl(data[i].evaluateUrl);
                var evaluateStatus = parseInt(data[i].evaluateStatus);
                var detailUrl = '/project/details/' + itemId + '.html';
                var projectType = parseInt(data[i].projectType);

                var time = 0;
                if (preheatEndType == 0) {
                    if (preheatEndDays < 0) {
                        time = '0天';
                    } else if (preheatEndDays > 9999) {
                        time = '9999天';
                    } else {
                        time = preheatEndDays + '天';
                    }
                } else if (preheatEndType == 1) {
                    time = preheatEndHour + '小时';
                } else if (preheatEndType == 2) {
                	time = "1小时";
                } else {
                	time = "0天";
                }

                html += '<li class="item-li" ';
                if(index <= 79){
                    html += 'clstag="pageclick|keycount|sceneIndex_201602246|' + index + '"';
                }
                html += '>';
                html += '<a target="_blank" href="' + detailUrl + '" class="item-a">';
                html += '<img class="item-img" data-original="' + projectImg + '" />';
                if(projectType == 7){
                	html += '<h4 class="title-position JDDZ-icon"><i class="title-icon"></i><b>京东定制</b></h4>';
                }
                html += '</a>';
                html += '<div class="item-title-box">';
                html += '<h3 class="item-title">';
                html += '<a target="_blank" href="' + detailUrl + '">' + projectName + '</a>';
                html += '</h3></div>';
                html += '<p class="item-detail"><a target="_blank" href="' + detailUrl + '">' + projectSummary + '</a></p>';
                html += '<div class="p-outer"><div class="p-bar">';
                html += '<div style="width: ' + formatProgress(progress) + '%"></div>';
                html += '</div></div>';
                html += '<div class="p-items">';
                if(evaluateStatus==1&&evaluateUrl!=null&&evaluateUrl!=""){
                	html += '<a class="item-tag" target="_blank" href="' + evaluateUrl +'">0元测评<i></i></a>';
                }
                html += '<div class="p-target">';
                if (type == 1) {
                    //普通
                    html += '<span class="p-title">目标</span>';
                    html += '<span class="num">￥' + amount + '</span>';
                    html += '<span class="line"></span>';
                    html += '</div>';
                } else if (type == 3) {
                    //永久
                    html += '<span class="p-title">目标</span>';
                    html += '<span class="num">无上限</span>';
                    html += '<span class="line"></span>';
                    html += '</div>';
                }
                html += '<div class="p-extra"><span class="p-title">距上架还有</span>';
                html += '<span class="num">' + time + '</span>';

                html += '</div>';
                html += '</div>';
                //--订阅--
                html += '<div class="item-order-wrap">';
                html += '<div class="order-wrap">';
                html += '<input type="text" maxlength="11" class="item-ipt" placeholder="请输入您的手机号"/>';
                html += '<input type="button" class="item-btn" projectId=' + itemId + ' value="上架提醒"/>';
                html += '</div>';
                html += '</div>';
                //<!--订阅 end-->
                html += '</li>';
                index ++;
            }
            $('#preheatList').append(html);
            $('.future').show();

            seajs.use([
                    'project/newUp'
                ],
                function(newUp) {
                    //解决图片加载不出来的问题
                    newUp.horizontalChange($('#leftArrow2'), $('#rightArrow2'), $('.future-list ul li'));
                });

            //解决预约框出不来的样式
            seajs.use([
                    'project/topList'
                ],
                function(topList) {

                    topList.futureListOrder();
                });

            seajs.use([
                    'project/imgSrc'
                ],
                function(imgSrc) {
                    //解决图片加载不出来的问题
                    imgSrc.imgLazyLoadNew();
                });
        },
        error: function (e) {
            return;
        }
    });
}

// 过滤http:
function getNoHttpUrl(url){
	var returnUrl = "";
	if(url != null && url != undefined){
		returnUrl = url.replace("http:","").replace("https:","");
	}else{
		returnUrl = url;
	}
	return returnUrl;
}