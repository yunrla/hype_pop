package org.hype.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.hype.domain.psReplyVO;

public interface PopUpReplyMapper {
     public Integer insertPopUpReply(psReplyVO vo);

	public List<psReplyVO> getUserReviews(Map<String, Integer> params);

	public int countUserReviews(Map<String, Integer> params);

	public String getUserIdByUserNo(int userNo);

	public int deleteReviewById(int reviewId);

	public Integer updateReply(psReplyVO vo);

	public List<psReplyVO> getOtherReviews(Map<String, Integer> params);

	public int getTotalReviews(Map<String, Integer> params);
	
	
	// �쑄�뵪 異붽� �맂 遺�遺�
	
	
	// �쑀���쓽 �뙎湲��쓣 媛��졇�삤�뒗 硫붿꽌�뱶
    //public List<psReplyVO> getMyPopupReviews(int userNo);
		
	// psNo濡� psName�쓣 媛��졇�삤�뒗 硫붿꽌�뱶
	public String getPsName(int psNo);
	
	public List<psReplyVO> getPopupRepliesWithPaging(@Param("userNo")int userNo,  @Param("startRow") int startRow, @Param("endRow") int endRow);
	
	public int getTotalPopupReplyCount(@Param("userNo") int userNo);
}
