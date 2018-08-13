package cn.devmgr.springcloud;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/ss")
public class SampleController{
    private static final Logger logger = LoggerFactory.getLogger(SampleController.class);
    
    @GetMapping
    public Map<String, Object> getAll(){
        logger.trace("SampleController.getAll() was called.");
        HashMap<String, Object> map = new HashMap<>();
        map.put("key1", "data from SampleController of sample-service");
        return map;
    }
    
    
}

