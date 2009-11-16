//loading wizard text - to be overridden in the JSP with a i18n key evaluated and setting a javascript var
var loadingWizardText="Loading wizard....."; 
var wizardSetupUrl = "/portaldev/occurrences/wizards";
var defaultWizardValue = "..........................................";

/**
 * Resets the indexes used by links and in name fields e.g. c[5]
 * so that they are sequential. This is required for the submitted url.
 */
function resetAllFilterIndexes(){
	var parentDiv = document.getElementById('filters');
	var filters = parentDiv.getElementsByTagName("div");
	
	var filterIndex = 0;
	
	for (var i=0; i<filters.length; i++){

		/**need to search/replace on the criteria.criteria[index]**/
		var elements = filters[i].getElementsByTagName("*");

		for (var j=0; j<elements.length; j++){
			
			if (elements[j].tagName=="SELECT" || elements[j].tagName=="INPUT") {
				//alert("Old Element Name ="+elements[j].name);
				var fsb = elements[j].name.indexOf("[");
				var lsb = elements[j].name.indexOf("]");
				elements[j].name = elements[j].name.substring(0, fsb+1) + i + elements[j].name.substring(lsb);	
				//alert("New Element Name ="+elements[j].name);
			}

			//fix add and remove links
			if (elements[j].tagName=="A" && (elements[j].name=="removeFilter" || elements[j].name=="addFilter" || elements[j].name=="startWizard")) {
				/**this needs to be revisited**/
				var link = elements[j];
				//set the index argument
				var fsc = link.href.indexOf("(");
				var lsc = link.href.indexOf(")");											
				var newLinkName = link.href.substring(0, fsc+1) + i + link.href.substring(lsc);	
				link.href = newLinkName;
				
				//if more than one filter, expose the remove link
				if (i==0 && elements[j].name=="removeFilter"){
					if (filters.length >1){
						elements[j].style.visibility = "visible";
					} else {
						elements[j].style.visibility = "hidden";
					}
				}
			}
		}
	}
}

/**
 * Removes a filter by its index
 */
function removeFilter(index) { 
	var parentDiv = document.getElementById('filters');
	var filters = parentDiv.getElementsByTagName("div");
	parentDiv.removeChild(filters[index]);
	resetAllFilterIndexes();
}

/**
 * Changes the values in a dropdown based on the index in another.
 * ddOptions is a 2D array of the dropdown options. The correct array of options
 * is selected using the index of theSelectElement
 */
function changeDropdownValues(theSelectElement, dropdownName, ddOptions) {
	// find the correct predicates array
	//the selectindex in the type dropdown corresponds to the 
	// correct predicate array
	var filter = theSelectElement.parentNode.parentNode;
	var filterId = theSelectElement.value;
	changeFilterDropdownValues(filter, filterId, dropdownName, ddOptions);
}

/**
 * Change the Object input  - retrieving the type using the selected index
 * of the supplied HTMLSelectElement.
 */
function changeObjectInput(theSelectElement) {
	changeObjectInput(theSelectElement, null);
}

/**
 * Retrieve the filter span. Valid types are "type", "predicate" and "value".
 */
function getFilterSpan(filterIndex, type){
	
	var filtersDiv = document.getElementById("filters");
	var filters = filtersDiv.getElementsByTagName("DIV");
		//get the links	
	var listOfSpans = filters[filterIndex].getElementsByTagName("SPAN");	
	
	for (var i=0; i<listOfSpans.length; i++){
		if(listOfSpans[i].className==type){
			return listOfSpans[i];
		}
	}	
}

/**
 * Changes the filter object value based on the filterType.
 * Currently supports three types of filter:
 * 1) String (with autocomplete if required)
 */	
