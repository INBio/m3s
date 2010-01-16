<%@ include file="/common/taglibs.jsp" %>

<h2><spring:message code="insert.excel.title"/></h2>

<br>
  <p>Este procedimiento puede tardar varios minutos dependiendo del tamaño del archivo.</p>
  <p>En cuanto este terminado se podrá observar en la tabla.</p>
</br>


<tiles:insert page="/WEB-INF/jsp/insert/importationTable.jsp"/>

<br>
  <p>Dando clic en el nombre del archivo podrá descargarlo</p>
</br>