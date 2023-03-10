package org.ppiyung.ppiyung.recruit.vo;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recruit {

	private int recruit_id;
	private String company_id;
	private String recruit_title;
	private String recruit_detail;
	private int work_area_id;
	private Date recruit_start_at ;
	private Date recruit_end_at;
	
}