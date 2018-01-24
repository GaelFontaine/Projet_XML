// JavaScript Document
function getDataOfSubjects(){
	"use strict";
	setHeader();
	fetch("http://localhost:9080/ep/equipe-par-centre-domaine-et-theme").then(function(response){
		return response.json();
	}).then(function(data){
		console.log(data.equipe);
		var ulSophia = document.getElementById("ulSophia");
		var ulBordeaux = document.getElementById("ulBordeaux");
		var ulGrenoble = document.getElementById("ulGrenoble");
		var ulLille = document.getElementById("ulLille");
		var ulNancy = document.getElementById("ulNancy");
		var ulParis = document.getElementById("ulParis");
		var ulRennes = document.getElementById("ulRennes");
		var ulSaclay = document.getElementById("ulSaclay");

		
		for(var i=0;i<data.equipe.length;i++){
			if(data.equipe[i].centre==="BSO"){
				var liCentreBSO = document.createElement("li");
				liCentreBSO.innerHTML = "<div> Equipe: "+data.equipe[i].id+"</div><div>Responsable: "+data.equipe[i].nomRes+"</div>";
				ulBordeaux.appendChild(liCentreBSO);
			}
			else if(data.equipe[i].centre==="GRA"){
				var liCentreGRA = document.createElement("li");
				liCentreGRA.innerHTML = "<div> Equipe: "+data.equipe[i].id+"</div><div>Responsable: "+data.equipe[i].nomRes+"</div>";
				ulGrenoble.appendChild(liCentreGRA);
			}
			else if(data.equipe[i].centre==="LNE"){
				var liCentreLNE = document.createElement("li");
				liCentreLNE.innerHTML = "<div> Equipe: "+data.equipe[i].id+"</div><div>Responsable: "+data.equipe[i].nomRes+"</div>";
				ulLille.appendChild(liCentreLNE);
			}
			else if(data.equipe[i].centre==="NGE"){
				var liCentreNGE = document.createElement("li");
				liCentreNGE.innerHTML = "<div> Equipe: "+data.equipe[i].id+"</div><div>Responsable: "+data.equipe[i].nomRes+"</div>";
				ulNancy.appendChild(liCentreNGE);
			}
			else if(data.equipe[i].centre==="PRO"){
				var liCentrePRO = document.createElement("li");
				liCentrePRO.innerHTML = "<div> Equipe: "+data.equipe[i].id+"</div><div>Responsable: "+data.equipe[i].nomRes+"</div>";
				ulParis.appendChild(liCentrePRO);
			}
			else if(data.equipe[i].centre==="RBA"){
				var liCentreRBA = document.createElement("li");
				liCentreRBA.innerHTML = "<div> Equipe: "+data.equipe[i].id+"</div><div>Responsable: "+data.equipe[i].nomRes+"</div>";
				ulRennes.appendChild(liCentreRBA);
			}
			else if(data.equipe[i].centre==="SAM"){
				var liCentreSAM = document.createElement("li");
				liCentreSAM.innerHTML = "<div> Equipe: "+data.equipe[i].id+"</div><div>Responsable: "+data.equipe[i].nomRes+"</div>";
				ulSophia.appendChild(liCentreSAM);
			}
			else if(data.equipe[i].centre==="SIF"){
				var liCentreSIF = document.createElement("li");
				liCentreSIF.innerHTML = "<div> Equipe: "+data.equipe[i].id+"</div><div>Responsable: "+data.equipe[i].nomRes+"</div>";
				ulSaclay.appendChild(liCentreSIF);
			}
			
			
			
		}
	}).catch(function(e){
		console.log("Oops, error"+e);
		});
}
	
	
function setHeader(){
	let myHeaders = new Headers({
		'Access-Control-Allow-Origin': '*',
		'Content-Type': 'text/plain'
	});
}
getDataOfSubjects();

