let baseWsUrl = "ws:192.168.1.24:8888/ws/weddingShow";
var socket: WebSocket;
socket = new WebSocket(baseWsUrl);
let messageCallbacks:any = [];

socket.onopen = (event: any) => {
  console.log("建立链接");

};

socket.onclose = (event: any) => {
  setTimeout(() => {
    socket = new WebSocket(baseWsUrl);
  }, 5000);
};

socket.onmessage = (event: any) => {
  let data = JSON.parse(event.data);
  execute(data)
};

function execute(data:any) {
  messageCallbacks.forEach((callback:any) => {
    callback(data)
  });
}

function listener2Callback(callback:any) {
  messageCallbacks.push(callback)
}

export default listener2Callback