function changeObjectInput(theSelectElement, value) {

	//reset the wizard div	
	//var wizardDiv = document.getElementById("filterWizard");
	//wizardDiv.innerHTML="&nbsp;";

	//reset the help div	
	//var selectedFilterHelp = document.getElementById("selectedFilterHelp");
	//selectedFilterHelp.innerHTML="&nbsp;";
	
	//the id of the filter - e.g. id=0, scientific name filter
	var filterId = theSelectElement.value;
	//the filter HTML element
	var filter = theSelectElement.parentNode.parentNode;
	// filter consists of 3 spans - type, predicate and value
	var listOfSpans = filter.getElementsByTagName("SPAN");
	
	//get value span	
	var valueSpan = null;
	for (var i=0; i<listOfSpans.length; i++){
		if(listOfSpans[i].className=="value"){
			valueSpan=listOfSpans[i];
			break;
		}
	}
	
	//establish the type of filter
	var filterType = 0;
	var currentFilterType = 0;
	var currentValue = "";

	if(valueSpan.getElementsByTagName("INPUT").length==1){
		currentFilterType=0;
		currentValue = valueSpan.getElementsByTagName("INPUT")[0].value;
	}


	if(currentFilterType==0){
		//string type	
		removeElement(valueSpan, "INPUT");		
		removeElement(valueSpan, "DIV");		
		removeElement(valueSpan, "SCRIPT");				
	}

	if(filterType==0){
		//string type	- add a new input field			
		var newInput = document.createElement('INPUT');
		newInput.name = "value";
		newInput.id = "valueId";
		newInput.className = "valueId";
		newInput.value = currentValue;
		
		if(value!=null)
			newInput.value = value;
		valueSpan.appendChild(newInput);
		newInput.tabindex=4;
		if (document.all) 
			newInput.attachEvent("onkeypress", addFilter);
		else
			newInput.addEventListener('keypress', addFilter, false);

		//add the filter help
		//addFilterHelp(filterId);

		var useAutoComplete = (autoCompleteUrls[filterId]!=null);
		//if this filter uses auto complete, setup required container divs
		if(useAutoComplete){
			//alert("use auto complete");
			var autoCompleteDiv = document.createElement('DIV');
			autoCompleteDiv.id="statescontainer";
			autoCompleteDiv.className="statescontainer";			
			autoCompleteDiv.innerHTML ="";
			autoCompleteDiv.style.left="0px";
			valueSpan.appendChild(autoCompleteDiv);			
			setupAutoComplete(autoCompleteUrls[filterId],"valueId", "statescontainer");
		}
	}
	//resetAllFilterIndexes();
	
}

/**
 * Add filter help to the view
 */
function addFilterHelp (filterId){
	var helpView = helpViews[filterId];
	if(helpView!=null){
		var filterHelpCallback = {
			success:function(o){
				//get the filterWizard div
				var filterHelpDiv = document.getElementById("selectedFilterHelp");
				//alert(o.responseText);
				filterHelpDiv.innerHTML = o.responseText;
				var filterWizard= document.getElementById("filterWizard");				
				filterWizard.innerHTML = "";
			},	
			failure: function(o){}
		}	
		YAHOO.util.Connect.asyncRequest('GET',
			helpView, 
			filterHelpCallback, 
			null); 						
	}
}

/**
 * Retrieve the currently selected filter.
 */
function getSelectedFilterSelect(filterIndex){
	var filtersDiv = document.getElementById("filters");
	var filters = filtersDiv.getElementsByTagName("DIV");
	var span = filters[filterIndex].getElementsByTagName("SPAN")[0];
	var selects = span.getElementsByTagName("SELECT");
	return selects[0];	
}

/**
 * Remove elements with the supplied tag name
 */
function removeElement(parentElement, elementTag){
	//alert("remove element:"+elementTag);
	var elements = parentElement.getElementsByTagName(elementTag);
	for (var j =0; j <elements.length; j++){
		parentElement.removeChild(elements[j]);			
	}	
}

/**
 * Changes the values in a dropdown based on the index in another. 
 * ddOptions is a 2D array of the dropdown options. The correct array of options
 * is selected using the index of theSelectElement
 */
