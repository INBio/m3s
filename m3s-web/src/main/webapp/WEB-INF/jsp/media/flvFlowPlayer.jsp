<!--
  Using Flow Player free software [GPL3.0 licence]
  look at: http://flowplayer.org/index.html
-->

<%@ include file="/common/taglibs.jsp"%>
<!-- 
  include flowplayer JavaScript file that does  
  Flash embedding and provides the Flowplayer API.
-->
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/flowplayer/example/flowplayer-3.1.4.min.js"></script>


<div id="videoDiv">
<!-- this A tag is where your Flowplayer will be placed. it can be anywhere -->

<a href="<c:out value="${pageContext.request.contextPath}"/>/getVideo?id=<c:out value="${id}"/>" 
   style="display:block;width:520px;height:330px"
   id="player"> 
</a>

<!-- this will install flowplayer inside previous A- tag. -->
<script>
  flowplayer("player", "${pageContext.request.contextPath}/javascript/flowplayer/flowplayer-3.1.5.swf");
</script>
        
</div>