package org.hype.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class likedExhViewVO {

	
	
	 private int userNo; // ȸ����ȣ
	 private int exhNo; // ����ȸ��ȣ
	 private Date likeDate; // ���ƿ� ��¥
	 private String uuid; // uuid
	 private String uploadPath; // ���ε� ���
	 private String fileName; // ���� �̸�
	 private String exhName;   // ����ȸ �̸�
}
