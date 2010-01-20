<%@ page language="java" pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" rel="stylesheet" href="${css}"/>
    <title><tiles:getAsString name="title"/></title>
  </head>

  <body>
    <div id="wrapper">
      <div id="nido">
        <div id="banner"> </div>
            
        <div id="contenido">
          <tiles:insert name="content"/>
            
          <div id="clear">
            <!--limpia los float en caso de ser utilizados arriba -->
          </div>
          
        </div> <!-- contenido -->
      </div> <!-- nido -->
    </div> <!-- wrapper -->

    <div id="footer">
      <div id="logos">
        <div id="contacto"><a href="mailto:<tiles:getAsString name="contactEmail"/>"><tiles:getAsString name="contactMessage"/></a></div>
      </div>
      <div id="footerIn"><tiles:getAsString name="footerMessage"/></div>
    </div>
   
  </body>
  
</html>





