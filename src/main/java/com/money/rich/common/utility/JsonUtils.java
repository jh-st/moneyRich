package com.money.rich.common.utility;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JsonUtils {

	public Map getStringToMap(String data) throws JsonProcessingException {
		return new ObjectMapper().readValue(data, Map.class);
	}

}
