<template>
  <!--    搜索头部区域-->
  <div style="padding: 10px; height: 50px; line-height: 50px; display: flex">
    <el-input
      v-model.trim="search"
      placeholder="请输入学生编号"
      style="width: 20%"
      clearable
    />
    <el-button
      type="primary"
      color="#fab6b6"
      style="margin-left: 10px"
      @click="find"
      >查询</el-button
    >
    <div style="flex: 1"></div>
    <div
      style="
        width: 150px;
        display: flex;
        justify-content: center;
        align-items: center;
      "
    ></div>
  </div>
  <div style="padding: 10px">
    <el-table :data="tableData" border="border" height="580">
      <el-table-column prop="cid" label="课程编号" width="120px" />
      <el-table-column prop="name" label="课程名称" />
      <el-table-column prop="fcid" label="先行课编号" width="120px" />
      <el-table-column prop="credit" label="学分" />
    </el-table>
  </div>
</template>

<script>
import request from "../../untils/request";

export default {
  name: "courseChoose",

  data() {
    return {
      tableData: [],
      form: {}, //某行数据&&添加数据
      search: "",
    };
  },
  created() {
    this.load();
  },
  methods: {
    load() {
      request.get("/course/view").then((res) => {
        this.tableData = res.data;
      });
    },
    find() {
      request
        .get("/course/find", {
          params: {
            search: this.search,
          },
        })
        .then((res) => {
          this.tableData = res.data;
          this.total = res.data.total;
        });
    },
    save() {
      request.post("/course/save", this.form).then((res) => {
        if (res.code === 0) {
          this.$message({
            type: "success",
            message: "新增成功",
          });
          this.addDialogVisible = false;
          this.form = {};
          this.load();
        } else {
          this.$message({
            type: "error",
            message: res.msg,
          });
        }
      });
    },
    update() {
      request.post("/course/update", this.form).then((res) => {
        console.log(res);
        if (res.code === 0) {
          this.$message({
            type: "success",
            message: "更新成功",
          });
          this.dialogVisible = false;
          this.load();
        } else {
          this.$message({
            type: "error",
            message: res.msg,
          });
        }
      });
    },
  },
};
</script>

<style scoped lang="less">
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
<style lang="less">
.el-message-box {
  background: #fcf9f4;
}
</style>
