package cn.devmgr.springcloud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;

@Configuration
public class RestClientConfig {
    private final static Logger logger = LoggerFactory.getLogger(RestClientConfig.class);

    /**
     * request body超过1M时，自动开启压缩模式；（需要服务端支持）
     * 压缩和解压会增加程序处理时间，一般内网调用不压缩速度会更快；压缩只是在网络环境较差的情况下有使用意义。
     * @return
     */
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        if (CollectionUtils.isEmpty(interceptors)) {
            interceptors = new ArrayList<>();
        }
        interceptors.add(new RestTemplateCompressInterceptor());

        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }


    class RestTemplateCompressInterceptor implements ClientHttpRequestInterceptor {

        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                            ClientHttpRequestExecution execution) throws IOException {
            logger.trace("RestTemplateCompressInterceptor:intercept() body-length: {}", body == null ? 0 : body.length);
            byte[] newBody = null;
            if(body == null || body.length < 1024 * 1024){
                // 小于1M不开启压缩模式
                logger.trace("{}不需要压缩", body == null ? 0 : body.length);
                newBody = body;
            }else{
                logger.trace("{}开启压缩模式", body.length);
                request.getHeaders().add("Content-Encoding", "gzip");
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                GZIPOutputStream gzip;
                try {
                    gzip = new GZIPOutputStream(baos);
                    gzip.write(body);
                    gzip.close();
                } catch (IOException e) {
                    logger.error("压缩request body出错.", e);
                    throw(e);
                }
                newBody = baos.toByteArray();
                logger.trace("压缩后大小 {}", newBody.length);
            }
            ClientHttpResponse response = execution.execute(request, newBody);
            return response;
        }
    }
}