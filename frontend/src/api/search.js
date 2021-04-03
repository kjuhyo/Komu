import { createInstance } from "./index.js";

const instance = createInstance();

/*
*  가수명으로 가수 검색 (좋아요 순)
*/
function getSingerName(name, success, fail){
    instance
        .get(`/search/singer/${name}`)
        .then(success)
        .catch(fail);
}

/*
*  노래제목이나 가수명으로 노래 검색 (인기순)
*/
function getPopularSongName(name, success, fail){
    instance
        .get(`/search/song/${name}`)
        .then(success)
        .catch(fail);
}

/*
*  노래제목이나 가수명으로 노래 검색 (최신순)
*/
function getNewSongName(name, success, fail){
    instance
        .get(`/search/song/new/${name}`)
        .then(success)
        .catch(fail);
}

export{
    getSingerName,
    getPopularSongName,
    getNewSongName
};