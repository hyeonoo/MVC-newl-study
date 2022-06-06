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
	
	public int removeNoticeAll(int[] id){		//�ϰ� ���� --> � ���� --> ������ ��ȯ
		
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
	
	
	public List<NoticeView> getNoticeList(){				// method �̸��� ������(�Ȱ��� ��� ���) ���� �����ɷ� �ϳ��� ����, �����ϴ� �������(return�̿��ؼ�)
		
		return getNoticeList("title", "", 1);			//�⺻�� 1 (1page�� ������ Notice list)
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
		
		

				// 1, 11, 21, 31 (10���� paging) -> an = a1+(n-1)*d = 1+(page-1)*10
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
				String title = rs.getString("title"); 				/* �̰͵��� Model�� �ȴ�. */ 
				String writerId = rs.getString("writer_id");
				Date regdate = rs.getTimestamp("regdate") ;			//getDate�� ȥ������ ���� 
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
				String title = rs.getString("title"); 				/* �̰͵��� Model�� �ȴ�. */ 
				String writerId = rs.getString("writer_id");
				Date regdate = rs.getTimestamp("regdate") ;			//getDate�� ȥ������ ���� 
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
	
	public Notice getNextNotice(int id) {		//id -> ���� notice�� id �̸鼭 �� �༮�� ���� notice�� �������(���� �Խñۿ� ���� id�� �˾ƾ� �Ѵ�.) ���� notice�� �����÷��� ���纸�� ū���̾�� �ϴµ� �� ������ �Ǵ°��� regdate!!
		
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
				String title = rs.getString("title"); 				/* �̰͵��� Model�� �ȴ�. */ 
				String writerId = rs.getString("writer_id");
				Date regdate = rs.getTimestamp("regdate") ;			//getDate�� ȥ������ ���� 
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
				String title = rs.getString("title"); 				/* �̰͵��� Model�� �ȴ�. */ 
				String writerId = rs.getString("writer_id");
				Date regdate = rs.getTimestamp("regdate") ;			//getDate�� ȥ������ ���� 
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
