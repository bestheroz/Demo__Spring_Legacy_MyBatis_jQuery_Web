CREATE TABLE SAMPLE_MEMBER_MST
             (
                          MEMBER_ID      VARCHAR (100) PRIMARY KEY NOT NULL,
                          MEMBER_PW      CHAR (128) NOT NULL,
                          MEMBER_NM      VARCHAR (100) NOT NULL,
                          MEMBER_TYP     VARCHAR (100) NOT NULL,
                          LOGIN_FAIL_CNT INT (1) DEFAULT 0 NOT NULL,
                          CLOSE_YN       CHAR (1) NOT NULL,
                          EXPIRE_DT      DATETIME NOT NULL,
                          REG_MEMBER_ID  VARCHAR (100) NOT NULL,
                          REG_DT         DATETIME NOT NULL,
                          UPD_MEMBER_ID VARCHAR (100) NOT NULL,
                          UPD_DT        DATETIME NOT NULL
             );

INSERT
INTO   SAMPLE_MEMBER_MST
       (
              MEMBER_ID,
              MEMBER_PW,
              MEMBER_NM,
              MEMBER_TYP,
              LOGIN_FAIL_CNT,
              CLOSE_YN,
              EXPIRE_DT,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              'developer',
              '4dff4ea340f0a823f15d3f4f01ab62eae0e5da579ccb851f8db9dfe84c58b2b37b89903a740e1ee172da793a6e79d560e5f7f9bd058a12a280433ed6fa46510a',
              '개발자',
              '999',
              0,
              'N',
              SYSDATE+365,
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_MEMBER_MST
       (
              MEMBER_ID,
              MEMBER_PW,
              MEMBER_NM,
              MEMBER_TYP,
              LOGIN_FAIL_CNT,
              CLOSE_YN,
              EXPIRE_DT,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              '1',
              '4dff4ea340f0a823f15d3f4f01ab62eae0e5da579ccb851f8db9dfe84c58b2b37b89903a740e1ee172da793a6e79d560e5f7f9bd058a12a280433ed6fa46510a',
              '1',
              '999',
              0,
              'N',
              SYSDATE+365,
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_MEMBER_MST
       (
              MEMBER_ID,
              MEMBER_PW,
              MEMBER_NM,
              MEMBER_TYP,
              LOGIN_FAIL_CNT,
              CLOSE_YN,
              EXPIRE_DT,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              'bestheroz',
              '4dff4ea340f0a823f15d3f4f01ab62eae0e5da579ccb851f8db9dfe84c58b2b37b89903a740e1ee172da793a6e79d560e5f7f9bd058a12a280433ed6fa46510a',
              '개발자/김동준M',
              '999',
              0,
              'N',
              SYSDATE+365,
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_MEMBER_MST
       (
              MEMBER_ID,
              MEMBER_PW,
              MEMBER_NM,
              MEMBER_TYP,
              LOGIN_FAIL_CNT,
              CLOSE_YN,
              EXPIRE_DT,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              'guest',
              '4dff4ea340f0a823f15d3f4f01ab62eae0e5da579ccb851f8db9dfe84c58b2b37b89903a740e1ee172da793a6e79d560e5f7f9bd058a12a280433ed6fa46510a',
              '게스트',
              '300',
              0,
              'N',
              SYSDATE+365,
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

COMMIT;
CREATE TABLE SAMPLE_MENU_MST
             (
                          MENU_ID        INT(10) PRIMARY KEY NOT NULL,
                          MENU_NM        VARCHAR (1000) NOT NULL,
                          MENU_TYP       CHAR (1) NOT NULL,
                          PAR_MENU_ID    INT(10) NOT NULL,
                          USE_YN         CHAR (1) NOT NULL,
                          POWER          INT(10) NOT NULL,
                          DISP_SEQ       INT(10) NOT NULL,
                          URL            VARCHAR (4000),
                          REMARK1        VARCHAR (4000),
                          REG_MEMBER_ID  VARCHAR (100) NOT NULL,
                          REG_DT         DATETIME NOT NULL,
                          UPD_MEMBER_ID VARCHAR (100) NOT NULL,
                          UPD_DT        DATETIME NOT NULL
             );

CREATE SEQUENCE SEQ_SAMPLE_MENU_MST START WITH 1000000000 MAXVALUE 9999999999 MINVALUE 1000000000;
INSERT
INTO   SAMPLE_MENU_MST
       (
              MENU_ID,
              MENU_NM,
              MENU_TYP,
              PAR_MENU_ID,
              USE_YN,
              POWER,
              DISP_SEQ,
              URL,
              REMARK1,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              SEQ_SAMPLE_MENU_MST.NEXTVAL,
              'Root',
              'G',
              '0',
              'Y',
              300,
              1,
              '/',
              'MYSPRING 최상위',
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_MENU_MST
       (
              MENU_ID,
              MENU_NM,
              MENU_TYP,
              PAR_MENU_ID,
              USE_YN,
              POWER,
              DISP_SEQ,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              SEQ_SAMPLE_MENU_MST.NEXTVAL,
              'JS 가이드',
              'G',
              1000000000,
              'Y',
              300,
              10,
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_MENU_MST
       (
              MENU_ID,
              MENU_NM,
              MENU_TYP,
              PAR_MENU_ID,
              USE_YN,
              POWER,
              DISP_SEQ,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              SEQ_SAMPLE_MENU_MST.NEXTVAL,
              'JSP 가이드',
              'G',
              1000000000,
              'Y',
              300,
              20,
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_MENU_MST
       (
              MENU_ID,
              MENU_NM,
              MENU_TYP,
              PAR_MENU_ID,
              USE_YN,
              POWER,
              DISP_SEQ,
              URL,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              SEQ_SAMPLE_MENU_MST.NEXTVAL,
              'Java 가이드는 가이드문서를 확인',
              'P',
              1000000000,
              'Y',
              300,
              30,
              '',
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_MENU_MST
       (
              MENU_ID,
              MENU_NM,
              MENU_TYP,
              PAR_MENU_ID,
              USE_YN,
              POWER,
              DISP_SEQ,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              SEQ_SAMPLE_MENU_MST.NEXTVAL,
              'GitLab',
              'G',
              1000000000,
              'Y',
              300,
              70,
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_MENU_MST
       (
              MENU_ID,
              MENU_NM,
              MENU_TYP,
              PAR_MENU_ID,
              USE_YN,
              POWER,
              DISP_SEQ,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              SEQ_SAMPLE_MENU_MST.NEXTVAL,
              '관리자',
              'G',
              1000000000,
              'Y',
              700,
              110,
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_MENU_MST
       (
              MENU_ID,
              MENU_NM,
              MENU_TYP,
              PAR_MENU_ID,
              USE_YN,
              POWER,
              DISP_SEQ,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              SEQ_SAMPLE_MENU_MST.NEXTVAL,
              '테스트레벨1',
              'G',
              1000000000,
              'Y',
              300,
              99999,
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_MENU_MST
       (
              MENU_ID,
              MENU_NM,
              MENU_TYP,
              PAR_MENU_ID,
              USE_YN,
              POWER,
              DISP_SEQ,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              SEQ_SAMPLE_MENU_MST.NEXTVAL,
              '테스트레벨21',
              'G',
              1000000006,
              'Y',
              300,
              1,
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_MENU_MST
       (
              MENU_ID,
              MENU_NM,
              MENU_TYP,
              PAR_MENU_ID,
              USE_YN,
              POWER,
              DISP_SEQ,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              SEQ_SAMPLE_MENU_MST.NEXTVAL,
              '테스트레벨22',
              'G',
              1000000006,
              'Y',
              300,
              2,
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_MENU_MST
       (
              MENU_ID,
              MENU_NM,
              MENU_TYP,
              PAR_MENU_ID,
              USE_YN,
              POWER,
              DISP_SEQ,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              SEQ_SAMPLE_MENU_MST.NEXTVAL,
              '테스트레벨31',
              'G',
              1000000007,
              'Y',
              300,
              1,
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_MENU_MST
       (
              MENU_ID,
              MENU_NM,
              MENU_TYP,
              PAR_MENU_ID,
              USE_YN,
              POWER,
              DISP_SEQ,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              SEQ_SAMPLE_MENU_MST.NEXTVAL,
              '테스트레벨32',
              'P',
              1000000007,
              'Y',
              300,
              2,
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_MENU_MST
       (
              MENU_ID,
              MENU_NM,
              MENU_TYP,
              PAR_MENU_ID,
              USE_YN,
              POWER,
              DISP_SEQ,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              SEQ_SAMPLE_MENU_MST.NEXTVAL,
              '테스트레벨33',
              'P',
              1000000008,
              'Y',
              300,
              1,
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_MENU_MST
       (
              MENU_ID,
              MENU_NM,
              MENU_TYP,
              PAR_MENU_ID,
              USE_YN,
              POWER,
              DISP_SEQ,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              SEQ_SAMPLE_MENU_MST.NEXTVAL,
              '테스트41',
              'P',
              1000000009,
              'Y',
              300,
              1,
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_MENU_MST
       (
              MENU_ID,
              MENU_NM,
              MENU_TYP,
              PAR_MENU_ID,
              USE_YN,
              POWER,
              DISP_SEQ,
              URL,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              SEQ_SAMPLE_MENU_MST.NEXTVAL,
              '메뉴관리',
              'P',
              1000000005,
              'Y',
              700,
              1,
              '/sample/admin/menu/adminMenu.view',
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_MENU_MST
       (
              MENU_ID,
              MENU_NM,
              MENU_TYP,
              PAR_MENU_ID,
              USE_YN,
              POWER,
              DISP_SEQ,
              URL,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              SEQ_SAMPLE_MENU_MST.NEXTVAL,
              '코드관리',
              'P',
              1000000005,
              'Y',
              700,
              3,
              '/sample/admin/valuelabel/adminValueLabel.view',
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_MENU_MST
       (
              MENU_ID,
              MENU_NM,
              MENU_TYP,
              PAR_MENU_ID,
              USE_YN,
              POWER,
              DISP_SEQ,
              URL,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              SEQ_SAMPLE_MENU_MST.NEXTVAL,
              '우편번호검색API',
              'P',
              1000000001,
              'Y',
              300,
              30,
              '/sample/guide/postcode/sampleGuidePostcode.view',
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_MENU_MST
       (
              MENU_ID,
              MENU_NM,
              MENU_TYP,
              PAR_MENU_ID,
              USE_YN,
              POWER,
              DISP_SEQ,
              URL,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              SEQ_SAMPLE_MENU_MST.NEXTVAL,
              '회원관리',
              'P',
              1000000005,
              'Y',
              700,
              5,
              '/sample/admin/member/adminMember.view',
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_MENU_MST
       (
              MENU_ID,
              MENU_NM,
              MENU_TYP,
              PAR_MENU_ID,
              USE_YN,
              POWER,
              DISP_SEQ,
              URL,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              SEQ_SAMPLE_MENU_MST.NEXTVAL,
              '모든 프로젝트 보기',
              'P',
              1000000004,
              'Y',
              300,
              1,
              'http://10.125.251.12/explore/projects',
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_MENU_MST
       (
              MENU_ID,
              MENU_NM,
              MENU_TYP,
              PAR_MENU_ID,
              USE_YN,
              POWER,
              DISP_SEQ,
              URL,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              SEQ_SAMPLE_MENU_MST.NEXTVAL,
              '개발환경구축가이드',
              'P',
              1000000004,
              'Y',
              300,
              10,
              NULL,
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_MENU_MST
       (
              MENU_ID,
              MENU_NM,
              MENU_TYP,
              PAR_MENU_ID,
              USE_YN,
              POWER,
              DISP_SEQ,
              URL,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              SEQ_SAMPLE_MENU_MST.NEXTVAL,
              '표준 웹 프로젝트',
              'P',
              1000000004,
              'Y',
              300,
              20,
              NULL,
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_MENU_MST
       (
              MENU_ID,
              MENU_NM,
              MENU_TYP,
              PAR_MENU_ID,
              USE_YN,
              POWER,
              DISP_SEQ,
              URL,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              SEQ_SAMPLE_MENU_MST.NEXTVAL,
              '모달(modal) 띄우기',
              'P',
              1000000001,
              'Y',
              300,
              10,
              '/sample/guide/modal/sampleGuideModal.view',
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_MENU_MST
       (
              MENU_ID,
              MENU_NM,
              MENU_TYP,
              PAR_MENU_ID,
              USE_YN,
              POWER,
              DISP_SEQ,
              URL,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              SEQ_SAMPLE_MENU_MST.NEXTVAL,
              'ajax 요청하기',
              'P',
              1000000001,
              'Y',
              300,
              1,
              '/sample/guide/ajax/sampleGuideAjax.view',
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_MENU_MST
       (
              MENU_ID,
              MENU_NM,
              MENU_TYP,
              PAR_MENU_ID,
              USE_YN,
              POWER,
              DISP_SEQ,
              URL,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              SEQ_SAMPLE_MENU_MST.NEXTVAL,
              '팝업(popup)창 띄우기',
              'P',
              1000000001,
              'Y',
              300,
              11,
              '/sample/guide/popup/sampleGuidePopup.view',
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_MENU_MST
       (
              MENU_ID,
              MENU_NM,
              MENU_TYP,
              PAR_MENU_ID,
              USE_YN,
              POWER,
              DISP_SEQ,
              URL,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              SEQ_SAMPLE_MENU_MST.NEXTVAL,
              '달력 및 시간: datetimepicker',
              'P',
              1000000001,
              'Y',
              300,
              40,
              '/sample/guide/datetimepicker/sampleGuideDatetimepicker.view',
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_MENU_MST
       (
              MENU_ID,
              MENU_NM,
              MENU_TYP,
              PAR_MENU_ID,
              USE_YN,
              POWER,
              DISP_SEQ,
              URL,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              SEQ_SAMPLE_MENU_MST.NEXTVAL,
              'ajax - 파일다운로드',
              'P',
              1000000001,
              'Y',
              300,
              6,
              '/sample/guide/ajax/sampleGuideAjax.view?#list4',
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_MENU_MST
       (
              MENU_ID,
              MENU_NM,
              MENU_TYP,
              PAR_MENU_ID,
              USE_YN,
              POWER,
              DISP_SEQ,
              URL,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              SEQ_SAMPLE_MENU_MST.NEXTVAL,
              '날짜 처리',
              'P',
              1000000001,
              'Y',
              300,
              20,
              '/sample/guide/format/sampleGuideFormat.view?#list1',
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_MENU_MST
       (
              MENU_ID,
              MENU_NM,
              MENU_TYP,
              PAR_MENU_ID,
              USE_YN,
              POWER,
              DISP_SEQ,
              URL,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              SEQ_SAMPLE_MENU_MST.NEXTVAL,
              '필드 검증하기(필수값 등)',
              'P',
              1000000001,
              'Y',
              300,
              15,
              '/sample/guide/validate/field/sampleGuideValidateField.view',
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_MENU_MST
       (
              MENU_ID,
              MENU_NM,
              MENU_TYP,
              PAR_MENU_ID,
              USE_YN,
              POWER,
              DISP_SEQ,
              URL,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              SEQ_SAMPLE_MENU_MST.NEXTVAL,
              '값 검증하기',
              'P',
              1000000001,
              'Y',
              300,
              16,
              '/sample/guide/validate/value/sampleGuideValidateValue.view',
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_MENU_MST
       (
              MENU_ID,
              MENU_NM,
              MENU_TYP,
              PAR_MENU_ID,
              USE_YN,
              POWER,
              DISP_SEQ,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              SEQ_SAMPLE_MENU_MST.NEXTVAL,
              '페이징 처리',
              'P',
              1000000001,
              'Y',
              300,
              90,
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_MENU_MST
       (
              MENU_ID,
              MENU_NM,
              MENU_TYP,
              PAR_MENU_ID,
              USE_YN,
              POWER,
              DISP_SEQ,
              URL,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              SEQ_SAMPLE_MENU_MST.NEXTVAL,
              'ajax - 파일업로드',
              'P',
              1000000001,
              'Y',
              300,
              5,
              '/sample/guide/ajax/sampleGuideAjax.view?#list2',
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_MENU_MST
       (
              MENU_ID,
              MENU_NM,
              MENU_TYP,
              PAR_MENU_ID,
              USE_YN,
              POWER,
              DISP_SEQ,
              URL,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              SEQ_SAMPLE_MENU_MST.NEXTVAL,
              '텍스트 에디터',
              'P',
              1000000001,
              'Y',
              300,
              60,
              '/sample/guide/texteditor/sampleGuideTexteditor.view',
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_MENU_MST
       (
              MENU_ID,
              MENU_NM,
              MENU_TYP,
              PAR_MENU_ID,
              USE_YN,
              POWER,
              DISP_SEQ,
              URL,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              SEQ_SAMPLE_MENU_MST.NEXTVAL,
              'HTML템플릿 - 핸들바JS',
              'P',
              1000000001,
              'Y',
              300,
              70,
              '/sample/guide/handlebarsjs/sampleGuideHandlebarsjs.view',
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_MENU_MST
       (
              MENU_ID,
              MENU_NM,
              MENU_TYP,
              PAR_MENU_ID,
              USE_YN,
              POWER,
              DISP_SEQ,
              URL,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              SEQ_SAMPLE_MENU_MST.NEXTVAL,
              '숫자 포맷&#40;콤마찍기 등&#41;',
              'P',
              1000000001,
              'Y',
              300,
              25,
              '/sample/guide/format/sampleGuideFormat.view?#list2',
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_MENU_MST
       (
              MENU_ID,
              MENU_NM,
              MENU_TYP,
              PAR_MENU_ID,
              USE_YN,
              POWER,
              DISP_SEQ,
              URL,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              SEQ_SAMPLE_MENU_MST.NEXTVAL,
              CHR(38)||CHR(76)||CHR(84)||CHR(59)||'my:html'||CHR(38)||CHR(71)||CHR(84)||CHR(59),
              'P',
              1000000002,
              'Y',
              300,
              10,
              '/sample/guide/html/sampleGuideHtml.view',
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_MENU_MST
       (
              MENU_ID,
              MENU_NM,
              MENU_TYP,
              PAR_MENU_ID,
              USE_YN,
              POWER,
              DISP_SEQ,
              URL,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              SEQ_SAMPLE_MENU_MST.NEXTVAL,
              '표준 DB I/F + 웹 프로젝트',
              'P',
              1000000004,
              'Y',
              300,
              30,
              NULL,
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_MENU_MST
       (
              MENU_ID,
              MENU_NM,
              MENU_TYP,
              PAR_MENU_ID,
              USE_YN,
              POWER,
              DISP_SEQ,
              URL,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              SEQ_SAMPLE_MENU_MST.NEXTVAL,
              '페이지 이동하기(href)',
              'P',
              1000000001,
              'Y',
              300,
              9,
              '/sample/guide/movepage/sampleGuideMovepage.view',
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_MENU_MST
       (
              MENU_ID,
              MENU_NM,
              MENU_TYP,
              PAR_MENU_ID,
              USE_YN,
              POWER,
              DISP_SEQ,
              URL,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              SEQ_SAMPLE_MENU_MST.NEXTVAL,
              '파일관리',
              'P',
              1000000005,
              'Y',
              700,
              40,
              '/sample/admin/file/adminFile.view',
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

COMMIT;

CREATE TABLE SAMPLE_CODE_MST
             (
                          GRCODE         VARCHAR (100) PRIMARY KEY NOT NULL,
                          GRCODE_NM      VARCHAR (1000) NOT NULL,
                          REMARK1        VARCHAR (4000),
                          REG_MEMBER_ID  VARCHAR (100) NOT NULL,
                          REG_DT         DATETIME NOT NULL,
                          UPD_MEMBER_ID VARCHAR (100) NOT NULL,
                          UPD_DT        DATETIME NOT NULL
             );

INSERT
INTO   SAMPLE_CODE_MST
       (
              GRCODE,
              GRCODE_NM,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              'MENU_TYP',
              '메뉴타입',
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_CODE_MST
       (
              GRCODE,
              GRCODE_NM,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              'USE_YN',
              '사용여부',
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_CODE_MST
       (
              GRCODE,
              GRCODE_NM,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              'MEMBER_TYP',
              '회원구분',
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_CODE_MST
       (
              GRCODE,
              GRCODE_NM,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              'USE_YN_REVERSE',
              '사용여부(아니오부터 출력)',
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

COMMIT;

CREATE TABLE SAMPLE_CODE_DET
             (
                          GRCODE         VARCHAR (100) NOT NULL,
                          CODE           VARCHAR (100) NOT NULL,
                          CODE_NM        VARCHAR (1000) NOT NULL,
                          USE_YN         VARCHAR (1) NOT NULL,
                          DISP_SEQ       INT(10) NOT NULL,
                          REMARK1        VARCHAR (4000),
                          REG_MEMBER_ID  VARCHAR (100) NOT NULL,
                          REG_DT         DATETIME NOT NULL,
                          UPD_MEMBER_ID VARCHAR (100) NOT NULL,
                          UPD_DT        DATETIME NOT NULL
             );

CREATE INDEX IDX_SAMPLE_CODE_DET
ON SAMPLE_CODE_DET
             (
                          GRCODE,
                          DISP_SEQ
             );

ALTER TABLE SAMPLE_CODE_DET ADD PRIMARY KEY (GRCODE, CODE);

INSERT
INTO   SAMPLE_CODE_DET
       (
              GRCODE,
              CODE,
              CODE_NM,
              USE_YN,
              DISP_SEQ,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              'USE_YN',
              'Y',
              '예',
              'Y',
              1,
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_CODE_DET
       (
              GRCODE,
              CODE,
              CODE_NM,
              USE_YN,
              DISP_SEQ,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              'USE_YN',
              'N',
              '아니요',
              'Y',
              2,
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_CODE_DET
       (
              GRCODE,
              CODE,
              CODE_NM,
              USE_YN,
              DISP_SEQ,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              'MENU_TYP',
              'G',
              '그룹',
              'Y',
              1,
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_CODE_DET
       (
              GRCODE,
              CODE,
              CODE_NM,
              USE_YN,
              DISP_SEQ,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              'MENU_TYP',
              'P',
              '페이지',
              'Y',
              2,
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_CODE_DET
       (
              GRCODE,
              CODE,
              CODE_NM,
              USE_YN,
              DISP_SEQ,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              'USE_YN_REVERSE',
              'N',
              '아니요',
              'Y',
              1,
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_CODE_DET
       (
              GRCODE,
              CODE,
              CODE_NM,
              USE_YN,
              DISP_SEQ,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              'USE_YN_REVERSE',
              'Y',
              '예',
              'Y',
              2,
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_CODE_DET
       (
              GRCODE,
              CODE,
              CODE_NM,
              USE_YN,
              DISP_SEQ,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              'MEMBER_TYP',
              '300',
              '손님',
              'Y',
              3,
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_CODE_DET
       (
              GRCODE,
              CODE,
              CODE_NM,
              USE_YN,
              DISP_SEQ,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              'MEMBER_TYP',
              '500',
              '일반유저',
              'Y',
              5,
              'bestheroz',
              SYSDATE,
              'developer',
              SYSDATE
       );

INSERT
INTO   SAMPLE_CODE_DET
       (
              GRCODE,
              CODE,
              CODE_NM,
              USE_YN,
              DISP_SEQ,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              'MEMBER_TYP',
              '700',
              '관리자',
              'Y',
              7,
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_CODE_DET
       (
              GRCODE,
              CODE,
              CODE_NM,
              USE_YN,
              DISP_SEQ,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              'MEMBER_TYP',
              '800',
              'Master 관리자',
              'Y',
              8,
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

INSERT
INTO   SAMPLE_CODE_DET
       (
              GRCODE,
              CODE,
              CODE_NM,
              USE_YN,
              DISP_SEQ,
              REG_MEMBER_ID,
              REG_DT,
              UPD_MEMBER_ID,
              UPD_DT
       )
       VALUES
       (
              'MEMBER_TYP',
              '999',
              '개발자',
              'Y',
              10,
              'bestheroz',
              SYSDATE,
              'bestheroz',
              SYSDATE
       );

COMMIT;

CREATE TABLE SAMPLE_FILE_MST
             (
                          FILE_SEQ       INT(10) PRIMARY KEY NOT NULL,
                          FILE_NM        VARCHAR(4000) NOT NULL,
                          FILE_NM_EXT    VARCHAR(100) NOT NULL,
                          MIME_TYP       VARCHAR(100) NOT NULL,
                          FILE_DATA      BLOB NOT NULL,
                          REG_MEMBER_ID  VARCHAR (100) NOT NULL,
                          REG_DT         DATETIME NOT NULL,
                          UPD_MEMBER_ID VARCHAR (100) NOT NULL,
                          UPD_DT        DATETIME NOT NULL
             );

CREATE SEQUENCE SEQ_SAMPLE_FILE_MST START WITH 1000000001 MAXVALUE 9999999999 MINVALUE 1000000000;
CREATE INDEX SAMPLE_FILE_MST_IDX1
ON SAMPLE_FILE_MST
             (
                          FILE_SEQ,
                          FILE_NM
             );