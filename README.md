# Testing qaontime.com in Selenium Webdriver with JAVA and TestNG 

Welcome to my project! 

This project was created using Apache Maven as a software project management tool. The purpose was to perform an automated web-testing of the qaontime.com, using TestNG as a testing framework. 

# Instructions
1. Go to https://www.eclipse.org/downloads/packages/ and following instructions, download and install Eclipse IDE for Java. Alternatively, you could also use InteliJ IDE, NetBeans or any other IDE that supports Java. 
2. Go to https://maven.apache.org/download.cgi and download Apache Maven. 
3. Open project folder in Eclipse IDE. 
4. Create "config.xml" file using valid login information (username and password), as follows:
<config>
	<user>
		<url>http://qaontime.com/register/</url>
		<userNameXml> valid username </userNameXml>
		<passwordXml> valid password </passwordXml>
	</user>
</config>

5. Go to folder "test-data" and update columns with usernames and passwords that you will use in your tests. 
6. If you need additional dependencies besides Selenium, testNG and xsls reader, go to file "pom.xml" and add them there. 
7. To run your tests, use files "smoke.hml" and "testingRegression.xml". 

I hope you'll find this project useful. 
# Good luck! 
