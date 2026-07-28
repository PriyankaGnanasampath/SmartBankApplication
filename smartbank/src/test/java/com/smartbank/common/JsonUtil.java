package com.smartbank.common;

import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
public class JsonUtil {

	public static <T> T readJson(String fieName, Class<T> className)throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		InputStream inputStream = JsonUtil.class.getClassLoader().getResourceAsStream(fieName);
		return mapper.readValue(inputStream, className);
	}

}
