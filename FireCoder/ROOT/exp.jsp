<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title>exp.html</title>
	
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="-1">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
	<script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
	<script type="text/javascript" src="https://getfirebug.com/firebug-lite.js"></script>
	<script type="text/javascript">
	$.ajaxSetup({cache:false,type:"POST"});
	function executeExp(){
		var expVarsStr = "[";
		$.each($("div#expVarsDiv").children(),function(index, value){
			expVarsStr += "{";
			expVarsStr += "name";
			expVarsStr += ":";
			expVarsStr += "\"" + $(value).children("input#expVar").attr("value") + "\"";
			expVarsStr += ",";
			expVarsStr += "value";
			expVarsStr += ":";
			expVarsStr += $(value).children("input#expVarValue").attr("value");
			expVarsStr += "}";
			if (index < $("div#expVarsDiv").children().length - 1){
				expVarsStr += ",";
			}
		});
		expVarsStr += "]";
	
		var param = {
			exp:"[EXP]"+document.getElementById("expField").value+"[/EXP]",
			expVars:expVarsStr
		};
		$.getJSON("expression/executeExp", param, function(data){
			$("#expResult").html(data.expResult);
		});
	}
	function addVar(){
		$("div#expVarsDiv").append(
			"<li>公式代入变量：<input id=\"expVar\" type=\"text\" value=\"A\"/>--" + 
			"公式代入值：<input id=\"expVarValue\" type=\"text\" value=\"1\"/></li>");
	}
	</script>
  </head>
  
  <body>
	<s:textfield id="expField" key="exp.inputLabel" size="100"></s:textfield>
	<input type="button" value="代入变量" onclick="addVar()" /><br>
	<div id="expVarsDiv">
	</div>
	<input type="button" value="计算" onclick="executeExp()" /><br/>
	<s:text name="exp.resultLabel"/><div id="expResult"></div>
  </body>
</html>
