
<%
	int x = 3;			
	int y = 4;
%>

<%-- <%
	int page = 1; ---> error!! duplicate(중복)  by 내장객체 jspService() 확인 하면 여기안에 page라는 변수(내장객체) 사용했기때문에 중복 에러
	확인 법은 C:\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\MVC-newl-study 여기서 jsp.java 열어서 확인가능
		
%> --%>

<%-- '<% %>' code block : java code로 출력해준다. --%>

<%-- '<%= %>' : 출력!! out.print();와 같은 기능 --%>

<%-- '<%@ %>' : Page 지시자 code <code block X>--%>

<%-- '<%! %>' : ... --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
	input{
		width: 50px;
		height: 50px;
	}
	.output{
		height: 50px;
		background: #e9e9e9;
		font-size: 24px;
		font-weight: bold;
		text-align: right;
		padding: 0px 5px;
	}
</style>
</head>
<body>
	
		<form action="calc3" method="post">
			<br>
			<table>
				<tr>
					<td class="output" colspan="4">${3+4}</td>
				</tr>
				<tr>
					<td><input type="submit" name="operator" value="CE"/></td>
					<td><input type="submit" name="operator" value="C"/></td>
					<td><input type="submit" name="operator" value="BS"/></td>
					<td><input type="submit" name="operator" value="/"/></td>
				</tr>
				<tr>
					<td><input type="submit" name="value" value="7"/></td>
					<td><input type="submit" name="value" value="8"/></td>
					<td><input type="submit" name="value" value="9"/></td>
					<td><input type="submit" name="operator" value="*"/></td>
				</tr>
				<tr>
					<td><input type="submit" name="value" value="4"/></td>
					<td><input type="submit" name="value" value="5"/></td>
					<td><input type="submit" name="value" value="6"/></td>
					<td><input type="submit" name="operator" value="-"/></td>
				</tr>
				<tr>
					<td><input type="submit" name="value" value="1"/></td>
					<td><input type="submit" name="value" value="2"/></td>
					<td><input type="submit" name="value" value="3"/></td>
					<td><input type="submit" name="operator" value="+"/></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" name="value" value="0"/></td>
					<td><input type="submit" name="dot" value="."/></td>
					<td><input type="submit" name="operator" value="="/></td>
				</tr>
			</table>
				
		</form>
	
	
</body>
</html>