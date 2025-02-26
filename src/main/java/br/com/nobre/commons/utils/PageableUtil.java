package br.com.nobre.commons.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.nobre.commons.dto.PageDto;
import br.com.nobre.commons.dto.PageableDto;
import br.com.nobre.commons.exception.InvalidParamsException;

public class PageableUtil {
	
	public static PageableDto convertParamsToPageable(Object startParam, Object limitParam) throws InvalidParamsException {
		
		int start = startParam != null ? Integer.valueOf(startParam.toString()) : 0;
		int limit = limitParam != null ? Integer.valueOf(limitParam.toString()) : 100;

		if(start < 0 || limit < 0) {
			throw new InvalidParamsException("Os parâmetros de paginação não podem ser menores que 0");
		}
		
		if(limit > 100) {
			throw new InvalidParamsException("Limit não pode ser maior que 100");
		}
		
		PageableDto pageableDto = new PageableDto(start, limit);
		return pageableDto;
		
	}
	
	public static Map<String, Object> createParamnsMap(Map<String, String[]> parameterMap) {

		Map<String, Object> paramsMap = new HashMap<>();

		for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
			String key = entry.getKey();
			String[] val = entry.getValue();

			paramsMap.put(key, val[0]);

		}

		return paramsMap;

	}

	public static <T> PageDto<T> createPage(List<T> data, int start, int limit, long size) {
		
		int page = limit != 0 ? limit : 1;
		long totalPage = size / page;

		if ((size % page) != 0) {
			totalPage++;
		}

		return new PageDto<T>(data, start, limit, size, totalPage <= 0 ? 1 : totalPage);
	}
	
}
