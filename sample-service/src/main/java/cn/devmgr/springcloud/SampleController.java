package cn.devmgr.springcloud;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/ss")
public class SampleController{
    private static final Logger logger = LoggerFactory.getLogger(SampleController.class);
    
    @GetMapping
    public Map<String, Object> getAll(HttpServletRequest request){
        logger.trace("SampleController.getAll() was called.");
        if(logger.isTraceEnabled()) {
            // Spring Cloud Sleuth给请求增加了traceid, spanid等request header，以便追踪请求，
            // 这里是打印出来所有的header，以便查看sleuth工作原理
            Enumeration<String> headers = request.getHeaderNames();
            while(headers.hasMoreElements()){
                String h = headers.nextElement();
                logger.trace("\t {} = {}", h, request.getHeader(h));
            }
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("key1", "data from SampleController of sample-service");
        return map;
    }
    
    
}

