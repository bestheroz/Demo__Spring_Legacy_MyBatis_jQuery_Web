package com.github.bestheroz.sample.web.admin.file;

import com.github.bestheroz.sample.web.login.LoginVO;
import com.github.bestheroz.sample.web.tablevo.samplefilemst.TableSampleFileMstDAO;
import com.github.bestheroz.sample.web.tablevo.samplefilemst.TableSampleFileMstVO;
import com.github.bestheroz.standard.common.exception.CommonException;
import com.github.bestheroz.standard.common.util.MyFileUtils;
import com.github.bestheroz.standard.common.util.MyMapperUtils;
import com.github.bestheroz.standard.common.util.MyNullUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class AdminFileService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TableSampleFileMstDAO tableSampleFileMstDAO;

    public List<AdminFileVO> getSampleFileMstVOList(final AdminFileVO vo) throws CommonException {
        return MyMapperUtils.writeObjectAsArrayList(this.tableSampleFileMstDAO.getList(MyMapperUtils.writeObjectAsObject(vo, TableSampleFileMstVO.class), null, "UPD_DT DESC"),
                AdminFileVO.class);
    }

    public void insertSampleFileMst(final TableSampleFileMstVO vo, final MultipartFile multipartFile, final LoginVO loginVO) throws CommonException {
        extractMultipartFile(vo, multipartFile);
        vo.setRegMemberId(loginVO.getMemberId());
        vo.setUpdMemberId(loginVO.getMemberId());
        this.tableSampleFileMstDAO.insert(vo);
    }

    public void updateSampleFileMst(final TableSampleFileMstVO vo, final MultipartFile multipartFile, final LoginVO loginVO) throws CommonException {
        extractMultipartFile(vo, multipartFile);
        vo.setUpdMemberId(loginVO.getMemberId());
        this.tableSampleFileMstDAO.update(vo, Collections.singleton("fileSeq"), null);
    }

    private void extractMultipartFile(final TableSampleFileMstVO vo, final MultipartFile multipartFile) {
        if (!MyNullUtils.isEmpty(multipartFile)) {
            try {
                vo.setFileData(ArrayUtils.toObject(multipartFile.getBytes()));
                vo.setMimeTyp(MyFileUtils.getMimeType(multipartFile));
                vo.setFileNmExt(MyFileUtils.getExtension(multipartFile.getOriginalFilename()));
                if (StringUtils.isEmpty(vo.getFileNm())) {
                    vo.setFileNm(multipartFile.getOriginalFilename());
                }
            } catch (final IOException e) {
                this.logger.warn(ExceptionUtils.getStackTrace(e));
                throw new CommonException(e);
            }
        }
    }

    public void deleteSampleFileMst(final TableSampleFileMstVO vo) throws CommonException {
        this.tableSampleFileMstDAO.delete(vo, Collections.singleton("fileSeq"));
    }
}
