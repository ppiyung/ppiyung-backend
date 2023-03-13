-- 삐융 베이스 SQL 스크립트
-- Version: v0.4.1
-- Author: @initbyran, @silver-hee, @schdevv, @0tak2, @gkswotjd45
--
-- 변경 사항
-- v0.4.1 member_tb의 샘플 데이터의 member_pw 값을 해싱된 값으로 교체
-- v0.4 community_like_tb, community_reply_tb의 article_id(FK)에 대한 제약조건 추가
--      샘플 데이터 일부 추가
-- v0.3 member_tb.member_verified 속성 추가
-- v0.2 member_tb.member_gender의 타입을 CHAR(1)로 수정
--        member_tb.member_type의 타입을 CHAR(1)로 수정
--        resume_tb.resume_open 속성 추가

-- 데이터베이스 생성
DROP DATABASE IF EXISTS ppiyung;
CREATE DATABASE ppiyung;
USE ppiyung;

-- 테이블 생성 
-- 직무분야
CREATE TABLE `work_area_tb` (
    `work_area_id`         int            NOT NULL AUTO_INCREMENT  PRIMARY KEY,
    `work_area_name`       varchar(32)    NOT NULL,
    `work_area_comment`    varchar(256)   NOT NULL
);
insert into work_area_tb(work_area_name, work_area_comment) values ('프론트엔드','프론트엔드입니다');
insert into work_area_tb(work_area_name, work_area_comment) values ('웹개발', '개발개발');
select * from work_area_tb;


-- 회원
CREATE TABLE `member_tb` (
    `member_id`          varchar(20)             NOT NULL  PRIMARY KEY ,
    `member_pw`          varchar(128)            NOT NULL,
    `member_name`        varchar(20)             NOT NULL,
    `member_birth`       date                    NOT NULL,
    `member_gender`      char(1)                 NULL    COMMENT '기업회원은  NULL',
    `member_phone`       varchar(20)             NOT NULL,
    `member_addr`        varchar(256)            NULL,
    `member_coord_x`     float                   NULL    COMMENT '위도 (일반회원은 NULL)',
    `member_coord_y`     float                   NULL    COMMENT '경도  (일반회원은 NULL)',
    `member_nickname`    varchar(20)             NOT NULL,
    `member_email`       varchar(128)            NOT NULL,
    `member_type`        char(1)                 NOT NULL    COMMENT '일반회원, 기업회원, 관리자',
    `member_reg_num`     varchar(32)             NULL    COMMENT '일반회원은 NULL',
    `member_info`        varchar(512)            NULL,
    `member_active`      boolean                 NULL    COMMENT 'true면 활성 회원. false면 탈퇴',
    `member_created_at`  datetime   DEFAULT now()  NULL,
    `work_area_id`       int                     NOT NULL,
    `member_img`         varchar(128)            NULL,
    `member_verified`      boolean                 NULL,
    FOREIGN KEY (work_area_id) REFERENCES work_area_tb (work_area_id)
);
INSERT INTO `member_tb` VALUES ('admin','$2y$04$G92ppy9s0BVNuuqbLjo.k.4M.EiVMOId0Dm2hYUJgJe13a.pa0lzS','관리자','2000-01-01',NULL,'010-1234-5678',NULL,NULL,NULL,'관리자','admin@gmail.com','A',NULL,NULL,1,NULL,1,NULL,NULL),
        ('carrot','$2y$04$G92ppy9s0BVNuuqbLjo.k.4M.EiVMOId0Dm2hYUJgJe13a.pa0lzS','당근마켓','2000-01-01',NULL,'010-1234-5678','서울',NULL,NULL,'당근당근','carrot@gmail.com','C','000-00-00000','바니바니당근당근',1,NULL,1,NULL,1),
        ('gang','$2y$04$G92ppy9s0BVNuuqbLjo.k.4M.EiVMOId0Dm2hYUJgJe13a.pa0lzS','강감찬','2000-01-01','M','010-1234-5678','인천',NULL,NULL,'강강강','gang@gmail.com','N',NULL,'강감찬입니다~~',1,NULL,1,NULL,NULL),
        ('hello','$2y$04$G92ppy9s0BVNuuqbLjo.k.4M.EiVMOId0Dm2hYUJgJe13a.pa0lzS','헬로마켓','2000-01-01',NULL,'010-1234-5678','경기',NULL,NULL,'헬로헬로','hello@gmail.com','C','000-00-00000','바니바니당근당근',1,NULL,1,NULL,1),
        ('hong','$2y$04$G92ppy9s0BVNuuqbLjo.k.4M.EiVMOId0Dm2hYUJgJe13a.pa0lzS','홍길동','2000-01-01','M','010-1234-5678','서울',NULL,NULL,'홍홍홍','hong@gmail.com','N',NULL,'홍길동입니당~잘부탁드려요홍홍홍',1,NULL,1,NULL,NULL),
        ('Lee','$2y$04$G92ppy9s0BVNuuqbLjo.k.4M.EiVMOId0Dm2hYUJgJe13a.pa0lzS','이순신','2000-01-01','M','010-1234-5678','인천',NULL,NULL,'강강강','Lee@gmail.com','N',NULL,'이순신입니다~~',1,NULL,1,NULL,NULL),
        ('shin','$2y$04$G92ppy9s0BVNuuqbLjo.k.4M.EiVMOId0Dm2hYUJgJe13a.pa0lzS','신사임당','2000-01-01','F','010-1234-5678','경기',NULL,NULL,'신신신','shin@gmail.com','N',NULL,'신사입니당~잘부탁드려요홍홍홍',1,NULL,1,NULL,NULL);
