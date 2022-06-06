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
		ServletContext application = request.getServletContext();		//값을 저장하기 위한곳
		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();
		
		response.setCharacterEncoding("UTF-8");					
		response.setContentType("text/html; charset = UTF-8");	 
		//request.setCharacterEncoding("UTF-8");
		/*
		 * mutibyte 문자 전송문제 해결 2가지
		 * 1. server.xml -> connect부분에 URIEncoding = "UTF-8" 설정
		 * 2. Servlet 문서에 request.setCharacterEncoding("UTF-8"); <추천>
		 */
		
		// 사용자가 값과 + or - 누르면 이쪽(operator)으로 전달
		String v_ = request.getParameter("v");
		String op = request.getParameter("operator");
		
		int v = 0;		 
		if(!v_.equals("")) v = Integer.parseInt(v_);
		
		//계산
		if(op.equals("=")) {	//=를 누르면 계산됨
			
			//int x = (Integer)application.getAttribute("value");	//앞에서 저장했던 application에 있던 값을 꺼내서(get) 연산
			//int x = (Integer)session.getAttribute("value");
			
			int x = 0;
			for(Cookie c : cookies) 						//cookie 찾는 과정
				if(c.getName().equals("value")) {
					x = Integer.parseInt(c.getValue());
					break;
				}
			
		
			int y = v;
			//String operator = (String)application.getAttribute("op");;
			//String operator = (String)session.getAttribute("op");;
			
			String operator = "";
			for(Cookie c : cookies) 					//cookie 찾는 과정
				if(c.getName().equals("op")) {
					operator = c.getValue();
					break;
				}
			
			int result=0;		//기본값
			
			if(operator.equals("+"))
				result = x+y;
			else
				result = x-y;
			
			response.getWriter().printf("reuslt is %d\n", result);
		
		// 값을 저장
		}else {	//operator가 != 이라면 + or - 이 될꺼고 application context에 저장(set)
			
			//application.setAttribute("value", v); 		//map collection
			//application.setAttribute("op", op);			// 두개를 꺼내 쓸 수 있다.
		
			//session.setAttribute("value", v);
			//session.setAttribute("op", op);
			
			//first,, Client에게 cookie 전달
			Cookie valueCookie = new Cookie("value", String.valueOf(v));
			Cookie opCookie = new Cookie("op", op);
			valueCookie.setPath("/calc2"); 						//cookie가 어느 경우에 전달되어야 하는지 "/notice/" path가 포함된 하위 url 요청할 경우 valueCookie를 가져옴
			valueCookie.setMaxAge(24*60*60);					// maxAge setting  --> 만료날짜 설정 ex. 1000 = 1000sec  (초단위설정), 60*60 = 60min
			opCookie.setPath("/calc2");							// /add 라고 할 경우 servlet 주소가 달라서 cookie 전달 안됨   // opCookie는 maxAge set을 안했기때문에 browser를 닫으면 cookie 사라진다.
			response.addCookie(valueCookie);
			response.addCookie(opCookie);
			
			response.sendRedirect("calc2.html");				//전에는 값을 입력하고 뒤로가기를 눌러 다시 계산 했지만 redirect하면 값을입력후 바로 같은 페이지를 돌려주어 바로 다른 값 입력 가능
		}
		
		
		
		
		
		
	
	}
}


/*
 * annotation
 * 	==>  class나 method에 붙여지는 주석
 * 		주석은 번역과정 또는 compile과정에서 사라지는 설명문인데 annotation은 compiler에 의해서 사라지지 않을 수 있다.
 * 		(옵션에 따라 사라지게 할 수도 있다.)
 * 		객체라는 것을 사용할 때 객체에 묻어있는 주석(annontation) 정보를 꺼내어서 그것을 활용할 수 있는 meta-data라고도 할 수 있다.
 * 		method나 class를 사용 할 때 붙여진 annotation 이용하여 실행하는데 영향을 줄 수 있다.(mapping정보를 남길 수 있다.)
 * 		annotation사용하면 맵핑할 필요 없음(web.xml가서)
 */
