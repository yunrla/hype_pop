package org.hype.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.hype.domain.gReplyVO;

public interface GReplyMapper {
	public int insertGReply(gReplyVO gVo);
	public List<gReplyVO> getAllReplyList(@Param("gno") int gno,@Param("userNo") int userNo);
	public gReplyVO getMyReply(@Param("gno") int gno,@Param("userNo") int userNo);
	public double getAvgStars(int gno);
	public int chkReplied(@Param("userNo") int userNo, @Param("gno") int gno);
	public int deleteReply(@Param("gno") int gno,@Param("userNo") int userNo);
	public int updateReply(gReplyVO vo);
	public int updateReplyCntPlus(@Param("gno") int gno);
	public int updateReplyCntMinus(@Param("gno") int gno);
	public List<gReplyVO> getAllReplyListWithPaging(@Param("gno") int gno, @Param("userNo") int userNo, @Param("offset") int offset, @Param("size") int size);
	public int getReplyCount(@Param("gno") int gno,@Param("userNo") int userNo);
	
	//�깉濡� 異붽� (�쑄)
	   //public List<gReplyVO> getGreply(int userNo);
	   public String getGname(int gno);
	   public int getTotalUserReplyCount(@Param("userNo") int userNo);
	   public List<gReplyVO> getUserRepliesWithPaging(@Param("userNo") int userNo, @Param("startRow") int startRow, @Param("endRow") int endRow);
	   
}