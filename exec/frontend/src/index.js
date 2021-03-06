import Vue from "vue";
import Router from "vue-router";
import Login from "./views/Login.vue";
import Profile from "./views/Profile.vue";
import MainNavbar from "./layout/MainNavbar.vue";
import MainFooter from "./layout/MainFooter.vue";
import Community from "./views/Community.vue";
import CommunityDetail from "./views/CommunityDetail.vue";
import CommunityUpdate from "./views/CommunityUpdate.vue";
import SingerDetail from "./views/SingerDetail.vue";
import Singer from "./views/Singer.vue";
import KomuWikiDetail from "./views/KomuWikiDetail.vue";
import KomuWiki from "./views/KomuWiki.vue";
import NotFound from "./views/NotFound.vue";
import Song from "./views/Song.vue";
import SongDetail from "./views/SongDetail.vue";
import GenreRecommend from "./views/GenreRecommend.vue";
import Write from "./views/Write.vue";
import KomuWrite from "./views/KomuWrite.vue";
import KomuUpdate from "./views/KomuUpdate.vue";
import LyricsRecommend from "./views/LyricsRecommend.vue";
import PopularSong from "./views/PopularSong.vue";
import MainPage from "./views/MainPage.vue";

Vue.use(Router);

export default new Router({
  mode:'history',
  routes: [
    {
      path: "/",
      name: "mainpage",
      components: { default: MainPage, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/login",
      name: "login",
      components: { default: Login, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 }
      }
    },
    {
      path: "/profile",
      name: "profile",
      components: { default: Profile, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/community",
      name: "community",
      components: { default: Community, header: MainNavbar, footer: MainFooter},
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/communitydetail/:name",
      name: "communitydetail",
      components: { default: CommunityDetail, header: MainNavbar, footer: MainFooter},
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/singerdetail",
      name: "singerdetail",
      components: { default: SingerDetail, header: MainNavbar, footer: MainFooter},
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/singer",
      name: "singer",
      components: { default: Singer, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/komuwikidetail/:name",
      name: "komuwikidetail",
      components: { default: KomuWikiDetail, header: MainNavbar, footer: MainFooter},
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },

    {
      path: "/komuwiki",
      name: "komuwiki",
      components: { default: KomuWiki, header: MainNavbar, footer: MainFooter},
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/song",
      name: "song",
      components: { default: Song, header: MainNavbar, footer: MainFooter},
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/songdetail",
      name: "songdetail",
      components: { default: SongDetail, header: MainNavbar, footer: MainFooter},
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/genrerecommend",
      name: "genrerecommend",
      components: { default: GenreRecommend, header: MainNavbar, footer: MainFooter},
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: "/lyricsrecommend",
      name: "lyricsrecommend",
      components: { default: LyricsRecommend, header: MainNavbar, footer: MainFooter},
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {  
      path: "/communitywrite",
      name: "communitywrite",
      components: { default: Write, header: MainNavbar, footer: MainFooter},
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {  
      path: "/communityupdate/:name",
      name: "communityupdate",
      components: { default: CommunityUpdate, header: MainNavbar, footer: MainFooter},
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {  
      path: "/popularsong",
      name: "PopularSong",
      components: { default: PopularSong, header: MainNavbar, footer: MainFooter},
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {  
      path: "/komuwikiwrite",
      name: "komuwikiwrite",
      components: { default: KomuWrite, header: MainNavbar, footer: MainFooter},
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {  
      path: "/komuwikiupdate/:name",
      name: "komuwikiupdate",
      components: { default: KomuUpdate, header: MainNavbar, footer: MainFooter},
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path:'/404',
      name:"notfound",
      components: { default: NotFound, header: MainNavbar, footer: MainFooter},
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" }
      }
    },
    {
      path: '/:pathMatch(.*)*',
      redirect: "/404"
    },
   
    
  ],
  scrollBehavior: to => {
    if (to.hash) {
      return { selector: to.hash };
    } else {
      return { x: 0, y: 0 };
    }
  }
});
