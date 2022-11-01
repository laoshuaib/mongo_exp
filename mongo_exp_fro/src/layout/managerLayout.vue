<!--suppress CssUnusedSymbol -->
<template>
  <div>
    <!--头部-->
    <ManagerHeader />
    <!--主体-->
    <div style="display: flex">
      <el-container>
        <el-aside width="200px">
          <!--      侧边栏-->
          <manager-aside />
        </el-aside>
        <el-main>
          <!--      内容区域-->
          <router-view style="flex: 1" />
        </el-main>
      </el-container>
    </div>
  </div>
</template>

<script>
import ManagerAside from "@/components/managerAside";
import ManagerHeader from "@/components/managerHeader";

export default {
  name: "managerLayout",
  components: {
    ManagerAside,
    ManagerHeader,
  },
  data() {},
};
</script>

<style scoped></style>
