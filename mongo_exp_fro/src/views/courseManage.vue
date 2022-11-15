<template>
  <!--    搜索头部区域-->
  <div style="padding: 10px; height: 50px; line-height: 50px; display: flex">
    <el-input
      v-model.trim="search"
      placeholder="请输入先行课编号"
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
    >
      <el-dropdown>
        <el-button type="primary" color="#fab6b6" style="margin-left: 15px">
          新增课程<el-icon><arrow-down /></el-icon>
        </el-button>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="addDialogVisible = true"
              >单次新增</el-dropdown-item
            >
            <el-dropdown-item @click="uploadDialogVisible = true"
              >批量新增</el-dropdown-item
            >
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
    <el-dialog v-model="addDialogVisible" title="新增课程" width="30%">
      <el-form :model="form" label-width="100px">
        <el-form-item label="课程名">
          <el-input v-model.trim="form.name"></el-input>
        </el-form-item>
        <el-form-item label="先行课编号">
          <el-input
            v-model.trim="form.fcid"
            oninput="value=value.replace(/[^0-9.]/g,'')"
          ></el-input>
        </el-form-item>
        <el-form-item label="学分">
          <el-input
            v-model.trim="form.credit"
            oninput="value=value.replace(/[^0-9.]/g,'')"
          ></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <span>
          <el-button type="primary" color="#fab6b6" @click="save"
            >保存</el-button
          >
        </span>
      </template>
    </el-dialog>
    <el-dialog v-model="uploadDialogVisible" title="新增课程" width="30%">
      <el-upload
        action="http://localhost:9090/course/excelUpload"
        accept=".xls,.xlsx"
        :on-success="handleSuccess"
        :on-error="handleError"
      >
        <el-button>选择EXCEL进行上传</el-button>
      </el-upload>
    </el-dialog>
  </div>
  <div style="padding: 10px">
    <el-dialog v-model="dialogVisible" title="课程信息" width="30%">
      <el-form :model="form" label-width="100px">
        <el-form-item label="课程ID">
          <el-input v-model="form.cid" disabled></el-input>
        </el-form-item>
        <el-form-item label="课程名称">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="先行课编号">
          <el-input
            v-model="form.fcid"
            oninput="value=value.replace(/[^0-9.]/g,'')"
          ></el-input>
        </el-form-item>
        <el-form-item label="学分">
          <el-input
            v-model="form.credit"
            oninput="value=value.replace(/[^0-9.]/g,'')"
          ></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <span>
          <el-button type="primary" color="#fab6b6" @click="update"
            >保存</el-button
          >
        </span>
      </template>
    </el-dialog>
    <el-table :data="tableData" border="border" height="580">
      <el-table-column prop="cid" label="课程编号" width="120px" />
      <el-table-column prop="name" label="课程名称" />
      <el-table-column prop="fcid" label="先行课编号" width="120px" />
      <el-table-column prop="credit" label="学分" />
      <el-table-column label="操作" width="200px" align="center">
        <template #default="scope">
          <el-button @click="handleEdit(scope.row)">编辑</el-button>
          <el-button @click="handleEdit(scope.row)" color="#fab6b6"
            >暂无</el-button
          >
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import request from "../../untils/request";

export default {
  name: "courseManage",

  data() {
    return {
      dialogVisible: false,
      addDialogVisible: false,
      uploadDialogVisible: false,
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
    handleSuccess(res) {
      if (res.code === 0) {
        this.$message({
          type: "success",
          message: "上传成功",
        });
        this.load();
      } else {
        this.$message({
          type: "error",
          message: res.msg,
        });
      }
    },
    handleError() {
      this.$message({
        type: "error",
        message: "excel数据格式错误",
      });
      this.load();
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
    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row));
      this.dialogVisible = true;
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
