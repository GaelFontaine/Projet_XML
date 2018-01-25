// JavaScript Document
var dataToInsert = new Array();
            var data = []
            fetch('http://localhost:9080/stat/nbequipe-par-centre').then(function(response) {
                        response.json().then(function(data) {
							console.log(data[0].occurrence);
                            for(var i = 0; i<data.length; i++){
                                // On récupère les occurence pour chaque centre (le nombre d'équipes pour le centre à l'indice [i]
                                dataToInsert[i] = data[i].occurrence;
                            }
							new Chartist.Bar('.ct-chart', {
								labels: ["Bordeaux", "Grenoble", "Lille", "Nancy", "Paris", "Rennes", "Sophia Antipolis", "Saclay"],
								series: [dataToInsert[0], dataToInsert[1], dataToInsert[2], dataToInsert[3], dataToInsert[4], dataToInsert[5], dataToInsert[6], dataToInsert[7]]
							}, {
								height: '300px',
								distributeSeries: true
							});
                        });

                    });
            