<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjr" uri="/struts-jquery-richtext-tags"%>
<%@ taglib prefix="sjt" uri="/struts-jquery-tree-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
    Index<br/>
    <s:text name="testname"><s:param name="0" value="111" /></s:text>
    <s:form id="form" theme="xhtml">
		<sj:datepicker id="date0" label="Select a Date" />
		<sj:datepicker value="tomorrow" id="date5" name="date5" displayFormat="DD, d MM yy" label="Tomorrow" />
		<sj:submit value="Submit Form" /><br/>
		<sj:dialog id="mydialog1" title="Local Dialog">
	     Mauris mauris ante, blandit et, ultrices a, suscipit eget, quam. Integer ut neque. Vivamus nisi metus, molestie vel, gravida in, condimentum sit amet, nunc. Nam a nibh. Donec suscipit eros. Nam mi. Proin viverra leo ut odio. Curabitur malesuada. Vestibulum a velit eu ante scelerisque vulputate.
		</sj:dialog><br/>
		<sjr:ckeditor id="richtextEditor" 
		    name="echo" 
		    rows="10" 
		    cols="80" 
		    width="800"
		    value="Cras dictum. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aenean lacinia mauris vel est. Suspendisse eu nisl. Nullam ut libero. Integer dignissim consequat lectus. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos."/>
		<sjr:ckeditor label="Your Text" href="%{remoteurl}" 
			id="richtextCustomeEditor" 
			name="echo" 
			rows="10" 
			cols="80" 
			loadingText="Loading content of textarea ..."
			width="600"
			toolbar="MyToolbar"
			skin="v2"
			editorLocal="fr"
			uploads="true"
			customConfig="%{contextPath}/js/ckeditor.config.js"/>
	</s:form>
	<sjt:tree id="treeStatic" jstreetheme="default">
		<sjt:treeItem title="Struts2">
			<sjt:treeItem title="General">
		        <sjt:treeItem title="Struts2" href="http://struts.apache.org/2.x/index.html"/>
		        <sjt:treeItem title="Struts2 @ Facebook" href="http://www.facebook.com/pages/Struts2-Users/103890046351798"/>
			</sjt:treeItem>
			<sjt:treeItem title="Plugins">
		        <sjt:treeItem title="Struts2 Plugins" href="https://cwiki.apache.org/S2PLUGINS/home.html"/>
		        <sjt:treeItem title="Struts2 jQuery Plugin" href="http://code.google.com/p/struts2-jquery/"/>
		        <sjt:treeItem title="Struts2 Full Hibernate Plugin" href="http://code.google.com/p/full-hibernate-plugin-for-struts2/"/>
			</sjt:treeItem>
			<sjt:treeItem title="Blogs">
		        <sjt:treeItem title="Struts2 jQuery News" href="http://www.jgeppert.com/category/java/struts2-jquery/"/>
			</sjt:treeItem>
			<sjt:treeItem title="AJAX Links">
				<s:url id="ajax1" value="/ajax1.action"/>
		        <sjt:treeItem title="Ajax 1" href="%{ajax1}" targets="resultDiv"/>
				<s:url id="ajax2" value="/ajax2.action"/>
		        <sjt:treeItem title="Ajax 2" href="%{ajax2}" targets="resultDiv" effect="highlight" effectDuration="2500"/>
				<s:url id="ajax3" value="/ajax3.action"/>
		        <sjt:treeItem title="Ajax 3" href="%{ajax3}" targets="resultDiv" onBeforeTopics="beforeLink" onCompleteTopics="completeLink"/>
				<s:url id="ajax4" value="/ajax4.action"/>
		        <sjt:treeItem title="Ajax 4" href="%{ajax4}" targets="resultDiv" effect="bounce" effectDuration="1000"/>
			</sjt:treeItem>
		</sjt:treeItem>
	</sjt:tree>
  </body>
</html>
