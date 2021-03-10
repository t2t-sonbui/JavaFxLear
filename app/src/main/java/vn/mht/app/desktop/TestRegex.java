package vn.mht.app.desktop;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegex {
    static org.apache.log4j.Logger mLogger = org.apache.log4j.Logger.getLogger(TestRegex.class);


    public static void main(String args[]) {
        String line = "    Zig App    REG_SZ    C:\\Users\\Son Bui\\AppData\\Local\\app\\app.exe";
//        Pattern patternAddress = Pattern.compile("^\\s+\\w+\\s+\\w+\\s+(.+)$");
        Pattern patternAddress = Pattern.compile("(\\s+\\w+)*\\s+(.+)");
        Matcher matcher = patternAddress.matcher(line);
        mLogger.info("Start finding:" + line);
//        while (matcher.find()) {
//            System.out.println(matcher.group());
//        }

        if (matcher.matches()) {
            String path = matcher.group(2);
            mLogger.info(" Got Path startup: " + matcher.group(0));
            mLogger.info(" Got Path startup: " + matcher.group(1));
            mLogger.info(" Got Path startup: " + matcher.group(2));
            if (path.equalsIgnoreCase("C:\\Users\\Son Bui\\AppData\\Local\\app\\app.exe"))
                mLogger.info(" Got Path startup: Match");
        }

    }


}
