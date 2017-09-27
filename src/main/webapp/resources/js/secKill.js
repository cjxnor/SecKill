//存放主要交互逻辑 js 代码
var secKill = {
    //封装秒杀相关 ajax 的 url
    URL: {
        nowTimeUrl: function () {
            return '/seckill/time/now';
        },
        exposerUrl: function (skId) {
            return '/seckill/' + skId + '/exposer';
        },
        executionUrl: function (skId, md5) {
            return '/seckill/' + skId + '/' + md5 + '/execution';
        }
    },
    //处理秒杀逻辑的函数
    //给出秒杀地址，控制显示逻辑，用户执行秒杀
    handlerSecKill: function (skId, secKillBox) {
        secKillBox.hide().html('<button class="btn btn-primary btn-lg" id="skBtn">开始秒杀</button>');
        //向后台提交 post 请求，获取秒杀地址
        //返回的 result 时封装的 SecKillResult 类型
        jQuery.post(secKill.URL.exposerUrl(skId), {}, function (result) {
            if (result && result['success']) {
                //获取秒杀地址
                var exposerData = result['data'];
                //根据返回的 exposerData 判断是否开启秒杀
                if (exposerData['exposed']) {
                    //开启秒杀
                    var md5 = exposerData['md5'];
                    var killUrl = secKill.URL.executionUrl(skId, md5);
                    console.log('秒杀地址：' + killUrl);
                    //绑定一次点击时间，防止多次点击
                    jQuery('#skBtn').one('click', function () {
                        //1.禁用该按钮
                        jQuery(this).addClass('disabled');
                        //2.发送秒杀请求,执行秒杀，cookie 值会自动传给后台，不用显示赋值
                        jQuery.post(killUrl, {}, function (result) {
                            if (result && result['success']) {
                                var killData = result['data'];
                                var killstate = killData['state'];
                                var killstateInfo = killData['stateInfo'];
                                //3.显示秒杀结果
                                secKillBox.html('<span class="label label-success">' + killstateInfo + '</span>');
                            } else {
                                var killError = killData['error'];
                                //没有成功秒杀的显示
                                secKillBox.html('<span class="label label-danger">' + killError + '</span>');
                            }
                        });
                    });
                    //显示秒杀操作按钮
                    secKillBox.show();
                } else {
                    //每个人的时间和系统时间有差异，可能会出现时间不一致问题
                    var nowTime = exposerData['nowTime'];
                    var startTime = exposerData['startTime'];
                    var endTime = exposerData['endTime'];
                    secKill.countDown(skId, nowTime, startTime, endTime);
                }
            } else {
                console.log('GET from ' + secKill.URL.exposerUrl(skId) + ' result ' + result);
            }
        });
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
            secKillBox.countdown(killTime, function (event) {
                //控制时间格式
                var fmtTime = event.strftime('秒杀倒计时：%D天 %H：%M：%S');
                secKillBox.html(fmtTime);
                //下面的 on 方法是倒计时结束后的回调事件
            }).on('finish.countdown', function () {
                //给出秒杀地址，控制显示逻辑，用户执行秒杀
                secKill.handlerSecKill(skId, secKillBox);
            });
        } else {
            //秒杀开始
            secKill.handlerSecKill(skId, secKillBox);
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
            //返回的 result 是封装的 SecKillResult 类型
            //单独在从 SecKillController 中获取系统时间，是为了确保用户不停刷新 detail.jsp 是得到系统时间
            jQuery.get(secKill.URL.nowTimeUrl(), {}, function (result) {
                //判断返回结果的状态
                if (result && result['success']) {
                    //获得的是当前时间的毫秒数
                    var nowTime = result['data'];
                    secKill.countDown(skId, nowTime, startTime, endTime);
                } else {
                    console.log('GET from ' + secKill.URL.nowTimeUrl() + ' result ' + result);
                }
            });

        }
    }
}