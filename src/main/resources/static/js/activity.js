/**
 * [zcActivity 众筹破亿]
 */
var zcActivity = {
    init: function(a) {
        this.renderHtml(a);
        this.wrap = $('#activity-zc');
        this.animationInit();
        this.setClose();

        var _this = this;
        setTimeout(function() {
            _this.close();
        }, 5000);
    },
    renderHtml: function(a) {
    	
    	var linkUrl = a['linkUrl'];
    	if(linkUrl != null && linkUrl != "" && linkUrl != undefined){
    		linkUrl = linkUrl.replace("http:","");
    	}
    	var imageUrl = a['imageUrl'];
    	if(imageUrl != null && imageUrl != "" && imageUrl != undefined){
    		imageUrl = imageUrl.replace("http:","");
    	}
        var html = '<div id="activity-zc">'

			+ '<div class="activity-text-wrap">'
			//+ '<div class="aw-text aw-text-01"></div>'
			//+ '<div class="aw-text aw-text-02"></div>'
			//+ '<div class="aw-text aw-text-03"></div>'
			//+ '<div class="aw-text01 aw-text-04"></div>'
			//+ '<div class="aw-text aw-text-05"></div>'
			// + '<div class="aw-text aw-text-06"></div>'
            //+'<div class="aw-link" clstag="pageclick|keycount|zc2home_page_201505073|1">感恩节特惠活动同期开启</div>'
			+ '<div class="aw-close aw-close01"></div>'
			+ '</div>'
            + '<a target="_blank" rel="noopener noreferrer" href='+linkUrl+' style="position: absolute; left: 0;top: 0; width: 100%; display: block;"><img src='+ imageUrl +' style="width: 100%; height: 500px;" /></a>'

            //+ '<div class="aw-close"></div> '
			+ '</div>';
        $('.header').before(html);
        //$(".aw-link").on("click",function(){
        //    window.open("http://zan.jd.com/");
        //});
    },
    animationInit: function() {
        var $wrap = this.wrap;
        $wrap.animate({
            'height': 550
        }, 1000, 'easeInOutQuint', function() {
            $wrap.addClass('animate-start')
        })
    },
    close: function() {
        var $wrap = this.wrap;
        $wrap.animate({
            'height': 0
        }, 1000, 'easeInOutQuint', function() {
            $wrap.remove();
        });
    },
		/*
	setClose: function() {
	var $wrap = this.wrap;
	var _this = this;
	$wrap.find('.aw-close').bind('click', function() {
		_this.close();
	}),
	*/
    setClose: function() {
        var $wrap = this.wrap;
        var _this = this;
        $wrap.find('.aw-close').bind('click', function() {
            _this.close();
        });
    }
};

$(function(){
    jQuery.extend( jQuery.easing,
    {
        easeInOutQuint: function (x, t, b, c, d) {
            if ((t/=d/2) < 1) return c/2*t*t*t*t*t + b;
            return c/2*((t-=2)*t*t*t*t + 2) + b;
    }
    });
    if(JrTools.getCookie("zc_first_activity_time")==null ||JrTools.getCookie("zc_first_activity_time")==undefined || JrTools.getCookie("zc_first_activity_time")==''){
        findActivityMsg();
    }
});

function findActivityMsg(){
    jQuery.ajax({
        type: "GET",
        url: "//"+window.location.host+"/findActivityMsg.html?temp=" + new Date().getTime() + Math.random(),
        dataType: "json",
        scriptCharset: "utf-8",
        success: function (a) {
            if (a != "" && a != null && a != undefined) {
                if (a['display'] == 1 &&(JrTools.getCookie("zc_first_activity")==null ||JrTools.getCookie("zc_first_activity")==undefined || JrTools.getCookie("zc_first_activity")=='')) {
                    zcActivity.init(a);
                    JrTools.setCookie("zc_first_activity",'1');
                }else{
                    if(a['display'] == 1){
                        var i=JrTools.getCookie("zc_first_activity");
                        var num=1;
                        try{
                            num=parseInt(i);
                        }catch (e){}
                        if( a['display'] == 1 &&num>=2){
                            JrTools.setCookie("zc_first_activity","0");
                            try{
                                var time=parseInt(a['time'])*1000;
                                setTimesCookie("zc_first_activity_time",'true',time);
                                zcActivity.init(a);
                            }catch(e){}
                        }
                        else if(a['display'] == 1 && num<2){
                            JrTools.setCookie("zc_first_activity",(num+1).toString());
                            zcActivity.init(a);
                        }
                    }
                }
            }
        }
    });
}
function setTimesCookie(c_name, value, times){
    var exdate = new Date()
    exdate.setTime(exdate.getTime() + times)
    document.cookie = c_name + "=" + encodeURI(value) +
        ((times == null) ? "" : ";expires=" + exdate.toGMTString())
}

