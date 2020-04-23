<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Чат</title>
    <script
            src="https://code.jquery.com/jquery-3.4.1.js"
            integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
    <script>
        var webSocket;
        function connect() {
            webSocket = new SockJS("http://localhost:8080/homework4_war/chat");
            document.cookie = 'X-Authorization=' + '12345' + ';path=/';

            webSocket.onmessage = function receiveMessage(response) {
                let data = response['data'];
                let json = JSON.parse(data);
                $('#messagesList').first().after("<li>" + json['from'] + ' ' + json['text'] + "</li>")
            }
        }

        function sendMessage(text, pageId) {
            let message = {
                "text": text,
                "from": pageId
            };

            webSocket.send(JSON.stringify(message));
        }
    </script>
</head>
<body onload="connect()">
<div>
    <br>
    <label for="message">Write your message here</label>
    <input name="message" id="message" placeholder="Message">
    <button onclick="sendMessage($('#message').val(), '${pageId}')">Send</button>
    <h3>Messages</h3>
    <ul id="messagesList">

    </ul>
</div>
</body>
</html>