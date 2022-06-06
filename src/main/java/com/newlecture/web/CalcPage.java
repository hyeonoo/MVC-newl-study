package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/calcpage")
public class CalcPage extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
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
			
		out.write("<form action=\"calc3\" method=\"post\">");		
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
}


/*
 * annotation
 * 	==>  class나 method에 붙여지는 주석
 * 		주석은 번역과정 또는 compile과정에서 사라지는 설명문인데 annotation은 compiler에 의해서 사라지지 않을 수 있다.
 * 		(옵션에 따라 사라지게 할 수도 있다.)
 * 		객체라는 것을 사용할 때 객체에 묻어있는 주석(annontation) 정보를 꺼내어서 그것을 활용할 수 있는 meta-data라고도 할 수 있다.
 * 		method나 class를 사용 할 때 붙여진 annotation 이용하여 실행하는데 영향을 줄 수 있다.(mapping정보를 남길 수 있다.)
 * 		annotation사용하면 맵핑할 필요 없음(web.xml가서)
 */
