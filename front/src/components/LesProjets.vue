<template>
  <div class="projets">
    <h2>Liste des projets</h2>
    <ul>
      <li v-for="elt in projectList">{{elt}}</li>
    </ul>

    <nav aria-label="Page navigation example">
      <ul class="pagination">
        <li class="page-item" v-on:click="prev"><a class="page-link" href="#">Previous</a></li>
        <li class="page-item"><a class="page-link" href="#">1</a></li>
        <li class="page-item"><a class="page-link" href="#">2</a></li>
        <li class="page-item"><a class="page-link" href="#">3</a></li>
        <li class="page-item" v-on:click="next"><a class="page-link" href="#">Next</a></li>
      </ul>
    </nav>
  </div>
</template>

<script>

// des données de test
let data = [
    {
      // les donnees ici
    }
  ]

export default {
  name: 'LesProjets',
  data () {
    return {
      projectList: ['Projet Java', 'Projet Xml', 'Projet Systeme destribue'],
      currentPage: 1,
      pageSize: 20
    }
  },
  methods: {
    // generaliser le fetch dans une fontion
    getProjects:function(page, size){
      fetch("/api/lesProjets/" + page + "/" + size).then(function(response) {
        // utliser la response
        this.projectList = response.data;
      })
    },
    next:function(){
      this.currentPage ++;
      getProjects(currentPage, pageSize);

    },
    prev:function(){
      this.currentPage --;
      getProjects(currentPage, pageSize);

    },
    getTestData: function(){
      this.projectList = data
    }
  }
}


// recuperer les donneés a l'aide de l'api rest
// e.g: on va recuperer les les premiers 20 projets
fetch("/api/lesProjets/1/20").then(function(response) {
  // utiliser response qui est un objet JSON de la forme
  // {
  //    msg:'',
  //    data:[]
  // }
});
// ou getProjects(1, 20);

// ajouter la pagination
// en cliquant sur next, on fait appel a l'api avec le lien
// api/projets/2/20


</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
