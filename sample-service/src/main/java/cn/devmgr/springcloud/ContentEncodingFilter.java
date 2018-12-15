package cn.devmgr.springcloud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Service
public class ContentEncodingFilter extends OncePerRequestFilter {
    Logger logger = LoggerFactory.getLogger(ContentEncodingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        String conentEncoding = request.getHeader("Content-Encoding");
        if(conentEncoding != null && ("gzip".equalsIgnoreCase(conentEncoding) || "deflate".equalsIgnoreCase(conentEncoding))){
            logger.trace("Content-Encoding: {}", conentEncoding);
            chain.doFilter(new GZIPRequestWrapper(request), response);
            return;
        }

        chain.doFilter(request, response);
    }
}
