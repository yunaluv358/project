import axios from 'axios'
import Vue from "vue";
import router from "@/router";

Vue.use(router)

const state = {
    searchWord : 'null',
    context : 'http://localhost:5000',
    soccer : [],
    bugsmusic : [],
    list : [],
    item : {},
    pager : {},
    pages : [],
    nowPage : 0
}

const actions = {
    async find({commit},payload){
        const headers = {
            authorization: 'JWT fefege..',
            Accept : 'application/json',
            'Content-Type': 'application/json'
        }
        switch(payload.searchWord){
            case '영화' :
                state.nowPage = 0
                state.searchWord = 'null'
                router.push('/movie').catch(error => {
                    if(error.name != "NavigationDuplicated"){
                        throw error;
                    }
                })
                break;
            case '벅스' :
                axios.post(state.context+`/proxies/bugsmusic/access`,payload.searchWord,headers)
                    .then(({data})=>{
                        commit('MUSIC_COMMIT', data)
                        router.push('/music').catch(error => {
                            if(error.name != "NavigationDuplicated"){
                                throw error;
                            }
                        })
                    }).catch(()=>{
                    alert('서버전송실패')
                })
                break;
            case '축구' :
                axios.get(state.context + `/soccers/access/`+ payload.searchWord)
                    .then(({data})=>{
                        commit('SOCCER_COMMIT',data)
                        router.push('/soccer').catch(error => {
                            if(error.name != "NavigationDuplicated"){
                                throw error;
                            }
                        })
                    }).catch(()=>{
                    alert('서버전송실패')
                })
                break;
            default :
                state.searchWord = payload.searchWord
                state.nowPage = payload.nowPage
                axios.get(`${state.context}/${payload.category}/0/${payload.searchWord}`)
                    .then(({data})=>{
                        commit('MOVIE',data)
                        router.push('/movie').catch(error=>{
                            if(error.name != "NavigationDuplicated"){
                                throw error;
                            }
                        })
                    })
                    .catch(()=>{
                        alert('서버전송실패')
                    })
                break;
        }

    },
    async transferPage({commit},payload){
        state.nowPage = 0
        state.searchWord = payload.searchWord
        axios.get(`${state.context}/${payload.category}/${payload.nowPage}/${payload.searchWord}`)
            .then(({data})=>{
                commit('MOVIE',data)
                router.push('/movie').catch(error => {
                    if(error.name != "NavigationDuplicated"){
                        throw error;
                    }
                })
            }).catch(()=>{
            alert('서버전송실패')
        })
    },
    async retrieveOne({commit}, payload){
        state.searchWord = payload.searchWord
        state.nowPage = 0
        axios.get(`${state.context}/${payload.category}/${payload.searchWord}`)
            .then(({data})=>{
                commit('MOVIE_SELECTONE',data)
                router.push('/movieDetail').catch(error => {
                    if(error.name != "NavigationDuplicated"){
                        throw error;
                    }
                })
            }).catch(()=>{
            alert('서버전송실패')
        })
    }
}

const mutations = {
    MOVIE(state, data) {
        state.list = [];
        state.pages = [];
        data.list.forEach(item => {
            state.list.push({
                seq : item.seq,
                title : item.title,
                standardDate : item.standardDate,
                movieNo : item.movieNo
            });
        })
        state.pager = data.pager
        let i = state.pager.startPage
        let arr = []
        for(;i<=state.pager.endPage;i++){
            arr.push(i+1)
        }
        state.pages = arr
        state.nowPage = data.pager.nowPage
    },
    SOCCER_COMMIT(state, data) {
        state.soccer = [];
        alert(data)
    },
    MUSIC_COMMIT(state, data) {
        state.bugsmusic = [];
        state.count = data.list.length;
        data.list.forEach(item => {
            state.bugsmusic.push({
                seq: item.seq,
                artist: item.artists,
                title: item.title,
                thumbnail: item.thumbnail
            });
        })
    },
    MOVIE_SELECTONE(state, data) {
        state.item = data
    }
}

export default {
    name : "soccer",
    namespaced : true,
    state,
    actions,
    mutations
}