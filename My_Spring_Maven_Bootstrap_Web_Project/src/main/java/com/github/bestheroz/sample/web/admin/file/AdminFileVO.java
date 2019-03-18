package com.github.bestheroz.sample.web.admin.file;

import java.io.Serializable;

import org.joda.time.LocalDateTime;

@SuppressWarnings("serial")
public class AdminFileVO implements Serializable {
	private Integer fileSeq;
	private String fileNm;
	private String fileNmExt;
	private String mimeTyp;
	private String updMemberId;
	private LocalDateTime updDt;

	public Integer getFileSeq() {
		return this.fileSeq;
	}

	public void setFileSeq(final Integer fileSeq) {
		this.fileSeq = fileSeq;
	}

	public String getFileNm() {
		return this.fileNm;
	}

	public void setFileNm(final String fileNm) {
		this.fileNm = fileNm;
	}

	public String getFileNmExt() {
		return this.fileNmExt;
	}

	public void setFileNmExt(final String fileNmExt) {
		this.fileNmExt = fileNmExt;
	}

	public String getMimeTyp() {
		return this.mimeTyp;
	}

	public void setMimeTyp(final String mimeTyp) {
		this.mimeTyp = mimeTyp;
	}

	public String getUpdMemberId() {
		return this.updMemberId;
	}

	public void setUpdMemberId(final String updMemberId) {
		this.updMemberId = updMemberId;
	}

	public LocalDateTime getUpdDt() {
		return this.updDt;
	}

	public void setUpdDt(final LocalDateTime updDt) {
		this.updDt = updDt;
	}

}
