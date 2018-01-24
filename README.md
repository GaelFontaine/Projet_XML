# Projet de XML
M1 MIAGE 2017-2018
* FONTAINE Gaël
* THIAW Mamadou
* CHEN Datao
* DORE Guillaume

Sujet : https://www-sop.inria.fr/members/Philippe.Poulard/projet-miage-2017.html

---

* Accès aux données

Base de donnée "Exist DB". 

Utilisation des requetes XQuery.


* Partie serveur 

Concernant la partie serveur, il gère les transactions entre le client et la base donnée. 

Le serveur peut interagir avec la base donnée native XML grâce à différentes requêtes XQuery. La réponse de la base se fait sous forme XML. Par la suite, le serveur encode ces données avec le format JSON et les envois au client.

* Partie client 

Une fois le serveur Java lancé, le client peut accéder aux différentes informations concernant les rapports d’activités, les projets et les centres de recherches. En ce qui concerne les pages existantes, nous avons développé :

--- Une carte listant tous les centres de recherches grâce à leurs coordonnées.

--- La liste de tous les projets en fonction de leurs domaines.

--- Des graphiques au format SVG représentant diverse information concernant les projets et les centres de recherches

Chaque requête du client est directement transmise au serveur qui lui répond sous format JSON. De son côté, le client décode cette réponse et affiche les données nécessaires à l’utilisateur.
