<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- ---입력코드-------------------------------------- MVC 1, 입력과 제어를 담당 : Controller[java code}-->    
<%-- <%
	int num = 0;
	String num_ = request.getParameter("n");
	if(num_ != null&& !num_.equals(""))
		num = Integer.parseInt(num_);
	
	String result;
	
	if(num%2 != 0)
		result = "홀수";
	
	else 
		result = "짝수";
%>    --%>

<!-- Spag.java ==> spag.jsp로 전이하도록해야되는데 저장소 필요
 forwording!!  -->
<!-- controller -> Model -> View -->
<!-- ---출력코드---------------------------------------- 출력 담당 : View[HTML code]   //	출력 data : Model -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%--jsp는 기본적으로 갖고 있는 내용 중 내장객체 pageContext가 있는데 servlet 객체를 얻을 수 있는데
예를들어 getRequest, getResponse, getSession 등을 얻을 수 있다. 이러한 객체를 얻을 수 있는 보따리 같은 어떤 보물상자가 
있다면 pageContext.setAttribute(); page내에서 심은 data를 사용할 수 있다. page내에서 저장해서 쓸 수 있는 객체 ==> page 객체--%>
<%
/* pageContext.setAttribute("aa", "hello"); */
pageContext.setAttribute("result", "hello");

%>
<body>
	<%=request.getAttribute("result") %>입니다.
	${result}	<!-- EL 활용 -->
	${requestScope.result}<br>
	
	${names[1]}<br>
	
	${notice.title}<br> 
	
	<%-- ${aa} --%>	<!-- page자체에서 pageContext(객체)에 심은 값도 EL 사용하여 쓸 수 있다. -->
	${result}<br>
	${param.n}<br>
	${param.n > 3}<br>
	${param.n ge 3}<br>
	${empty param.n}<br>	<!-- ${param.n == null || param.n ==""}와 같다 -->
	${empty param.n?'값이 비어있습니다.' : param.n}<br>
	${param.n/2}<br>		<!-- int/int ==> 소수점 결과 나옴 -->
	${header.accept}<br>	<!-- 개발자 도구f12에서 network정보에서 header 선택후 request Header정보에서 뽑아오고 싶은거  -->
							<!-- Accept?? : browser에 요청을 할때 내가 읽어들일수 있는 문서에 대한 종류, 타입을 server에 알려주는것 -->
</body>
</html>