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
	
		response.setCharacterEncoding("UTF-8");					 // 사용자에게 보내는 코딩 방식을 보내는 것 
		response.setContentType("text/html; charset = UTF-8");	 // 받은 것을 어떻게 해석할 것인가를 대한 정보를 보내기위한 (contentType이 무엇이다)
																 // client에게 전달되어 char가 utf-8로 되어있으니 그걸로 읽어라, 이 문서는 html문서이다.
		
		PrintWriter out = response.getWriter();	//출력
		
		String cnt_ = request.getParameter("cnt");	//_는 임시변수로 나타내기위한
		
		int cnt = 100;		//기본값 100 
		if(cnt_ != null && !cnt_.equals("")) //cnt_ 가 null이 아니거나 빈문자가 아닐경우 정수로 형변환시키기 위한
			cnt = Integer.parseInt(cnt_);
		
		/*int cnt = Integer.parseInt(request.getParameter("cnt"));	 여기서 서버 돌리면 에러남 ==> 왜냐 ?? 쿼리 값을 안심어 주어서
																	 localhost:8080/hi에 ?cnt=3 해주면 출력됨 <전체 요청은 /hi?cnt=3이고 
																	 쿼리 스트링을 이용한 추가적 옵션은 ?cnt=8이 된다.
																	 에러 없이 출력하려면??
																	 무턱대고 Interger.parseInt로 형변환을 사용했기 때문에 nullpointerException발생
																	 
																	 */
		
		
		for(int i=0; i<cnt; i++) {
			out.println((i+1)+" : 안녕 Servlet!!<br /> ");
		}
			//실행을 할려면 servlet이라는 class가 mapping 되어있어야 한다. ==> web.xml에 mapping
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
