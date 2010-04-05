
/**
 * Change the Object input  - retrieving the type using the selected index
 * of the supplied HTMLSelectElement.
 */
function changeAssociatedToValue(urlContext) {
	
	//the new Assotiation Type
	var associatedToValueTypeElement = document.getElementById('associatedToValueTypeId');
	var associatedToValueTypeId = associatedToValueTypeElement.value;
	
	
	//get the actual value of the associatedToValue textField
	var associatedToValueTextField = document.getElementById('associatedToValueId');
	var associatedToActualValue = associatedToValueTextField.value;
	
	//window.alert("associatedToValueTypeId = " + associatedToValueTypeId+" con el nuevo valor: " + associatedToActualValue );
	
	//YAHOO.util.Connect.asyncRequest('GET', 'http://localhost:8080/m3s-web/ajax/taxonomyBySpecimenNumber?specimenNumber=2', callback);

	if(associatedToActualValue != ""){
  	  //specimen number
	  if(associatedToValueTypeId == 0){
		YAHOO.util.Connect.asyncRequest('GET', urlContext+'/ajax/taxonomyBySpecimenNumber?param='+associatedToActualValue, callback);
		YAHOO.util.Connect.asyncRequest('GET', urlContext+'/ajax/siteBySpecimenNumber?param='+associatedToActualValue, callbackSite);
	  //observation number
	  } else if(associatedToValueTypeId == 1){
		YAHOO.util.Connect.asyncRequest('GET', urlContext+'/ajax/taxonomyByObservationNumber?param='+associatedToActualValue, callback);
		YAHOO.util.Connect.asyncRequest('GET', urlContext+'/ajax/siteByObservationNumber?param='+associatedToActualValue, callbackSite);
	  //gathering code
	  } else if(associatedToValueTypeId == 2){
		YAHOO.util.Connect.asyncRequest('GET', urlContext+'/ajax/taxonomyByGatheringCode?param='+associatedToActualValue, callback);
		YAHOO.util.Connect.asyncRequest('GET', urlContext+'/ajax/siteByGatheringCode?param='+associatedToActualValue, callbackSite);
	  //no association
	  } else if(associatedToValueTypeId == 3){
		  associatedToValueTextField.value =""; 
	  }
	}
	
}

/** 
 * This function is fired when the user enters and taxonName and 
 * Kingdom and clicks the add taxonomy button.
 * 
 * @return
 */
function checkAndAddTaxonomy(urlContext) {

	//get the actual value of the associatedToValue textField
	var taxonomyValueTextField = document.getElementById('taxonomyId');
	var name = taxonomyValueTextField.value;
	
	//get the actual value of the associatedToValue textField
	var kingdomValueTextField = document.getElementById('kingdomId');
	var kingdom = kingdomValueTextField.value;
	
	if(name != "" && kingdom != ""){
      var id = 1234;
	  var value = name+ " ["+kingdom+"]; ";
	  //window.alert(value);
	  //addTaxonomy(id, value)
	  YAHOO.util.Connect.asyncRequest('GET', 
			  urlContext+'/ajax/taxonomyByNameAndKingdom?name='+name+'&kingdom='+kingdom, callback);
	}
}

/**
 * The expected input is and XML document with the taxons info.
 * 
 * @param o
 * @return
 */
var handleSuccess = function(o){
	
	var root = o.responseXML.documentElement; //root element -> response
	//var rootChildNodes = root.childNodes;
	var rootChildNoder = root.getElementByTagName("taxon");
	
	for(var i=0; i< rootChildNodes.length; i++){
	 var basicElement = root.childNodes[i];  //taxon
	 var id =  basicElement.getElementByTagName("id")[0].childNodes[0].nodeValue; //id
	 var name =  basicElement.getElementByTagName("name")[0].childNodes[0].nodeValue; //name
	 var kingdom =  basicElement.getElementByTagName("kingdom")[0].childNodes[0].nodeValue; //kingdom

	 value = name+ " ["+kingdom+"]";
	 addTaxonomy(id, value);
	}				
}

/**
 * The expected input is and XML document with the site info.
 * 
 * @param o
 * @return
 */
var handleSiteSuccess = function(o){
	
	var root = o.responseXML.documentElement; //root element -> response
	var basicElement = root.getElementByTagName("site")[0];  //site
	var description =  basicElement.getElementByTagName("description")[0].childNodes[0].nodeValue; //description
	//var id en caso de ponerse el id deberia agregarse una linea aca.

	if(description != ""){
	  var siteElement = document.getElementById('siteDescriptionId');
	  siteElement.value = description;
	}
}


var handleFailure = function(o){
	window.alert("Ha ocurrido un error.");
}

var callback =
{
  success:handleSuccess,
  failure: handleFailure,
  argument: { foo:"foo", bar:"bar" }
};

var callbackSite =
{
  success:handleSiteSuccess,
  failure: handleFailure,
};

/*
* taxonomy= es spam donde se pone la taxonomia elegida por el usuario, usando el add taxonomy
*   o a traves de las asociaciones con algo
* taxonomyListId = es de donde vienen los taxones para los efectos del sistema.
* 
* Se pasa la lista de taxones (separados por ;) del taxonomyListId a taxonomy 
* como elementos nuevos utiliando el método addTaxonomy(id,value)
* 
* Se debe invocar cuando se carga la pagina!
*/
function setTaxonomy(){
		
	var taxonomyListElement = document.getElementById('taxonomyListId');
	var taxonomyArray = taxonomyListElement.value.split(";");
	
	//window.alert("'"+taxonomyListElement.value+"'");
	
	for (var i = 0 ; i < taxonomyArray.length ; i++){
		if(taxonomyArray[i] != "") {
          //window.alert(taxonomyArray[i]);
          addTaxonomy(taxonomyArray[i],taxonomyArray[i]);
		}
    }
}


function preSubmit(){
	//window.alert("antes de enviar");
	setTaxonomyListVariable();
}

/**
 * taxonomy= es spam donde se pone la taxonomia elegida por el usuario, usando el add taxonomy
 *   o a traves de las asociaciones con algo
 * taxonomyListId = es donde deben quedar los taxones para los efectos del sistema.
 * 
 * @return
 */
function setTaxonomyListVariable(){
	var taxonomyElement = document.getElementById('taxonomy');
	
	var taxonomyListElement = document.getElementById('taxonomyListId');
	//window.alert(taxonomyElement.childNodes.length);
	taxonomyListElement.value= "";

	//set values for taxonomy
	for (var j =0; j <taxonomyElement.childNodes.length; j++){
		//window.alert(taxonomyElement.childNodes[j].textContent);
		taxonomyListElement.value += taxonomyElement.childNodes[j].textContent+";";
	}
	
	//window.alert(taxonomyListElement.value);
	
}

/**
 * Add this taxonomy in the taxonomy elements of the GUI
 * 
 * @param id
 * @param value
 * @return
 */
function addTaxonomy(id, value) {
  var ni = document.getElementById('taxonomy');
  var divIdName = "my"+id+"Div";
  var newdiv = document.createElement('div');
  newdiv.setAttribute("id",divIdName);
  newdiv.innerHTML = "<a href=\"javascript:\" onclick=\"removeTaxonomyElement(\'"+divIdName+"\')\">"+value+"</a>";
  ni.appendChild(newdiv);
}

/**
 * Remove an specific element of the GUI
 * 
 * @param divNum
 * @return
 */
function removeTaxonomyElement(divNum) {
  var d = document.getElementById('taxonomy');
  var olddiv = document.getElementById(divNum);
  d.removeChild(olddiv);
}