function changeFilterDropdownValues(filter, subjectIndex, dropdownName, ddOptions) {
	
	// find the correct dropdown, remove children (the existing options)
	var dropdowns = filter.getElementsByTagName("SELECT");

	//TODO find the label of the currently selected element
	//if label exists in new array, set it to this value	
	for (var i=0; i<dropdowns.length; i++){
		var nameStr = dropdowns[i].name.toString();
		var dereferencer = nameStr.lastIndexOf(".");
		var ddName = nameStr.substring(dereferencer +1 );
		if ( ddName == dropdownName){
			var thedropdown = dropdowns[i]; 
			var noOfOptions= thedropdown.options.length;
			//clear the array
			for (var j =0; j <noOfOptions; j++){
				thedropdown.remove(0);			
			}
			var ddArr = ddOptions[subjectIndex];
			//add the new ones
			for (var k =0; k < ddArr.length; k++){
				thedropdown.options[k] = new Option(ddArr[k], k);
			}		
		}	
	}
}

/**
 * Removes the selected filter. The filterIndex is the position of the
 * filter in the list of constructed filters.
 */
function removeConstructedFilter(filterIndex){
	//alert("removing index:"+filterIndex)	;
	var criteriaStr ="";
	var hiddenCriteriaDiv = document.getElementById("hiddenCriteria");
	if(hiddenCriteriaDiv!=null){
		var criteriaInputs = hiddenCriteriaDiv.getElementsByTagName("INPUT");
	
		for (var i=0; i<criteriaInputs.length; i++){
			if(i!=filterIndex){
				if(criteriaStr.length >0)
					criteriaStr=criteriaStr+"&";
				criteriaStr=criteriaStr+criteriaInputs[i].value;
			}
		}
	}
	refreshFilters("refreshFilters", criteriaStr);
}

/**
 * Initiate the Ajax call that adds the selected filter.
 */
function addConstructedFilter(){
	
	var subject = document.getElementById("newFilterSubject").value;
	var predicate = document.getElementById("newFilterPredicate").value;
	var value = null;
	
	//look for input then a select
	var newFilterValueSpan = document.getElementById("newFilterValue");
	var inputElements = newFilterValueSpan.getElementsByTagName("INPUT");
	if(inputElements.length==1){
		value = inputElements[0].value;
	} else {
		//look for a select
		var selectElements = newFilterValueSpan.getElementsByTagName("SELECT");
		if(selectElements.length==1){
			value = selectElements[0].value;
		} 
	}	
	
	if(value==null || value==""){	
		return;
	}
	
	var criteriaStr ="";
	var hiddenCriteriaDiv = document.getElementById("hiddenCriteria");
	if(hiddenCriteriaDiv!=null){
		var criteriaInputs = hiddenCriteriaDiv.getElementsByTagName("INPUT");
		for (var i=0; i<criteriaInputs.length; i++){
			if(criteriaStr.length >0)
				criteriaStr=criteriaStr+"&";
			criteriaStr=criteriaStr+criteriaInputs[i].value;
		}
	}
	var extraCriteria = "newSubject="+subject+"&newPredicate="+predicate+"&newValue="+value;
	criteriaStr=criteriaStr+"&"+extraCriteria;
	refreshFilters("addOrUpdateFilters", criteriaStr);		
}

/**
 * Refresh the current selected filters.
 */
function refreshFilters(method, criteriaString){
	//alert(criteriaString);
	var changeFilterCallback = {
		success:function(o){
			//get the filterWizard div
			var filtersDiv = document.getElementById("constructedFilters");
			//alert(o.responseText);
			filtersDiv.innerHTML = o.responseText;
		},	
		failure: function(o){}
	}	
	var currentPath = document.location.pathname;
	var pathRoot = currentPath.substring(0, currentPath.lastIndexOf('/'));
	var urlStr = pathRoot+"/"+method+"?" +criteriaString;
	YAHOO.util.Connect.asyncRequest('GET',
		urlStr, 
		changeFilterCallback, 
		null); 	
}

