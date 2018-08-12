package cn.devmgr.springcloud;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/ss")
public class SampleController{
    
    @GetMapping
    public Map<String, Object> getAll(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("key1", "data from SampleController of sample-service");
        return map;
    }
    
    
}

