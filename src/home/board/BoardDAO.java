package home.board;
import java.sql.*;
import javax.sql.*;

import javax.naming.*;
import java.util.*;

public class BoardDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private static BoardDAO instance = new BoardDAO();
	
	public static BoardDAO getInstance() {	// singleton
		return instance;
	}
	
	private Connection getConnection() throws Exception{
	      Context initCtx = new InitialContext();
	      Context envCtx = (Context) initCtx.lookup("java:comp/env");
	      DataSource ds = (DataSource)envCtx.lookup("jdbc/orcl");
	      Connection conn =ds.getConnection();
	      return ds.getConnection();		
	}
	
	public void insertArticle(BoardDTO article)	{
		// writeForm에서 보낸 hidden 값 저장
		int num=article.getNum();
		int ref=article.getRef();
		int re_step=article.getRe_step();
		int re_level=article.getRe_level();
		int number=0;
		String sql="";
		
		try {
			conn = getConnection();
			// 게시글 수를 확인한 후 글번호가 자동으로 증가하는 메소드
			pstmt = conn.prepareStatement("select max(num) from board");
			rs = pstmt.executeQuery();
						
			if(rs.next())					// select문의 결과가 있을 경우 true
				number = rs.getInt(1)+1;	 // select한 결과의 값 +1
			else
				number = 1;		// 첫번째 게시글일 경우
			
			if(num != 0) {
				// 답변글일 경우
				// ref 와 re_step의 값이 동일한 컬럼을 검색
				sql ="update homeboard set re_step = re_step+1 where ref = ? and re_step > ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, re_step);
				pstmt.executeUpdate();
				re_step = re_step+1;
				re_level = re_level+1;
			}else {
				// 답변글이 아닐경우
				ref = number;
				re_step = 0;
				re_level = 0;
			}
			
			
			// 게시글 작성 쿼리문
            sql = "insert into homeboard(num, writer,email,subject,pw,reg_date,";
		    sql+="ref,re_step,re_level,content,ip) values(board_seq.nextval,?,?,?,?,?,?,?,?,?,?)";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, article.getWriter());
            pstmt.setString(2, article.getEmail());
            pstmt.setString(3, article.getSubject());
            pstmt.setString(4, article.getPw());
			pstmt.setTimestamp(5, article.getReg_date());
            pstmt.setInt(6, ref);
            pstmt.setInt(7, re_step);
            pstmt.setInt(8, re_level);
			pstmt.setString(9, article.getContent());
			pstmt.setString(10, article.getIp());
			
            pstmt.executeUpdate();
		}catch(Exception ex) {
            ex.printStackTrace();
        } finally {
        	closeAll();
        }
	}
	
	// 테이블에 저장된 모든 게시글의 수를 검색하는 메소드
	public int getArticleCount() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int x=0;
		try {
			conn = getConnection();
			 pstmt = conn.prepareStatement("select count(*) from homeboard");
	         rs = pstmt.executeQuery();
	         
	         if(rs.next()) {
	        	 x = rs.getInt(1);
	         }
		}catch(Exception ex) {
            ex.printStackTrace();
        } finally {
        	closeAll();
        }
		return x;
	}
	
	
	public List getArticles(int start, int end) throws Exception{
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List articleList=null;
        try {
        	conn = getConnection();
            pstmt = conn.prepareStatement(
            "select num,writer,email,subject,pw,reg_date,ref,re_step,re_level,content,ip,readcount,r "+
        	"from (select num,writer,email,subject,pw,reg_date,ref,re_step,re_level,content,ip,readcount,rownum r " +
        	"from (select num,writer,email,subject,pw,reg_date,ref,re_step,re_level,content,ip,readcount " +
        	"from homeboard order by ref desc, re_step asc) order by ref desc, re_step asc ) where r >= ? and r <= ? ");
            pstmt.setInt(1, start);
			pstmt.setInt(2, end);
            rs = pstmt.executeQuery();        	
        if(rs.next()) {
        	articleList = new ArrayList(end);
        	do{
                 BoardDTO article= new BoardDTO();
				 article.setNum(rs.getInt("num"));
				 article.setWriter(rs.getString("writer"));
                 article.setEmail(rs.getString("email"));
                 article.setSubject(rs.getString("subject"));
                 article.setPw(rs.getString("pw"));
			     article.setReg_date(rs.getTimestamp("reg_date"));
				 article.setReadcount(rs.getInt("readcount"));
                 article.setRef(rs.getInt("ref"));
                 article.setRe_step(rs.getInt("re_step"));
				 article.setRe_level(rs.getInt("re_level"));
                 article.setContent(rs.getString("content"));
			     article.setIp(rs.getString("ip")); 
				  
			     articleList.add(article);
        	 	 }while(rs.next());
        	}	
        }catch(Exception ex) {
            ex.printStackTrace();
        } finally {
        	closeAll();
        }
		return articleList;
	}
	
	public BoardDTO getArticle(int num) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        BoardDTO article=null;
		try {
			conn = getConnection();
			
			// 조회수 계산하는 메소드
            pstmt = conn.prepareStatement(
            		"update homeboard set readcount=readcount+1 where num = ?");
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
			// 
            pstmt = conn.prepareStatement(
            		"select * from homeboard where num = ?");
            pstmt.setInt(1, num);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                article = new BoardDTO();
                article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
                article.setEmail(rs.getString("email"));
                article.setSubject(rs.getString("subject"));
                article.setPw(rs.getString("pw"));
			    article.setReg_date(rs.getTimestamp("reg_date"));
				article.setReadcount(rs.getInt("readcount"));
                article.setRef(rs.getInt("ref"));
                article.setRe_step(rs.getInt("re_step"));
				article.setRe_level(rs.getInt("re_level"));
                article.setContent(rs.getString("content"));
			    article.setIp(rs.getString("ip"));
            }
	}catch(Exception ex) {
        ex.printStackTrace();
    }finally {
    	closeAll();
    }
	return article;
}
	

	private void closeAll() {
		if(rs != null) {try {rs.close();}catch(SQLException s) {}}
		if(pstmt != null) {try {pstmt.close();}catch(SQLException s) {}}
		if(conn != null) {try {conn.close();}catch(SQLException s) {}}
	}
}
