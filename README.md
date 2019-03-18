# My_Spring_Maven_Bootstrap_Web_Project
A custom spring framework of lastest version with Maven, DB-MyBatis, Bootstrap.

# DemoSpringBasicWithDbMyBatis

## A version of the Spring Framework that includes interworking between Db and MyBatis. It also includes libraries that you use often in your projects.

> Db - MyBatis 간의 연동이 포함된  스프링 프레임워크 버전입니다. 또한 프로젝트에서 자주 사용하는 라이브러리가 포함되었습니다.

## The UI framework uses Bootstrap, mostly expressed in jsp and ajax.

> UI 프레임워크는 Bootstrap을 사용하였으며 대부분 jsp와 ajax로 표현되어 있습니다.

## Base on Java8, tomcat9.

> 자바 8, 톰캣 9 기반

	<?xml version="1.0" encoding="UTF-8"?>
	<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
		<modelVersion>4.0.0</modelVersion>
		<groupId>com.github.bestheroz</groupId>
		<artifactId>myproject</artifactId>
		<name>DemoSpringBasic</name>
		<packaging>war</packaging>
		<version>1.0.0-BUILD-SNAPSHOT</version>
		<properties>
			<m2eclipse.wtp.contextRoot>/</m2eclipse.wtp.contextRoot>
			<java-version>1.8</java-version>
			<org.springframework-version>5.1.3.RELEASE</org.springframework-version>
			<org.aspectj-version>1.9.2</org.aspectj-version>
			<org.slf4j-version>1.7.25</org.slf4j-version>
			<ch.qos.logback-version>1.2.3</ch.qos.logback-version>
			<jdbcdslogexp2-version>2.1</jdbcdslogexp2-version>
			<javax.servlet-version>4.0.1</javax.servlet-version> <!-- tomcat9 : 4.0.1 tomcat8.5 : 3.1.0, tomcat7 : 3.0.1 -->
			<javax.servlet.jsp-version>2.3.3</javax.servlet.jsp-version> <!-- tomcat9 & tomcat 8.5 : 2.3.3, tomcat7 : 2.2.1 -->
			<jstl-version>1.2</jstl-version>
			<org.junit.jupiter-version>5.3.2</org.junit.jupiter-version>
			<org.apache.commons-lang3-version>3.8.1</org.apache.commons-lang3-version>
			<com.google.code.gson-version>2.8.5</com.google.code.gson-version>
			<mybatis-version>3.4.6</mybatis-version>
			<mybatis-spring-version>1.3.2</mybatis-spring-version>
			<HikariCP-version>3.2.0</HikariCP-version>
			<!-- maven 빌드 및 ant 배포 관련 -->
			<maven-eclipse-plugin-version>2.10</maven-eclipse-plugin-version>
			<maven-compiler-plugin-version>3.8.0</maven-compiler-plugin-version>
			<exec-maven-plugin-version>1.6.0</exec-maven-plugin-version>
		</properties>
		<dependencies>
			<!-- Spring -->
			<dependency>
				<groupId>org.springframework</groupId>
				<artifactId>spring-context</artifactId>
				<version>${org.springframework-version}</version>
				<exclusions>
					<!-- Exclude Commons Logging in favor of SLF4j -->
					<exclusion>
						<groupId>commons-logging</groupId>
						<artifactId>commons-logging</artifactId>
					</exclusion>
				</exclusions>
			</dependency>
			<dependency>
				<groupId>org.springframework</groupId>
				<artifactId>spring-webmvc</artifactId>
				<version>${org.springframework-version}</version>
			</dependency>
			<dependency>
				<groupId>org.springframework</groupId>
				<artifactId>spring-jdbc</artifactId>
				<version>${org.springframework-version}</version>
			</dependency>

			<!-- AspectJ -->
			<dependency>
				<groupId>org.aspectj</groupId>
				<artifactId>aspectjrt</artifactId>
				<version>${org.aspectj-version}</version>
			</dependency>
			<!-- aop-Logging 사용관련 -->
			<dependency>
				<groupId>org.aspectj</groupId>
				<artifactId>aspectjweaver</artifactId>
				<version>${org.aspectj-version}</version>
			</dependency>

			<!-- Logging -->
			<dependency>
				<groupId>org.slf4j</groupId>
				<artifactId>slf4j-api</artifactId>
				<version>${org.slf4j-version}</version>
			</dependency>
			<!-- logback -->
			<dependency>
				<groupId>ch.qos.logback</groupId>
				<artifactId>logback-core</artifactId>
				<version>${ch.qos.logback-version}</version>
			</dependency>
			<dependency>
				<groupId>ch.qos.logback</groupId>
				<artifactId>logback-classic</artifactId>
				<version>${ch.qos.logback-version}</version>
			</dependency>

			<!-- @Inject -->
			<dependency>
				<groupId>javax.inject</groupId>
				<artifactId>javax.inject</artifactId>
				<version>1</version>
			</dependency>

			<!-- Servlet -->
			<dependency>
				<groupId>javax.servlet</groupId>
				<artifactId>javax.servlet-api</artifactId>
				<version>${javax.servlet-version}</version>
				<scope>provided</scope>
			</dependency>
			<dependency>
				<groupId>javax.servlet.jsp</groupId>
				<artifactId>javax.servlet.jsp-api</artifactId>
				<version>${javax.servlet.jsp-version}</version>
				<scope>provided</scope>
			</dependency>
			<dependency>
				<groupId>javax.servlet</groupId>
				<artifactId>jstl</artifactId>
				<version>${jstl-version}</version>
			</dependency>

			<!-- Test -->
			<dependency>
				<groupId>org.junit.jupiter</groupId>
				<artifactId>junit-jupiter-api</artifactId>
				<version>${org.junit.jupiter-version}</version>
				<scope>test</scope>
			</dependency>

			<!-- StringUtils, ExceptionUtils -->
			<dependency>
				<groupId>org.apache.commons</groupId>
				<artifactId>commons-lang3</artifactId>
				<version>${org.apache.commons-lang3-version}</version>
			</dependency>

			<!-- gson, json -->
			<dependency>
				<groupId>com.google.code.gson</groupId>
				<artifactId>gson</artifactId>
				<version>${com.google.code.gson-version}</version>
			</dependency>

			<!-- myBatis -->
			<dependency>
				<groupId>org.mybatis</groupId>
				<artifactId>mybatis</artifactId>
				<version>${mybatis-version}</version>
			</dependency>
			<dependency>
				<groupId>org.mybatis</groupId>
				<artifactId>mybatis-spring</artifactId>
				<version>${mybatis-spring-version}</version>
			</dependency>

			<!-- db connection pool 이용 -->
			<dependency>
				<groupId>com.zaxxer</groupId>
				<artifactId>HikariCP</artifactId>
				<version>${HikariCP-version}</version>
			</dependency>
			<!-- jdbc -->
			<dependency>
				<groupId>oracle.jdbc</groupId>
				<artifactId>jdbc</artifactId>
				<version>12.2.0.1</version>
				<scope>system</scope>
				<systemPath>${project.basedir}/src/main/webapp/WEB-INF/lib/ojdbc8.jar</systemPath>
			</dependency>

			<!-- sql log util -->
			<dependency>
				<groupId>org.jdbcdslog</groupId>
				<artifactId>jdbcdslogexp2</artifactId>
				<version>${jdbcdslogexp2-version}</version>
			</dependency>

		</dependencies>
		<build>
			<plugins>
				<plugin>
					<artifactId>maven-eclipse-plugin</artifactId>
					<version>${maven-eclipse-plugin-version}</version>
					<configuration>
						<additionalProjectnatures>
							<projectnature>org.springframework.ide.eclipse.core.springnature</projectnature>
						</additionalProjectnatures>
						<additionalBuildcommands>
							<buildcommand>org.springframework.ide.eclipse.core.springbuilder</buildcommand>
						</additionalBuildcommands>
						<downloadSources>true</downloadSources>
						<downloadJavadocs>true</downloadJavadocs>
					</configuration>
				</plugin>
				<plugin>
					<groupId>org.apache.maven.plugins</groupId>
					<artifactId>maven-compiler-plugin</artifactId>
					<version>${maven-compiler-plugin-version}</version>
					<configuration>
						<source>${java-version}</source>
						<target>${java-version}</target>
						<compilerArgument>-Xlint:all</compilerArgument>
						<showWarnings>true</showWarnings>
						<showDeprecation>true</showDeprecation>
					</configuration>
				</plugin>
				<plugin>
					<groupId>org.codehaus.mojo</groupId>
					<artifactId>exec-maven-plugin</artifactId>
					<version>${exec-maven-plugin-version}</version>
					<configuration>
						<mainClass>org.test.int1.Main</mainClass>
					</configuration>
				</plugin>
			</plugins>
		</build>
	</project>


