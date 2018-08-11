package cn.devmgr.springcloud;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController;

import cn.devmgr.springcloud.common.TvSeriesController;


@RestController
public class TvSeriesControllerImpl implements TvSeriesController{
    
    public Map<String, Object> getAll(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("key1", "this is from TvSeriesControllerImpl of sample-service");
        return map;
    }
    
    
}