/**
 * Sets the content of the filter wizard.
 */
function updateFilterWizard(o){
	var wizardDiv = document.getElementById("filterWizard");
	wizardDiv.innerHTML = o.responseText;
	wizardDiv.style.visibility="visible";	
}

/**
 * Refresh the filter wizard.
 */
function refreshWizard (filterId, filterIndex, currValue){
	var wizardCallback = {
		success:function(o){ updateFilterWizard(o); },	
		failure:function(o){}
	}	
	//use the hidden element which contains request data
	//make the ajax request for the wizard
	var urlStr = getFilterWizardUrl(filterId, filterIndex, currValue);
	YAHOO.util.Connect.asyncRequest('GET',
		urlStr, 
		wizardCallback, 
		null); 
}

/**
 * Constructs the url for the ajax request.
 */
function getFilterWizardUrl(filterId, filterIndex, currValue){
	var currentPath = document.location.pathname;
//	var pathRoot = currentPath.substring(0, currentPath.indexOf('occurrence'));
//	var urlStr = pathRoot+"occurrence/wizards?filterId="+filterId+"&filterIndex="+filterIndex;
	var urlStr = wizardSetupUrl+"?filterId="+filterId+"&filterIndex="+filterIndex;
	var filterValueSpan = getFilterSpan(filterIndex, "value");
	var hiddenInput = filterValueSpan.getElementsByTagName("INPUT")[0];
	var value = hiddenInput.value;
	if(currValue!=null)
		urlStr = urlStr+"&currValue="+currValue;
	return urlStr;
}

/**
 * Setup the filter wizard for the selected filterIndex. 
 * The filterIndex points to values within a filter array.
 */
function setupWizard (filterIndex){
	document.getElementById("filterWizard").innerHTML = loadingWizardText;
	//clear the filter help
	document.getElementById("selectedFilterHelp").innerHTML ="";
	var theFilterSelect = getSelectedFilterSelect(filterIndex);
	var filterId = theFilterSelect.value;
	refreshWizard(filterId, filterIndex, currValue);
}

/**
 * Close the existing wizard, removing the content from the filter wizard div.
 */
function closeWizard (filterIndex){
	//get the filterWizard div and reset content
	var wizardDiv = document.getElementById("filterWizard");
	wizardDiv.style.visibility="hidden";
	wizardDiv.innerHTML = "";
	var theFilterSelect = getSelectedFilterSelect(filterIndex);
	theFilterSelect.disabled = false;
}

/**
 * Reset the current wizard values.
 */
function resetWizardValues(filterIndex){
	setWizardValues(0, "", defaultWizardValue);
}

/**
 * Sets the values in the anchor and hidden div.
 */
function setWizardValues(filterIndex, theValue, theDisplayValue){
	//get the hidden input
	var filtersDiv = document.getElementById("filters");
	if(filtersDiv==null)
		return;
	var filters = filtersDiv.getElementsByTagName("DIV");
	//first span is subject, predicate and value
	var span = filters[filterIndex].getElementsByTagName("SPAN")[2];
	//get the link�
	var filterLink = span.getElementsByTagName("A")[0];
	filterLink.innerHTML=theDisplayValue;
	var hiddenInput = span.getElementsByTagName("INPUT")[0];
	hiddenInput.value=theValue;
}

/**
 * Event handler looking for return key presses.
 */
function addFilter(ev){
	if(ev.keyCode==13){
		addConstructedFilter();
	}
}

function selectAll(containerId){
	var checkboxes = document.getElementById(containerId).getElementsByTagName('INPUT');
	for (var i=0; i<checkboxes.length; i++)
		checkboxes[i].checked=true;
}

function deselectAll(containerId){
	var checkboxes = document.getElementById(containerId).getElementsByTagName('INPUT');
	for (var i=0; i<checkboxes.length; i++)
		checkboxes[i].checked=false;
}