select * from member_tb;

 
-- 채용공고 
CREATE TABLE `recruit_tb` (
    `recruit_id`          int                         NOT NULL  AUTO_INCREMENT PRIMARY KEY,
    `company_id`          varchar(20)                 NOT NULL,
    `recruit_title`       varchar(36)                 NOT NULL,
    `recruit_detail`      varchar(1024)               NULL,
    `work_area_id`        int                         NOT NULL,
    `recruit_start_at`    datetime  DEFAULT now()     NOT NULL,
    `recruit_end_at`      datetime  DEFAULT now()     NOT NULL,
    FOREIGN KEY (company_id) REFERENCES member_tb (member_id),
    FOREIGN KEY (work_area_id) REFERENCES work_area_tb (work_area_id)
);
insert into recruit_tb values (1,'carrot','개발할 사람~','개발하쟝','1','2023-03-01 00:00:00','2023-03-15 00:00:00');
select * from recruit_tb;

-- 채용공고 북마크 
CREATE TABLE `bookmark` (
    `recruit_id`           int                       NOT NULL,
    `member_id`            varchar(20)               NOT NULL,
    `bookmark_created_at`  datetime  DEFAULT now()   NOT NULL,
    PRIMARY KEY (recruit_id, member_id),
    FOREIGN KEY (recruit_id) REFERENCES recruit_tb (recruit_id),
    FOREIGN KEY (member_id) REFERENCES member_tb (member_id)
);
insert into bookmark values (1, 'hong', '2023-03-08 00:00:00');
select * from bookmark;

-- 지원이력 
CREATE TABLE `apply_tb` (
    `apply_id`            int                          NOT NULL  AUTO_INCREMENT PRIMARY KEY,
    `apply_result`        boolean                      NULL    COMMENT '합격 true,  불합격  fasle , 미정 null',
    `member_id`           varchar(20)                  NOT NULL,
    `recruit_id`          int                          NOT NULL,
    `apply_created_at`    datetime  DEFAULT now()      NOT NULL,
    FOREIGN KEY (member_id) REFERENCES member_tb (member_id),
    FOREIGN KEY (recruit_id) REFERENCES recruit_tb (recruit_id)
);
insert into apply_tb values (1, null, 'hong', 1, '2023-03-08 01:00:00');
select * from apply_tb;

-- 이력서
CREATE TABLE `resume_tb` (
    `member_id`          varchar(20)                  NOT NULL,
    `resume_location`    varchar(128)                 NOT NULL,
    `resume_filename`    varchar(128)                 NOT NULL,
    `resume_filetype`    varchar(10)                  NOT NULL,
    `resume_updated_at`  datetime  DEFAULT now()      NOT NULL,
	`resume_open`        boolean                      NOT NULL,
    PRIMARY KEY (member_id),
    FOREIGN KEY (member_id) REFERENCES member_tb (member_id)
);
insert into resume_tb values ('hong', 'where', '이력서', 'pdf', '2023-03-05 00:00:00', false);
select * from resume_tb;

