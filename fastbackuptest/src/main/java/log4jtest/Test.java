package log4jtest;

import java.util.logging.Logger;

/**
 * Created by zhaoyue on 16-8-22.
 */
public class Test {
    private static Logger logger = Logger.getLogger("Test.class");

    /**
     * @param args
     */
    public static void main(String[] args) {
        // System.out.println("This is println message.");

        // 记录debug级别的信息
//        logger.debug("This is debug message.");
        // 记录info级别的信息
        logger.info("This is info message.");
        // 记录error级别的信息
//        logger.error("This is error message.");
    }
}
