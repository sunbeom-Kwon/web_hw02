package Hw2;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
	final String JDBC_DRIVER = "org.h2.Driver";
	final String JDBC_URL = "jdbc:h2:tcp://localhost/~/jwbookdb";
	
	// DB 연결을 가져오는 메서드 
	public Connection open() { 
		Connection conn = null; 
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, "jwbook","1234");
		}catch (Exception e) { e.printStackTrace(); }
		return conn;
	}

// 뉴스 기사 목록 전체를 가져오는 메서드
	public List<Board> getAll() throws Exception {
	      Connection conn = open();
	      List<Board> boardList = new ArrayList<>();
	      
	      String sql = "select aid, title, date as cdate, writer, email from board";
	      PreparedStatement pstmt = conn.prepareStatement(sql);
	      ResultSet rs = pstmt.executeQuery();
	      
	      try (conn; pstmt; rs) {
	         while(rs.next()) {
	            Board n = new Board();
	            n.setAid(rs.getInt("aid"));
	            n.setTitle(rs.getString("title"));
	            n.setDate(rs.getString("cdate"));
	            n.setEmail(rs.getString("email"));
	            n.setWriter(rs.getString("writer"));
	            
	            boardList.add(n);
	            }
	         return boardList;
	      }
	   }

	//뉴스 한 개 클릭시 세부내용 보여주는 메서드
	   public Board getBoard(int aid) throws SQLException {
	      Connection conn = open();
	      Board n = new Board();
	      String sql = "select aid, title, email, writer, password, content from Board where aid=?";
	      PreparedStatement pstmt = conn.prepareStatement(sql);
	      pstmt.setInt(1, aid);
	      ResultSet rs = pstmt.executeQuery();
	      rs.next();
	   
	      try(conn; pstmt; rs) {
	         n.setAid(rs.getInt("aid"));
	         n.setTitle(rs.getString("title"));
	         n.setEmail(rs.getString("email"));
	         n.setContent(rs.getString("content"));
	         n.setWriter(rs.getString("writer"));
	         n.setPassword(rs.getString("password"));
	         pstmt.executeQuery();
	         return n;
	      }
	   }
	
	//뉴스 추가 메서드
	public void addBoard(Board n) throws Exception {
	   Connection conn = open();
	   String sql = "insert into board(title,writer,email,content,password,date) values(?,?,?,?,?,CURRENT_TIMESTAMP())";/*date,CURRENT_TIMESTAMP(),*/
	   PreparedStatement pstmt = conn.prepareStatement(sql);
	   
	   try(conn; pstmt) {
	      pstmt.setString(1,n.getTitle());
	      pstmt.setString(2,n.getWriter());
	      pstmt.setString(3,n.getEmail());
	      pstmt.setString(4,n.getContent());
	      pstmt.setString(5,n.getPassword());
	      pstmt.executeUpdate();
	   }
	}
   public void modBoard(Board n) throws Exception {
	      Connection conn = open();
	      String sql = "UPDATE Board SET title=?, email=?, writer=?, password=?, content=? WHERE aid = ?";
	      PreparedStatement pstmt = conn.prepareStatement(sql);
	      
	      try(conn; pstmt) {
	         pstmt.setString(1,n.getTitle());
	         pstmt.setString(2,n.getEmail());
	         pstmt.setString(3,n.getWriter());
	         pstmt.setString(4,n.getPassword());
	         pstmt.setString(5,n.getContent());
	         pstmt.setInt(6,n.getAid());
	         pstmt.executeUpdate();
	      }
	   }
	
// 뉴스 삭제 메서드
   public void delBoard(int aid) throws SQLException {
	      Connection conn = open();
	      String sql="delete from board where aid=?";
	      PreparedStatement pstmt=conn.prepareStatement(sql);
	      
	      try(conn; pstmt) {
	         pstmt.setInt(1, aid);
	      if (pstmt.executeUpdate() == 0) { 
	         throw new SQLException(sql+"DB에러"+aid);
	         }
	      }
	   }
}
