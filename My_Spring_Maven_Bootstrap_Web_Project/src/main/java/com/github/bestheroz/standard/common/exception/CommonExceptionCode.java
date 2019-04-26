package com.github.bestheroz.standard.common.exception;

public enum CommonExceptionCode {
    SUCCESS_NORMAL("S000", "처리되었습니다."),

    ERROR_SYSTEM_ERROR("E000", "시스템에러"),

    ERROR_INVALID_REQUEST("E001", "올바르지 않은 요청"),

    ERROR_INVALID_COMMON_PARAMETER("E002", "올바르지 않은 공통 파라미터"),

    ERROR_INVALID_PARAMETER("E003", "올바르지 않은 파라미터"),

    ERROR_NOT_ALLOWED_MEMBER("E004", "허용되지 않는 사용자"),

    ERROR_FILE_NOT_FOUND("E005", "파일이 존재하지 않습니다."),

    ERROR_PATH_NOT_FOUND("E006", "경로가 존재하지 않습니다"),

    ERROR_FAIL_TRANSFORM_DATA("E007", "데이터 변환 실패(Primitive -> Collection)"),

    ERROR_SELECTKEY_RETURN_NO_DATA("E008", "키 생성에 실패하였습니다."),

    ERROR_VALUE_TOO_LARGE_FOR_COLUMN("E009", "입력하신 값이 너무 큽니다."),

    ERROR_LOGIN_FAIL_CNT("E010", "로그인 실패 회수 초과"),

    ERROR_TRY_LOGIN_FIRST("E011", "처리를 위해 로그인이 필요합니다."),

    ERROR_FAIL_FILE_MIMETYPE("E012", "올바르지 않은 파일 내용입니다."),

    ERROR_FAIL_FILE_EXT("E013", "올바르지 않은 파일 확장자 입니다."),

    ERROR_FAIL_IMAGE_MIMETYPE("E014", "올바르지 않은 이미지 형식입니다."),

    ERROR_FAIL_IMAGE_EXT("E015", "올바르지 않은 이미지 확장자 입니다."),

    ERROR_FAIL_EXCEL_MIMETYPE("E016", "올바르지 않은 엑셀 형식입니다."),

    ERROR_FAIL_EXCEL_EXT("E017", "올바르지 않은 엑셀 확장자 입니다."),

    ERROR_FAIL_WORD_MIMETYPE("E018", "올바르지 않은 워드문서 형식입니다."),

    ERROR_FAIL_WORD_EXT("E019", "올바르지 않은 워드문서 확장자 입니다."),

    ERROR_FAIL_PDF_MIMETYPE("E020", "올바르지 않은 PDF 문서 형식입니다."),

    ERROR_FAIL_PDF_EXT("E021", "올바르지 않은 PDF 문서 확장자 입니다."),

    ERROR_FAIL_FILE_SIZE("E022", "최대 2MB 이하의 파일 첨부만 가능합니다."),

    ERROR_NO_DATA_SUCCESS("E023", "처리건 없음."),

    ERROR_UNIQUE_CONSTRAINT_VIOLATED("E024", "이미 존재하는 데이터입니다."),

    ERROR_CANNOT_INSERT_NULL("E025", "필수 값을 채워주세요."),

    ERROR_INVALID_ID_PWD("E026", "입력하신 ID와 Password 정보가 없습니다."),

    ERROR_DIR_PATH_MUST_ENDS_WITH_SLASH("E027", "디렉토리 경로는 항상 '/' 으로 끝나야만 합니다."),

    ERROR_ILLEGAL_BLOCK_SIZE_AES("E028", "암/복호화 오류 발생"),

    // 이 라인 위로 새로운 메시지를 정의해주세요.
    // 개발자용 메시지는 최하위에 존재하게 해주세요

    ERROR_FAIL_TO_DECRYPTION_DATA("E998", "(개발자용)암호화 값이 아닌 값을 Decryption 하고 있음!"),

    ERROR_TOO_MANY_RESULT("E996", "(개발자용)sqlSession.selectOne();에서 여러 row 리턴!"),

    ERROR_VIOLATED_CHILD_RECORD_FOUND("E997", "(개발자용)데이터를 삭제하기전 제약조건을 확인!"),

    ERROR_MAPPED_STATMENTS_COLLECTION_DOES_NOT_CONTAIN("E998", "(개발자용)SQL을 수행할 Mapper를 찾지 못함!"),

    ERROR_BAD_SQL_GRAMMER("E999", "(개발자용)SQL 문법 에러!");

    private String code;
    private String messsage;

    private CommonExceptionCode(final String code, final String message) {
        this.code = code;
        this.messsage = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.messsage;
    }

    @Override
    public String toString() {
        return this.name() + "(\"" + this.code + "\", \"" + this.messsage + "\");";
    }
}
