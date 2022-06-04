package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calc2")
public class Calc extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application = request.getServletContext();		//���� �����ϱ� ���Ѱ�
		
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
			
			int x = (Integer)application.getAttribute("value");	//�տ��� �����ߴ� application�� �ִ� ���� ������(get) ����
			int y = v;
			String operator = (String)application.getAttribute("op");;
			int result=0;		//�⺻��
			
			if(operator.equals("+"))
				result = x+y;
			else
				result = x-y;
			
			response.getWriter().printf("reuslt is %d\n", result);
		
		// ���� ����
		}else {	//operator�� != �̶�� + or - �� �ɲ��� application context�� ����(set)
			
			application.setAttribute("value", v); 		//map collection
			application.setAttribute("op", op);			// �ΰ��� ���� �� �� �ִ�.
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
