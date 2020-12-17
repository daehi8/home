package home.model.service;

import java.util.List;

import home.model.dto.ReplyDTO;

public interface ReplyService {

	// 댓글 리스트
	public List getReply(int boardNo)throws Exception;

	// 댓글 추가
	public int insertReply(ReplyDTO replyDTO)throws Exception;
	
	// 댓글 삭제
	public int deleteReply(int no)throws Exception;
	
	// 댓글 수정
	public int updateReply(ReplyDTO replyDTO)throws Exception;
	
	public int maxNoreply()throws Exception;
}
