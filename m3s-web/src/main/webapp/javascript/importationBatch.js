/*
 * Esta clase no se utiliza!
 * 
 *  @deprecated (15/01/2009 - james)
 *
 */

function setupImportationTable(urlContext, username){	
  YAHOO.util.Event.addListener(window, "load", function() {
    YAHOO.example.XPath = function() {
      var myColumnDefs = [
        {key:"File Name"},
        {key:"Status"},
        {key:"Date"}
      ];

      var myDataSource = new YAHOO.util.DataSource(urlContext+"/ajax/importationData?param="+username);
      myDataSource.responseType = YAHOO.util.DataSource.TYPE_XML;
      myDataSource.useXPath = true;
      myDataSource.responseSchema = {
        metaFields: {rootatt:"/myroot/@rootatt"},
        resultNode: "item",
        fields: [{key:"File Name", locator:"userfilename"}, {key:"Status", locator:"status"}, {key:"Date", locator:"creationdate"} ]
      };

      var myDataTable = new YAHOO.widget.DataTable("xpath", myColumnDefs, myDataSource, {
        selectionMode:"single" 
      });

      myDataTable.subscribe("rowMouseoverEvent", myDataTable.onEventHighlightRow); 
      myDataTable.subscribe("rowMouseoutEvent", myDataTable.onEventUnhighlightRow); 
      myDataTable.subscribe("rowClickEvent", myDataTable.onEventSelectRow);
      //downloadFile(urlContext,myDataTable.getColumn(myDataTable.getLastSelectedRecord()))


      return {
        oDS: myDataSource,
        oDT: myDataTable
      };
    }();
  });
}

function downloadFile(urlContext, selectedColumn){
	//
	window.open(urlContext+"/getImportationFile?id="+selectedColumn.getKey());
}




