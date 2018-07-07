<template>
  <div class="profile-container">
    <component :is="currentRole"></component>
  </div>
</template>

<script>
  import { mapGetters } from 'vuex'
  import adminProfile from './admin'
  import workerProfile from './worker'
  import requesterProfile from './requester'
  // import editorProfile from './editor'

  export default {
    name: 'profile',
    // components: { adminProfile, editorProfile },
    components: { adminProfile, workerProfile, requesterProfile },
    data() {
      return {
        currentRole: 'adminProfile'
      }
    },
    computed: {
      ...mapGetters([
        'roles'
      ])
    },
    created() {
      if (this.roles) {
        this.currentRole = this.roles[0] + 'Profile'
      }
    }
  }
</script>