-- 입사제안
CREATE TABLE `suggest_tb` (
    `suggest_id`            int                        NOT NULL AUTO_INCREMENT  PRIMARY KEY,
    `suggest_created_at`    datetime  DEFAULT now()    NOT NULL,
    `company_id`            varchar(20)                NOT NULL,
    `member_id`             varchar(20)                NOT NULL,
    FOREIGN KEY (company_id) REFERENCES member_tb (member_id),
    FOREIGN KEY (member_id) REFERENCES member_tb (member_id)
);
insert into suggest_tb values(1, default, 'carrot', 'hong');
select * from suggest_tb;

-- 알림
CREATE TABLE `notification_tb` (
    `notification_id`            int                       NOT NULL  AUTO_INCREMENT PRIMARY KEY,
    `member_id`                  varchar(20)               NOT NULL,
    `suggest_id`                 int                       NOT NULL    COMMENT '둘 중에 NULL이 아닌 것에 대해 처리',
    `apply_id`                   int                       NOT NULL,
    `notification_created_at`    datetime  DEFAULT now()   NOT NULL,
    FOREIGN KEY (member_id) REFERENCES member_tb (member_id),
    FOREIGN KEY (suggest_id) REFERENCES suggest_tb (suggest_id),
    FOREIGN KEY (apply_id) REFERENCES apply_tb (apply_id)
);
insert into notification_tb values(default, 'hong', 1 , 1, default);
select * from notification_tb ;

-- 커뮤니티 게시글
CREATE TABLE `community_article_tb` (
    `article_id`            int                       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `article_title`         varchar(50)               NOT NULL,
    `article_content`       varchar(2048)             NULL,
    `member_id`             varchar(20)               NOT NULL,
    `article_created_at`    datetime  DEFAULT now()   NOT NULL,
    FOREIGN KEY (member_id) REFERENCES member_tb (member_id)
);
INSERT INTO `community_article_tb` VALUES  (default, '게시글 제목', '게시글 내용입니다', 'hong', default),
        (default,'나는 이순신','한산도대첩','lee',default),
        (default,'신사임당','율곡이이 어머니','shin',default),
        (default,'가나다','한산도대첩','lee',default),
        (default,'마바사','율곡이이 어머니','shin',default),
        (default,'아자차','투붐','lee',default),
        (default,'카타파','한산도대첩','lee',default),
        (default,'하하하','율곡이이 어머니','shin',default),
        (default,'도레미','투붐','lee',default),
        (default,'파솔라','투붐','lee',default);
select * from community_article_tb;

-- 커뮤니티 좋아요
CREATE TABLE `community_like_tb` (
    `article_id`         int                       NOT NULL,
    `member_id`          varchar(20)               NOT NULL,
    `like_created_at`    datetime  DEFAULT now()   NOT NULL,
    PRIMARY KEY (article_id, member_id),
    FOREIGN KEY (member_id) REFERENCES member_tb (member_id),
    FOREIGN KEY (article_id) REFERENCES community_article_tb (article_id) ON DELETE CASCADE
);
insert into community_like_tb values (1, 'hong', default);
select * from community_like_tb;

-- 커뮤니티 댓글
CREATE TABLE `community_reply_tb` (
    `reply_id`            int                       NOT NULL AUTO_INCREMENT  PRIMARY KEY,
    `reply_content`       varchar(128)              NULL,
    `article_id`          int                       NOT NULL,
    `member_id`           varchar(20)               NOT NULL,
    `reply_created_at`    datetime  DEFAULT now()   NOT NULL,
    FOREIGN KEY (member_id) REFERENCES member_tb (member_id),
    FOREIGN KEY (article_id) REFERENCES community_article_tb (article_id) ON DELETE CASCADE
);
insert into community_reply_tb values (default, '좋은 게시글이네요' , 1, 'hong', default);
select * from community_reply_tb;


