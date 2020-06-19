import Vue from "vue";
import VueRouter from "vue-router";
import Home from "@/components/Home";
import Soccer from "@/components/Soccer";
import Movie from "@/components/Movie";
import Music from "@/components/Music";
import MovieDetail from "@/components/MovieDetail";
Vue.use(VueRouter);
export default new VueRouter({
    mode: "history",
    base: process.env.BASE_URL,
    routes: [
        { path: "/", component: Home },
        { path: "/soccer", component: Soccer },
        { path: "/movie", component: Movie },
        { path: "/music", component: Music },
        { path: "/movieDetail", component: MovieDetail},
    ]
});