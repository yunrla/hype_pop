package org.hype.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class cartVO {
	   public int gno; // �긽�뭹 踰덊샇
	   public int userNo; // �쉶�썝 踰덊샇
	   public int camount; // 媛쒖닔
	   public int gprice; // �긽�뭹 媛�寃�
	   public String gname; // �긽�뭹紐�
	   public List<gImgVO> gimg; // �긽�뭹 �궗吏� 
	   public int cprice; // 珥앷�寃�
}
