//package org.hype.controller;
//
//import java.util.List;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import lombok.extern.log4j.Log4j;
//
//@Log4j
//@RestController
//@RequestMapping("/announcement")
//public class AnnouncementController {
//
//
//
//    // 1. �������� ��� ��ȸ
//    @GetMapping(value = "/list", produces = "application/json")
//    public ResponseEntity<List<AnnouncementVO>> getAnnouncements() {
//        log.info("�������� ��� ��ȸ");
//        List<AnnouncementVO> announcements = announcementService.getAllAnnouncements();
//        return new ResponseEntity<>(announcements, HttpStatus.OK);
//    }
//
//    // 2. �������� ���� ��ȸ
//    @GetMapping(value = "/{id}", produces = "application/json")
//    public ResponseEntity<AnnouncementVO> getAnnouncement(@PathVariable("id") Long id) {
//        log.info("�������� ���� ��ȸ: " + id);
//        AnnouncementVO announcement = announcementService.getAnnouncementById(id);
//        return new ResponseEntity<>(announcement, HttpStatus.OK);
//    }
//
//    // 3. �������� �������� �˻�
//    @GetMapping(value = "/search", produces = "application/json")
//    public ResponseEntity<List<AnnouncementVO>> searchAnnouncements(@RequestParam("title") String title) {
//        log.info("�������� �������� �˻�: " + title);
//        List<AnnouncementVO> announcements = announcementService.searchByTitle(title);
//        return new ResponseEntity<>(announcements, HttpStatus.OK);
//    }
//
//    // 4. ���� �ۼ� �������� �̵�
//    @GetMapping("/inquiry/write")
//    public String writeInquiryPage() {
//        log.info("���� �ۼ� �������� �̵�");
//        return "/inquiry/write"; 
//    }
//
//    // 5. ���� ��� ó��
//    @PostMapping("/inquiry/submit")
//    public ResponseEntity<String> submitInquiry(@RequestBody InquiryVO inquiry) {
//        log.info("���� ��� ó��: " + inquiry);
//        int insertCount = inquiryService.registerInquiry(inquiry);
//        return insertCount == 1 ? 
//            new ResponseEntity<>("success", HttpStatus.OK) :
//            new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    // 6. �� ���� ���� ��ȸ
//    @GetMapping("/inquiry/myTitles/{userNo}")
//    public ResponseEntity<List<String>> getMyInquiryTitles(@PathVariable("userId") Long userId) {
//        log.info("�� ���� ���� ��ȸ: " + userId);
//        List<String> titles = inquiryService.getMyInquiryTitles(userId);
//        return new ResponseEntity<>(titles, HttpStatus.OK);
//    }
//
//    // 7. �� ���� ���� ���� ��ȸ
//    @GetMapping("/inquiry/myAll/{userNo}")
//    public ResponseEntity<List<InquiryVO>> getMyInquiries(@PathVariable("userId") Long userId) {
//        log.info("�� ���� ���� ���� ��ȸ: " + userId);
//        List<InquiryVO> inquiries = inquiryService.getMyInquiries(userId);
//        return new ResponseEntity<>(inquiries, HttpStatus.OK);
//    }
//}
