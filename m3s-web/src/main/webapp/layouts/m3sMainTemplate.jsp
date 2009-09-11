<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><tiles:getAsString name="title"/></title>
  </head>
  
  <body>
    <!-- <table width="100%" border="0" cellpadding="0" cellspacing="0"> -->
    <tiles:insert name="header"/>
    
    <table width="100%" border="0">
      
      <tr>
        <td valign="top" align="left">
          <tiles:insert name="content"/>
        </td>
      </tr>
      
      <tr>
        <td>
          <tiles:insert name="footer"/>
        </td>
      </tr>
    
    </table>
  </body>
  
</html>