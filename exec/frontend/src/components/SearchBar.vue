<template>
  <div v-if="isMobile">
    <form class="m-search-container" @submit.prevent="letsearch">
      <input
        type="text"
        id="search-bar"
        placeholder="검색어를 입력해주세요"
        @keyup.enter="letsearch"
        v-model="data"
      />
      <a href="#"
        ><img
          class="search-icon"
          src="http://www.endlessicons.com/wp-content/uploads/2012/12/search-icon.png"
          @click="letsearch"
      /></a>
    </form>
  </div>

  <div v-else>
    <form class="search-container" @submit.prevent="letsearch">
      <input
        type="text"
        id="search-bar"
        placeholder="검색어를 입력해주세요"
        @keyup.enter="letsearch"
        v-model="data"
      />
      <img
        class="search-icon"
        src="http://www.endlessicons.com/wp-content/uploads/2012/12/search-icon.png"
        @click="letsearch"
      />
    </form>
  </div>
</template>

<script>
import '../assets/css/searchbar.scss';

export default {
  data() {
    return {
      // searchInput: '',
      isMobile: false,
      data: '',
    };
  },
  mounted() {
    this.onResize();
    window.addEventListener('resize', this.onResize);
  },
  methods: {
    onResize: function () {
      this.isMobile = window.innerWidth <= 480;
    },
    letsearch: function () {
      console.log('여기는서치바응답하라오바');
      console.log(this.data);
      this.$emit('eventdata', this.data);
    },
  },
};
</script>
