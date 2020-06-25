package azure;

import com.google.gson.Gson;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class AzureMain {
    private static final Logger logger = LoggerFactory.getLogger(AzureMain.class);

    public static void main(String[] args) {
        HashMap<String, Object> req = new HashMap<>();
        req.put("deviceId", "testDevice01:testDevice02:testDevice03:testDevice04:testDevice05:testDevice06");
        req.put("commandData", "commandData");
        getProcessedReqDataTest(req);
    }

    private static ArrayList<HashMap<String, Object>> getProcessedReqDataTest(HashMap<String, Object> command) {
        UUID sessionId = UUID.randomUUID();
        StopWatch stopWatch = new StopWatch();
        if (command.get("deviceId") != null) {

            String[] dvcIds = StringUtils.split((String) command.get("deviceId"), ":");
            HashMap<String, Object> common = (HashMap<String, Object>) command.clone();
            common.remove("deviceId");

            logger.info("[ASYNC_MULTI_DVC_CMD][{}] PARALLEL PROCESSING-START: COUNT={}", sessionId, dvcIds.length);
            stopWatch.start();

            Observable.fromArray(dvcIds)
                    .flatMap(req ->
                            Observable.fromCallable(() -> sendAsyncCommand(req, common, sessionId))
                                    .subscribeOn(Schedulers.io())
                    )
                    .onErrorReturn(e -> {
                        if (e instanceof IOException) {
                            logger.error("[ASYNC_MULTI_DVC_CMD][{}] IOException", sessionId);
                            e.printStackTrace();
                        }
                        else if (e instanceof Exception) {
                            logger.error("[ASYNC_MULTI_DVC_CMD][{}] Exception", sessionId);
                            e.printStackTrace();
                        }
                        else {
                            logger.error("[ASYNC_MULTI_DVC_CMD][{}] CAN NOT CATCH Exception", sessionId);
                            e.printStackTrace();
                        }
                        return "EXCEPTION";
                    })
                    .doOnComplete(() ->
                            logger.info("[ASYNC_MULTI_DVC_CMD][{}] PARALLEL PROCESSING-PUBLISHING END", sessionId))
                    .subscribe(result -> {
                        if (!result.equals("OK")) {
                            logger.error("[ASYNC_MULTI_DVC_CMD][{}] The exception(s) occurred when parallel processing. Check the request data!!!", sessionId);
                            return;
                        }
                    });

            stopWatch.stop();
            logger.info("[ASYNC_MULTI_DVC_CMD][{}] PARALLEL PROCESSING-MAIN THREAD END: TOTAL_TIME={}ms", sessionId, stopWatch.getTotalTimeMillis());
        } else {
            logger.error("[ASYNC_MULTI_DVC_CMD][{}] 'deviceId' is null! / DATA={}", sessionId, ObjectUtils.nullSafeToString(command));
            throw new InvalidParameterException();
        }
        return null;
    }

    public static String sendAsyncCommand(String dvcId, HashMap<String, Object> commandContent, UUID sessionId) {

        UUID internalSessionId = UUID.randomUUID();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        HashMap<String, Object> command = (HashMap<String, Object>) commandContent.clone();
        command.put("deviceId", dvcId);
        Gson gson = new Gson();
        String payload = gson.toJson(command);

        logger.info("[ASYNC_MULTI_DVC_CMD][{}][{}] INVOKE-START: TARGET={} / DATA={}", sessionId, internalSessionId, (String) command.get("deviceId"), payload);
//        try {
//            throw new InvalidParameterException();
//            MethodResult result = iotHubInitUtil.getInstance()
//                    .invoke((String) command.get("deviceId"), method, responseTimeout,
//                            connectTimeout, payload);
//            if (result == null) {
//                stopWatch.stop();
//                logger.error("[ASYNC_MULTI_DVC_CMD][{}][{}] NULL RETURNED : executionTime={}ms", sessionId, internalSessionId, stopWatch.getTotalTimeMillis());
//                // throw new IOException("Method invoke returns null");
//            } else {
//                stopWatch.stop();
//                logger.info("[ASYNC_MULTI_DVC_CMD][{}][{}] INVOKE-END: executionTime={}ms / result={}", sessionId, internalSessionId, stopWatch.getTotalTimeMillis(), gson.toJson(result));
//            }
//        } catch (Exception ex) {
//            stopWatch.stop();
//            logger.error("[ASYNC_MULTI_DVC_CMD][{}][{}] EXCEPTION: executionTime={}ms", sessionId, internalSessionId, stopWatch.getTotalTimeMillis());
//            ex.printStackTrace();
//        }

        return "OK";
    }
}
