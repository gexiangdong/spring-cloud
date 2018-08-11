package cn.devmgr.springcloud.shared;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/tvseries")
public interface SampleController {
    
    @GetMapping
    public Map<String, Object> getAll();
    
    
}
