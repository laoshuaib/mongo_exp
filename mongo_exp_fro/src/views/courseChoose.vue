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
      >查询
    </el-button>
    <div style="flex: 1"></div>
    <div
      style="
        width: 150px;
        display: flex;
        justify-content: center;
        align-items: center;
      "
    >
      <el-button
        type="primary"
        color="#fab6b6"
        style="margin-left: 15px"
        @click="drawerVisible = true"
        >已选课程
      </el-button>
    </div>
  </div>
  <div style="padding: 10px">
    <el-drawer
      v-model="drawerVisible"
      title="已选课程"
      direction="rtl"
      size="45%"
    >
      <el-table :data="drawerData" height="580">
        <el-table-column property="cid" label="课程编号" width="150" />
        <el-table-column property="cname" label="课程名称" width="200" />
        <el-table-column property="credit" label="学分" width="200" />
        <el-table-column label="操作" width="100px" align="center">
          <template #default="scope">
            <el-button size="mini" @click="handleDelete(scope.row)"
              >删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-drawer>
    <el-table :data="tableData" border="border" height="580">
      <el-table-column prop="cid" label="课程编号" width="120px" />
      <el-table-column prop="cname" label="课程名称" />
      <el-table-column prop="fcid" label="先行课编号" width="120px" />
      <el-table-column prop="credit" label="学分" />
      <el-table-column prop="tid" label="教师编号" width="120px" />
      <el-table-column prop="tname" label="教师姓名" />
      <el-table-column label="操作" width="200px" align="center">
        <template #default="scope">
          <el-button @click="handleEdit(scope.row)" color="#fab6b6"
            >选课
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import request from "../../untils/request";

export default {
  name: "courseChoose",

  data() {
    return {
      drawerVisible: false,
      tableData: [],
      drawerData: [],
      form: {}, //某行数据&&添加数据
      search: "",
      tempSid: "",
    };
  },
  methods: {
    load() {
      request
        .get("/course/findSelected", {
          params: {
            sid: this.tempSid,
          },
        })
        .then((res) => {
          if (res.code === 0) {
            this.$message({
              type: "success",
              message: "获取已选课程成功",
            });
            this.drawerData = res.data;
          } else {
            this.$message({
              type: "error",
              message: res.msg,
            });
          }
        });
      request
        .get("/course/findUnSelect", {
          params: {
            sid: this.tempSid,
          },
        })
        .then((res) => {
          if (res.code === 0) {
            this.$message({
              type: "success",
              message: "获取未选课程成功",
            });
            this.tableData = res.data;
          } else {
            this.$message({
              type: "error",
              message: res.msg,
            });
          }
        });
    },
    find() {
      this.tempSid = this.search;
      this.load();
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

/deep/ .el-form-item {
  margin-bottom: 30px;
}
</style>
<style lang="less">
.el-message-box {
  background: #fcf9f4;
}
</style>
