/**
 * Sets up the auto complete using the supplied query url.
 */
function setupAutoComplete(queryUrl, inputId, containerId){
	  	// Instantiate one XHR DataSource and define schema as an array:
    //     ["Record Delimiter",
    //     "Field Delimiter"]
    oACDS = new YAHOO.widget.DS_XHR(queryUrl, ["\n", "\t"]);
    oACDS.responseType = YAHOO.widget.DS_XHR.TYPE_FLAT;
    oACDS.maxCacheEntries = 60;
    oACDS.queryMatchSubset = true;

    // Instantiate AutoComplete
    var myInput = document.getElementById(inputId);
    var myContainer = document.getElementById(containerId);
    oAutoComp0 = new YAHOO.widget.AutoComplete(myInput,myContainer,oACDS);
    oAutoComp0.delimChar = "";
    oAutoComp0.queryDelay = 0.25;
    oAutoComp0.formatResult = function(oResultItem, sQuery) {
        var sKey = oResultItem[0];
        var nQuantity = oResultItem[1];
        var sKeyQuery = sKey.substr(0, sQuery.length);
        var sKeyRemainder = sKey.substr(sQuery.length);
        var aMarkup = ["<div id='ysearchresult'><div class='ysearchquery'>",
            nQuantity,
            "</div><span style='font-weight:bold'>",
            sKeyQuery,
            "</span>",
            sKeyRemainder,
            "</div>"];
        return (aMarkup.join(""));
    };
}