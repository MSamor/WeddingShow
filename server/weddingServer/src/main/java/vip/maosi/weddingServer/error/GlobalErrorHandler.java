package vip.maosi.weddingServer.error;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import vip.maosi.weddingServer.response.RGenerator;
import vip.maosi.weddingServer.util.JsonUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Slf4j
@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(MissingRequestValueException.class)
    public void handlerBadRequestError(
            HttpServletRequest request,
            HttpServletResponse response,
            MissingRequestValueException exception
    ) throws IOException {
        String query = request.getQueryString();
        String url = StringUtils.isEmpty(query)
                ? request.getRequestURL().toString()
                : request.getRequestURL() + "?" + query;
        log.error(String.format("!!! 请求内容不合规, method: %s, url: %s", request.getMethod(), url), exception);
        String message = "Bad Request";
        if (exception instanceof MissingServletRequestParameterException) {
            var name = ((MissingServletRequestParameterException) exception).getParameterName();
            var type = ((MissingServletRequestParameterException) exception).getParameterType();
            message = String.format("缺少名为%s的%s类型参数", name, type);
        } else if (exception instanceof MissingRequestHeaderException) {
            var name = ((MissingRequestHeaderException) exception).getHeaderName();
            message = String.format("缺少请求头: %s", name);
        } else if (exception instanceof MissingRequestCookieException) {
            var name = ((MissingRequestCookieException) exception).getCookieName();
            message = String.format("缺少Cookie: %s", name);
        } else if (exception instanceof MissingPathVariableException) {
            var name = ((MissingPathVariableException) exception).getVariableName();
            message = String.format("缺少Path: %s", name);
        } else if (exception instanceof MissingMatrixVariableException) {
            var name = ((MissingMatrixVariableException) exception).getVariableName();
            message = String.format("缺少Matrix: %s", name);
        }
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        var result = JsonUtils.toJson(RGenerator.resCustom(-1, message));
        response.getOutputStream().write(result.getBytes(StandardCharsets.UTF_8));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public void handleNotFoundError(
            HttpServletRequest request,
            HttpServletResponse response,
            NoHandlerFoundException exception
    ) throws IOException {
        String query = request.getQueryString();
        String url = StringUtils.isEmpty(query)
                ? request.getRequestURL().toString()
                : request.getRequestURL() + "?" + query;
        log.error(String.format("!!! 尝试访问不存在的接口, method: %s, url: %s", request.getMethod(), url));
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        var result = JsonUtils.toJson(RGenerator.resCustom(-1, "接口不存在"));
        response.getOutputStream().write(result.getBytes(StandardCharsets.UTF_8));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public void handleNotReadableError(
            HttpServletRequest request,
            HttpServletResponse response,
            HttpMessageNotReadableException exception
    ) throws IOException {
        String query = request.getQueryString();
        String url = StringUtils.isEmpty(query)
                ? request.getRequestURL().toString()
                : request.getRequestURL() + "?" + query;
        log.error(String.format("!!! 请求正文内容格式不正确, method: %s, url: %s", request.getMethod(), url), exception);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        var msg = "请求正文内容格式不正确";
        var result = JsonUtils.toJson(RGenerator.resCustom(-1, msg));
        response.getOutputStream().write(result.getBytes(StandardCharsets.UTF_8));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public void handleTypeMismatchError(
            HttpServletRequest request,
            HttpServletResponse response,
            MethodArgumentTypeMismatchException exception
    ) throws IOException {
        String query = request.getQueryString();
        String url = StringUtils.isEmpty(query)
                ? request.getRequestURL().toString()
                : request.getRequestURL() + "?" + query;
        log.error(String.format("!!! 参数类型不匹配, method: %s, url: %s", request.getMethod(), url), exception);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        final var requiredTypeName = ClassUtils.getSimpleName(exception.getRequiredType());
        final var paramTypeName = ClassUtils.getSimpleName(exception.getValue());
        final var msg = String.format("参数类型不匹配，名为%s的参数需要的类型是%s，提供的类型是%s，提供的值为%s", exception.getName(), requiredTypeName, paramTypeName, exception.getValue());
        var result = JsonUtils.toJson(RGenerator.resCustom(-1, msg));
        response.getOutputStream().write(result.getBytes(StandardCharsets.UTF_8));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public void handleMethodNotSupportedError(
            HttpServletRequest request,
            HttpServletResponse response,
            HttpRequestMethodNotSupportedException exception
    ) throws IOException {
        String query = request.getQueryString();
        String url = StringUtils.isEmpty(query)
                ? request.getRequestURL().toString()
                : request.getRequestURL() + "?" + query;
        log.error(String.format("!!! 请求方式不支持, method: %s, url: %s", request.getMethod(), url), exception);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        final var msg = String.format("不支持%s请求方式，只支持%s请求方式", exception.getMethod(), Arrays.toString(exception.getSupportedMethods()));
        final var result = JsonUtils.toJson(RGenerator.resCustom(-1, msg));
        response.getOutputStream().write(result.getBytes(StandardCharsets.UTF_8));
    }

    @ExceptionHandler({ConstraintViolationException.class, MethodArgumentNotValidException.class})
    public void handleViolationExceptionError(
            HttpServletRequest request,
            HttpServletResponse response,
            ConstraintViolationException exception
    ) throws IOException {
        String query = request.getQueryString();
        String url = StringUtils.isEmpty(query)
                ? request.getRequestURL().toString()
                : request.getRequestURL() + "?" + query;
        log.error(String.format("参数错误, method: %s, url: %s", request.getMethod(), url), exception);
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        var msg = "参数校验错误，请检查后重试!原因:" + exception.getMessage();
        var res = RGenerator.resCustom(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
        var result = JsonUtils.toJson(res).getBytes(StandardCharsets.UTF_8);
        response.getOutputStream().write(result);
    }

    @ExceptionHandler(Throwable.class)
    public void handleGlobalError(
            HttpServletRequest request,
            HttpServletResponse response,
            Throwable error
    ) throws IOException {
        String query = request.getQueryString();
        String url = StringUtils.isEmpty(query)
                ? request.getRequestURL().toString()
                : request.getRequestURL() + "?" + query;
        log.error(String.format("发生未知错误, method: %s, url: %s", request.getMethod(), url), error);
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        var msg = "服务繁忙，请稍后再试";
        var res = RGenerator.resCustom(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
        var result = JsonUtils.toJson(res).getBytes(StandardCharsets.UTF_8);
        response.getOutputStream().write(result);
    }
}
