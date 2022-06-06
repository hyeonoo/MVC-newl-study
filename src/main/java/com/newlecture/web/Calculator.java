package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculator")
public class Calculator extends HttpServlet {
//	@Override
//	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		
//		if(req.getMethod().equals("GET")) {	//request의 getMethod() --> calculator.html에 form method에 get으로 왔는지 post로 왔는지 문자로 반환해준다. 대문자로 써야함
//			System.out.println("GET 요청이 왔습니다.");
//		}else if(req.getMethod().equals("POST")) {
//			System.out.println("POST 요청이 왔습니다.");
//		}
//		
//		super.service(req, resp);	//service --> 요청에 따른 두함수(doGet, doPost)를 호출되게 하는 것
//									//만약 get 요청을 하게 되면 if문의 메세지 출력하고 service가 실행될텐데 doGet method를 찾으려고 하지만 doGet메서드가 정의가 안됬으니 오류남
	
//	}
	
//GET과 POST를 하나의 servlet으로 담을수 있다. path설정까지 !!
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet method가 호출 되었습니다.");
		
		Cookie[] cookies = request.getCookies();
		
		String exp = "0";
		if(cookies!= null)
		for(Cookie c : cookies) 					//cookie 찾는 과정
			if(c.getName().equals("exp")) {
				exp = c.getValue();
				break;
			}
		
		response.setCharacterEncoding("UTF-8");					
		response.setContentType("text/html; charset = UTF-8");	
		PrintWriter out = response.getWriter();
		
		
		out.write("<!DOCTYPE html>");
		out.write("<html>");
		out.write("<head>");
		out.write("<meta charset=\"UTF-8\">");
		out.write("<title>Insert title here</title>");

		out.write("<style>");
		out.write("input{");
		out.write("width: 50px;");		
		out.write("height: 50px;");	
		out.write("}");
		out.write(".output{");	
		out.write("height: 50px;");	
		out.write("background: #e9e9e9;");		
		out.write("font-size: 24px;");
		out.write("font-weight: bold;");		
		out.write("text-align: right;");		
		out.write("padding: 0px 5px;");
		out.write("}");
		out.write("</style>");
		out.write("</head>");
		out.write("<body>");
			
		out.write("<form  method=\"post\">");		
		out.write("계산할 값을 입력하세요.<br>");			
		out.write("<table>");			
		out.write("<tr>");			
		//out.printf("<td class=\"output\" colspan=\"4\">%d</td>", 3+4);				//calc3.html에서 3+4로 하면 3+4 그대로 출력, but 여기서는 계산되어서 출력	
		out.printf("<td class=\"output\" colspan=\"4\">%s</td>", exp);					// cookie이용한 계산
		out.write("</tr>");
		out.write("<tr>");				
		out.write("<td><input type=\"submit\" name=\"operator\" value=\"CE\" /></td>");		
		out.write("<td><input type=\"submit\" name=\"operator\" value=\"C\" /></td>");				
		out.write("<td><input type=\"submit\" name=\"operator\" value=\"BS\" /></td>");					
		out.write("<td><input type=\"submit\" name=\"operator\" value=\"/\" /></td>");					
		out.write("</tr>");				
		out.write("<tr>");				
		out.write("<td><input type=\"submit\" name=\"value\" value=\"7\" /></td>");					
		out.write("<td><input type=\"submit\" name=\"value\" value=\"8\" /></td>");					
		out.write("<td><input type=\"submit\" name=\"value\" value=\"9\" /></td>");					
		out.write("<td><input type=\"submit\" name=\"operator\" value=\"*\" /></td>");						
		out.write("</tr>");				
		out.write("<tr>");				
		out.write("<td><input type=\"submit\" name=\"value\" value=\"4\" /></td>");					
		out.write("<td><input type=\"submit\" name=\"value\" value=\"5\" /></td>");					
		out.write("<td><input type=\"submit\" name=\"value\" value=\"6\" /></td>");					
		out.write("<td><input type=\"submit\" name=\"operator\" value=\"-\" /></td>");					
		out.write("</tr>");				
		out.write("<tr>");				
		out.write("<td><input type=\"submit\" name=\"value\" value=\"1\" /></td>");					
		out.write("<td><input type=\"submit\" name=\"value\" value=\"2\" /></td>");					
		out.write("<td><input type=\"submit\" name=\"value\" value=\"3\" /></td>");				
		out.write("<td><input type=\"submit\" name=\"operator\" value=\"+\" /></td>");					
		out.write("</tr>");				
		out.write("<tr>");				
		out.write("<td></td>");					
		out.write("<td><input type=\"submit\" name=\"value\" value=\"0\" /></td>");					
		out.write("<td><input type=\"submit\" name=\"dot\" value=\".\" /></td>");					
		out.write("<td><input type=\"submit\" name=\"operator\" value=\"=\" /></td>");			
		out.write("</table>");		
						
		out.write("</form>");		
			
			
		out.write("</body>");
		out.write("</html>");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPOST method가 호출 되었습니다.");
		
		Cookie[] cookies = request.getCookies();
		
		// 사용자가 값과 + or - 누르면 이쪽(operator)으로 전달, 셋중 하나만 전달됨
		String value = request.getParameter("value");
		String operator = request.getParameter("operator");
		String dot = request.getParameter("dot");
		
		//exp 만들어내기위해 읽어오기
		String exp = "";

		if(cookies!= null)
		for(Cookie c : cookies) 					//cookie 찾는 과정
			if(c.getName().equals("exp")) {
				exp = c.getValue();
				break;
			}
		
		//계산된 결과 남기기(=)
		if(operator != null && operator.equals("=")) {
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");	//계산  ===> 지원되지 않아서 이제 error 남
			try {
				exp = String.valueOf(engine.eval(exp));
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// "C" --> cookie 삭제!!
		}else if(operator != null && operator.equals("C")) {
			exp = "";
			
		}else {	
		
		// 하나만 누적 , 나머지 2개는 null로 온다.
			exp += (value == null)?"" : value;
			exp += (operator == null)?"" : operator;
			exp += (dot == null)?"" : dot;
		}
		//사용자가 전달하는 값을 가지고 exp만들어서 cookie에 저장하고 그것을 가지고 redirect
		Cookie expCookie = new Cookie("exp", exp);
		
		if(operator != null && operator.equals("C")) 
			expCookie.setMaxAge(0);	// cookie 바로 삭제
		
		expCookie.setPath("/calculator"); 	//path는 하나만 설정 가능
		response.addCookie(expCookie);
		response.sendRedirect("calculator");	
	}
}
