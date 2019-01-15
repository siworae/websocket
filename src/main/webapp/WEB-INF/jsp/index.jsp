<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Websocket Demo</title>
  </head>
  <body>
  Websocket Demo
  <hr>
  <button id="ws">使用ws创建连接</button>
  <button id="sockjs">使用sockjs创建连接</button>
  <button id="close">关闭websocket连接</button>

  </body>
</html>
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/sockjs-1.1.0.min.js"></script>
<script type="text/javascript">
  $(function () {
      $("#ws").click(function () {
           ws_connect();
      });
      $("#sockjs").click(function () {
          sockjs_connect();
      });
      $("#close").click(function () {
          websocket.close();
      });

  });
  function ws_connect() {
      websocket = new WebSocket("ws://localhost:8080/ws/websocket/socketServer.do");
  }
  function sockjs_connect() {
      websocket = new SockJS("http://localhost:8080/ws/sockjs/socketServer.do");
  }

</script>