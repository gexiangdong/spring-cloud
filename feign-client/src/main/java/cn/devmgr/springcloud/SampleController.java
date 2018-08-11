package cn.devmgr.springcloud;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.devmgr.springcloud.common.TvSeriesController;

@RestController
@RequestMapping("/sample")
public class SampleController {
    @Autowired TvSeriesController clientController;
    
    @GetMapping
    public Map<String, Object> getAll(){
        Map<String, Object> map = clientController.getAll();
        if(!map.containsKey("kkk")) {
            map.put("kkk", "这个是在feignclient-sample里添加的");
        }
        return map;
    }

}
