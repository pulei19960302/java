$(function() {
    // var path = 'http://39.104.119.105:8080';
    var path = 'http://192.168.0.160:8089/app/';
    var common = {
        data: {},
        ajax: function(type, url, data, callback) {
            if(typeof data == 'function') {
                callback = data;
                data = '';
            }
            $.ajax({
                type: type,
                url: path + url,
                data: data,
                async: true,
                success: function(res) {
                    if(!common.isFalse(res) && res.status == 0) {
                        callback(res)
                    }
                    common.isShow(['.loding', '.spinner']);
                },
                error:function(){
                    common.isShow(['.loding', '.spinner']);
                    alert('网络错误');
                }
            });
        },
        isFalse: function(o) {
            if(!o || o === 'null' || o === 'undefined' || o === 'false' || o === 'NaN') return true
            return false
        },
        changeDateFormatStr: function(cellval) { //console.log(cellval);
            if(!common.isFalse(cellval)) {
                var year = (cellval + "").substring(0, 4);
                var month = (cellval + "").substring(4, 6);
                var currentDate = (cellval + "").substring(6, 8);
                return year + "-" + month + "-" + currentDate + " ";
            }
        },
        getTime: function(cellval) {
            if(!common.isFalse(cellval)) {
                var time = Math.floor(cellval / 1000);
                var second = time % 100;
                time = Math.floor(time / 100);
                var minitue = time % 100;
                var hour = Math.floor(time / 100);
                return hour + ':' + minitue + ':' + second;
            }
        },
        path: {
            imgSrc: 'http://120.79.178.28:8081'
        },
        imgTab: function(res) {
            if(!common.isFalse(res)) {
                res = res.replace(/src="\/upload/g, "src=\"" + common.path.imgSrc + "/upload");
            }
            return res;
        },
        isShow: function(arr) {
            $.each(arr, function(index, item) {
                $(item).addClass('isShow');
            });
        },
        GetRequest: function() {//这个对于vue没有卵用
			var url = location.search; //获取url中"?"符后的字串  
			var theRequest = new Object();
			if(url.indexOf("?") != -1) {
				var str = url.substr(1);
				strs = str.split("&");
				for(var i = 0; i < strs.length; i++) {
					theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
				}
			}
			return theRequest;
		}
        
    };
    common.ajax("post", "/information/shareId.app", common.GetRequest(), function(res) {
        var name = res.result;
        var date = common.changeDateFormatStr(name.createDate);
        var time = common.getTime(name.createTime);
        var title = name.informationName;
        var html = name.content;
        var anthor = name.anthor ?  name.anthor : '';
        var source = name.source ? '来源:' + name.source : '';
        html = common.imgTab(html);
        $('.html .date').text(date + time);
        $('.anthor .name').text(anthor);
        $('.source').text(name.browseNumber);
        $('.html h1').text(title);
        $('.innerHtml').html(html);
        common.isShow(['.loding', '.spinner']);
    });

});