package com.github.bestheroz.sample.web.admin.member;

import org.joda.time.LocalDateTime;

import java.io.Serializable;

@SuppressWarnings("ALL")
public class AdminMemberVO implements Serializable {
    private String memberId;
    private String memberNm;
    private String memberPw;
    private String memberTyp;
    private Integer loginFailCnt;
    private String closeYn;
    private LocalDateTime expireDt;
    private String updMemberId;
    private LocalDateTime updDt;

    public String getMemberId() {
        return this.memberId;
    }

    public void setMemberId(final String memberId) {
        this.memberId = memberId;
    }

    public String getMemberNm() {
        return this.memberNm;
    }

    public void setMemberNm(final String memberNm) {
        this.memberNm = memberNm;
    }

    public String getMemberPw() {
        return this.memberPw;
    }

    public void setMemberPw(final String memberPw) {
        this.memberPw = memberPw;
    }

    public String getMemberType() {
        return this.memberTyp;
    }

    public void setMemberType(final String memberTyp) {
        this.memberTyp = memberTyp;
    }

    public Integer getLoginFailCnt() {
        return this.loginFailCnt;
    }

    public void setLoginFailCnt(final Integer loginFailCnt) {
        this.loginFailCnt = loginFailCnt;
    }

    public String getCloseYn() {
        return this.closeYn;
    }

    public void setCloseYn(final String closeYn) {
        this.closeYn = closeYn;
    }

    public LocalDateTime getExpireDt() {
        return this.expireDt;
    }

    public void setExpireDt(final LocalDateTime expireDt) {
        this.expireDt = expireDt;
    }

    public String getUpdMemberId() {
        return this.updMemberId;
    }

    public void setUpdMemberId(final String updMemberId) {
        this.updMemberId = updMemberId;
    }

    @SuppressWarnings("unused")
    public LocalDateTime getUpdDt() {
        return this.updDt;
    }

    public void setUpdDt(final LocalDateTime updDt) {
        this.updDt = updDt;
    }

}
