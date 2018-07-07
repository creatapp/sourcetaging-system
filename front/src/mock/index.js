import Mock from 'mockjs'
import userAPI from './user'
import missionAPI from './mission'

// Mock.setup({
//   timeout: '350-600'
// })

// 登录注册相关
Mock.mock(/\/login/, 'post', userAPI.login)
Mock.mock(/\/register/, 'post', userAPI.register)
Mock.mock(/\/findPassword/, 'get', userAPI.findPassword)

Mock.mock(/\/getWorkerData/, 'get', userAPI.getWorkerData)
Mock.mock(/\/getRequesterData/, 'get', userAPI.getRequesterData)

Mock.mock(/\/user\/logout/, 'post', userAPI.logout)
Mock.mock(/\/getUserInfo/, 'get', userAPI.getUserInfo)

Mock.mock(/\/changeNickname/, 'get', userAPI.changeNickname)
Mock.mock(/\/changeEmail/, 'get', userAPI.changeEmail)
Mock.mock(/\/changePassword/, 'post', userAPI.changePassword)

Mock.mock(/\/buildMission/, 'post', missionAPI.buildMission)
Mock.mock(/\/askForNewList/, 'get', missionAPI.getNewMissions)
Mock.mock(/\/askForHotList/, 'get', missionAPI.getHotMissions)
Mock.mock(/\/askForRecommendList/, 'get', missionAPI.getRecommendMissions)
Mock.mock(/\/signUpNewMission/, 'get', missionAPI.signUpNewMission)
Mock.mock(/\/askForDoingList/, 'get', missionAPI.getDoingMissions)
Mock.mock(/\/askForDoneList/, 'get', missionAPI.getDoneMissions)

Mock.mock(/\/askForPic/, 'get', missionAPI.askForPic)
Mock.mock(/\/saveTagAnswer/, 'post', missionAPI.saveTagAnswer)
Mock.mock(/\/updateTagAnswer/, 'post', missionAPI.updateTagAnswer)
Mock.mock(/\/saveCheckAnswer/, 'post', missionAPI.saveCheckAnswer)
Mock.mock(/\/updateCheckAnswer/, 'post', missionAPI.updateCheckAnswer)

Mock.mock(/\/getAllWorkers/, 'get', missionAPI.getAllWorkers)
Mock.mock(/\/getAllRequesters/, 'get', missionAPI.getAllRequesters)
Mock.mock(/\/getAllMissions/, 'get', missionAPI.getAllMissions)
Mock.mock(/\/getRequesterMissions/, 'get', missionAPI.getAllMissionsByRequester)
Mock.mock(/\/getParticipants/, 'get', missionAPI.getParticipants)
export default Mock
