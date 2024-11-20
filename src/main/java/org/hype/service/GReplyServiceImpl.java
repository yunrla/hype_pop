package org.hype.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.hype.domain.gReplyVO;
import org.hype.domain.psReplyVO;
import org.hype.mapper.GReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;
@Log4j
@Service
public class GReplyServiceImpl implements GReplyService {
	@Autowired
	private GReplyMapper gMapper;
	
	@Override
	public int insertGReply(gReplyVO gVo) {
		gMapper.updateReplyCntPlus(gVo.getGno());
		return gMapper.insertGReply(gVo);
	}
	
	@Override
	public List<gReplyVO> getAllReplyList(@Param("gno") int gno,@Param("userNo") int userNo) {
		return gMapper.getAllReplyList(gno, userNo);
	}
	
	@Override
	public gReplyVO getMyReply(@Param("gno") int gno,@Param("userNo") int userNo) {
		return gMapper.getMyReply(gno, userNo);
	}
	
	@Override
	public double getAvgStars(int gno) {
		return gMapper.getAvgStars(gno);
	}
	
	@Override
	public String chkReplied(int userNo, int gno) {
    	log.warn("bbbbbbbbbbbb" + userNo + gno);
    	int result = gMapper.chkReplied(userNo, gno);
		return String.valueOf(result);
	}
	
	@Override
	public int deleteReply(int gno, int userNo) {
		gMapper.updateReplyCntMinus(gno);
		return gMapper.deleteReply(gno, userNo);
	}
	
	@Override
	public int updateReply(gReplyVO vo) {
		return gMapper.updateReply(vo);
	}
	
	@Override
    public List<gReplyVO> getAllReplyListWithPaging(@Param("gno") int gno, @Param("userNo") int userNo, @Param("offset") int offset, @Param("size") int size) {
        return gMapper.getAllReplyListWithPaging(gno, userNo, offset, size);
    }
	
	@Override
	public int getReplyCount(@Param("gno") int gno,@Param("userNo") int userNo) {
	    return gMapper.getReplyCount(gno, userNo);
	}
   //�깉濡� 異붽�(源��쑄)
//   @Override
//   @Transactional
//   public Map<String, Object> getGreplyReviews(int userNo) {
//    
//      //�궡 �뙎湲� 由ъ뒪�듃
//      List<gReplyVO>greplies = gMapper.getGreply(userNo);
//      // gName ���옣�븷 由ъ뒪�듃
//      List<String> gnames = new ArrayList<>();
//       
//      // 媛� gNo�뿉 ���빐 gName 媛��졇�삤湲�
//       for (gReplyVO greply : greplies) {
//              String gname = gMapper.getGname(greply.getGno()); 
//              gnames.add(gname);
//          }
//      
//      
//          Map<String, Object> result = new HashMap<>();
//          result.put("greplies", greplies);
//          result.put("gnames", gnames);
//          return result; // 寃곌낵瑜� Map �삎�깭濡� 諛섑솚 
//   }
//   
   
   
	@Override
	public String getGName(int gno) {
	    return gMapper.getGname(gno); 
	}
	
	@Override
	public int getTotalUserReplyCount(@Param("userNo") int userNo) {
	    return gMapper.getTotalUserReplyCount(userNo); // 전체 댓글 수 반환
	}
	
	@Override
	public List<gReplyVO> getUserRepliesWithPaging(int userNo, int pageNum, int amount) {
		int startRow = (pageNum - 1) * amount;
		int endRow = pageNum * amount;
	    return gMapper.getUserRepliesWithPaging(userNo,pageNum,amount);
	}
   
}