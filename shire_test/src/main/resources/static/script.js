function connect() {
  var socket = new SockJS('/chat-messaging');
  stompClient = Stomp.over(socket);
  stompClient.connect({}, function (frame) {
    console.log("connected: " + frame);
    stompClient.subscribe('/chat/1', function (response) {  //добавить chatId
      var data = JSON.parse(response.body);
      draw("left", data.message);
    });
  });
}

function draw(side, text) {
  console.log("drawing...");
  var $chat;
  $chat = $($('.message_template').clone().html());
  $chat.addClass(side).find('.text').html(text);
  $('.messages').append($chat);
  return setTimeout(function () {
    return $chat.addClass('appeared');
  }, 0);

}

function disconnect() {
  stompClient.disconnect();
}

function sendMessage() {
  stompClient.send("/app/chat/1", {},
      JSON.stringify({'message': $("#message_input_value").val()}));

}
