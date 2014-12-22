package com.vintiduo.page.log;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

public class Logger {

    Log log;

    private Logger(Log log) {
        this.log = log;
    }

    public static Logger forClass(Class<?> clazz) {
        return new Logger(LogFactory.getLog(clazz));
    }

    private String format(String method, String message, Object... args) {
        List<String> pairs = new ArrayList<>();
        for (int i = 0; i <= args.length / 2; i+= 2) {
            pairs.add(String.format("%s=<%s>", args[i], args[i + 1]));
        }
        return String.format("%s - %s - (%s)", method, message, StringUtils.join(pairs, ", "));
    }

    public void info(String method, String message, Object... args) {
        if (log.isInfoEnabled()) {
            log.info(format(method, message, args));
        }
    }
}
