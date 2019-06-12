package cn.devmgr.springcloud.configclient;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RefreshScope     // 用途参照 README.md中进阶部分
@RestController
@RequestMapping("/")
public class SampleController {

    @Value("${sample:VALUENOTFOUND}")
    private String value;

    @GetMapping
    public Map<String, Object> get(){
        Map<String, Object> map = new HashMap<>();
        map.put("value", value);
        return map;
    }
}
