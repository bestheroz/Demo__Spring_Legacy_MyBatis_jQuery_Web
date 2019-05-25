package com.github.bestheroz.standard.context.converter;

import com.github.bestheroz.standard.common.util.MyDateUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDateTime;
import org.springframework.core.convert.converter.Converter;

public class LocalDateTimeConverter implements Converter<String, LocalDateTime> {
    @Override
    public LocalDateTime convert(final String arg0) {
        if (StringUtils.isNotEmpty(arg0)) {
            try {
                // 1. long값(timestamp)
                return MyDateUtils.getLocalDateTime(Long.parseLong(arg0));
            } catch (final Throwable e) {
                try {
                    // 2. yyyy-MM-dd
                    return MyDateUtils.getLocalDateTime(arg0, MyDateUtils.YYYY_MM_DD);
                } catch (final Throwable e2) {
                    try {
                        // 3. yyyyMMdd
                        return MyDateUtils.getLocalDateTime(arg0, MyDateUtils.YYYYMMDD);
                    } catch (final Throwable e3) {
                        try {
                            // 4. yyyyMMddHHmmss
                            return MyDateUtils.getLocalDateTime(arg0, MyDateUtils.YYYYMMDDHHMMSS);
                        } catch (final Throwable e4) {
                            return null;
                        }
                    }
                }
            }
        } else {
            return null;
        }
    }
}
