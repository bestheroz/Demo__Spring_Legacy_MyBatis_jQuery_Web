package com.github.bestheroz.sample.web.tablevo.samplecodemst;

import org.joda.time.LocalDateTime;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TableSampleCodeMstVO implements Serializable {
    private String grcode;
    private String grcodeNm;
    private String remark1;
    private String regMemberId;
    private LocalDateTime regDt;
    private String updMemberId;
    private LocalDateTime updDt;

    public String getGrcode() {
        return this.grcode;
    }

    public void setGrcode(final String grcode) {
        this.grcode = grcode;
    }

    public String getGrcodeNm() {
        return this.grcodeNm;
    }

    public void setGrcodeNm(final String grcodeNm) {
        this.grcodeNm = grcodeNm;
    }

    public String getRemark1() {
        return this.remark1;
    }

    public void setRemark1(final String remark1) {
        this.remark1 = remark1;
    }

    public String getRegMemberId() {
        return this.regMemberId;
    }

    public void setRegMemberId(final String regMemberId) {
        this.regMemberId = regMemberId;
    }

    public LocalDateTime getRegDt() {
        return this.regDt;
    }

    public void setRegDt(final LocalDateTime regDt) {
        this.regDt = regDt;
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
