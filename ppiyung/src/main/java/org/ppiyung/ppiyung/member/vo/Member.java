package org.ppiyung.ppiyung.member.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	private String member_id;
	private String member_pw;
	private String member_name;
	private Date member_birth;
	private boolean member_gender;
	private String member_phone;
	private String member_addr;
	private double member_coord_x;
	private double member_coord_y;
	private String member_nickname;
	private String member_email;
	private int member_type;
	private String member_reg_num;
	private String member_info;
	private boolean member_active;
	private Date member_created_at;
	private int work_area_id;
	private String member_img;
}
