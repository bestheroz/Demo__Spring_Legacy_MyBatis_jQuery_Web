package com.github.bestheroz.standard.context.converter;

import com.github.bestheroz.standard.common.util.MyDateUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDateTime;
import org.springframework.core.convert.converter.Converter;

public class LocalDateTimeConverter implements Converter<String, LocalDateTime> {
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
                        // 3. yyyy-MM-ddTHH:mm:ss.SSSZ
                        return MyDateUtils.getLocalDateTime(arg0, MyDateUtils.ISO_8601);
                    } catch (final Throwable e3) {
                        try {
                            // 4. yyyyMMdd
                            return MyDateUtils.getLocalDateTime(arg0, MyDateUtils.YYYYMMDD);
                        } catch (final Throwable e4) {
                            try {
                                // 5. yyyyMMddHHmmss
                                return MyDateUtils.getLocalDateTime(arg0, MyDateUtils.YYYYMMDDHHMMSS);
                            } catch (final Throwable e5) {
                                return null;
                            }
                        }
                    }
                }
            }
        } else {
            return null;
        }
    }
}
