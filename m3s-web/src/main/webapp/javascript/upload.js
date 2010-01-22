/*
 * Utilizada en upload.jsp para mostrar el archivo de ayuda según el tipo de multimedio que se va
 * a insertar
 * 
 * Los divs de ayuda deben tener los nombres: jpgImageHelpDiv y importationHelpDiv
 * 
 * 2010.01.18
 */

/*
 * Limpia los divs de ayuda que  
 */
function cleanHelpDivs(){	
  document.getElementById('jpgImageHelpDiv').style.display='none';
  document.getElementById('importationHelpDiv').style.display='none';
  document.getElementById('jpgImageFormDiv').style.display='none';
  document.getElementById('importationFormDiv').style.display='none';
  
}

function viewJpgImageDivs(){
	cleanHelpDivs();
	document.getElementById('jpgImageHelpDiv').style.display='block';
	document.getElementById('jpgImageFormDiv').style.display='block';
}

function viewImportationDivs(){
	cleanHelpDivs();
	document.getElementById('importationHelpDiv').style.display='block';
	document.getElementById('importationFormDiv').style.display='block';
}


