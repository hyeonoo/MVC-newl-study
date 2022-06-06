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
//		if(req.getMethod().equals("GET")) {	//request�� getMethod() --> calculator.html�� form method�� get���� �Դ��� post�� �Դ��� ���ڷ� ��ȯ���ش�. �빮�ڷ� �����
//			System.out.println("GET ��û�� �Խ��ϴ�.");
//		}else if(req.getMethod().equals("POST")) {
//			System.out.println("POST ��û�� �Խ��ϴ�.");
//		}
//		
//		super.service(req, resp);	//service --> ��û�� ���� ���Լ�(doGet, doPost)�� ȣ��ǰ� �ϴ� ��
//									//���� get ��û�� �ϰ� �Ǹ� if���� �޼��� ����ϰ� service�� ������ٵ� doGet method�� ã������ ������ doGet�޼��尡 ���ǰ� �ȉ����� ������
	
//	}
	
//GET�� POST�� �ϳ��� servlet���� ������ �ִ�. path�������� !!
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet method�� ȣ�� �Ǿ����ϴ�.");
		
		Cookie[] cookies = request.getCookies();
		
		String exp = "0";
		if(cookies!= null)
		for(Cookie c : cookies) 					//cookie ã�� ����
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
		out.write("����� ���� �Է��ϼ���.<br>");			
		out.write("<table>");			
		out.write("<tr>");			
		//out.printf("<td class=\"output\" colspan=\"4\">%d</td>", 3+4);				//calc3.html���� 3+4�� �ϸ� 3+4 �״�� ���, but ���⼭�� ���Ǿ ���	
		out.printf("<td class=\"output\" colspan=\"4\">%s</td>", exp);					// cookie�̿��� ���
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
		System.out.println("doPOST method�� ȣ�� �Ǿ����ϴ�.");
		
		Cookie[] cookies = request.getCookies();
		
		// ����ڰ� ���� + or - ������ ����(operator)���� ����, ���� �ϳ��� ���޵�
		String value = request.getParameter("value");
		String operator = request.getParameter("operator");
		String dot = request.getParameter("dot");
		
		//exp ���������� �о����
		String exp = "";

		if(cookies!= null)
		for(Cookie c : cookies) 					//cookie ã�� ����
			if(c.getName().equals("exp")) {
				exp = c.getValue();
				break;
			}
		
		//���� ��� �����(=)
		if(operator != null && operator.equals("=")) {
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");	//���  ===> �������� �ʾƼ� ���� error ��
			try {
				exp = String.valueOf(engine.eval(exp));
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// "C" --> cookie ����!!
		}else if(operator != null && operator.equals("C")) {
			exp = "";
			
		}else {	
		
		// �ϳ��� ���� , ������ 2���� null�� �´�.
			exp += (value == null)?"" : value;
			exp += (operator == null)?"" : operator;
			exp += (dot == null)?"" : dot;
		}
		//����ڰ� �����ϴ� ���� ������ exp���� cookie�� �����ϰ� �װ��� ������ redirect
		Cookie expCookie = new Cookie("exp", exp);
		
		if(operator != null && operator.equals("C")) 
			expCookie.setMaxAge(0);	// cookie �ٷ� ����
		
		expCookie.setPath("/calculator"); 	//path�� �ϳ��� ���� ����
		response.addCookie(expCookie);
		response.sendRedirect("calculator");	
	}
}
