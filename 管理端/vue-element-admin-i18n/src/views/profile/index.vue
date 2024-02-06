<template>
  <div class="app-container">
    <div v-if="user">
      <el-row :gutter="20">

        <el-col :span="6" :xs="24">
          <user-card :user="user" />
        </el-col>

        <el-col :span="18" :xs="24">
          <el-card>
            <el-tabs v-model="accountTab">

              <el-tab-pane label="Account" name="account">
                <account :user="user" />
              </el-tab-pane>
            </el-tabs>
          </el-card>
        </el-col>

      </el-row>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import UserCard from './components/UserCard'
import Account from './components/Account'
import { getUser1 } from '@/api/user'

export default {
  name: 'Profile',
  components: { UserCard, Account },
  data() {
    return {
      user: {},
      accountTab: 'account'
    }
  },
  computed: {
    ...mapGetters([
      'name',
      'avatar',
      'roles'
    ])
  },
  created() {
    this.getUser()
    this.getUserInfo()
  },
  methods: {
    getUser() {
      this.user = {
        name: this.name,
        role: this.roles.join(' | '),
        phone: 'admin@test.com',
        avatar: this.avatar,
        introduction: 'asdasd'
      }
    },
    async getUserInfo() {
      await getUser1({})
        .then(res => {
          console.log(res)
          console.log(res.data)
          this.user = {
            name: res.data.name,
            role: this.roles.join(' | '),
            phone: res.data.phone,
            avatar: this.avatar,
            introduction: res.data.introduction
          }
        })
        .catch(err => {
          this.$message.error('请求出错了：' + err.message)
        })
    }
  }

}
</script>
