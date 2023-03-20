package org.ppiyung.ppiyung.notify.vo;

import java.util.Date;

import org.ppiyung.ppiyung.recruit.vo.Apply;
import org.ppiyung.ppiyung.recruit.vo.Recruit;
import org.ppiyung.ppiyung.recruit.vo.Suggest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationExtended {
	private int notificationId;
	private String memberId;
	private String memberName;
	private int suggestId;
	private int applyId;
	private Date notificationCreatedAt;

	private Recruit recruit;
	private Apply apply;
	private Suggest suggest;
}
