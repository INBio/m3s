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
}

function viewJpgImageHelp(){
	cleanHelpDivs();
	document.getElementById('jpgImageHelpDiv').style.display='block';
}

function viewImportationHelp(){
	cleanHelpDivs();
	document.getElementById('importationHelpDiv').style.display='block';
}


