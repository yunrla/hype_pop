package org.hype.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.hype.domain.Criteria;
import org.hype.domain.psReplyVO;
import org.hype.domain.qnaVO;
import org.hype.mapper.PopUpReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReplyServiceImpl implements ReplyService{
	
	@Autowired
	PopUpReplyMapper mapper;
	
	@Transactional
	@Override
	public Integer insertPopUpReply(psReplyVO vo) {
	    // userNo濡� userId 議고쉶
	    String userId = mapper.getUserIdByUserNo(vo.getUserNo());
	    
	    // 議고쉶�븳 userId瑜� vo�뿉 �꽕�젙
	    vo.setUserId(userId);
	    
	    // 由щ럭 �뜲�씠�꽣 �궫�엯
	    Integer result = mapper.insertPopUpReply(vo);
	    
	    return result;
	}
	@Override
	public List<psReplyVO> getUserReviews(int psNo, int userNo) {
	    Map<String, Integer> params = new HashMap<>();
	    params.put("psNo", psNo);
	    params.put("userNo", userNo);
	    
	    List<psReplyVO> result = mapper.getUserReviews(params);
	    return result;
	}
	@Override
	public boolean hasUserReviewed(int psNo, int userNo) {
	    // psNo�� userNo瑜� 留ㅺ컻蹂��닔濡� �쟾�떖�븯�뿬 由щ럭 媛쒖닔瑜� 議고쉶
	    Map<String, Integer> params = new HashMap<>();
	    params.put("psNo", psNo);
	    params.put("userNo", userNo);
	    
	    int count = mapper.countUserReviews(params);
	    
	    // 由щ럭媛� 議댁옱�븯硫� true, �븘�땲硫� false 諛섑솚
	    return count > 0;
	}
	@Override
	public boolean deleteReview(int reviewId) {
	    // reviewId濡� 由щ럭 �궘�젣
	    int rowsAffected = mapper.deleteReviewById(reviewId); // �궘�젣�맂 �뻾 �닔 諛섑솚
	    return rowsAffected > 0; // �궘�젣 �꽦怨� �뿬遺� 諛섑솚
	}
	@Override
	public Integer updateReply(psReplyVO vo) {
		  
	    // �뙎湲� �닔�젙
	    Integer result = mapper.updateReply(vo);
	    
	    return result;
	}
	@Override
	public List<psReplyVO> getOtherReviews(Integer psNo, Integer userNo, Criteria cri) {
	    Map<String, Integer> params = new HashMap<>();
	    params.put("psNo", psNo);
	    params.put("userNo", userNo);
	    params.put("pageNum", cri.getPageNum());  // �쁽�옱 �럹�씠吏� 踰덊샇 異붽�
	    params.put("amount", cri.getAmount());    // �럹�씠吏��떦 �빆紐� �닔 異붽�

	    // Mapper �샇異� �떆 params 留듭쓣 �궗�슜
	    List<psReplyVO> result = mapper.getOtherReviews(params);
	    return result;
	}
	
	@Override
	public int getTotalReviews(Integer psNo, Integer userNo) {
	    Map<String, Integer> params = new HashMap<>();
	    params.put("psNo", psNo);
	    params.put("userNo", userNo);

	    // Mapper �샇異� �떆 params 留듭쓣 �궗�슜
	    return mapper.getTotalReviews(params);
	}

	@Override
	public String getPsName(int psNo) {
	    return mapper.getPsName(psNo); // 이름
	}
	
	
	
	@Override
	public int getTotalPopupReplyCount(@Param("userNo") int userNo) {
	    return mapper.getTotalPopupReplyCount(userNo); // 전체 댓글 수 반환
	}
	
	@Override
	public List<psReplyVO> getPopupRepliesWithPaging(int userNo, int pageNum, int amount) {
		int startRow = (pageNum - 1) * amount;
		int endRow = pageNum * amount;
	    return mapper.getPopupRepliesWithPaging(userNo,startRow,endRow);
	}
		

		 
}
