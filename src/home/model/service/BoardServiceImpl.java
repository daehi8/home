package home.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import home.model.dto.BoardDTO;
import home.model.dto.ReplyDTO;

@Service("boardDAO")
public class BoardServiceImpl implements BoardService{

	@Autowired
	private SqlSessionTemplate dao = null;	
	
	@Override
	public void insertArticle(BoardDTO article) {
		int no = article.getNo();
		int ref = article.getRef();
		int re_step = article.getRe_step();
		int re_level = article.getRe_level();
		int number = 0;
		
		if (dao.selectOne("board.maxNoArticle") != null) {
			number = dao.selectOne("board.maxNoArticle");
		}
		if(number != 0) {number += 1;}
		else if(number == 0) {number = 1;}
		
		if(no != 0) {
			dao.update("board.refArticle", article);
			re_step = re_step+1;
			re_level = re_level+1;
		}
		else {ref = number; re_step = 0; re_level = 0;}
		article.setRef(ref);
		article.setRe_step(re_step);
		article.setRe_level(re_level);
		
		dao.insert("board.insertArticle", article);		
	}
	
	@Override
	public int deleteCheck(int no) throws Exception {
		int result = dao.selectOne("board.deleteCheck", no);
		
		return result;
	}

	@Override
	public int getArticleCount() throws Exception {
		int result = dao.selectOne("board.getArticleCount");
		return result;
	}

	@Override
	public List getArticles(int start, int end) throws Exception {
		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		return dao.selectList("board.getArticleList", map);
	}

	@Override
	public BoardDTO getArticle(int no) {
		dao.update("board.getReadCount", no);
		return dao.selectOne("board.getArticle", no);
	}

	@Override
	public BoardDTO updateGetArticle(int no) throws Exception {
		dao.update("board.getReadCount", no);
		return dao.selectOne("board.getArticle", no);
	}

	@Override
	public int updateArticle(BoardDTO article, int memNo, int no) throws Exception {
		int result = -1;
		int dbMemNo = dao.selectOne("board.articleCheck", no);
		if(dbMemNo == memNo) {
			dao.update("board.updateArticle", article);
			result = 1;
		}else {
			result = 0;
		}
			return result;
	}

	@Override
	public int deleteArticle(int no, int memberNo) throws Exception {
		int result = -1;
		int dbMemNo = dao.selectOne("board.articleCheck", no);
		if(dbMemNo == memberNo) {
			dao.delete("board.deleteArticle", no);
			dao.delete("board.deleteArticleReply", no);
			result = 1;
		}else {
			result = 0;
		}
			return result;
	}

	@Override
	public int deleteCommentArticle(int no, int memberNo) throws Exception {
		int result = -1;
		int dbMemNo = dao.selectOne("board.articleCheck", no);
		if(dbMemNo == memberNo) {
			dao.update("board.delCommentArticle", no);
			result = 1;
		}else {
			result = 0;
		}
			return result;
	}

	@Override
	public String selectMemId(int memNo) throws Exception {
		return dao.selectOne("board.selectMemId", memNo);
	}

	@Override
	public int selectNoCheck(String id) throws Exception {
		return dao.selectOne("board.selectNoCheck", id);
	}

	@Override
	public List populerArticle() throws Exception {
		return dao.selectList("board.populerArticle");
	}
	
}
