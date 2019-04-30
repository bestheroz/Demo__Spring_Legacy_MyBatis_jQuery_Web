package com.github.bestheroz.sample.web.admin.member;

import com.github.bestheroz.sample.web.login.LoginVO;
import com.github.bestheroz.sample.web.tablevo.samplemembermst.TableSampleMemberMstDAO;
import com.github.bestheroz.sample.web.tablevo.samplemembermst.TableSampleMemberMstVO;
import com.github.bestheroz.standard.common.exception.CommonException;
import com.github.bestheroz.standard.common.util.MyMapperUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AdminMemberService {
    @Autowired
    private TableSampleMemberMstDAO tableMemberMstDAO;

    public List<AdminMemberVO> getSampleMemberMstVOList(final AdminMemberVO vo) throws CommonException {
        final List<String> whereKey = new ArrayList<>();
        if (StringUtils.isNotEmpty(vo.getMemberId())) {
            whereKey.add("memberId");
        }
        return MyMapperUtils.writeObjectAsArrayList(this.tableMemberMstDAO.getList(MyMapperUtils.writeObjectAsObject(vo, TableSampleMemberMstVO.class), whereKey, "UPD_DT DESC"),
                AdminMemberVO.class);
    }

    public void insertSampleMemberMst(final TableSampleMemberMstVO vo, final LoginVO loginVO) throws CommonException {
        vo.setRegMemberId(loginVO.getMemberId());
        vo.setUpdMemberId(loginVO.getMemberId());
        this.tableMemberMstDAO.insert(vo);
    }

    public void updateSampleMemberMst(final TableSampleMemberMstVO vo, final LoginVO loginVO) throws CommonException {
        vo.setUpdMemberId(loginVO.getMemberId());
        this.tableMemberMstDAO.update(vo, Collections.singletonList("memberId"), null);
    }

    public void deleteSampleMemberMst(final TableSampleMemberMstVO vo) throws CommonException {
        this.tableMemberMstDAO.delete(vo, Collections.singletonList("memberId"));
    }
}
