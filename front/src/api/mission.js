import request from '@/utils/request'

export function buildMission(classLabel, description, title, type, perPoints, pics) {
  const data = {
    classLabel,
    description,
    title,
    type,
    perPoints,
    pics
  }
  return request({
    url: '/buildMission',
    method: 'post',
    data
  })
}

export const signUpNewMission = function(missionId, kind) {
  return request({
    url: '/signUpNewMission',
    method: 'get',
    params: {
      missionId: missionId,
      kind: kind
    }
  })
}

export const getNewMissions = function() {
  return request({
    url: '/askForNewList',
    method: 'get'
  })
}
export const getHotMissions = function() {
  return request({
    url: '/askForHotList',
    method: 'get'
  })
}
export const getRecommendMissions = function() {
  return request({
    url: '/askForRecommendList',
    method: 'get'
  })
}

export const getDoingMissions = function() {
  return request({
    url: '/askForDoingList',
    method: 'get'
  })
}
export const getDoneMissions = function() {
  return request({
    url: '/askForDoneList',
    method: 'get'
  })
}

export const getAllMissions = function() {
  return request({
    url: '/getAllMissions',
    method: 'get'
  })
}

export const getRequesterMissions = function() {
  return request({
    url: '/getAllMissions',
    method: 'get'
  })
}

export const getParticipants = function(missionId) {
  return request({
    url: '/getParticipants',
    method: 'get',
    params: missionId
  })
}

// 图片标注模块
export const askForPic = function(missionId, kind) {
  const now = new Date().getTime()
  return request({
    url: '/askForPic',
    method: 'get',
    params: {
      missionId: missionId,
      kind: kind,
      time: now }
  })
}

export const saveTagAnswer = function(missionId, picId, mark, tag, time) {
  return request({
    url: '/saveTagAnswer',
    method: 'post',
    data: {
      missionId,
      picId,
      mark,
      tag,
      time
    }
  })
}
export const updateTagAnswer = function(missionId, picId, mark, tag, time) {
  return request({
    url: '/updateTagAnswer',
    method: 'post',
    data: {
      missionId,
      picId,
      mark,
      tag,
      time
    }
  })
}

export const saveCheckAnswer = function(missionId, picId, authorId, checkAnswer, time) {
  return request({
    url: '/saveCheckAnswer',
    method: 'post',
    data: {
      missionId,
      authorId,
      picId,
      checkAnswer,
      time
    }
  })
}
export const updateCheckAnswer = function(missionId, picId, authorId, checkAnswer, time) {
  return request({
    url: '/updateCheckAnswer',
    method: 'post',
    data: {
      missionId,
      authorId,
      picId,
      checkAnswer,
      time
    }
  })
}

export function getEstimatedTime(mission) {
  // const theMission = {
  //   type: 'border_mark',
  //   picNumber: 120,
  //   perPoints: 2,
  //   classTag: 'people'
  // }
  return request({
    url: '/mission/getEstimatedTime',
    method: 'get',
    mission
  })
}
