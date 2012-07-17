HTML数据抽取工具包，需要编写XSL样式表

依赖库：dom4j-1.6.1.jar, jtidy-r938.jar

示例：
	HTMLDataPump pump = new HTMLDataPumpImpl();
	pump.setHTMLSource(htmlSourceStr);// 设置HTML源
	pump.setXSLFile("E:/temp/jobToXmlTest.xsl");// 设置XSL样式表
	pump.setOutputFile("E:/temp/jobDataTest.xml");// 设置数据XML输出位置
	pump.run();// 开始抽取