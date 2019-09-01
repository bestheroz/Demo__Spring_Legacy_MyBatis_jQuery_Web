package com.github.bestheroz.sample.web.common.util;

import com.github.bestheroz.sample.web.login.LoginVO;
import com.github.bestheroz.sample.web.tablevo.samplefilemst.TableSampleFileMstDAO;
import com.github.bestheroz.sample.web.tablevo.samplefilemst.TableSampleFileMstVO;
import com.github.bestheroz.standard.common.exception.CommonException;
import com.github.bestheroz.standard.common.exception.CommonExceptionCode;
import com.github.bestheroz.standard.common.util.MyAccessBeanUtils;
import com.github.bestheroz.standard.common.util.MyFileUtils;
import com.github.bestheroz.standard.common.util.MyNullUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

public class MyBlobFileUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyBlobFileUtils.class);

    public static File getFileFromSampleFileMstObject(final Integer fileSeq, final String fileName) throws CommonException {
        final TableSampleFileMstVO tableSampleFileMstVO = new TableSampleFileMstVO();
        tableSampleFileMstVO.setFileSeq(fileSeq);
        tableSampleFileMstVO.setFileNm(fileName);
        final Set<String> whereKeys = new HashSet<>();
        whereKeys.add("fileSeq");
        if (StringUtils.isNotEmpty(tableSampleFileMstVO.getFileNm())) {
            whereKeys.add("fileNm");
        }
        final TableSampleFileMstVO sampleFileMstVO = MyAccessBeanUtils.getBean(TableSampleFileMstDAO.class).getVO(tableSampleFileMstVO, whereKey);
        if (sampleFileMstVO == null) {
            LOGGER.warn("fileSeq: {}, fileName: {} :: {}", fileSeq, fileName, CommonExceptionCode.ERROR_FILE_NOT_FOUND.toString());
            throw new CommonException(CommonExceptionCode.ERROR_FILE_NOT_FOUND);
        }

        try {
            final File file = new File(sampleFileMstVO.getFileNm());
            FileUtils.writeByteArrayToFile(file, ArrayUtils.toPrimitive(sampleFileMstVO.getFileData()));
            return file;
        } catch (final Throwable e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            throw new CommonException(e);
        }
    }

    public static TableSampleFileMstVO insert(final MultipartFile multipartFile, final LoginVO loginVO) throws CommonException {
        final TableSampleFileMstVO tableSampleFileMstVO = new TableSampleFileMstVO();
        if (!MyNullUtils.isEmpty(multipartFile)) {
            try {
                tableSampleFileMstVO.setRegMemberId(loginVO.getMemberId());
                tableSampleFileMstVO.setUpdMemberId(loginVO.getMemberId());
                tableSampleFileMstVO.setFileData(ArrayUtils.toObject(multipartFile.getBytes()));
                tableSampleFileMstVO.setMimeTyp(MyFileUtils.getMimeType(multipartFile));
                tableSampleFileMstVO.setFileNm(multipartFile.getOriginalFilename());
                tableSampleFileMstVO.setFileNmExt(MyFileUtils.getExtension(multipartFile));
                MyAccessBeanUtils.getBean(TableSampleFileMstDAO.class).insert(tableSampleFileMstVO);
            } catch (final IOException e) {
                LOGGER.warn(ExceptionUtils.getStackTrace(e));
                throw new CommonException(e);
            }
        }
        return tableSampleFileMstVO;
    }

    public static TableSampleFileMstVO update(final Integer fileSeq, final MultipartFile multipartFile, final LoginVO loginVO) throws CommonException {
        final TableSampleFileMstVO tableSampleFileMstVO = new TableSampleFileMstVO();
        if (fileSeq != null) {
            if (!MyNullUtils.isEmpty(multipartFile)) {
                try {
                    tableSampleFileMstVO.setFileSeq(fileSeq);
                    tableSampleFileMstVO.setRegMemberId(loginVO.getMemberId());
                    tableSampleFileMstVO.setUpdMemberId(loginVO.getMemberId());
                    tableSampleFileMstVO.setFileData(ArrayUtils.toObject(multipartFile.getBytes()));
                    tableSampleFileMstVO.setMimeTyp(MyFileUtils.getMimeType(multipartFile));
                    tableSampleFileMstVO.setFileNm(multipartFile.getOriginalFilename());
                    tableSampleFileMstVO.setFileNmExt(MyFileUtils.getExtension(multipartFile));
                    MyAccessBeanUtils.getBean(TableSampleFileMstDAO.class).update(tableSampleFileMstVO, Collections.singleton("fileSeq"), null);
                } catch (final IOException e) {
                    LOGGER.warn(ExceptionUtils.getStackTrace(e));
                    throw new CommonException(e);
                }
            }
        } else {
            return insert(multipartFile, loginVO);
        }
        return tableSampleFileMstVO;
    }
}