## The default log of the framework

> 프레임워크의 기본 로그

	14:56:39.730 [INFO ] com.mycompany.standard.context.logging.MyRequestLoggingFilter.beforeRequest(22) === <GET>/sample/admin/member/adminMember.view, parameters={}
	14:56:39.731 [INFO ] Interceptor.preHandle(RequestFacade, CompressingHttpServletResponse, HandlerMethod) START ....... Execute Time ....... : 00:00:00.000
	14:56:39.731 [INFO ] Interceptor.preHandle(RequestFacade, CompressingHttpServletResponse, HandlerMethod) E N D ....... Execute Time ....... : 00:00:00.000 - return Value(boolean) : true
	14:56:39.731 [INFO ] AdminMemberController.view() START ....... Execute Time ....... : 00:00:00.000
	14:56:39.732 [INFO ] AdminMemberController.view() E N D ....... Execute Time ....... : 00:00:00.000 - return Value(String) : "/sample/admin/member/AdminMember"
	14:56:39.757 [INFO ] com.mycompany.standard.common.interceptor.Interceptor.afterCompletion(71) === /sample/admin/member/adminMember.view ....... Request Complete Execute Time(include JSP) viewName : /sample/admin/member/AdminMember ....... : 00:00:00.025

## Added work (View detail url: [https://blog.naver.com](https://blog.naver.com/PostList.nhn?blogId=bestheroz&from=postList&categoryNo=92))

> 추가된 작업 (상세보기 url: [https://blog.naver.com](https://blog.naver.com/PostList.nhn?blogId=bestheroz&from=postList&categoryNo=92))

1. Communication between the server and the client treats the java.util.Date type as a Long(timestamp) value.
 
 > 서버와 클라이언트 간 통신에서 java.util.Date형 을 Long(Time Stamp) 값으로 처리.

2. added trace-log.

 > 추적로그를 추가하였습니다.
 
3. Variable-bound DB SQL Logging.
 
 > 변수 바인딩된 DB SQL 로그 남기기.
  
4. Automatic generation of SQL using @SelectProvider, @InsertProvider, @UpdateProvider, @DeleteProvider.
 
 > @SelectProvider, @InsertProvider, @UpdateProvider, @DeleteProvider 을 이용한 SQL 자동생성.
 
5. java.util.Date -> org.joda.time.LocalDateTime

## Add in the future

> 향후 추가될..

 1. ....
