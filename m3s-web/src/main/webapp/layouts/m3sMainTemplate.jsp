<%@ page language="java" pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="<c:out value="${pageContext.request.contextPath}"/>/<spring:theme code='m3s.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<c:out value="${pageContext.request.contextPath}"/>/<spring:theme code='galleries.css'/>"/>    
    <title><tiles:getAsString name="title"/></title>
  </head>
  
  <body>
    <div id="wrapper">
      <div id="inner-wrap">
        <div id="bor-der">
          <div id="bor-izq">
            
            <tiles:insert name="header"/>
            
            <tiles:insert name="menu"/>
            
            <tiles:insert name="userInfo"/>
            
            <div id="content">
              <tiles:insert name="content"/>
            </div>
            
            <div id="clear">
              <!--limpia los float en caso de ser utilizados arriba -->
            </div>
            
            <tiles:insert name="footer"/>
            
          </div> <!-- bor-izq -->
        </div> <!-- bor-der -->
      </div> <!-- inner-wrap -->
    </div> <!-- wrapper -->
  </body>
  
</html>