package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add2")
public class Add2 extends HttpServlet {
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
		
		String[] num_ = request.getParameterValues("num");	//+values --> array
		
		int result=0;
		
		for(int i=0; i<num_.length; i++) {
			int num = Integer.parseInt(num_[i]);	//������ ����Ȱ��� �ݺ� x
			result+=num;
		}
		
		
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
