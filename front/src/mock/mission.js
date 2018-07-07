import Mock from 'mockjs'
import { param2Obj } from '@/utils'

export default {
  buildMission: config => {
    const result = {
      result: true,
      message: ''
    }
    return result
  },
  getNewMissions: config => {
    if (Math.random() > 0.9) {
      const newMissions2 = {
        result: false,
        message: '系统出了一些故障，请等一会儿在再试一下吧'
      }
      return newMissions2
    } else {
      const newMissions = Mock.mock(
        {
          'result': true,
          'message|5-10': [{
            'id|+1': 1,
            'title|1': ['人脸画框', '马路描边', '动物识别', '猫狗区分'],
            'type|1': ['border_mark', 'comment', 'frame_mark', 'frame_mc', 'border_mc'],
            'description': '@csentence',
            'price|1-10': 1,
            'classLabel|1-3': ['@integer(1,20)'],
            'publishDate|1': ['2018-4-23', '2018-5-23', '2018-5-24', '2018-5-25', '2018-5-26', '2018-5-27', '2018-5-28', '2018-5-29']
          }]
        })
      return newMissions
    }
  },
  getHotMissions: config => {
    if (Math.random() > 0.9) {
      const newMissions2 = {
        result: false,
        message: '系统出了一些故障，请等一会儿在再试一下吧'
      }
      return newMissions2
    } else {
      const newMissions = Mock.mock(
        { 'result': true,
          'message|4': [{
            'id|+1': 1,
            'title|1': ['人脸画框', '马路描边', '动物识别', '猫狗区分'],
            'type|1': ['border_mark', 'comment', 'frame_mark', 'frame_mc', 'border_mc'],
            'description': '@csentence',
            'price|1-10': 1,
            'classLabel|1-3': ['@integer(1,20)'],
            'publishDate|1': ['2018-4-23', '2018-5-23', '2018-5-24', '2018-5-25', '2018-5-26', '2018-5-27', '2018-5-28', '2018-5-29'],
            'participantsNum|100-200': 1
          }]
        })
      return newMissions
    }
  },
  getRecommendMissions: config => {
    if (Math.random() > 0.9) {
      const newMissions2 = {
        result: false,
        message: '系统出了一些故障，请等一会儿在再试一下吧'
      }
      return newMissions2
    } else {
      const newMissions = Mock.mock(
        {
          'result': true,
          'message|6': [{
            'id|+1': 1,
            'title|1': ['人脸画框', '马路描边', '动物识别', '猫狗区分'],
            'type|1': ['border_mark', 'comment', 'frame_mark', 'frame_mc', 'border_mc'],
            'description': '@csentence',
            'price|1-10': 1,
            'classLabel|1-3': ['@integer(1,20)'],
            'publishDate|1': ['2018-4-23', '2018-5-23', '2018-5-24', '2018-5-25', '2018-5-26', '2018-5-27', '2018-5-28', '2018-5-29'],
            'recommend|0-1': 0.1
          }]
        })
      return newMissions
    }
  },
  signUpNewMission: req => {
    return Mock.mock(
      {
        'result|1': [true, false, true, true, true],
        'message': '请先完成您当前正在进行的任务。'
      }
    )
  },
  getDoingMissions: config => {
    if (Math.random() > 0.9) {
      return {
        result: false,
        message: '系统出了一些故障，请等一会儿在再试一下吧'
      }
    } else {
      return Mock.mock(
        {
          'result': true,
          'message|0-6': [{
            'id|+1': 1,
            'title|1': ['人脸画框', '马路描边', '动物识别', '猫狗区分'],
            'type|1': ['border_mark', 'comment', 'frame_mark', 'frame_mc', 'border_mc'],
            'description': '@csentence',
            'done|1-10': 1,
            'total|11-20': 1,
            'kind|1': ['check', 'tag'],
            'price|1-10': 1,
            'classLabel|1-3': ['@integer(1,20)'],
            'signDate|1': ['2018-4-23', '2018-5-23', '2018-5-24', '2018-5-25', '2018-5-26', '2018-5-27', '2018-5-28', '2018-5-29']
          }]
        })
    }
  },
  getDoneMissions: config => {
    if (Math.random() > 0.9) {
      return {
        result: false,
        message: '系统出了一些故障，请等一会儿在再试一下吧'
      }
    } else {
      return Mock.mock(
        {
          'result': true,
          'message|0-6': [{
            'id|+1': 1,
            'title|1': ['人脸画框', '马路描边', '动物识别', '猫狗区分'],
            'type|1': ['border_mark', 'comment', 'frame_mark', 'frame_mc', 'border_mc'],
            'kind|1': ['check', 'tag'],
            'description': '@csentence',
            'right|0-10': 1,
            'wrong|0-10': 1,
            'toBeJudge|0-10': 1,
            'classLabel|1-3': ['@integer(1,20)'],
            'submitDate|1': ['2018-4-23', '2018-5-23', '2018-5-24', '2018-5-25', '2018-5-26', '2018-5-27', '2018-5-28', '2018-5-29']
          }]
        })
    }
  },
  askForPic: req => {
    const { kind } = param2Obj(req.url)
    if (kind === 'tag') {
      if (Math.random() > 0.9) {
        return {
          result: false,
          message: '系统出了一些故障，请等一会儿在再试一下吧'
        }
      } else {
        return Mock.mock({
          'result': true,
          'message': {
            'authorId|+1': 10000,
            'picData': Mock.Random.dataImage('720x300'),
            'picId|+1': 10000,
            'mark': '',
            'tag': ''
          }
        })
      }
    } else {
      if (Math.random() > 0.9) {
        return {
          result: false,
          message: '系统出了一些故障，请等一会儿在再试一下吧'
        }
      } else {
        return Mock.mock({
          'result': true,
          'message': {
            'authorId|+1': 10000,
            'picData': Mock.Random.dataImage('720x300'),
            'picId|+1': 10000,
            'mark': Mock.Random.dataImage('336x280'),
            'tag': '@csentence'
          }
        })
      }
    }
  },
  saveTagAnswer: req => {
    return Mock.mock(
      {
        'result|1': [true, false, true, true, true],
        'message': '出了未知bug'
      }
    )
  },
  updateTagAnswer: req => {
    return Mock.mock(
      {
        'result|1': [true, false, true, true, true],
        'message': '出了未知bug'
      }
    )
  },
  saveCheckAnswer: req => {
    return Mock.mock(
      {
        'result|1': [true, false, true, true, true],
        'message': '出了未知bug'
      }
    )
  },
  updateCheckAnswer: req => {
    return Mock.mock(
      {
        'result|1': [true, false, true, true, true],
        'message': '出了未知bug'
      }
    )
  },
  getAllMissions: req => {
    if (Math.random() > 0.9) {
      return {
        result: false,
        message: '系统出了一些故障，请等一会儿在再试一下吧'
      }
    } else {
      var allMissions = Mock.mock(
        {
          'missions|8': [{
            'id|+1': 10000,
            'title|1': ['人脸画框', '马路描边', '动物识别', '猫狗区分'],
            'type|1': ['border_mark', 'comment', 'frame_mark', 'border_mc', 'frame_mc'],
            'description': '@csentence',
            'classLabel|1-3': ['@integer(1,20)'],
            'tagPrice|3-9': 1,
            'checkPrice|1-5': 1,
            'done|0-200': 1,
            'total|200-300': 1,
            'requester': '@CNAME',
            'totalPoints|300-1000': 1,
            'publishDate': '@date',
            'finishDate': '-',
            'status': 'doing'
          }]
        })
      var finishedMissions = Mock.mock({
        'missions|8': [{
          'id|+1': 15000,
          'title|1': ['人脸画框', '马路描边', '动物识别', '猫狗区分'],
          'type|1': ['border_mark', 'comment', 'frame_mark', 'border_mc', 'frame_mc'],
          'description': '@csentence',
          'classLabel|1': ['plants', 'animals', 'people', 'science', 'goods'],
          'tagPrice|3-9': 1,
          'checkPrice|1-5': 1,
          'done': 200,
          'total': 200,
          'requester': '@CNAME',
          'totalPoints|300-1000': 1,
          'publishDate': '@date',
          'finishDate': '@date',
          'status': 'done'
        }]
      })
      allMissions.missions = allMissions.missions.concat(finishedMissions.missions)
      const res = {
        result: true,
        message: {}
      }
      res.message = allMissions.missions
      return res
    }
  },
  getAllWorkers: req => {
    if (Math.random() > 0.9) {
      return {
        result: false,
        message: '系统出了一些故障，请等一会儿在再试一下吧'
      }
    } else {
      return Mock.mock(
        {
          result: true,
          'message|100': [{
            'id|+1': 10000,
            'nickname': '@CNAME',
            'email': '@EMAIL',
            'points|100-200': 1
          }]
        }
      )
    }
  },
  getAllRequesters: req => {
    if (Math.random() > 0.9) {
      return {
        result: false,
        message: '系统出了一些故障，请等一会儿在再试一下吧'
      }
    } else {
      return Mock.mock(
        {
          result: true,
          'message|50-60': [{
            'id|+1': 10000,
            'nickname': '@CNAME',
            'email': '@EMAIL',
            'releasedMissions|5-7': 1,
            'paidPoints|2000-3000': 1
          }]
        }
      )
    }
  },
  getParticipants: req => {
    if (Math.random() > 0.9) {
      return {
        result: false,
        message: '系统出了一些故障，请等一会儿在再试一下吧'
      }
    } else {
      return Mock.mock(
        {
          result: true,
          'message|20-30': [{
            'id|+1': 10000,
            'nickname': '@CNAME',
            'email': '@EMAIL',
            'points|100-200': 1
          }]
        }
      )
    }
  },
  getAllMissionsByRequester: req => {
    if (Math.random() > 0.9) {
      return {
        result: false,
        message: '系统出了一些故障，请等一会儿在再试一下吧'
      }
    } else {
      var allMissions = Mock.mock(
        {
          'missions|8': [{
            'id|+1': 1,
            'title|1': ['人脸画框', '马路描边', '动物识别', '猫狗区分'],
            'type|1': ['border_mark', 'comment', 'frame_mark', 'border_mc', 'frame_mc'],
            'description': '@csentence',
            'classLabel|1-3': ['@integer(1,20)'],
            'tagPrice|0.1': 1,
            'checkPrice|0.1': 1,
            'done|0-200': 1,
            'total|200-300': 1,
            'requesterName': '王二',
            'totalPoints|300-1000': 1
          }]
        })
      var finishedMissions = Mock.mock({
        'missions|3': [{
          'id|+1': 9,
          'title|1': ['人脸画框', '马路描边', '动物识别', '猫狗区分'],
          'type|1': ['border_mark', 'comment', 'frame_mark', 'border_mc', 'frame_mc'],
          'description': '@csentence',
          'classLabel|1-3': ['@integer(1,20)'],
          'tagPrice|0.1': 1,
          'checkPrice|0.1': 1,
          'done': 200,
          'total': 200,
          'requesterName': '王二',
          'totalPoints|300-1000': 1
        }]
      })
      allMissions.missions = allMissions.missions.concat(finishedMissions.missions)
      const res = {
        result: true,
        message: {}
      }
      res.message = allMissions.missions
      return res
    }
  }
}

