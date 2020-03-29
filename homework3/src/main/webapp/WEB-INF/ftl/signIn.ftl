<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Test</title>
</head>
<body>
<#if error??>
    <div class="alert alert-danger" role="alert">Логин или пароль введены неверно</div>
</#if>
<h4>Sign in Page</h4>

<form method="post">
    <input type="text" name="email">
    <br>
    <input type="password" name="password">
    <br>
    <label for="remember-me">
        <input type="checkbox" id="remember-me" name="remember-me">Remember me</label>
    <input type="submit" value="SignIn">
    <br>
</form>
</body>
</html>