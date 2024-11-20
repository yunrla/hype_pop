package org.hype.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class psReplyVO {

	 public int psNo; // �뙘�뾽�뒪�넗�뼱 踰덊샇
	 public int userNo; // �쉶�썝踰덊샇
	 public String psComment; // �썑湲곌�
	 public int psScore; // 蹂꾩젏
	 public int psReplyNo; // �뙎湲� 踰덊샇
	 public Date psRegDate; // �뙎湲� �옉�꽦�씪
	 public Date psUpdateDate; // �뙎湲� �닔�젙�씪
	 public String userId;
	 public String psName;  
}
