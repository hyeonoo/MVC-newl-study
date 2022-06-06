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
			result = "홀수";
		
		else 
			result = "짝수";
		
		request.setAttribute("result", result);	//request에 값을 담은것 
		
		String[] names = {"newlec", "dragon"};
		request.setAttribute("names", names); //request에 값을 담은것
		
		Map<String, Object> notice = new HashMap<String, Object>();
		notice.put("id", 1);
		notice.put("title", "EL은 좋아요");
		request.setAttribute("notice", notice); //request에 값을 담은것
		
		//redirect : 현재 작업하는 것과는 상관없는 전혀 새로운 것 요청
		
		//forward : 현재 작업하는 걸 이어갈 수 있도록 공유하는 
		RequestDispatcher dispatcher = request.getRequestDispatcher("spag.jsp");	//spag.jsp로 요청 전달 역할
		dispatcher.forward(request, response);
		
		/*
			forward -> 현재 작업했던 내용들을 request, response에 담고 있다면 그 내용이 spag.jsp로 이어져서 요청이 진행
			이어진다면.. spag.jsp에서 쓰고 있는 request와 response가 Spag.java에서 forward할때 전달했던 request와 response가 똑같고,
			spag.jsp에서는 Spag.java의 내용을 꺼내서 볼 수 있고, 그리고 Spag.java에서 내용이 계속 이어지는 것
			Spag.java의 request에서 spag.jsp로 이어갈수 있도록 하기 위해 result를 넘겨 주어야 하는데 그때 사용되는 저장소가 request이다.
			forward 사이에 이어갈 수 있도록 하는 저장소는 request!! 사용!!
	
 		*/
		
		/*
			상태를 저장할때 사용할 수 있는 저장소 4개
			pageContext : 내장객체 설명할 때 page내에서 혼자서 사용할 수 있는 저장소
			request : forward관계 사이에서 사용할 수 있는 저장소
			session : 현재 session에서 공유될수 있는 저장소
			page : 모든 session 모든 page에서 공유될수 있는 저장소
			cookie :  client에 저장할 수 있는 저장소 
			
			Server 상의 저장소 4개 (cookie제외)
		*/
	}
}
