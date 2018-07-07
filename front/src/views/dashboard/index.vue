<template>
  <div class="dashboard-container">
    <component :is="currentRole"></component>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import adminDashboard from './admin'
import workerDashboard from './worker'
import requesterDashboard from './requester'
// import editorDashboard from './editor'

export default {
  name: 'dashboard',
  // components: { adminDashboard, editorDashboard },
  components: { adminDashboard, workerDashboard, requesterDashboard },
  data() {
    return {
      currentRole: 'adminDashboard'
    }
  },
  computed: {
    ...mapGetters([
      'roles'
    ])
  },
  created() {
    if (this.roles) {
      this.currentRole = this.roles[0] + 'Dashboard'
    }
  }
}
</script>
