package com.newlecture.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.entity.NoticeView;

public class NoticeService {
	
	public int removeNoticeAll(int[] id){		//일괄 삭제 --> 몇개 삭제 --> 정수로 반환
		
		return 0;
	}
	
	public int pubNoticeAll(int[] id){
		
		return 0;
	}
	
	public int insertNotice(Notice notice){
		
		return 0;
	}
	
	public int deleteNotice(int id){
		
		return 0;
	}
	
	public int updateNotice(Notice notice){
		
		return 0;
	}
	
	List<Notice> getNoticeNewestList(){
		
		return null;
	}
	
	
	public List<NoticeView> getNoticeList(){				// method 이름이 같으면(똑같은 기능 담당) 인자 많은걸로 하나로 통합, 재사용하는 방식으로(return이용해서)
		
		return getNoticeList("title", "", 1);			//기본값 1 (1page에 나오는 Notice list)
	}
	
	public List<NoticeView> getNoticeList(int page){
		
		return getNoticeList("title", "", page);
	}
	

	public List<NoticeView> getNoticeList(String field/*title, writer_id*/, String query/*A*/, int page){
		
		List<NoticeView> list = new ArrayList<>();
		
		System.out.println("NoticeService.java getNoticeList : " + list);
		
		String sql = "SELECT n1.* FROM "+
					" (SELECT @ROWNUM:=@ROWNUM+1 AS rownum, n.* FROM notice_view AS n, "+
					" (SELECT @ROWNUM:=0) AS r WHERE "+field+" LIKE ? ORDER BY regdate DESC) AS n1 "+ 
					" WHERE rownum >= ? AND rownum <= ?"; 
		
		

				// 1, 11, 21, 31 (10개씩 paging) -> an = a1+(n-1)*d = 1+(page-1)*10
				// 10, 20, 30, 40 -> page*10
		System.out.println("NoticeService.java getNoticeList :"+ sql);
		System.out.println(field);
		
		String url =  "jdbc:mysql://localhost:3306/newlec_study?useUnicode=true&characterEncoding=utf8";

		try {
			Class.forName( "com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "root", "1234");
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setString(1, "%"+query+"%");
			st.setInt(2, 1+(page-1)*10);
			st.setInt(3, page*10);
			
			ResultSet rs = st.executeQuery();
			    
			while(rs.next()){ 
				int id = rs.getInt("id");
				String title = rs.getString("title"); 				/* 이것들이 Model이 된다. */ 
				String writerId = rs.getString("writer_id");
				Date regdate = rs.getTimestamp("regdate") ;			//getDate로 혼동하지 말기 
				int hit = rs.getInt("hit");
				String files = rs.getString("files");
				//String content = rs.getString("content");
				int cmtCount = rs.getInt("cmt_count");
										 

					NoticeView notice = new NoticeView(
							id,
							title,
							writerId,
							regdate,
							hit,
							files,
							//content,
							cmtCount
					);
				list.add(notice);
			}
			
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return list;
	}
	
	public int getNoticeCount() {
		
		return getNoticeCount("title", "");
	}
	
	public int getNoticeCount(String field, String query) {
	
		int count = 0;
		
		String sql = "SELECT COUNT('id') AS count FROM "+
				" (SELECT @ROWNUM:=@ROWNUM+1 AS rownum, n.* FROM notice AS n, "+
				" (SELECT @ROWNUM:=0) AS r WHERE "+field+" LIKE ? ORDER BY regdate DESC) AS n1 "; 
		
		System.out.println("NoticeService.java getNoticeCount :"+ sql);
		System.out.println(field);
		
		String url =  "jdbc:mysql://localhost:3306/newlec_study?useUnicode=true&characterEncoding=utf8";
		
		try {
			Class.forName( "com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "root", "1234");
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setString(1, "%"+query+"%");
						
			ResultSet rs = st.executeQuery();
			
			if(rs.next())
				count = rs.getInt("count");
			
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}	
	
	public Notice getNotice(int id) {
		
		Notice notice = null;
		
		String sql = "SELECT * FROM notice WHERE id=?";
		
		System.out.println("NoticeService.java getNotice :"+ sql);
		
		String url =  "jdbc:mysql://localhost:3306/newlec_study?useUnicode=true&characterEncoding=utf8";

		try {
			Class.forName( "com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "root", "1234");
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1, id);
			
			ResultSet rs = st.executeQuery();
			    
			if(rs.next()){ 
				int nid = rs.getInt("id");
				String title = rs.getString("title"); 				/* 이것들이 Model이 된다. */ 
				String writerId = rs.getString("writer_id");
				Date regdate = rs.getTimestamp("regdate") ;			//getDate로 혼동하지 말기 
				int hit = rs.getInt("hit");
				String files = rs.getString("files");
				String content = rs.getString("content");
										 

				notice = new Notice(
					nid,
					title,
					writerId,
					regdate,
					hit,
					files,
					content
				);
				
			}
			
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return notice;
	}
	
	public Notice getNextNotice(int id) {		//id -> 현재 notice의 id 이면서 이 녀석의 다음 notice를 얻기위함(다음 게시글에 대한 id를 알아야 한다.) 다음 notice를 가져올려면 현재보다 큰놈이어야 하는데 그 기준이 되는것이 regdate!!
		
		Notice notice = null;
		
		String sql = "SELECT * FROM notice"+
				" WHERE id in(SELECT id FROM notice WHERE id > ? ORDER BY regdate DESC) "+ 
				" LIMIT 1";
		
		System.out.println("NoticeService.java getNextNotice :"+ sql);
		
		String url =  "jdbc:mysql://localhost:3306/newlec_study?useUnicode=true&characterEncoding=utf8";

		try {
			Class.forName( "com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "root", "1234");
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1, id);
			
			ResultSet rs = st.executeQuery();
			    
			if(rs.next()){ 
				int nid = rs.getInt("id");
				String title = rs.getString("title"); 				/* 이것들이 Model이 된다. */ 
				String writerId = rs.getString("writer_id");
				Date regdate = rs.getTimestamp("regdate") ;			//getDate로 혼동하지 말기 
				int hit = rs.getInt("hit");
				String files = rs.getString("files");
				String content = rs.getString("content");
										 

				notice = new Notice(
					nid,
					title,
					writerId,
					regdate,
					hit,
					files,
					content
				);
				
			}
			
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return notice;
	}
	
	public Notice getPrevNotice(int id) {
		
		Notice notice = null;
		
		String sql = "SELECT * FROM "+
				" (SELECT * FROM notice ORDER BY regdate DESC) n "+
				" WHERE id in "+
				" (SELECT id FROM notice WHERE id < ? ORDER BY regdate DESC)"+
				" LIMIT 1";
		
		System.out.println("NoticeService.java getPrevNotice :"+ sql);
		
		String url =  "jdbc:mysql://localhost:3306/newlec_study?useUnicode=true&characterEncoding=utf8";

		try {
			Class.forName( "com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "root", "1234");
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1, id);
			
			ResultSet rs = st.executeQuery();
			    
			if(rs.next()){ 
				int nid = rs.getInt("id");
				String title = rs.getString("title"); 				/* 이것들이 Model이 된다. */ 
				String writerId = rs.getString("writer_id");
				Date regdate = rs.getTimestamp("regdate") ;			//getDate로 혼동하지 말기 
				int hit = rs.getInt("hit");
				String files = rs.getString("files");
				String content = rs.getString("content");
										 

				notice = new Notice(
					nid,
					title,
					writerId,
					regdate,
					hit,
					files,
					content
				);
				
			}
			
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return notice;
	}
}
