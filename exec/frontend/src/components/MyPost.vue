<template>
  <div>
    <div class="mypost-article" v-if="this.commuList.length == 0">
      작성한 글이 없어요!
    </div>
    <table v-else>
      <thead class="thead-css">
        <tr>
          <th width="5%" scope="col">No</th>
          <th width="60%" scope="col">Title</th>
          <th width="15%" scope="col">Writer</th>
          <th width="20%" scope="col">Date</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(commu, idx) in commuList" :key="idx">
          <td data-label="No">{{ commu.cid }}</td>
          <td data-label="Contents">
            <router-link :to="`/communitydetail/${commu.cid}`">
              {{ commu.c_title }}
            </router-link>
          </td>
          <td data-label="Writer">{{ commu.nickname }}</td>
          <td data-label="Date">{{ commu.c_date }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import '../assets/css/article.css';
import { getuidCookie } from '@/util/cookie.js';
import { profileByUid } from '@/api/user.js';

export default {
  namu: 'MyPost',
  props: {
    commuList: { type: Object },
  },
  data: function () {
    return {
      uid: '',
      nickname: '',
    };
  },
  created() {
    this.initUser(),
      profileByUid(
        this.uid,
        (response) => {
          this.nickname = response.data.info.nickname;
        },
        (error) => {
          console.log(error);
        }
      );
  },
  methods: {
    initUser() {
      this.uid = getuidCookie();
    },
  },
};
</script>
<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700;800&display=swap');

.mypost-article {
  font-family: 'Nanum Gothic', sans-serif;
  text-align: center;
  /* margin: auto; */
  padding: 20px;
  font-size: 18px;
  margin: 10px;
}
</style>
