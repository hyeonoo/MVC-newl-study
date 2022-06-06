package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calc")
public class Calc extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setCharacterEncoding("UTF-8");					
		response.setContentType("text/html; charset = UTF-8");	 
		//request.setCharacterEncoding("UTF-8");
		/*
		 * mutibyte ���� ���۹��� �ذ� 2����
		 * 1. server.xml -> connect�κп� URIEncoding = "UTF-8" ����
		 * 2. Servlet ������ request.setCharacterEncoding("UTF-8"); <��õ>
		 */
		
		PrintWriter out = response.getWriter();	//���
		
		String x_ = request.getParameter("x");
		String y_ = request.getParameter("y");
		String op = request.getParameter("operator");
		
		int x = 0;		// ���ڿ� �ð�� x or y = 0�� �´� 
		int y = 0;
		
		if(!x_.equals("")) x = Integer.parseInt(x_);
		if(!y_.equals("")) y = Integer.parseInt(y_);
		
		int result=0;		//�⺻��
		
		if(op.equals("����"))
			result = x+y;
		else
			result = x-y;
		
		response.getWriter().printf("reuslt is %d\n", result);
		
		
	
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
