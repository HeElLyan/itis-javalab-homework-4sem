<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Rooms</title>
</head>
<body>
<div>
    <form method="post">
        <br>
        Chat name
        <input type="text" name="name">
        <br>
        <input type="submit" value="Create a chat">
        <br>
    </form>

    <h1>Available rooms:</h1>
    <ul>
        <#list rooms as room>
            <a href="/homework4_war/rooms/${room.id}">${room.name}</a>
            <br>
        </#list>
    </ul>
</div>
</body>
</html>