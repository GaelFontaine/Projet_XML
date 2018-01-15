<template>
  <div id="app">
    <div class="row">
      <div class="col-md-4">
        <ul class="list-group">
            <li v-for="elt in menu" class="list-group-item"><a :href="'/#/' + elt.link">{{elt.item}}</a></li>
        </ul>
      </div>
      <div class="col-md-8">
            <router-view/>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'app',
  data () {
    return {
      menu: [
        {
          item: 'Les projets',
          link: 'projets'
        },
        {
          item: 'Les centres de recherche',
          link: 'centresDeRecherche'
        },
        {
          item: 'Les statistiques',
          link: 'statistiques'
        },
        {
          item: 'La carte interactive',
          link: 'carteInteractive'
        }
      ]
    }
  }
}
</script>

