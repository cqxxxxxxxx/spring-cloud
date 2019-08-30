package com.cqx.web.feign;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.transform.Result;
import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/8/25
 */
public class CustomDecode implements Decoder {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private static final String CODE_KEY = "code";
    private static final String DATA_KEY = "data";
    private static final String MSG_KEY = "msg";

    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {

        throw new RuntimeException();
    }
}