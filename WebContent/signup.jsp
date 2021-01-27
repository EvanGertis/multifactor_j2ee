<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="EUC-KR">
		<title>Signup</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	</head>
	<body>
		<div class="wrapper">
			<h2>Signup</h2>
			<form class="form-sigin" action="signup.user" method="post" name="signupForm">
				<label>ID</label><input class="form-control" type="text" name="id" size="20" /><br />
				<label>Password</label><input class="form-control" type="password" name="password" size="20" /><br />
				<label>Confirm Password</label><input class="form-control" type="password" name="confirmPassword" size="20" /><br />
				<label>Name</label><input class="form-control" type="text" name="name" size="20" /><br />
				<label>Email</label><input class="form-control" type="email" name="email" size="30" /><br />
				<input class="btn btn-lg btn-primary btn-block" type="button" value="signup" onclick="infoConfirm()" />
			</form>
			You already have an account ? <a href="login_view.user">Login</a>
		</div>
	</body>
</html>
<script src="users.js"></script>
<script type="text/javascript" src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>