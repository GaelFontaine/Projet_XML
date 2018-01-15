import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import LesProjets from '@/components/LesProjets'
import LesCentresDeRecherche from '@/components/LesCentresDeRecherche'
import LesStatistiques from '@/components/LesStatistiques'
import LaCarteInteractive from '@/components/LaCarteInteractive'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/projets',
      name: 'LesProjets',
      component: LesProjets
    },
    {
      path: '/centresDeRecherche',
      name: 'LesCentresDeRecherche',
      component: LesCentresDeRecherche
    },
    {
      path: '/statistiques',
      name: 'LesStatistiques',
      component: LesStatistiques
    },
    {
      path: '/carteInteractive',
      name: 'LaCarteInteractive',
      component: LaCarteInteractive
    }
  ]
})
