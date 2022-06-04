package com.newlecture.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public class CharacterEncodingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//FilterChain chain이 다음으로 넘겨줄 것인가 아닌가를 결정
		
		System.out.println("before filter");	//요청이 오면 실행
		request.setCharacterEncoding("UTF-8"); 	//이렇게 filter 전에 설정 해줌으로써 모든 서블릿은 인코딩 필터가 적용된 환경을 갖게 된다.
		
		chain.doFilter(request, response);	//요청이 오면 흐름을 넘겨 다음 필터(또는 서블릿)를 실행되게 하는 것 그 후의 결과가 response로 다시 돌아오면 
											// 그다음이 실행(다음 실행을 관할하기 위한 역할)
		
		System.out.println("after filter");	// 이상태로는 에러는 나지 않지만 출력 안됨 (FilterChain이 결졍짓는것이기 때문에)

	}

}
