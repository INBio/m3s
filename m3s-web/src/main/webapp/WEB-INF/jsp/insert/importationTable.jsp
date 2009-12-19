<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/tableScripts.jsp" %>

<p>Tabla.[${username}]</p>
 
  
<body class="yui-skin-sam">
  <div id="xpath"></div>
</body>

<script type="text/javascript">


YAHOO.util.Event.addListener(window, "load", function() {
    YAHOO.example.XPath = function() {
        var myColumnDefs = [
            {key:"File Name"},
            {key:"Status"},
            {key:"Date"}
        ];

        var myDataSource = new YAHOO.util.DataSource("http://localhost:8080/m3s-web/ajax/importationData?param=jgutierrez");
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
        myDataTable.subscribe("rowClickEvent", downloadFile); 


        return {
            oDS: myDataSource,
            oDT: myDataTable
        };
    }();
});

function downloadFile(){
	window.alert("downloadFile");
}
</script>

  
  