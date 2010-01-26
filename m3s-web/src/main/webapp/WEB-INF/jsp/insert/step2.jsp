<%@ include file="/common/taglibs.jsp"%>

<h2><spring:message code="insert.jpg.title"/></h2>

  <h3><spring:message code="preview.title"/></h3>
  
    <%-- Acá irán los thumnails temporales de la inserciones--%>
  <span id="temporalThumbnails"></span>
  <div class="clear"></div>
  <br/>
  

  <div id="insert-metadata-form">
    <h3><spring:message code="metadata.title"/></h3>
    <tiles:insert page="/WEB-INF/jsp/metadata.jsp"/>
  </div>


<script type="text/javascript">
  setThumbnails('${pageContext.request.contextPath}');  

  /*
  * taxonomy= es spam donde se pondrán las estampillas de las imagenes que se van a insertar
  * fileNameId = es de donde vienen los nombres de las imagenes en el sistema.
  * 
  * Se pasa la lista de taxones (separados por ;) del fileNameId a temporalThumbnails 
  * como elementos nuevos utiliando el método addImageThumbnail(imageName,pageContext)
  * 
  * Se debe invocar cuando se carga la pagina!
  */
  function setThumbnails(urlContext){
    var thumbSystemNames = document.getElementById('fileNameId');
    var thumbSystemNamesArray = thumbSystemNames.value.split(";");
      
    for (var i = 0 ; i < thumbSystemNamesArray.length ; i++){
      if(thumbSystemNamesArray[i] != "") {
        addImageThumbnail(thumbSystemNamesArray[i],urlContext);
      }
    }
  }


  /**
   * Add a thumbnail in the temporalThumbnails spam of the GUI
   * 
   * @param systemMediaId
   * @return
   */
  function addImageThumbnail(systemMediaId, urlContext) {
    var temporalThumbnailsDiv = document.getElementById('temporalThumbnails');

    var newdiv = document.createElement('div');
    newdiv.setAttribute("class","insert-preview-thumbs");

    var mediaKindDiv = document.createElement('div');
    mediaKindDiv.setAttribute('class','media-kind');
    mediaKindDiv.setAttribute('style','background-image:url(images/image.png)');

    var thumbDiv = document.createElement('div');
    thumbDiv.setAttribute('class','thumb-image');
    thumbDiv.setAttribute('style','background-image:url('+urlContext+'/getImage?temporal=thumb-'+systemMediaId+');');

    newdiv.appendChild(mediaKindDiv);
    newdiv.appendChild(thumbDiv);
    temporalThumbnailsDiv.appendChild(newdiv);
  }  
</script>