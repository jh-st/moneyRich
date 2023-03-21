package com.money.rich.common.utility;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.money.rich.common.response.ResponseResult;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ResponseUtils {

	public <T> ResponseResult<T> getResult(final T data) {
		final ResponseResult<T> result = new ResponseResult<>();
		result.setData(data);
		result.setStatus(HttpStatus.OK.value());
		result.setDateTime(LocalDateTime.now());
		return result;
	}

	public <T> ResponseResult<T> getResult(final int status, final String message, final String detail) {
		final ResponseResult<T> result = new ResponseResult<>();
		result.setStatus(status);
		result.setMessage(message);
		result.setDateTime(LocalDateTime.now());
		result.setDetail(detail);
		return result;
	}


}
