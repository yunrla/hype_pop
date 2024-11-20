package org.hype.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hype.domain.Criteria;
import org.hype.domain.psReplyVO;
import org.hype.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.log4j.Log4j;

@Log4j
@RestController
@RequestMapping("/reply")
public class ReplyController {

   @Autowired
    private ReplyService service;
   
   @PostMapping("/insertReply")
   @ResponseBody
   public ResponseEntity<Map<String, String>> insertReply(@RequestBody Map<String, Object> requestData) {
       
       // psNo, psScore, userNo瑜� Integer濡� 吏곸젒 諛쏆븘�삤湲�
       int psNo = (requestData.get("psNo") instanceof Integer) ? (Integer) requestData.get("psNo") : Integer.parseInt((String) requestData.get("psNo"));
       int psScore = (requestData.get("psScore") instanceof Integer) ? (Integer) requestData.get("psScore") : Integer.parseInt((String) requestData.get("psScore"));
       String psComment = (String) requestData.get("psComment");
       int userNo = (requestData.get("userNo") instanceof Integer) ? (Integer) requestData.get("userNo") : Integer.parseInt((String) requestData.get("userNo"));

       System.out.println("psNo: " + psNo);
       System.out.println("rating: " + psScore);
       System.out.println("reviewText: " + psComment);
       System.out.println("userNo: " + userNo);
       
       psReplyVO vo = new psReplyVO();
       
       vo.setPsNo(psNo);
       vo.setPsScore(psScore);
       vo.setPsComment(psComment);
       vo.setUserNo(userNo);
       
       Integer result = service.insertPopUpReply(vo);
     
       // �쓳�떟 �뜲�씠�꽣 �깮�꽦
       Map<String, String> response = new HashMap<>();
       if (result != null && result > 0) {
           response.put("status", "success");
           response.put("message", "�뙎湲��씠 �꽦怨듭쟻�쑝濡� �벑濡앸릺�뿀�뒿�땲�떎.");
       } else {
           response.put("status", "failure");
           response.put("message", "�뙎湲� �벑濡앹뿉 �떎�뙣�븯���뒿�땲�떎.");
       }

       // JSON �쓳�떟 �쟾�넚
       return ResponseEntity.ok()
           .contentType(MediaType.APPLICATION_JSON)
           .body(response);
   }



