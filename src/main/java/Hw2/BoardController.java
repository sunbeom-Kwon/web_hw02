package Hw2;

import java.io.IOException; 


import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

@WebServlet(urlPatterns = "/board.nhn")
public class BoardController extends HttpServlet {
   private static final long serialVersionUID = 1L;
   
   private BoardDAO dao;
   private ServletContext ctx;
   

   // 웹 리소스 기본 경로 지정
   private final String START_PAGE = "Hw2/boardList.jsp";
   
   public void init (ServletConfig config) throws ServletException { 
      super.init(config);
      dao = new BoardDAO();
      ctx = getServletContext();
   }

//service()1
   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("utf-8");
      String action = request.getParameter("action");
      dao = new BoardDAO();
      // 자바 리플렉션을 사용해 if(switch) 없이 요청에 따라 구현 메서드이 실행되도록 구성
      
      Method m;
      String view = null;
      
      // action 파라미터 없이 접근한 경우
      if (action == null) {
         action= "listBoard";
      }
      try {
         m = this.getClass().getMethod(action, HttpServletRequest.class);
         view = (String)m.invoke(this, request);
      }catch (NoSuchMethodException e) {
         e.printStackTrace();
         ctx.log(action+"요청 action 없음!!");
         request.setAttribute("error", "action 파라미터가 잘못되었습니다!!");
         view = START_PAGE;
      }catch (Exception e) {
         e.printStackTrace();
      }
      
      // POST 요청에서는 리디렉션 방법으로 이동하도록 분기 
      if(view.startsWith("redirect:/")){
      String rview = view.substring("redirect:/".length()); 
      response.sendRedirect(rview);
      } else {
         RequestDispatcher dispatcher=request.getRequestDispatcher(view);
         dispatcher.forward(request, response);
      }
   }

   //addBoard
   public String addBoard(HttpServletRequest request) { 
      Board n = new Board();
      try {
         // 입력값을 Board 객체로 매핑
         //BeanUtils.populate(n, request.getParameterMap());
         //dao.addBoard(n);
      } catch (Exception e) {
         //e.printStackTrace();
         //ctx.log("방명록 추가 과정에서 문제 발생!!");
         //request.setAttribute("error", "방명록이 정상적으로 등록되지 않았습니다!!"); 
         //return listBoard(request);
      }
      return "Hw2/boardAdd.jsp";
   }
   public String insertBoard(HttpServletRequest request) { 
	      Board n = new Board();
	      try {
	         // 입력값을 Board 객체로 매핑
	         BeanUtils.populate(n, request.getParameterMap());
	         dao.addBoard(n);
	      } catch (Exception e) {
	         e.printStackTrace();
	         ctx.log("방명록 추가 과정에서 문제 발생!!");
	         request.setAttribute("error", "방명록이 정상적으로 등록되지 않았습니다!!"); 
	         return listBoard(request);
	      }
	      return "redirect:/board.nhn?action=listBoard";
	   }
   
   //getAll
   public String listBoard(HttpServletRequest request) {
      List<Board > list;
      try {
         list = dao.getAll();
         request.setAttribute("boardlist", list);
      } catch (Exception e) {
         e.printStackTrace();
         ctx.log("방명록 목록 생성 과정에서 문제발생!!");
         request.setAttribute("error", "방명록 목록이 정상적으로 처리되지 않았습니다!!");
      }
      return "Hw2/boardList.jsp";
      }
   
 //getBoard
   public String getBoard(HttpServletRequest request) { 
      int aid = Integer.parseInt(request.getParameter("aid")); 
      try {
         Board n = dao.getBoard(aid);
         request.setAttribute("board", n);
      } catch (SQLException e) {
         e.printStackTrace();
         ctx.log("방명록을 가져오는 과정에서 문제 발생!!");
         request.setAttribute("error", "방명록을 정상적으로 가져오지 못했습니다!!");
      }
      return "Hw2/boardMod.jsp";
   }
   
   public String modBoard(HttpServletRequest request) { 
	      Board n = new Board();
	      try {
	         BeanUtils.populate(n, request.getParameterMap());
	         dao.modBoard(n);
	      } catch (Exception e) {
	         e.printStackTrace();
	           ctx.log("방명록 수정 과정에서 문제 발생!!");
	           request.setAttribute("error", "방명록이 정상적으로 수정되지 않았습니다!!"); 
	           return listBoard(request);
	      }
	      return "redirect:/board.nhn?action=listBoard";
	   }
   public String deleteBoard(HttpServletRequest request) {
	      int aid = Integer.parseInt(request.getParameter("aid")); 
	      Board n = new Board();
	      try {
	         BeanUtils.populate(n, request.getParameterMap());
	         dao.delBoard(aid);
	      } catch(Exception e) {
	         e.printStackTrace();
	         ctx.log("방명록 삭제 과정에서 문제 발생!!");
	         request.setAttribute("error", "방명록이 정상적으로 삭제되지 않았습니다!!"); 
	         return listBoard(request);
	      }
	      return "redirect:/board.nhn?action=listBoard";
	   }
}