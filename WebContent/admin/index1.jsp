<%--
  Created by IntelliJ IDEA.
  User: Mi
  Date: 2017/5/15
  Time: 0:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>doremi商城后台管理系统</title>

    <!-- Bootstrap -->
    <link href="__PUBLIC__/static/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="__PUBLIC__/static/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <style>
        .title {
            margin-top: 25%;
        }

        h2 {
            color: #fff;
            opacity: 0.8;
            cursor: pointer;
            line-height: 0.8em;
        }

        .login-panel {
            margin-top: 40px;
            border: 0;
            background: rgba(255, 255, 255, .3);
        }

        .login-ph {
            background: transparent !important;
            color: #fff !important;
            border-bottom: 0 !important;
        }

        .text-gray {
            color: #ccc;
        }

        .text-white {
            color: #fff;
        }

        .login-addon {
            border: 0;
            background: #fff;
        }

        .login-input:focus {
            outline: 0;
            box-shadow: none;
            -webkit-box-shadow: none;
        }

        .login-input {
            border: 0;
        }

        .btn-login {
            width: 100%;
        }

        .tip {
            margin-top: 15%;
        }

        a:hover {
            color: #fff;
        }

        .login-type :hover {
            color: #fff !important;
            background: transparent !important;
            cursor: pointer;
        }

    </style>
</head>
<body style="font-family:Times New Roman,Arial">
<!--modal dialog-->
<div class="modal fade tip" tabindex="-1" role="dialog" id="tips">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">提示</h4>
            </div>
            <div class="modal-body">
                <p id="tipsContent"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">我知道了</button>
            </div>
        </div>
    </div>
</div>

<div class="backstretch">
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="text-center title">
                    <h2>乡村医疗智能助手</h2>
                    <h2>后台管理系统</h2>
                </div>
                <div class="panel panel-default login-panel">
                    <div class="panel-heading login-ph">
                        <ul class="nav nav-tabs nav-justified">
                            <li class="login-type psd-login" role="presentation">
                                <a style="text-decoration : none"><span style="color: #fff">密码登陆</span></a>
                            </li>
                            <li class="login-type code-login" role="presentation">
                                <a style="text-decoration : none"><span style="color: #ccc">短信登陆</span></a>
                            </li>
                        </ul>
                    </div>
                    <div class="panel-body" id="password-panel">
                        <form role="form" method="post">
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon login-addon">
											<i class="fa fa-user text-gray"></i>
										 </span>
                                    <input type="text" class="form-control login-input" placeholder="手机号码或用户名"
                                           name="phone" autofocus>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon login-addon">
											<i class="fa fa-lock text-gray"></i>
										 </span>
                                    <input type="password" class="form-control login-input" placeholder="密码"
                                           name="password">
                                </div>
                            </div>
                            <div id="remember" class="checkbox login-ph">
                                <a attr-value=0 onclick="remember()"
                                   style="border: 1px solid #ccc;width: 5px;height: 5px;background-color: #fff;cursor: pointer;text-decoration : none">
                                    &nbsp;&nbsp;&nbsp;&nbsp;</a>
                                <label style="padding-left: 10px">记住密码</label>
                            </div>
                            <div>
                                <input type="button" class="btn btn-success btn-login" value="登录"
                                       onclick="checkAndLogin(2)">
                            </div>
                        </form>
                    </div>
                    <div class="panel-body" id="code-panel" style="display: none;">
                        <form role="form" method="post">
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon login-addon">
											<i class="fa fa-user text-gray"></i>
										 </span>
                                    <input type="text" name="code-phone" class="form-control login-input"
                                           placeholder="手机号码或用户名"
                                           autofocus>
                                    <input type="text" name="purpose" value="1" style="display: none;">
                                    <input type="text" name="type" value="2" style="display: none;">
                                    <span class="input-group-btn">
                                           <input type="button" value="获取验证码" class="btn btn-success" id="get-code"/>
                                           </span>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon login-addon">
											<i class="fa fa-lock text-gray"></i>
										 </span>
                                    <input type="text" class="form-control login-input" placeholder="验证码"
                                           name="code">
                                </div>
                            </div>
                            <div>
                                <input type="button" class="btn btn-success btn-login" value="登录"
                                       onclick="checkAndLogin(2)">
                            </div>
                        </form>
                    </div>
                    <div class="text-right" style="padding-right: 20px;padding-bottom: 10px;cursor: pointer">
                        <a>忘记密码&nbsp;|</a>
                        <a>注册新账号</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="__PUBLIC__/static/js/bootstrap.min.js"></script>

<!-- jquery for backstretch-->
<script src="__PUBLIC__/static/js/jquery.backstretch.min.js"></script>

<!--md5 script-->
<script src="__PUBLIC__/static/js/JQuery.md5.js"></script>
<script src="__PUBLIC__/static/js/jquery.cookie.js"></script>
<!--my script-->
<script src="__PUBLIC__/static/js/common/api.js"></script>
<script src="__PUBLIC__/static/js/common/common.js"></script>
<script src="__PUBLIC__/static/js/common/login.js"></script>

<script>
    if ($.cookie('admin_uid') != undefined && $.cookie(('admin_token')) != undefined) {
        loginBackground($.cookie('admin_uid'), $.cookie('admin_token'), 2);
    }

    if ($.cookie('admin_phone') != undefined && $.cookie('admin_password') != undefined) {
        $('input[name="phone"]').val($.cookie('admin_phone'));
        $('input[name="password"]').val($.cookie('admin_password'));
        $('#remember a').html('<i class="fa fa-fw fa-check" style="color: #222"></i>');
        $('#remember a').attr('attr-value', 1);
    }

    $(function () {
        $.backstretch([
            "__PUBLIC__/images/background1.jpg",
            "__PUBLIC__/images/background2.jpg",
            "__PUBLIC__/images/background3.jpg"
        ], {
            fade: 800,
            duration: 3000
        });
    });
</script>
</body>
</html>
