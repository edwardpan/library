HTML���ݳ�ȡ���߰�����Ҫ��дXSL��ʽ��

�����⣺dom4j-1.6.1.jar, jtidy-r938.jar

ʾ����
	HTMLDataPump pump = new HTMLDataPumpImpl();
	pump.setHTMLSource(htmlSourceStr);// ����HTMLԴ
	pump.setXSLFile("E:/temp/jobToXmlTest.xsl");// ����XSL��ʽ��
	pump.setOutputFile("E:/temp/jobDataTest.xml");// ��������XML���λ��
	pump.run();// ��ʼ��ȡ