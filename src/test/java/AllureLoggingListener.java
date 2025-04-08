import io.qameta.allure.Attachment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureLoggingListener implements ITestListener {
    private static final Logger logger = LoggerFactory.getLogger(AllureLoggingListener.class);

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Test failed: {}", result.getName());
        saveLog();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test passed: {}", result.getName());
        saveLog();
    }

    @Attachment(value = "Test Logs", type = "text/plain")
    public static String saveLog() {
        return "Check logs for details...";
    }
}