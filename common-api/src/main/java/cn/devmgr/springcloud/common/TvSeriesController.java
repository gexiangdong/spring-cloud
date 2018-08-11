package cn.devmgr.springcloud.common;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/tvseries")
public interface TvSeriesController {
    
    @GetMapping
    public Map<String, Object> getAll();
    
    
}
