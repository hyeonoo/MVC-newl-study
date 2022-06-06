package com.newlecture.web.controller.notice;

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

@WebServlet("/notice/list")
public class ListController extends HttpServlet {
	
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
		request.setAttribute("list", list);
		
		System.out.println(list);
		
		System.out.println(list);
//		List<Notice> list = new ArrayList<>();								// �ּ�ó�� �Ȱ͵��� ��� service.java �� !!
		
		/* String driverName =  "com.mysql.cj.jdbc.Driver"; */
//		String url =  "jdbc:mysql://localhost:3306/newlec_study?useUnicode=true&characterEncoding=utf8";
//		String sql = "SELECT * FROM NOTICE ";

//		try {
//			Class.forName( "com.mysql.cj.jdbc.Driver");
//			Connection con = DriverManager.getConnection(url, "root", "1234");
//			Statement st = con.createStatement();
//			ResultSet rs = st.executeQuery(sql);
//			    
//			while(rs.next()){ 
//				int id = rs.getInt("id");
//				String title = rs.getString("title"); 				/* �̰͵��� Model�� �ȴ�. */ 
//				String writerId = rs.getString("writer_id");
//				Date regdate = rs.getTimestamp("regdate") ;			//getDate�� ȥ������ ���� 
//				int hit = rs.getInt("hit");
//				String files = rs.getString("files");
//				String content = rs.getString("content");
//										 
//
//					Notice notice = new Notice(
//							id,
//							title,
//							writerId,
//							regdate,
//							hit,
//							files,
//							content
//					);
//				list.add(notice);
//			}
			
//			rs.close();
//			st.close();
//			con.close();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		//forward : �۾��ߴ��� �̾� �޾� ����ϰ� ������
		request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp").forward(request, response);
		
	}

}
