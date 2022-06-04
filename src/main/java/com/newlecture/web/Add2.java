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
		 * mutibyte 문자 전송문제 해결 2가지
		 * 1. server.xml -> connect부분에 URIEncoding = "UTF-8" 설정
		 * 2. Servlet 문서에 request.setCharacterEncoding("UTF-8"); <추천>
		 */
		
		PrintWriter out = response.getWriter();	//출력
		
		String[] num_ = request.getParameterValues("num");	//+values --> array
		
		int result=0;
		
		for(int i=0; i<num_.length; i++) {
			int num = Integer.parseInt(num_[i]);	//변수로 선언된것은 반복 x
			result+=num;
		}
		
		
		response.getWriter().printf("reuslt is %d\n", result);
		
		
	
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
