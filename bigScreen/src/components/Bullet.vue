<template>
  <div class="wrapper">
    <div
      id="bullet"
      v-for="(item, index) in messages"
      :key="index"
      :style="{
        transform: 'translateX('+item.right + 'px)' ,
        top: item.top + 'px',
        color: item.color,
      }"
    >
      <div class="avatar" ref="avatar">
        <img
          class="avatarImg"
          src="../assets/Image_created_with_a_mobile_phone.png"
          alt="头像"
        />
        <div class="text">新婚快乐</div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { ref, onMounted, Ref } from "vue";

let avatar = ref("avatar");
interface BarrageItem {
  message: string;
  color: string;
  top: number;
  right: number;
}
const messages: Ref<BarrageItem[]> = ref([]);
const barrageItemHeight: number = 80;
const screenWith: number = window.innerWidth;

let timer = null;

onMounted(() => {
  addBarrage();
  startBarrage();
});

const startBarrage = () => {
  messages.value.map((item) => {
    item.top = Math.random() * 1000;
  });
  timer = setInterval(() => {
    messages.value.map((item) => {
      item.right -= 3;
      if (item.right < -5000) {
        messages.value.pop()
      }
    });
  }, 1);
};

const addBarrage = () => {
  const item1 = {
    message: "新婚快乐！",
    color: "red",
    top: 10,
    right: screenWith,
  };
  const item2 = {
    message: "新婚快乐2！",
    color: "red",
    top: 30,
    right: screenWith,
  };
  messages.value.push(item1);
  setTimeout(() => {
    messages.value.push(item2);
  }, 2000);
};
</script>

<style scoped>
.wrapper {
  position: absolute;
  top: 0;
  width: 100%;
  height: 100%;
  z-index: 1;
  display: flex;
  overflow: hidden;
}
#bullet {
  position: absolute;
}
.avatar {
  justify-content: center;
  align-items: center;
  background-color: pink;
  border-radius: 50px;
  padding: 3px 15px;
  border: 5px solid white;
  opacity: 0.8;
  display: flex;
}
.avatarImg {
  border-radius: 50%;
  width: 4rem;
  height: 4rem;
}
.text {
  font-size: 2.5rem;
  margin-left: 10px;
}
</style>