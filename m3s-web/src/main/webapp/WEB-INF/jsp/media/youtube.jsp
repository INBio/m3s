<!--
  Almost all of the code used in this jsp page was taken from this url:
  http://code.google.com/apis/ajax/playground/?exp=youtube#simple_embed
  That is free under this terms:

  copyright (c) 2009 google inc.

  You are free to copy and use this sample.
  License can be found here: http://code.google.com/apis/ajaxsearch/faq/#license
-->

<%@ include file="/common/taglibs.jsp"%>
<script src="http://www.google.com/jsapi" type="text/javascript"></script>
<script type="text/javascript">
  // The video to load.  
  var videoID = "<c:out value='${videoIdKey}'/>";
  google.load("swfobject", "2.1");
</script>  

<head>
      
  <script type="text/javascript">
    
  function _run() {
  /*
  * Simple player embed
  */
  // Lets Flash from another domain call JavaScript
  var params = { allowScriptAccess: "always" };
  // The element id of the Flash embed
  var atts = { id: "ytPlayer" };
  // All of the magic handled by SWFObject (http://code.google.com/p/swfobject/)
  swfobject.embedSWF("http://www.youtube.com/v/" + videoID + "&enablejsapi=1&playerapiid=player1",
                           "videoDiv", "480", "295", "8", null, null, params, atts);
  }

  google.setOnLoadCallback(_run);
      
  </script>
</head>

    
<div id="videoDiv">Loading...</div>
 
