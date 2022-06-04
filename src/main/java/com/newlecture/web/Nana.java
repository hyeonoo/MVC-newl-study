package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hi")
public class Nana extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setCharacterEncoding("UTF-8");					 // ����ڿ��� ������ �ڵ� ����� ������ �� 
		response.setContentType("text/html; charset = UTF-8");	 // ���� ���� ��� �ؼ��� ���ΰ��� ���� ������ ���������� (contentType�� �����̴�)
																 // client���� ���޵Ǿ� char�� utf-8�� �Ǿ������� �װɷ� �о��, �� ������ html�����̴�.
		
		PrintWriter out = response.getWriter();	//���
		
		String cnt_ = request.getParameter("cnt");	//_�� �ӽú����� ��Ÿ��������
		
		int cnt = 100;		//�⺻�� 100 
		if(cnt_ != null && !cnt_.equals("")) //cnt_ �� null�� �ƴϰų� ���ڰ� �ƴҰ�� ������ ����ȯ��Ű�� ����
			cnt = Integer.parseInt(cnt_);
		
		/*int cnt = Integer.parseInt(request.getParameter("cnt"));	 ���⼭ ���� ������ ������ ==> �ֳ� ?? ���� ���� �Ƚɾ� �־
																	 localhost:8080/hi�� ?cnt=3 ���ָ� ��µ� <��ü ��û�� /hi?cnt=3�̰� 
																	 ���� ��Ʈ���� �̿��� �߰��� �ɼ��� ?cnt=8�� �ȴ�.
																	 ���� ���� ����Ϸ���??
																	 ���δ�� Interger.parseInt�� ����ȯ�� ����߱� ������ nullpointerException�߻�
																	 
																	 */
		
		
		for(int i=0; i<cnt; i++) {
			out.println((i+1)+" : �ȳ� Servlet!!<br /> ");
		}
			//������ �ҷ��� servlet�̶�� class�� mapping �Ǿ��־�� �Ѵ�. ==> web.xml�� mapping
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
