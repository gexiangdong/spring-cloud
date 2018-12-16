package cn.devmgr.springcloud;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 此Interceptor是在application.yml中配置使用的。
 */
public class FeignClientInterceptor implements RequestInterceptor {
    private final static Logger logger = LoggerFactory.getLogger(FeignClientInterceptor.class);


    @Override
    public void apply(RequestTemplate requestTemplate) {
        logger.trace("FeignClientInterceptor.apply url: {}", requestTemplate.url());

    }
}
