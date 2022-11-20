<template>
  <div style="padding: 10px">
    <el-table :data="tableData" border="border" height="580">
      <el-table-column prop="_id" label="课程编号" width="120px" />
      <el-table-column prop="name" label="课程名称" />
      <el-table-column prop="count" label="选课人数" width="120px" />
    </el-table>
  </div>
</template>

<script>
import request from "../../untils/request";

export default {
  name: "coursesCount",

  data() {
    return {
      tableData: [],
      form: {}, //某行数据&&添加数据
    };
  },
  created() {
    this.load();
  },
  methods: {
    load() {
      request.get("/course/viewChosenCount").then((res) => {
        this.tableData = res.data;
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
