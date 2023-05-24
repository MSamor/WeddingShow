import { createApp } from 'vue'
import './style.css'
import 'animate.css';
import App from './App.vue'

// 加载粒子动画效果
import Particles from "vue3-particles";
createApp(App).use(Particles).mount('#app')