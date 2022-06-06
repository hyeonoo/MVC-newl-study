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

@WebServlet("/calc2")
public class Calc2 extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application = request.getServletContext();		//���� �����ϱ� ���Ѱ�
		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();
		
		response.setCharacterEncoding("UTF-8");					
		response.setContentType("text/html; charset = UTF-8");	 
		//request.setCharacterEncoding("UTF-8");
		/*
		 * mutibyte ���� ���۹��� �ذ� 2����
		 * 1. server.xml -> connect�κп� URIEncoding = "UTF-8" ����
		 * 2. Servlet ������ request.setCharacterEncoding("UTF-8"); <��õ>
		 */
		
		// ����ڰ� ���� + or - ������ ����(operator)���� ����
		String v_ = request.getParameter("v");
		String op = request.getParameter("operator");
		
		int v = 0;		 
		if(!v_.equals("")) v = Integer.parseInt(v_);
		
		//���
		if(op.equals("=")) {	//=�� ������ ����
			
			//int x = (Integer)application.getAttribute("value");	//�տ��� �����ߴ� application�� �ִ� ���� ������(get) ����
			//int x = (Integer)session.getAttribute("value");
			
			int x = 0;
			for(Cookie c : cookies) 						//cookie ã�� ����
				if(c.getName().equals("value")) {
					x = Integer.parseInt(c.getValue());
					break;
				}
			
		
			int y = v;
			//String operator = (String)application.getAttribute("op");;
			//String operator = (String)session.getAttribute("op");;
			
			String operator = "";
			for(Cookie c : cookies) 					//cookie ã�� ����
				if(c.getName().equals("op")) {
					operator = c.getValue();
					break;
				}
			
			int result=0;		//�⺻��
			
			if(operator.equals("+"))
				result = x+y;
			else
				result = x-y;
			
			response.getWriter().printf("reuslt is %d\n", result);
		
		// ���� ����
		}else {	//operator�� != �̶�� + or - �� �ɲ��� application context�� ����(set)
			
			//application.setAttribute("value", v); 		//map collection
			//application.setAttribute("op", op);			// �ΰ��� ���� �� �� �ִ�.
		
			//session.setAttribute("value", v);
			//session.setAttribute("op", op);
			
			//first,, Client���� cookie ����
			Cookie valueCookie = new Cookie("value", String.valueOf(v));
			Cookie opCookie = new Cookie("op", op);
			valueCookie.setPath("/calc2"); 						//cookie�� ��� ��쿡 ���޵Ǿ�� �ϴ��� "/notice/" path�� ���Ե� ���� url ��û�� ��� valueCookie�� ������
			valueCookie.setMaxAge(24*60*60);					// maxAge setting  --> ���ᳯ¥ ���� ex. 1000 = 1000sec  (�ʴ�������), 60*60 = 60min
			opCookie.setPath("/calc2");							// /add ��� �� ��� servlet �ּҰ� �޶� cookie ���� �ȵ�   // opCookie�� maxAge set�� ���߱⶧���� browser�� ������ cookie �������.
			response.addCookie(valueCookie);
			response.addCookie(opCookie);
			
			response.sendRedirect("calc2.html");				//������ ���� �Է��ϰ� �ڷΰ��⸦ ���� �ٽ� ��� ������ redirect�ϸ� �����Է��� �ٷ� ���� �������� �����־� �ٷ� �ٸ� �� �Է� ����
		}
		
		
		
		
		
		
	
	}
}


/*
 * annotation
 * 	==>  class�� method�� �ٿ����� �ּ�
 * 		�ּ��� �������� �Ǵ� compile�������� ������� �����ε� annotation�� compiler�� ���ؼ� ������� ���� �� �ִ�.
 * 		(�ɼǿ� ���� ������� �� ���� �ִ�.)
 * 		��ü��� ���� ����� �� ��ü�� �����ִ� �ּ�(annontation) ������ ����� �װ��� Ȱ���� �� �ִ� meta-data��� �� �� �ִ�.
 * 		method�� class�� ��� �� �� �ٿ��� annotation �̿��Ͽ� �����ϴµ� ������ �� �� �ִ�.(mapping������ ���� �� �ִ�.)
 * 		annotation����ϸ� ������ �ʿ� ����(web.xml����)
 */
