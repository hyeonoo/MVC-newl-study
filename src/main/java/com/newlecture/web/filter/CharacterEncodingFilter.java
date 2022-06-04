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
		
		//FilterChain chain�� �������� �Ѱ��� ���ΰ� �ƴѰ��� ����
		
		System.out.println("before filter");	//��û�� ���� ����
		request.setCharacterEncoding("UTF-8"); 	//�̷��� filter ���� ���� �������ν� ��� ������ ���ڵ� ���Ͱ� ����� ȯ���� ���� �ȴ�.
		
		chain.doFilter(request, response);	//��û�� ���� �帧�� �Ѱ� ���� ����(�Ǵ� ����)�� ����ǰ� �ϴ� �� �� ���� ����� response�� �ٽ� ���ƿ��� 
											// �״����� ����(���� ������ �����ϱ� ���� ����)
		
		System.out.println("after filter");	// �̻��·δ� ������ ���� ������ ��� �ȵ� (FilterChain�� �������°��̱� ������)

	}

}
