<template>
  <div class="swiper">
    <Swiper
      :slides-per-view="1"
      :space-between="50"
      :loop="true"
      :autoplay="autoplayOptions"
      @swiper="onSwiper"
      @slideChange="onSlideChange"
      :modules="modules"
      effect="fade"
      class="mySwiper"
    >
      <SwiperSlide
        class="swiperItem"
        v-for="(item, index) in swiperList"
        :key="index"
      >
        <img class="img" :src="item.fileData" alt="" srcset="" />
      </SwiperSlide>
    </Swiper>
  </div>
</template>

<script lang="ts" setup>
import { getImageList } from "@/request/bullet";
import { defineComponent, onMounted, reactive, ref } from "vue";
import { Swiper, SwiperSlide } from "swiper/vue";
import { Autoplay } from "swiper";
import listener2Callback from "@/request/socket";
import "swiper/css";

let modules = [Autoplay];
const autoplayOptions = {
  delay: 5000,
  disableOnInteraction: false,
};

interface image {
  fileData: string;
  fileSize: number;
  fileType: string;
  filename: string;
  id: number;
}

let swiperList = ref<image[]>([]);

onMounted(async () => {
  getImage();
});
const onSwiper = (swiper: any) => {};
const onSlideChange = () => {};

const getImage = async () => {
  let data = await getImageList();
  data.data.forEach((el: image) => {
    el.fileData = "data:" + el.fileType + ";base64," + el.fileData;
    swiperList.value.push(el);
  });
};

listener2Callback((data: any) => {
  console.log("同步图片");
  if (data.code == 2) {
    swiperList.value = [];
    getImage();
  }
});
</script>

<style>
.swiper {
  z-index: 2;
  height: 100%;
}
.swiperItem {
  display: flex;
  justify-content: center;
  align-items: center;
}
.img {
  height: 1000px;
}
</style>