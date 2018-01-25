// JavaScript Document
//			var dataToInsert = new Array(8);
//          var data = []
            fetch('http://localhost:9080/stat/nbpers-par-centre').then(function(response) {
                response.json().then(function(dataRes) {
//					console.log(data[0].occurrence);
					var dataToInsert = new Array(8);
                    for(var i = 0; i<dataRes.length; i++){
                        // On récupère les occurence pour chaque centre (le nombre de personne pour le centre à l'indice [i]
                        dataToInsert[i] = dataRes[i].occurrence;
                    }
					var total = dataToInsert[0] + dataToInsert[1] + dataToInsert[2] + dataToInsert[3] + dataToInsert[4] + dataToInsert[5] + dataToInsert[6] + dataToInsert[7];
					var data = {
                // récupérer depuis bd
						labels: ['Bordeaux', 'Grenoble', 'Lille', 'Nancy', 'Paris', 'Rennes', 'Sophia Antipolis', 'Saclay'],
						series: [100*(dataToInsert[0]/total), 100*(dataToInsert[1]/total), 100*(dataToInsert[2]/total), 100*(dataToInsert[3]/total), 100*(dataToInsert[4]/total), 100*(dataToInsert[5]/total), 100*(dataToInsert[6]/total), 100*(dataToInsert[7]/total)]
					};
					var options = {
                		labelInterpolationFnc: function(value) {
                    		return value[0];
                		}
            		};
					 var responsiveOptions = [
                		['screen and (min-width: 640px)', {
							chartPadding: 30,
							labelOffset: 100,
							labelDirection: 'explode',
							labelInterpolationFnc: function(value) {
								return value;
							}
                		}],
						['screen and (min-width: 1024px)', {
							labelOffset: 80,
							chartPadding: 20
						}]
            		];
					new Chartist.Pie('.ct-chart2', data, options, responsiveOptions);
							console.log(options);
						});

            })