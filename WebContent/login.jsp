<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="EUC-KR">
		<title>Login</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	</head>
	<body>
		<h2>Login</h2>
		<div class="wrapper">
			<form class="form-signin" action="login.user" method="post">
				<label>ID</label><input class="form-control" type="text" name="id"> <br />
				<label>Password</label><input class="form-control" type="password" name="password"><br />
				<label>Code</label><input class="form-control" type="text" name="code"> <br />
				<input class="btn btn-lg btn-primary btn-block" type="submit" value="login">
			</form>
			You don't have an account? <a href="signup_view.user">Signup</a>
		</div>
	</body>
</html>
<script type="text/javascript" src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>