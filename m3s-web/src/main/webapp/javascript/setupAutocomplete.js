/**
 * Sets up the yahoo auto complete using the supplied query url.
 * Idea based on Gbif Customizable Portal
 * 
 * param: queryURl where to query for data, i.e "${pageContext.request.contextPath}/ajax/test" 
 * param: inputId ID of the HTML element that holds the input field
 * param: containerId ID of the HTML elements that will hold the popup suggestions box
 * 
 */

function setupAutoComplete(queryUrl, inputId, containerId){
YAHOO.example.BasicRemote = function() {
    // Use an XHRDataSource    
    var oDS = new YAHOO.util.XHRDataSource(queryUrl);
    // Set the responseType
    oDS.responseType = YAHOO.util.XHRDataSource.TYPE_TEXT;
    // Define the schema of the delimited results
    oDS.responseSchema = { recordDelim: "\n", fieldDelim: "\t"
    };
    // Enable caching
    oDS.maxCacheEntries = 5;

    // Instantiate the AutoComplete
    var oAC = new YAHOO.widget.AutoComplete(inputId, containerId, oDS);
    
    return {
        oDS: oDS,
        oAC: oAC
    };
}();
}


/**
 * Sets up the yahoo auto complete using the supplied query url.
 * Idea based on Gbif Customizable Portal
 * 
 * param: queryURl where to query for data, i.e "${pageContext.request.contextPath}/ajax/test" 
 * param: inputId ID of the HTML element that holds the input field
 * param: containerId ID of the HTML elements that will hold the popup suggestions box
 * 
 */

function setupMultiValueAutoComplete(queryUrl, inputId, containerId){
YAHOO.example.BasicRemote = function() {
    // Use an XHRDataSource    
    var oDS = new YAHOO.util.XHRDataSource(queryUrl);
    // Set the responseType
    oDS.responseType = YAHOO.util.XHRDataSource.TYPE_TEXT;
    // Define the schema of the delimited results
    oDS.responseSchema = { recordDelim: "\n", fieldDelim: "\t"
    };
    // Enable caching
    oDS.maxCacheEntries = 5;

    // Instantiate the AutoComplete
    var oAC = new YAHOO.widget.AutoComplete(inputId, containerId, oDS);
    oAC.delimChar = [";"]; // Enable semi-colon delimiters
    
    return {
        oDS: oDS,
        oAC: oAC
    };
}();
}

function helloWorld(theSelectElement, inputId, containerId){
	
	var filterId = theSelectElement.value;
	var useAutoComplete = (autoCompleteUrls[filterId]!=null);
	
	//if this filter uses auto complete, setup required container divs
	if(useAutoComplete){
		alert("debería buscar acá: "+ autoCompleteUrls[filterId]);
		setupAutoComplete(autoCompleteUrls[filterId],inputId, containerId);
	} else {
		alert("no auto complete");
	}
	
}
