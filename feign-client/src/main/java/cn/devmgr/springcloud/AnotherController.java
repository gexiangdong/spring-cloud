package cn.devmgr.springcloud;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/")
public class AnotherController {
    @Autowired RemoteSampleController client;
    
    @GetMapping
    public Map<String, Object> getAll(){
        
        Map<String, Object> map = client.getAll();
        if(!map.containsKey("kkk")) {
            map.put("kkk", "这个是在feignclient-sample里添加的");
        }
        
       
        return map;
    }

}
