package org.hype.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hype.domain.cartVO;
import org.hype.domain.goodsVO;
import org.hype.domain.likeVO;
import org.hype.domain.mCatVO;
import org.hype.domain.pCatVO;
import org.hype.domain.popStoreVO;
import org.hype.domain.psReplyVO;
import org.hype.mapper.AttachMapper;
import org.hype.mapper.PopUpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class PopUpServiceImpl implements PopUpService {

	@Autowired
	public AttachMapper amapper;

	@Autowired
	PopUpMapper mapper;

	public List<popStoreVO> getPopularPopUps() {
		return mapper.getPopularPopUps();
	}

	// 愿��떖�궗�뿉 �뵲瑜� �긽�쐞 �뒪�넗�뼱 議고쉶
	public Map<String, List<popStoreVO>> getTopStoresByInterests(int userno) {
		List<String> interests = mapper.getUserInterests(userno);
		Map<String, List<popStoreVO>> results = new HashMap<>();

		if (interests != null && !interests.isEmpty()) {
			for (String interest : interests) {
				List<popStoreVO> topStores = mapper.getTopStoresByInterest(interest);
				results.put(interest, topStores); // 愿��떖�궗�� 愿��젴�맂 �긽�쐞 �뒪�넗�뼱 紐⑸줉�쓣 Map�뿉 異붽�
			}
		}
		return results;
	}

	@Override
	public popStoreVO getStoreInfoByName(String storeName) {

		popStoreVO result = mapper.getStoreInfoByName(storeName);

		return result;
	}

	@Transactional
	@Override
	public likeVO likeCount(int psNo, int userNo) {
		// 醫뗭븘�슂 紐⑸줉�뿉�꽌 �쁽�옱 �궗�슜�옄�쓽 醫뗭븘�슂 �긽�깭 �솗�씤
		Map<String, Integer> params = new HashMap<>();
		params.put("psNo", psNo);
		params.put("userNo", userNo);

		int likeStatus = mapper.checkLikeStatus(params);

		if (likeStatus == 0) {
			// 醫뗭븘�슂媛� �뾾�뒗 寃쎌슦, �뙘�뾽 �뒪�넗�뼱�쓽 醫뗭븘�슂 �닔 利앷�
			mapper.incrementLikeCount(psNo); // 醫뗭븘�슂 �닔 1 利앷�

			// 醫뗭븘�슂 紐⑸줉�뿉 �깉濡쒖슫 醫뗭븘�슂 湲곕줉 異붽�
			likeVO newLike = new likeVO();
			newLike.setPsNo(psNo);
			newLike.setUserNo(userNo);
			mapper.insertLike(newLike); // �깉濡쒖슫 醫뗭븘�슂 異붽�

			return newLike; // �깉濡쒖슫 醫뗭븘�슂 �젙蹂� 諛섑솚
		} else {
			// 醫뗭븘�슂媛� �씠誘� �엳�뒗 寃쎌슦, �뙘�뾽 �뒪�넗�뼱�쓽 醫뗭븘�슂 �닔 媛먯냼
			mapper.decrementLikeCount(psNo); // 醫뗭븘�슂 �닔 1 媛먯냼

			// 醫뗭븘�슂 紐⑸줉�뿉�꽌 �빐�떦 湲곕줉 �궘�젣
			likeVO likeInfo = new likeVO();
			likeInfo.setPsNo(psNo);
			likeInfo.setUserNo(userNo);
			mapper.deleteLike(likeInfo); // 醫뗭븘�슂 �궘�젣

			return null; // �궘�젣�맂 寃쎌슦 null 諛섑솚
		}
	}

	@Override
	public Integer getLikeCount(int psNo) {
		Integer result = mapper.getLikeCount(psNo);

		return result;
	}

	@Override
	public boolean checkUserLike(int psNo, int userNo) {
		Map<String, Integer> params = new HashMap<>();
		params.put("psNo", psNo);
		params.put("userNo", userNo);

		int likeStatus = mapper.checkLikeStatus(params);

		if (likeStatus == 1) {
			return true;
		} else {
			return false;

		}
	}

	@Override
	public String getStoreNameByPsNo(int referenceNo) {
		String result = mapper.getStoreNameByPsNo(referenceNo);
		return result;
	}

// �빐�떦 �뙘�뾽�뒪�넗�뼱�쓽 援우쫰 �젙蹂대뱾 諛쏆븘�삤湲�
	@Transactional
	@Override
	public List<goodsVO> getGoodsInfoByName(String storeName) {

		int psNo = mapper.getPsNo(storeName);

		System.out.println("psNo: " + psNo);

		List<goodsVO> result = mapper.getGoodsInfoByName(psNo);
		System.out.println("議고쉶�맂 �긽�뭹 �닔: " + result.size());

		for (goodsVO goods : result) {
			System.out.println("�긽�뭹紐�: " + goods.getGname() + ", 媛�寃�: " + goods.getGprice());
		}

		return result;
	}

	@Override
	public List<popStoreVO> popUpSearchByData(String searchData) {
		List<popStoreVO> result = mapper.popUpSearchByData(searchData);

		return result;
	}

	@Override
	public List<Map<String, Object>> getInterestsByPsNo(int psNo) {
		List<Map<String, Object>> result = mapper.getInterestsByPsNo(psNo);

		return result;
	}

	@Override
	public double calculateAverageRating(int psNo) {
		// 荑쇰━ 寃곌낵媛� �씠誘� �룊洹좎쓣 諛섑솚�븯誘�濡�, 諛붾줈 由ы꽩
		return mapper.findRatingsByPsNo(psNo); // 荑쇰━�뿉�꽌 �씠誘� �룊洹좎쓣 怨꾩궛�븿
	}

	@Override
	public List<popStoreVO> getAllPopUpData() {
		List<popStoreVO> result = mapper.showCalendar();
		return result;
	}

	@Override
	public List<popStoreVO> findNearbyStores(double lat, double lng, double radius) {
		Map<String, Object> params = new HashMap<>();
		params.put("lat", lat);
		params.put("lng", lng);
		params.put("radius", radius);

		return mapper.findNearbyStores(params);
	}

// �슂�뀎�씠嫄� 蹂묓빀 遺�遺�
	@Override
	public List<popStoreVO> showCalendar() {
		return mapper.showCalendar();
	}

	@Override
	public List<pCatVO> getCategoryData() {

		return mapper.getCategoryData();
	}

	// 愿��떖�궗 媛��졇�삤湲�
	@Override
	public List<mCatVO> getUserInterest(int userNo) {

		return mapper.getUserInterest(userNo);
	}

	// 醫뗭븘�슂 �븳 �뙘�뾽�뒪�넗�뼱 媛��졇�삤湲�
	@Override
	public List<likeVO> getUserLike(int userNo) {

		return mapper.getUserLike(userNo);
	}
	


}
