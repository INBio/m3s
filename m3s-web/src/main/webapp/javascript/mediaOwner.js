
/**
 * Change the Object input  - retrieving the type using the selected index
 * of the supplied HTMLSelectElement.
 */
function changeMediaOwnerInput() {
	
	//the new media Owner TypeId
	var mediaOwnerTypeElement = document.getElementById('ownerTypeId');
	var mediaOwnerTypeId = mediaOwnerTypeElement.value;
	//window.alert("media Owner Type Id = " + mediaOwnerTypeId);
	
	//get the actual value of the MediaOwnerValue textField
	var mediaOwnerValueTextField = document.getElementById('ownerId');
	
	var mediaOwnerActualValue = mediaOwnerValueTextField.value;
	//window.alert("media Owner Actual value = " + mediaOwnerActualValue);
	
	
	//get value span	
	var valueSpan = document.getElementById('newOwnerValue');;
	removeElement(valueSpan, "INPUT");		
	removeElement(valueSpan, "DIV");		
	removeElement(valueSpan, "SCRIPT");

//var element = document.getElementById(id);
//element.parentNode.removeChild(element);

				
	
	//add the new input field
	var newInput = document.createElement('INPUT');
	newInput.name = "ownerValue";
	newInput.id = "ownerId";
	//newInput.className = "ownerInputClass";
	newInput.value = mediaOwnerActualValue;
	valueSpan.appendChild(newInput);
	newInput.tabindex="13";

	//autocomplete?
	var useAutoComplete = (mediaOwnerAutoCompleteUrls[mediaOwnerTypeId]!=null);
        //if this filter uses auto complete, setup required container divs
	if(useAutoComplete){
	  //alert("use auto complete");
	  var autoCompleteDiv = document.createElement('DIV');
	  autoCompleteDiv.id="ownerContainer";
	  autoCompleteDiv.className="yui-skin-m3s";
	  
	  //autoCompleteDiv.innerHTML ="";
	  //autoCompleteDiv.style.left="0px";
  	  valueSpan.appendChild(autoCompleteDiv);			
  	  setupAutoComplete(mediaOwnerAutoCompleteUrls[mediaOwnerTypeId],"ownerId", "ownerContainer");
	}
	
}


