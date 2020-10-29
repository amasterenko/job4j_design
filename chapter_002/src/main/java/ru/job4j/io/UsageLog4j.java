package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte byteNum = 1;
        short shortNum = 2;
        int intNum = 3;
        long lngNum = 4;
        float floatNum = 5.0f;
        double dblNum = 6.0;
        boolean bool = true;
        char ch = 'c';
        LOG.debug("byte {}, short {}, int {}, long {}, float {}, double {}, boolean {}, char {}",
                byteNum, shortNum, intNum, lngNum, floatNum, dblNum, bool, ch);
    }
}