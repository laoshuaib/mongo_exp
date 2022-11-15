<template>
  <!--    搜索头部区域-->
  <div style="padding: 10px; height: 50px; line-height: 50px; display: flex">
    <el-input
      v-model.trim="search"
      placeholder="暂时废弃"
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
      <el-button
        type="primary"
        color="#fab6b6"
        style="margin-left: 15px"
        @click="addDialogVisible = true"
        >新增学生</el-button
      >
    </div>
    <el-dialog v-model="addDialogVisible" title="新增学生" width="30%">
      <el-form :model="form" label-width="100px">
        <el-form-item label="姓名">
          <el-input v-model.trim="form.name"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio-group v-model="form.sex">
            <el-radio label="男">男</el-radio>
            <el-radio label="女">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="年龄">
          <el-input
            v-model.trim="form.age"
            oninput="value=value.replace(/[^0-9.]/g,'')"
          ></el-input>
        </el-form-item>
        <el-form-item label="出生日期">
          <el-input v-model.trim="form.birthday"></el-input>
        </el-form-item>
        <el-form-item label="院系名称">
          <el-input v-model.trim="form.dname"></el-input>
        </el-form-item>
        <el-form-item label="班级">
          <el-input v-model.trim="form.grade"></el-input>
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
  </div>
  <div style="padding: 10px">
    <el-dialog v-model="dialogVisible" title="学生信息" width="30%">
      <el-form :model="form" label-width="100px">
        <el-form-item label="学生ID">
          <el-input v-model="form.sid" disabled></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio-group v-model="form.sex">
            <el-radio label="男">男</el-radio>
            <el-radio label="女">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="年龄">
          <el-input
            v-model.trim="form.age"
            oninput="value=value.replace(/[^0-9.]/g,'')"
          ></el-input>
        </el-form-item>
        <el-form-item label="出生日期">
          <el-input v-model.trim="form.birthday"></el-input>
        </el-form-item>
        <el-form-item label="院系名称">
          <el-input v-model.trim="form.dname"></el-input>
        </el-form-item>
        <el-form-item label="班级">
          <el-input v-model.trim="form.grade"></el-input>
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
      <el-table-column prop="sid" label="学生编号" width="120px" />
      <el-table-column prop="name" label="姓名" />
      <el-table-column prop="sex" label="性别" />
      <el-table-column prop="age" label="年龄" />
      <el-table-column prop="birthday" label="出生日期" width="120px" />
      <el-table-column prop="dname" label="院系名称" />
      <el-table-column prop="grade" label="班级" />
      <el-table-column label="操作" width="200px" align="center">
        <template #default="scope">
          <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
          <el-button size="mini" @click="handleEdit(scope.row)" color="#fab6b6"
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
  name: "studentManage",

  data() {
    return {
      dialogVisible: false,
      addDialogVisible: false,
      tableData: [],
      form: {}, //某行数据
      search: "",
    };
  },
  created() {
    this.load();
  },
  methods: {
    load() {
      request.get("/student/view").then((res) => {
        this.tableData = res.data;
      });
    },
    save() {
      request.post("/student/save", this.form).then((res) => {
        if (res.code === 0) {
          this.$message({
            type: "success",
            message: "新增成功",
          });
          this.addDialogVisible = false;
          this.load();
        } else {
          this.$message({
            type: "error",
            message: res.msg,
          });
        }
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
    update() {
      request.post("/student/update", this.form).then((res) => {
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
