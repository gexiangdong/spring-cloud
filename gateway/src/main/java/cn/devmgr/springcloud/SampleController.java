package cn.devmgr.springcloud;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/gs")
public class SampleController {
    private static final Logger logger = LoggerFactory.getLogger(SampleController.class);


    @GetMapping
    public Map<String, Object> getAll() {
        logger.trace("SampleController.getAll() was called.");
        HashMap<String, Object> map = new HashMap<>();
        map.put("key1", "data from SampleController of sample-service ");
        return map;
    }


    @GetMapping("/response-entity-builder-with-http-headers")
    public ResponseEntity<String> usingResponseEntityBuilderAndHttpHeaders() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Baeldung-Example-Header",
                "Value-ResponseEntityBuilderWithHttpHeaders");

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body("Response with header using ResponseEntity");
    }

}

