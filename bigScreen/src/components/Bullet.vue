<template>
  <div class="wrapper">
    <div
      id="bullet"
      v-for="(item, index) in messages"
      :key="index"
      :style="{
        transform: 'translateX(' + item.right + 'px)',
        top: item.top + 'px',
        color: item.color,
      }"
    >
      <div class="avatar" ref="avatar">
        <img class="avatarImg" src="../assets/valentine_011-hearts-love-affection-like.png" alt="头像" />
        <div class="nickname">{{ item.nickName }}:</div>
        <div class="text">{{ item.message }}</div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { ref, onMounted, Ref } from "vue";
import listener2Callback from "@/request/socket";

listener2Callback((data: any) => {
  console.log("发送弹幕");
  if (data.code == 1) {
    addBarrage(data.data);
  }
});

let avatar = ref("avatar");
interface BarrageItem {
  message: string;
  color: string;
  top: number;
  right: number;
  nickName: string;
  url: string;
}
const messages: Ref<BarrageItem[]> = ref([]);
const barrageItemHeight: number = 80;
const screenWith: number = window.innerWidth;

let heightArr = [0, 100, 200, 300, 400, 500, 600];

let timer = null;

onMounted(() => {
  startBarrage();
});

const startBarrage = () => {
  timer = setInterval(() => {
    messages.value.map((item) => {
      item.right -= 1;
      if (item.right < -5000) {
        messages.value.pop();
      }
    });
  }, 1);
};

const addBarrage = (text: string) => {
  let bullet = JSON.parse(text);
  const item = {
    message: bullet.text,
    color: "#191a23",
    top: heightArr[parseInt((Math.random() * 7).toFixed(0))],
    right: screenWith,
    nickName: bullet.nickName,
    url: bullet.url,
  };
  messages.value.push(item);
};
</script>

<style scoped>
.wrapper {
  position: absolute;
  top: 0;
  width: 100%;
  height: 100%;
  z-index: 10;
  display: flex;
  overflow: hidden;
}
.nickname {
  font-size: 2rem;
  color: black;
  margin-left: 0.5rem;
}
#bullet {
  position: absolute;
}
.avatar {
  justify-content: center;
  align-items: center;
  background-color: #ffd9d9;
  border-radius: 50px;
  padding: 3px 15px;
  border: 5px solid rgb(255, 255, 255);
  opacity: 0.8;
  display: flex;
}
.avatarImg {
  border-radius: 50%;
  width: 4rem;
  height: 4rem;
}
.text {
  font-size: 2rem;
  margin-left: 10px;
}
</style>