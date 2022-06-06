package com.newlecture.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/spag")
public class Spag extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int num = 0;
		String num_ = request.getParameter("n");
		if(num_ != null&& !num_.equals(""))
			num = Integer.parseInt(num_);
		
		String result;
		
		if(num%2 != 0)
			result = "Ȧ��";
		
		else 
			result = "¦��";
		
		request.setAttribute("result", result);	//request�� ���� ������ 
		
		String[] names = {"newlec", "dragon"};
		request.setAttribute("names", names); //request�� ���� ������
		
		Map<String, Object> notice = new HashMap<String, Object>();
		notice.put("id", 1);
		notice.put("title", "EL�� ���ƿ�");
		request.setAttribute("notice", notice); //request�� ���� ������
		
		//redirect : ���� �۾��ϴ� �Ͱ��� ������� ���� ���ο� �� ��û
		
		//forward : ���� �۾��ϴ� �� �̾ �� �ֵ��� �����ϴ� 
		RequestDispatcher dispatcher = request.getRequestDispatcher("spag.jsp");	//spag.jsp�� ��û ���� ����
		dispatcher.forward(request, response);
		
		/*
			forward -> ���� �۾��ߴ� ������� request, response�� ��� �ִٸ� �� ������ spag.jsp�� �̾����� ��û�� ����
			�̾����ٸ�.. spag.jsp���� ���� �ִ� request�� response�� Spag.java���� forward�Ҷ� �����ߴ� request�� response�� �Ȱ���,
			spag.jsp������ Spag.java�� ������ ������ �� �� �ְ�, �׸��� Spag.java���� ������ ��� �̾����� ��
			Spag.java�� request���� spag.jsp�� �̾�� �ֵ��� �ϱ� ���� result�� �Ѱ� �־�� �ϴµ� �׶� ���Ǵ� ����Ұ� request�̴�.
			forward ���̿� �̾ �� �ֵ��� �ϴ� ����Ҵ� request!! ���!!
	
 		*/
		
		/*
			���¸� �����Ҷ� ����� �� �ִ� ����� 4��
			pageContext : ���尴ü ������ �� page������ ȥ�ڼ� ����� �� �ִ� �����
			request : forward���� ���̿��� ����� �� �ִ� �����
			session : ���� session���� �����ɼ� �ִ� �����
			page : ��� session ��� page���� �����ɼ� �ִ� �����
			cookie :  client�� ������ �� �ִ� ����� 
			
			Server ���� ����� 4�� (cookie����)
		*/
	}
}
