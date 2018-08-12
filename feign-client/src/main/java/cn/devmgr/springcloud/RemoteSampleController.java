package cn.devmgr.springcloud;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 这个和shared下面的SampleControoler是一样的。
 * 这样单独写一份（可以只写自己需要调用的方法），可以降低两个项目之间的耦合度，在实际项目中是值得的。
 *
 */
@RequestMapping("/ss")
public interface RemoteSampleController {

    @GetMapping
    public Map<String, Object> getAll();

}
