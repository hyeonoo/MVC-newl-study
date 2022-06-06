package com.newlecture.web;

import java.io.IOException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/calc3")
public class Calc3 extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cookie[] cookies = request.getCookies();
		
		// 사용자가 값과 + or - 누르면 이쪽(operator)으로 전달, 셋중 하나만 전달됨
		String value = request.getParameter("value");
		String operator = request.getParameter("operator");
		String dot = request.getParameter("dot");
		
		//exp 만들어내기위해 읽어오기
		String exp = "";

		if(cookies!= null)
		for(Cookie c : cookies) 					//cookie 찾는 과정
			if(c.getName().equals("exp")) {
				exp = c.getValue();
				break;
			}
		
		//계산된 결과 남기기(=)
		if(operator != null && operator.equals("=")) {
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");	//계산  ===> 지원되지 않아서 이제 error 남
			try {
				exp = String.valueOf(engine.eval(exp));
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// "C" --> cookie 삭제!!
		}else if(operator != null && operator.equals("C")) {
			exp = "";
			
		}else {	
		
		// 하나만 누적 , 나머지 2개는 null로 온다.
			exp += (value == null)?"" : value;
			exp += (operator == null)?"" : operator;
			exp += (dot == null)?"" : dot;
		}
		//사용자가 전달하는 값을 가지고 exp만들어서 cookie에 저장하고 그것을 가지고 redirect
		Cookie expCookie = new Cookie("exp", exp);
		
		if(operator != null && operator.equals("C")) 
			expCookie.setMaxAge(0);	// cookie 바로 삭제
		
		expCookie.setPath("/"); 	//path는 하나만 설정 가능
		response.addCookie(expCookie);
		response.sendRedirect("calcpage");				//전에는 값을 입력하고 뒤로가기를 눌러 다시 계산 했지만 redirect하면 값을입력후 바로 같은 페이지를 돌려주어 바로 다른 값 입력 가능
		
	}
		
		
}
			
			
		
		
		
		
		
		
	
	


