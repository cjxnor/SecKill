//存放主要交互逻辑 js 代码
var secKill = {
    //封装秒杀相关 ajax 的 url
    URL: {
        nowTimeUrl: function () {
            return '/seckill/time/now';
        }
    },
    //验证手机号码是否符合规范的函数
    validatePhone: function (userPhone) {
        if (userPhone && userPhone.length == 11 && !isNaN(userPhone)) {
            return true;
        } else {
            return false;
        }
    },
    //倒计时函数
    countDown: function (skId, nowTime, startTime, endTime) {
        var secKillBox = jQuery("#secKill-box");
        //时间的判断
        if (nowTime > endTime) {
            //秒杀结束
            secKillBox.html("秒杀已经结束！");
        } else if (nowTime < startTime) {
            //秒杀未开始，开始倒计时
            //防止时间偏移，增加 1000 ms
            var killTime = new Date(startTime + 1000);
            secKillBox.countdown(killTime,function (event) {
                //控制时间格式
                var fmtTime = event.strftime('秒杀倒计时：%D天 %H：%M：%S');
                secKillBox.html(fmtTime);
            });
        } else {
            //秒杀开始
        }
    },
    //秒杀详情
    detail: {
        //详情页初始化
        init: function (params) {
            //手机验证和登录，计时交互
            //在 cookie 中查找手机号
            var userPhone = jQuery.cookie("userPhone");

            //验证手机号
            if (!secKill.validatePhone(userPhone)) {
                //绑定手机号
                var userPhoneModal = jQuery("#userPhone-modal");
                userPhoneModal.modal({
                    //显示模态窗口
                    show: true,
                    //指定一个静态的背景，当用户点击模态框外部时不会关闭模态框
                    backdrop: 'static',
                    //当按下 escape 键时关闭模态框，设置为 false 时则按键无效
                    keyboard: false
                });
                //为按钮绑定事件
                jQuery("#userPhoneBtn").click(function () {
                    var inputUserPhone = jQuery("#userPhone").val();
                    //验证手机号是否符合要求
                    if (secKill.validatePhone(inputUserPhone)) {
                        jQuery.cookie('userPhone', inputUserPhone, {
                            //指定 cookie 保存事件为 7 天
                            expires: 7,
                            //指定 cookie 的路径，只在该路径下有效
                            path: '/seckill'
                        });
                        //刷新页面
                        window.location.reload();
                    } else {
                        //为了防止用户看到 jQuery 填充 html 的中间过程，先把该节点隐藏，后再显示
                        jQuery("#userPhoneMessage").hide().html('<label class="label label-danger">手机号有误！请重新输入</label>').show(500);
                    }
                });
            }
            //已经登录，计时交互
            var skId = params['skId'];
            //时间传过来的是毫秒时间
            var startTime = params['startTime'];
            var endTime = params['endTime'];
            jQuery.get(secKill.URL.nowTimeUrl(), {}, function (result) {
                //判断返回结果的状态
                if (result && result['success']) {
                    //获得的是当前时间的毫秒数
                    var nowTime = result['data'];
                    secKill.countDown(skId,nowTime,startTime,endTime);
                } else {
                    console.log('GET from ' + secKill.URL.nowTimeUrl() + ' result ' + result);
                }
            });

        }
    }
}