const mission = {
  state: {
    currentMission: {}
    // id: '',
    // title: '',
    // type: '',
    // description: '',
    // done: 0,
    // total: 0,
    // pics: []
  },

  mutations: {
    ENTER_MISSION: (state, mission) => {
      state.currentMission = mission
    },
    EXIT_MISSION: (state) => {
      state.currentMission = {}
    }
  }
}
export default mission
