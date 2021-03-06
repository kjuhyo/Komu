import { createInstance } from "./index.js";

const instance = createInstance();

function getboard(cid, uid, success, fail){
    instance
        .get(`/community/${cid}?uid=${uid}`)
        .then(success)
        .catch(fail);
}

function getlist(page, success, fail){
    instance
    .get(`/community/list/${page}`)
    .then(success)
    .catch(fail);
}
function poplist(page, success,fail){
    instance
    .get(`/community/popul/${page}`)
    .then(success)
    .catch(fail);
}

function letlike(articlelike, success,fail){
    instance
    .post(`/community/like`, articlelike)
    .then(success)
    .catch(fail);
}

function letdelete(cid, success,fail){
    instance
    .post(`/community/delete/${cid}`)
    .then(success)
    .catch(fail);
}

function insert(FormData, success, fail){
    instance
    .post(`/community/insert`, FormData,{
        headers: {
          "Content-Type": "multipart/form-data"
        }
      })
    .then(success)
    .catch(fail);
}

function insert_nopic(article, success, fail){
    instance
    .post(`/community/insert/np`, article)
    .then(success)
    .catch(fail);
}

function update_nopic(community, success, fail){
    instance
    .put(`/community//update/np`, community)
    .then(success)
    .catch(fail);
}

function update(FormData, success, fail){
    instance
    .put(`/community/update`, FormData,{
        headers: {
          "Content-Type": "multipart/form-data"
        }
      })
    .then(success)
    .catch(fail);
}

function search_list(c_title, success, fail){
    instance
    .get(`/community/${c_title}`)
    .then(success)
    .catch(fail);
}
function insert_comm(comm, success,fail){
    instance
    .post(`/comment/community/regist`,comm)
    .then(success)
    .catch(fail);
}

export{
    getboard,
    getlist,
    letlike,
    insert,
    insert_nopic,
    update_nopic,
    update,
    search_list,
    letdelete,
    insert_comm,
    poplist,

};