// JavaScript Document
function getDataOfSubjects(){
	"use strict";
	setHeader();
	fetch("http://127.0.0.1:9080/projet/domaine").then(function(response){
		return response.json();
	}).then(function(data){
		var listDomain = document.getElementById('listDomain');
		for(var i=0;i<data.domaine.length;i++){
			var domain = document.createElement("li");
			domain.setAttribute("id",i);
			domain.innerHTML = data.domaine[i];
			listDomain.appendChild(domain);
		}
		console.log(data);
		console.log(data.domaine.length);
	}).catch(function(e){
		console.log("Oops, error"+e);
});
}


getDataOfSubjects();

function getDataOfTheme(){
	"use strict";
	setHeader();
	fetch("http://127.0.0.1:9080/projet/theme").then(function(response){
		return response.json();
	}).then(function(data){
		console.log(data);
		var domainSanteBio = document.getElementById(0);
		var divSanteBio = document.createElement("div");
		var listSanteBio = document.createElement("ul");
		listSanteBio.setAttribute("class","subMenu");
		for(var i=0;i<data.santeBio.length;i++){
			var libelleSanteBio = document.createElement("li");
			libelleSanteBio.innerHTML =data.santeBio[i];
			listSanteBio.appendChild(libelleSanteBio);
		}
		divSanteBio.appendChild(listSanteBio);
		domainSanteBio.appendChild(divSanteBio);
		
		
		var domainMathApp = document.getElementById(1);
		var divMathApp = document.createElement("div");
		var listMathApp = document.createElement("ul");
		listMathApp.setAttribute("class","subMenu");
		for(i=0;i<data.mathApp.length;i++){
			var libelleMathApp = document.createElement("li");
			libelleMathApp.innerHTML =data.mathApp[i];
			listMathApp.appendChild(libelleMathApp);
		}
		divMathApp.appendChild(listMathApp);
		domainMathApp.appendChild(divMathApp);	
		
		
		var domainReseSys = document.getElementById(2);
		var divReseSys = document.createElement("div");
		var listReseSys = document.createElement("ul");
		listReseSys.setAttribute("class","subMenu");
		for(i=0;i<data.reseSys.length;i++){
			var libelleReseSys = document.createElement("li");
			libelleReseSys.innerHTML =data.reseSys[i];
			listReseSys.appendChild(libelleReseSys);
		}
		divReseSys.appendChild(listReseSys);
		domainReseSys.appendChild(divReseSys);	
		
		
		var domainPic = document.getElementById(3);
		var divPic = document.createElement("div");
		var listPic = document.createElement("ul");
		listPic.setAttribute("class","subMenu");
		for(i=0;i<data.pic.length;i++){
			var libellePic = document.createElement("li");
			libellePic.innerHTML =data.pic[i];
			listPic.appendChild(libellePic);
		}
		divPic.appendChild(listPic);
		domainPic.appendChild(divPic);	
		
		
		var domainApla = document.getElementById(4);
		var divApla = document.createElement("div");
		var listApla = document.createElement("ul");
		listApla.setAttribute("class","subMenu");
		for(i=0;i<data.apla.length;i++){
			var libelleApla = document.createElement("li");
			libelleApla.innerHTML =data.apla[i];
			listApla.appendChild(libelleApla);
		}
		divApla.appendChild(listApla);
		domainApla.appendChild(divApla);	
		
	}).catch(function(e){
		console.log("Oops, error"+e);
});
}
getDataOfTheme();


function setHeader(){
	
	let myHeaders = new Headers({
    'Access-Control-Allow-Origin': '*',
    'Content-Type': 'text/plain'
	});
}


