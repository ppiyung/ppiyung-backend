package org.ppiyung.ppiyung.board.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 페이지에 페이징 번트들을 만들기 위한 계산 클래스
@ToString
public class pageMake {
	@Getter
	@Setter
	private BoardPage page;

	@Getter
	private int totalCount; // 총 게시글 수
	@Getter@Setter
	private int startPage; // 시작 페이지
	@Getter@Setter
	private int endPage; // 마지막 페이지
	@Getter@Setter
	private boolean prev; // 이전 페이지
	@Getter@Setter
	private boolean next; // 다음 페이지
	@Getter@Setter
	private int displayPageNum = 5; // 화면 화 면단에 보여지는 페이지 버튼 수

	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
		// 총 게시글 수를 셋팅할때 calcData() 메서드를 호출하여 페이징 관련 버튼 계산을 함.
	}
	
	// 페이징의 버튼들을 생성하는 계산식을 만듬 => 끝 페이지 번호, 시작 페이지 번호, 이전, 다음 버튼들을 구함.
	private void calcData() {
		//끝 페이지 번호 = (현재 페이지 번호 / 화면에 보여질 페이지 번호의 갯수) * 화면에 보여질 페이지 번호의 갯수
		endPage = (int) (Math.ceil(page.getPage() / (double) displayPageNum) * displayPageNum);

		startPage = (endPage - displayPageNum) + 1;
		if (startPage <= 0)
			startPage = 1;
		
		//마지막 페이지 번호 = 총 게시글 수 / 한 페이지당 보여줄 게시글의 갯수
		int tempEndPage = (int) (Math.ceil(totalCount / (double) page.getPerPageNum()));
		if (endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		
		//이전 버튼 생성 여부 = 시작 페이지 번호 == 1 ? false : true
		prev = startPage == 1 ? false : true;
		
		//다음 버튼 생성 여부 = 끝 페이지 번호 * 한 페이지당 보여줄 게시글의 갯수 < 총 게시글의 수 ? true: false
		next = endPage * page.getPerPageNum() < totalCount ? true : false;

	}

}