   @PostMapping("/getUserReviews")
   public ResponseEntity<Map<String, Object>> getUserReviews(@RequestBody Map<String, Integer> request) {
       // �슂泥� 蹂몃Ц�뿉�꽌 psNo�� userNo 異붿텧
       Integer psNo = request.get("psNo");
       Integer userNo = request.get("userNo");
       System.out.println("Received request: " + request);


       // 由щ럭 媛��졇�삤湲�
       List<psReplyVO> reviews = service.getUserReviews(psNo, userNo);

       // �쓳�떟 留� 援ъ꽦
       Map<String, Object> response = new HashMap<>();
       response.put("status", "success");
       response.put("message", reviews.isEmpty() ? "由щ럭媛� �뾾�뒿�땲�떎." : "由щ럭瑜� 遺덈윭�솕�뒿�땲�떎.");
       response.put("reviews", reviews);

       return ResponseEntity.ok()
               .contentType(MediaType.APPLICATION_JSON)
               .body(response);
   }
   @PostMapping("/checkUserReview")
   public ResponseEntity<Map<String, Boolean>> checkUserReview(@RequestBody psReplyVO request) {
       int psNo = request.getPsNo();
       int userNo = request.getUserNo();

       // �쑀��媛� �씠誘� 由щ럭瑜� �옉�꽦�뻽�뒗吏� �솗�씤
       boolean hasReviewed = service.hasUserReviewed(psNo, userNo);

       // �쓳�떟 �뜲�씠�꽣 �깮�꽦
       Map<String, Boolean> response = new HashMap<>();
       response.put("hasReviewed", hasReviewed);

       // JSON �삎�떇�쑝濡� �쓳�떟 諛섑솚
       return ResponseEntity.ok()
               .contentType(MediaType.APPLICATION_JSON)
               .body(response);
   }
   @PostMapping("/deleteReview")
   public ResponseEntity<Map<String, Object>> deleteReview(@RequestBody Map<String, Integer> request) {
       int reviewId = request.get("reviewId"); // reviewId瑜� 吏곸젒 媛��졇�샂
       System.out.println("�뙎湲� 踰덊샇 : " + reviewId);
       boolean isDeleted = service.deleteReview(reviewId); // 由щ럭 �궘�젣 �떆�룄

       // �쓳�떟 �뜲�씠�꽣 �깮�꽦
       Map<String, Object> response = new HashMap<>();
       response.put("isDeleted", isDeleted); // �궘�젣 �꽦怨� �뿬遺�

       // JSON �삎�떇�쑝濡� �쓳�떟 諛섑솚
       return ResponseEntity.ok()
               .contentType(MediaType.APPLICATION_JSON)
               .body(response);
   }
   @PostMapping("/updateReply")
   @ResponseBody
   public ResponseEntity<Map<String, String>> updateReply(@RequestBody psReplyVO vo) {
       // �뵒踰꾧렇 濡쒓퉭
       System.out.println("psNo: " + vo.getPsNo());
       System.out.println("rating: " + vo.getPsScore());
       System.out.println("reviewText: " + vo.getPsComment());
       System.out.println("userNo: " + vo.getUserNo());

       // VO 媛앹껜 �깮�꽦 諛� 媛� �꽕�젙
       psReplyVO rvo = new psReplyVO();
       rvo.setPsNo(vo.getPsNo());
       rvo.setPsScore(vo.getPsScore());
       rvo.setPsComment(vo.getPsComment());
       rvo.setUserNo(vo.getUserNo());

       Integer result = service.updateReply(rvo);

       // �쓳�떟 �뜲�씠�꽣 �깮�꽦
       Map<String, String> response = new HashMap<>();
       if (result != null && result > 0) {
           response.put("status", "success");
           response.put("message", "�뙎湲��씠 �꽦怨듭쟻�쑝濡� �닔�젙�릺�뿀�뒿�땲�떎.");
       } else {
           response.put("status", "failure");
           response.put("message", "�뙎湲� �닔�젙�뿉 �떎�뙣�븯���뒿�땲�떎.");
       }

       // JSON �쓳�떟 �쟾�넚
       return ResponseEntity.ok()
           .contentType(MediaType.APPLICATION_JSON)
           .body(response);
   }
   @PostMapping("/getOtherReviews")
   public ResponseEntity<Map<String, Object>> getOtherReviews(@RequestBody Map<String, Object> request) {
       // �슂泥� 蹂몃Ц�뿉�꽌 psNo�� userNo 異붿텧
       Integer psNo = Integer.parseInt(String.valueOf(request.get("psNo"))); // String�뿉�꽌 Integer濡� 蹂��솚
       Integer userNo = Integer.parseInt(String.valueOf(request.get("userNo"))); // String�뿉�꽌 Integer濡� 蹂��솚
       Integer pageNum = Integer.parseInt(String.valueOf(request.get("pageNum"))); // �럹�씠吏� 踰덊샇
       Integer amount = Integer.parseInt(String.valueOf(request.get("amount"))); // �빆紐� �닔

       System.out.println("Received request: " + request);

       // Criteria 媛앹껜 �깮�꽦 諛� �꽕�젙
       Criteria cri = new Criteria();
       if (pageNum <= 0) {
           cri.setPageNum(1); // 湲곕낯 �럹�씠吏� 踰덊샇 �꽕�젙
       } else {
           cri.setPageNum(pageNum);
       }

       if (amount <= 0) {
           cri.setAmount(10); // 湲곕낯 �빆紐� �닔 �꽕�젙
       } else {
           cri.setAmount(amount);
       }

       // 由щ럭 媛��졇�삤湲�
       List<psReplyVO> reviews = service.getOtherReviews(psNo, userNo, cri);

       // �쓳�떟 留� 援ъ꽦
       Map<String, Object> response = new HashMap<>();
       response.put("status", "success");
       response.put("message", reviews.isEmpty() ? "由щ럭媛� �뾾�뒿�땲�떎." : "由щ럭瑜� 遺덈윭�솕�뒿�땲�떎.");
       response.put("reviews", reviews);
       response.put("totalReviews", service.getTotalReviews(psNo, userNo)); // �쟾泥� 由щ럭 �닔 異붽�

       return ResponseEntity.ok()
               .contentType(MediaType.APPLICATION_JSON)
               .body(response);
   }
   //(마이페이지에서 댓글 가져오기, 윤)

   @GetMapping(value ="/getMyPopupReply", produces = MediaType.APPLICATION_JSON_VALUE)
   @ResponseBody
   public ResponseEntity<Map<String, Object>> getMyPopupReply(
           @RequestParam int userNo, // 사용자 번호
           @RequestParam(defaultValue = "1") int pageNum, // 페이지 번호 (기본값 1)
           @RequestParam(defaultValue = "5") int amount) { // 페이지당 항목 수 (기본값 5)

      

       // 페이징 처리된 댓글 목록 가져오기
       List<psReplyVO> replies = service.getPopupRepliesWithPaging(userNo, pageNum, amount);

       if (pageNum <= 0) pageNum = 1;
       if (amount <= 0) amount = 5;
       
       // 전체 댓글 수 조회
       int totalCount = service.getTotalPopupReplyCount(userNo);

       if (replies == null || replies.isEmpty()) {
           return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 댓글이 없으면 204 응답
       }

       // 댓글마다 팝업스토어 이름(psName)을 가져오기
       for (psReplyVO reply : replies) {
           String psName = service.getPsName(reply.getPsNo()); // psNo로 psName 조회
           reply.setPsName(psName); // psName 설정
           log.info("psName" + psName);
       }
       
      

       // 응답 객체 구성
       Map<String, Object> response = new HashMap<>();
       response.put("replies", replies); // 댓글 목록
       response.put("totalCount", totalCount); // 전체 댓글 수
       log.info("total:" + totalCount);

       return new ResponseEntity<>(response, HttpStatus.OK); // 댓글과 전체 댓글 수 포함한 응답
   }
}
