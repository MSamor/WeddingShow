let baseWsUrl = "ws:localhost:8888/ws/weddingShow";
var socket: WebSocket;
socket = new WebSocket(baseWsUrl);

socket.onopen = (event:any) => {};

socket.onclose = (event:any) => {
    setTimeout(() => {
      socket = new WebSocket(baseWsUrl);
    }, 5000);
  };
export default socket