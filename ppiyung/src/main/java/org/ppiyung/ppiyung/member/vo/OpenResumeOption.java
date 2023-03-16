package org.ppiyung.ppiyung.member.vo;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class OpenResumeOption {

	private String companyId;
	private int workAreaId;
	
	/* 현재 페이지 */
	private int pageNum;

	/* 한 페이지 당 보여질 게시물 갯수 */
	private int amount;
	
	// 참고) https://kimvampa.tistory.com/170,  https://kimvampa.tistory.com/173?category=843151

    private int skip; 	// 스킵 할 게시물 수 의미( (pageNum-1) * amount ) 
	// ex) 1page 에서 보여줄 게시물은 가장 최근꺼부터 (1) ~ (10)번쨰 게시글 
	// ex) 2page 에서 보여줄 게시물은  (11)번째 게시물 부터 ~ 20 번째 게시물
    
    
	/* 기본 생성자 -> 기봅 세팅 : pageNum = 1, amount = 10 */
	public OpenResumeOption() {
		this(1, 10);
	}

	/* 생성자 => 원하는 pageNum, 원하는 amount */
	public OpenResumeOption(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.skip = (pageNum-1) * amount;
		this.amount = amount;
	}

	public void setpageNum(int pageNum) {
		this.skip = (pageNum-1) * this.amount;
		this.pageNum = pageNum;
	}
	
	public void setAmount(int amount) {
		this.skip = (this.pageNum-1) * amount;
		this.amount = amount;
	}
}