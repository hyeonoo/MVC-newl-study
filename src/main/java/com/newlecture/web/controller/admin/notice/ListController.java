package com.newlecture.web.controller.admin.notice;

import java.io.IOException;
//import java.sql.Connection;
//import java.util.Date;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.entity.NoticeView;
import com.newlecture.web.service.NoticeService;

@WebServlet("/admin/notice/list")
public class ListController extends HttpServlet {
	// 404 error : url ����
	// 405 error : �޼��� ����
	// 403 error : ���� ����
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] openIds = request.getParameterValues("open-id");
		String[] delIds = request.getParameterValues("del-id");
		String cmd = request.getParameter("cmd");
		
		switch(cmd) {
		case "�ϰ�����":
			for(String openId : openIds)
				System.out.printf("open-id : %s\n", openId);
			break;
		case "�ϰ�����":
			for(String delId : delIds)
				System.out.printf("del-id : %s\n", delId);
			break;
		}
		
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//list?f=title&q=a �̷������� ���޵ȴ�. 
		
		//�⺻��(�ӽú��� ����)
		String field_ = request.getParameter("f");	//list.jsp �˻������� Ȯ��
		String query_ = request.getParameter("q");
		String page_ = request.getParameter("p");
		
		//page -> int�� �ϸ� �ȵȴ�. ���޵Ǵ°��� �����̱� ������.. null�� ���� �� ���� ������ ���� null�� ���� �� �ִ� �ڷ��� String ���
		
		String field = "title";
		if(field_ != null && !field_.equals(""))
			field = field_;
		
		String query = "";
		if(query_ != null && !query_.equals(""))
			query = query_;
		
		int page = 1;
		if(page_ != null && !page_.equals(""))
			page = Integer.parseInt(page_);
		
		NoticeService service = new NoticeService();
		List<NoticeView> list = service.getNoticeList(field, query, page);
		int count = service.getNoticeCount(field, query);
		
		request.setAttribute("list", list);	
		request.setAttribute("count", count);
		
		
		System.out.println(list);

		request.getRequestDispatcher("/WEB-INF/view/admin/board/notice/list.jsp").forward(request, response);
		
	}

}
