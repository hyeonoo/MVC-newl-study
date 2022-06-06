package com.newlecture.web.controller.notice;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.service.NoticeService;

@WebServlet("/notice/detail")			//먼저 실행되고 detail.jsp가 실행되어야 한다.
public class DetailController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));		//사용자가 요청할때 전달했던 id 값
		
		NoticeService service = new NoticeService();
		Notice notice = service.getNotice(id);
		request.setAttribute("n", notice);

		
		
		//redirect : servlet 호출했는데 아예 다른 page로 가버리는 
		//forward : 작업했던걸 이어 받아 사용하고 싶을때
		request.getRequestDispatcher("/WEB-INF/view/notice/detail.jsp").forward(request, response);		//데이터 서비스 받아 사용자에게 돌려주기위한 출력 로직
	}
}
