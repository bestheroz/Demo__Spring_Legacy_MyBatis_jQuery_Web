package com.github.bestheroz.sample.web.login;

import java.io.Serializable;

import org.joda.time.LocalDateTime;

@SuppressWarnings("serial")
public class LoginVO implements Serializable {
	private String memberId;
	private String memberPw;
	private String memberNm;
	private String memberTyp;
	private Integer loginFailCnt;
	private String closeYn;
	private LocalDateTime expireDt;

	public String getMemberId() {
		return this.memberId;
	}

	public void setMemberId(final String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPw() {
		return this.memberPw;
	}

	public void setMemberPw(final String memberPw) {
		this.memberPw = memberPw;
	}

	public String getMemberNm() {
		return this.memberNm;
	}

	public void setMemberNm(final String memberNm) {
		this.memberNm = memberNm;
	}

	public String getMemberTyp() {
		return this.memberTyp;
	}

	public void setMemberTyp(final String memberTyp) {
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

}
