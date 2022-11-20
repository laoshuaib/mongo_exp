<!--suppress CssUnusedSymbol -->
<template>
  <div>
    <el-menu
      active-text-color="#dc143c"
      background-color="#fdf6ec"
      default-active="courseManage"
      router
      :default-openeds="[1]"
    >
      <el-menu-item index="courseManage">
        <el-icon><Menu /></el-icon>
        <span>课程管理</span>
      </el-menu-item>
      <el-menu-item index="studentManage">
        <el-icon><User /></el-icon>
        <span>学生管理</span>
      </el-menu-item>
      <el-menu-item index="teacherManage">
        <el-icon><Avatar /></el-icon>
        <span>教师管理</span>
      </el-menu-item>
      <el-menu-item index="courseChoose">
        <el-icon><Monitor /></el-icon>
        <span>学生选课</span>
      </el-menu-item>
      <el-menu-item index="courses">
        <el-icon><Histogram /></el-icon>
        <span>统计1</span>
      </el-menu-item>
      <el-menu-item index="studentsCount">
        <el-icon><Histogram /></el-icon>
        <span>统计3</span>
      </el-menu-item>
      <el-menu-item index="coursesCount">
        <el-icon><Histogram /></el-icon>
        <span>统计9</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>
<script>
export default {
  name: "managerAside",
};
</script>

<style>
/*尽量避免使用submenu，会把页面撑爆*/
.el-menu {
  height: calc(calc(100vh) - 50px);
}
</style>
