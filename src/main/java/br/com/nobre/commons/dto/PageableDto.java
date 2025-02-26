package br.com.nobre.commons.dto;

public class PageableDto {
	
	public final int start;
	public final int limit;
	
	public PageableDto(int start, int limit) {
		this.start = start * limit;
		this.limit = limit;
	}
	
}
