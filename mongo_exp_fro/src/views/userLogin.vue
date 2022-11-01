<!--suppress CssUnusedSymbol -->
<template>
  <div
    :style="{
      width: '100%',
      height: '100vh',
      overflow: 'hidden',
      background: 'url(' + bg + ')',
      backgroundSize: 'cover',
    }"
  >
    <div style="width: 400px; margin: 150px auto">
      <div
        style="
          color: #ffffff;
          font-size: 30px;
          text-align: center;
          padding: 30px 0;
        "
      >
        xx选课系统
      </div>
      <el-form ref="form" :model="form" :rules="rules">
        <el-form-item prop="id">
          <el-input prefix-icon="Avatar" v-model.trim="form.id"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            prefix-icon="Lock"
            v-model.trim="form.password"
            @keyup.enter="login"
            show-password
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button
            style="width: 100%"
            type="primary"
            @click="login"
            size="large"
            color="#f3d19e"
            >登录</el-button
          >
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
// import request from "../../untils/request";

export default {
  name: "userLogin",
  data() {
    return {
      bg: require("../assets/background.jpg"),
      form: {},
      rules: {
        id: [
          {
            required: true,
            message: "请输入工号",
            trigger: "blur",
          },
        ],
        password: [
          {
            required: true,
            message: "请输入密码",
            trigger: "blur",
          },
        ],
      },
    };
  },

  created() {
    sessionStorage.removeItem("user");
  },
  methods: {
    login() {
      //暂时不做登录逻辑，点击立即跳转
      this.$router.push("/manager/courseManage");
      // this.$refs["form"].validate((valid) => {
      //   if (valid) {
      //     request.post("/user/login", this.form).then((res) => {
      //       if (res.code === 0) {
      //         this.$message({
      //           type: "success",
      //           message: "登录成功",
      //         });
      //         sessionStorage.setItem("user", JSON.stringify(res.data)); //缓存用户信息
      //         if (res.data.job === 0) {
      //           this.$router.push("/worker/orderMeal"); //职工登录成功页面跳转
      //         } else {
      //           this.$router.push("/manager/orderMeal"); //经理登录成功页面跳转
      //         }
      //       } else {
      //         this.$message({
      //           type: "error",
      //           message: res.msg,
      //         });
      //       }
      //     });
      //   }
      // });
    },
  },
};
</script>

<style scoped lang="less">
/deep/.el-input__wrapper {
  height: 47px;
  font-size: 16px;
}
/deep/.el-form-item__error {
  font-size: 14px;
  font-weight: bold;
  color: #eb3434;
}
/deep/.el-form-item {
  margin-bottom: 30px;
}
</style>
