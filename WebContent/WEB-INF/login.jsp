<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>登录</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<link rel="stylesheet"  href="${pageContext.request.contextPath}//bootstrap3.3.7/css/bootstrap.min.css"/>


<link rel='stylesheet' href='//netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.min.css'>
<link rel='stylesheet' href='${pageContext.request.contextPath}//bootstrap3.3.7/css/bootstrap.min.css'>
<style>
      #fullBg {
  z-index: -1;
  height: 100%;
  width: 100%;
  position: absolute;
  top: 0px;
  background: url("https://31.media.tumblr.com/3b08b64bfe69d670c91c03a5e2fd42b6/tumblr_mv5lnjrRQ51qfj2dqo1_1280.jpg") no-repeat center center fixed;
  background-size: cover;
}

.form-signin {
  max-width: 280px;
  margin: 60px auto 10px;
}
.form-signin .form-signin-heading {
  text-align: center;
  font-weight: bold;
  text-shadow: 0px 1px 2px #111;
  color: #fff;
  margin-bottom: 20px;
}
.form-signin .checkbox {
  font-weight: normal;
}
.form-signin .form-control {
  position: relative;
  font-size: 16px;
  height: auto;
  padding: 10px;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
}
.form-signin input[type="text"] {
  margin-bottom: -1px;
  border-top: 1px solid #000;
  border-right: 1px solid #000;
  border-left: 1px solid #000;
  border-radius: 6px 6px 0px 0px;
}
.form-signin input[type="text"]:focus {
  box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
}
.form-signin input[type="password"] {
  z-index: 2;
  margin-bottom: 20px;
  border-top: none;
  border-bottom: 1px solid #000;
  border-right: 1px solid #000;
  border-left: 1px solid #000;
  border-radius: 0px 0px 6px 6px;
  box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0px 1px 0px 0px rgba(255, 255, 255, 0.5);
}
.form-signin input[type="password"]:focus {
  box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0px 1px 0px 0px rgba(255, 255, 255, 0.5);
}
.form-signin .btn {
  font-weight: bold;
  text-shadow: 0px -1px 0px rgba(0, 0, 0, 0.2);
}

p.sign-up {
  text-shadow: 0px 1px 2px #222;
  color: #fff;
  font-size: 12px;
}

    </style>
<script>
  window.console = window.console || function(t) {};
</script>
<script>
  if (document.location.search.match(/type=embed/gi)) {
    window.parent.postMessage("resize", "*");
  }
</script>
</head>
<body translate="no">
<div id="fullBg" >
<div class="container">
<form id="user_login" name="loginform" action="${pageContext.request.contextPath}//user_login.do" method="post" class="form-signin">
<h1 class="form-signin-heading">请登录</h1>
<input type="text" class="form-control" name="loginName" placeholder="请输入用户名:" required="" autofocus="" />
<input type="password" class="form-control" name="password" placeholder="请输入密码:" required="" />
<button class="btn btn-lg btn-primary btn-block" type="submit">Log in</button>
</form>
</div>
<!-- <p class="text-center sign-up"><strong>Sign up</strong> for a new account</p> -->
</div>

<script src="https://static.codepen.io/assets/editor/live/css_reload-5619dc0905a68b2e6298901de54f73cefe4e079f65a75406858d92924b4938bf.js"></script>
</body>
</html>
