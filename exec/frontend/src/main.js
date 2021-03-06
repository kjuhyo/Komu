// =========================================================
// * Vue Material Kit - v1.2.2
// =========================================================
//
// * Product Page: https://www.creative-tim.com/product/vue-material-kit
// * Copyright 2019 Creative Tim (https://www.creative-tim.com)
// * Licensed under MIT (https://github.com/creativetimofficial/vue-material-kit/blob/master/LICENSE.md)
//
// * Coded by Creative Tim
//
// =========================================================
//
// * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

import Vue from "vue";
import { BootstrapVue, BootstrapVueIcons } from 'bootstrap-vue';
import App from "./App.vue";
import router from ".";
import store from './store';
//import axios from 'axios'

//axios.defaults.baseURL = 'http://localhost:9999/komu/'

import MaterialKit from "./plugins/material-kit";

//Vue.prototype.axios = axios // 사용법 => this.axios
Vue.config.productionTip = false;

Vue.use(MaterialKit);
Vue.use(BootstrapVue);
Vue.use(BootstrapVueIcons);

const NavbarStore = {
  showNavbar: false
};

Vue.mixin({
  data() {
    return {
      NavbarStore
    };
  }
});

new Vue({
  router,
  store,
  //beforeCreate() {
  //  this.$store.dispatch('getMemberInfo')
  //},
  render: h => h(App)
}).$mount("#app");
