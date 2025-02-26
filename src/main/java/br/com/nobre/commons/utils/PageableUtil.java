package br.com.nobre.commons.utils;

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
	
}
