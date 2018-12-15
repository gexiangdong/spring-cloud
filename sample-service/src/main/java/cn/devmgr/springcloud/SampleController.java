package cn.devmgr.springcloud;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
            printRequestHeaders(request);
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("key1", "data from SampleController of sample-service");
        return map;
    }


    @PostMapping
    public Map<String, Object> createOne(HttpServletRequest request, @RequestBody Map<String, Object> body){
        logger.trace("SampleController.createOne() was called.");
        printRequestHeaders(request);
        logger.trace("requst body: {}", body);

        HashMap<String, Object> map = new HashMap<>();
        map.put("message", "ok");
        return map;
    }

    private void printRequestHeaders(HttpServletRequest request) {
        Enumeration<String> headers = request.getHeaderNames();
        while(headers.hasMoreElements()){
            String h = headers.nextElement();
            logger.trace("\t {} = {}", h, request.getHeader(h));
        }

    }


}

