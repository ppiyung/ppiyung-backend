package org.ppiyung.ppiyung.board.vo;

import lombok.Getter;
import lombok.Setter;

// 특정 페이지 조회를 위한 Boardpage 클래스
public class BoardPage {
	
	@Getter
	private int page; // 현재 페이지 번호
	@Getter
	private int perPageNum;// 한 페이지당 보여줄 게시글 갯수
	
	public int getPageStart() { //페이지 시작
        return (this.page-1)*perPageNum;
        // 현재 페이지의 게시글 시작 번호 = (현제 페이지번호  -1) * 페이지당 보여줄 게시글 갯수
       
    }
    
	// 최초로 게시판 목록에 들어 왔을 때 기본 설정값.
    public BoardPage() {
		// TODO Auto-generated constructor stub
        this.page = 1; 
        this.perPageNum = 10;
    }
   
    //페이지가 음수가 되지 않도록 설정. 음수가 되면 1페이지를 나타냄.
    public void setPage(int page) {
        if(page <= 0) {
            this.page = 1;
        } else {
            this.page = page;
        }
    }
    
    // 페이지 당 보여줄 게시글 수가 변하지 않게 설정.
    public void setPerPageNum(int pageCount) {
        int cnt = this.perPageNum;
        if(pageCount != cnt) {
            this.perPageNum = cnt;
        } else {
            this.perPageNum = pageCount;
        }
    }
	
}
