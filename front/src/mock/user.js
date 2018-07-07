import Mock from 'mockjs'

export default {
  login: config => {
    return Mock.mock(
      {
        'result|1': [true, false, true, true, true],
        'message|1': ['密码错误！', '用户不存在！']
      })
  },
  register: config => {
    return {
      result: true,
      message: ''
    }
  },
  getUserInfo: config => {
    if (Math.random() > 0.9) {
      return {
        result: false,
        message: '系统出了一些故障，请等一会儿在再试一下吧'
      }
    } else {
      return Mock.mock({
        result: true,
        message: {
          'id|1': ['100001', '100009', '100003', '100005', '100002'],
          'nickname': '@CNAME',
          'email': '@EMAIL'
        }
      })
    }
  },
  findPassword: req => {
    return {
      result: true,
      message: ''
    }
  },
  changeNickname: req => {
    return {
      result: true,
      message: '修改成功'
    }
  },
  changeEmail: req => {
    return {
      result: true,
      message: '修改成功'
    }
  },
  changePassword: req => {
    return {
      result: true,
      message: '修改成功'
    }
  },
  getWorkerData: req => {
    if (Math.random() > 0.9) {
      return {
        result: false,
        message: '系统出了一些故障，请等一会儿在再试一下吧'
      }
    } else {
      return Mock.mock({
        result: true,
        message: {
          id: '123124',
          points: 50,
          doingMissions: 3,
          doneMissions: 4,
          rightAns: 5,
          wrongAns: 6,
          toBeJudgedAns: 7,
          totalAns: 8,
          ansAccuracy: 0.8,
          email: '1422145612@qq.com' }
      })
    }
  },
  getRequesterData: req => {
    if (Math.random() > 0.9) {
      return {
        result: false,
        message: '系统出了一些故障，请等一会儿在再试一下吧'
      }
    } else {
      return Mock.mock({
        result: true,
        message: {
          nickname: '大皮',
          doingMissions: 3,
          doneMissions: 5,
          paidPoints: 2000
        }
      })
    }
  },
  logout: () => 'success'
}
