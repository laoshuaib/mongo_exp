<!--suppress CssUnusedSymbol, JSUnusedGlobalSymbols -->
<template>
  <div
    style="
      height: 50px;
      line-height: 50px;
      border-bottom: 1px solid #ccc;
      display: flex;
      background: #fdf6ec;
    "
  >
    <div
      style="
        width: 200px;
        padding-left: 50px;
        font-weight: bold;
        font-size: 18px;
        color: #c95f5f;
      "
    >
      xx选课系统
    </div>
  </div>
</template>

<script>
export default {
  name: "managerHeader",
  components: {},
  data() {},
  created() {
    let str = sessionStorage.getItem("user");
    this.form = JSON.parse(str);
  },
  methods: {},
};
</script>

<style scoped>
/deep/ .el-input {
  width: 85%;
}

/deep/ .el-dialog {
  background: #fcf9f4;
}

/deep/.el-form-item {
  margin-bottom: 30px;
}
</style>
