package org.delivery.api.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Slf4j
@Component
public class LoggerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // req, res 일회성을 읽을 수 있어서 로그 찍어도 정상로직 진행되도록 형변환
        ContentCachingRequestWrapper req = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper res = new ContentCachingResponseWrapper((HttpServletResponse) response);

        // 실행 전 (request > header, body 먼저 찍는 것이 BEST, 별도의 캐싱 클래스 필요)

        chain.doFilter(req, res);

        // 실행 후

        // request 정보
        StringBuilder requestHeaderValues = new StringBuilder();

        req.getHeaderNames().asIterator().forEachRemaining(headerKey -> {
            String headerValue = req.getHeader(headerKey);

            // authorization-token : ???, user-agent: ???
            requestHeaderValues.append("[").append(headerKey).append(" : ").append(headerValue).append("] ");
        });

        String requestBody = new String(req.getContentAsByteArray());
        String uri = req.getRequestURI();
        String method = req.getMethod();

        log.info(">>>>> uri :{}, method : {}, header : {}, body : {}", uri, method, requestHeaderValues, requestBody);

        // response 정보
        StringBuilder responseHeaderValues = new StringBuilder();

        res.getHeaderNames().forEach(headerKey -> {
            String headerValue = res.getHeader(headerKey);
            responseHeaderValues.append("[").append(headerKey).append(" : ").append(headerValue).append("] ");
        });

        String responseBody = new String(res.getContentAsByteArray());
        log.info("<<<<< uri :{}, method : {}, header : {}, body : {}", uri, method, responseHeaderValues, responseBody);

        // response의 바디를 읽었기 때문에 다시 초기화 필요(안하면 body 비어서 내려감)
        res.copyBodyToResponse();
    }
}
