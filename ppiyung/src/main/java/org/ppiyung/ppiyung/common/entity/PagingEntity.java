package org.ppiyung.ppiyung.common.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 페이지에 페이징 번트들을 만들기 위한 계산 클래스
@Data
@Getter
@Setter
@ToString
public class PagingEntity {
	
	/* 현재 페이지 */
	private int pageNum;

	/* 한 페이지 당 보여질 게시물 갯수 */
	private int amount;
	
	// 참고) https://kimvampa.tistory.com/170,  https://kimvampa.tistory.com/173?category=843151

    private int skip; 	// 스킵 할 게시물 수 의미( (pageNum-1) * amount ) 
	// ex) 1page 에서 보여줄 게시물은 가장 최근꺼부터 (1) ~ (10)번쨰 게시글 
	// ex) 2page 에서 보여줄 게시물은  (11)번째 게시물 부터 ~ 20 번째 게시물
    
    
	/* 기본 생성자 -> 기봅 세팅 : pageNum = 1, amount = 10 */
	public PagingEntity() {
		this(1, 10);
	}

	/* 생성자 => 원하는 pageNum, 원하는 amount */
	public PagingEntity(int pageNum, int amount) {
		this.pageNum = pageNum;
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
