<template>
  <div class="wrapper">
    <parallax class="section page-header header-filter" :style="headerStyle">
      <div class="container">
        <div class="md-layout">
          <div
            class="md-layout-item md-size-50 md-small-size-70 md-xsmall-size-100"
          >
            <span class="mainpage-title">Find your KPOP</span>
            <h4 class="mainpage-title-sub">
              K-pop refers to popular music that is popular in Korea. Recently,
              K-POP is creating a sensation all across the world. And a large
              number of young people all over the world now enjoy K-pop. So,
              this website is created for people who are interested in kpop.
              Please enjoy this website!
            </h4>
            <br />
          </div>
        </div>
      </div>
    </parallax>
    <!-- 헤더 end -->

    <!-- 메인페이지 바디 -->
    <div class="main main-raised">
      <!-- 1ST SECTION = 코뮤, 메인기능 -->
      <div class="section">
        <div class="container first-container">
          <div class="md-layout">
            <div
              class="md-layout-item md-size-66 md-xsmall-size-100 mx-auto text-center"
            >
              <h2 class="title text-center">What is KOMU?</h2>
              <h5 class="description">
                Hi! KOMU stands for Korean Music. We provides you
                with'Recommendation','Komuwiki', and'Community' services. Meet
                various Korean songs on the KOMU website!
              </h5>
            </div>
          </div>

          <!-- 기능 -->
          <div class="features text-center">
            <div class="md-layout">
              <!-- recommend -->
              <div class="md-layout-item md-medium-size-33 md-small-size-100">
                <div class="info">
                  <div class="icon icon-danger">
                    <md-icon>play_circle</md-icon>
                  </div>
                  <h4 class="info-title">Recommend</h4>
                  <p>
                    We recommend songs based on your favorite songs. Please
                    choose a song and pretty words to use the recommended
                    service.
                  </p>
                </div>
              </div>

              <!-- komuwiki -->
              <div class="md-layout-item md-medium-size-33 md-small-size-100">
                <div class="info">
                  <div class="icon icon-success">
                    <md-icon>menu_book</md-icon>
                  </div>
                  <h4 class="info-title">Komuwiki</h4>
                  <p>
                    Don't you want to know the meaning and culture of Korean in
                    the lyrics? Check the meaning of words in the Komuwiki and
                    add them yourself!
                  </p>
                </div>
              </div>

              <!-- community -->
              <div class="md-layout-item md-medium-size-33 md-small-size-100">
                <div class="info">
                  <div class="icon icon-info">
                    <md-icon>chat</md-icon>
                  </div>
                  <h4 class="info-title">Community</h4>
                  <p>
                    Post articles about KPOP or Korea! Communicate with people
                    in the community through comments and posts.
                  </p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- 1ST SECTION END -->
    </div>
    <!-- 메인페이지 바디 끝 -->
  </div>
</template>

<script>
import '../assets/css/mainpage.scss';

export default {
  bodyClass: 'landing-page',
  props: {
    header: {
      type: String,
      default: require('@/assets/img/concert11.jpg'),
    },
    teamImg1: {
      type: String,
      default: require('@/assets/img/faces/avatar.jpg'),
    },
    teamImg2: {
      type: String,
      default: require('@/assets/img/faces/christian.jpg'),
    },
    teamImg3: {
      type: String,
      default: require('@/assets/img/faces/kendall.jpg'),
    },
  },
  data() {
    return {
      name: null,
      email: null,
      message: null,
    };
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
};
</script>

<style lang="scss" scoped>
.md-card-actions.text-center {
  display: flex;
  justify-content: center !important;
}
.contact-form {
  margin-top: 30px;
}

.md-has-textarea + .md-layout {
  margin-top: 15px;
}
</style>
