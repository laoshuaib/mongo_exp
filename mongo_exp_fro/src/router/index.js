import { createRouter, createWebHistory } from "vue-router";
import managerLayout from "../layout/managerLayout.vue";
const routes = [
  {
    path: "/",
    redirect: "/login",
  },
  {
    path: "/manager",
    name: "managerLayout",
    component: managerLayout,
    children: [
      {
        path: "courseManage",
        name: "courseManage",
        component: () => import("@/views/courseManage"),
      },
      {
        path: "studentManage",
        name: "studentManage",
        component: () => import("@/views/studentManage"),
      },
      {
        path: "teacherManage",
        name: "teacherManage",
        component: () => import("@/views/teacherManage"),
      },
      {
        path: "courseChoose",
        name: "courseChoose",
        component: () => import("@/views/courseChoose"),
      },
      {
        path: "courses",
        name: "courses",
        component: () => import("@/views/courses"),
      },
      {
        path: "coursesCount",
        name: "coursesCount",
        component: () => import("@/views/coursesCount"),
      },
      {
        path: "studentsCount",
        name: "studentsCount",
        component: () => import("@/views/studentsCount"),
      },
    ],
  },
  {
    path: "/login",
    name: "Login",
    component: () => import("@/views/userLogin"),
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
