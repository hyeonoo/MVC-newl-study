package com.newlecture.web;

import java.io.IOException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/calc3")
public class Calc3 extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		
		expCookie.setPath("/"); 	//path�� �ϳ��� ���� ����
		response.addCookie(expCookie);
		response.sendRedirect("calcpage");				//������ ���� �Է��ϰ� �ڷΰ��⸦ ���� �ٽ� ��� ������ redirect�ϸ� �����Է��� �ٷ� ���� �������� �����־� �ٷ� �ٸ� �� �Է� ����
		
	}
		
		
}
			
			
		
		
		
		
		
		
	
	


