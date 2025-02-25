package br.com.nobre.commons.dto;

import java.util.List;

public class Page<T> {

	public final List<T> data;
	public final int start;
	public final int limit;
	public final long size;
	public final long totalPage;
	
	public Page(List<T> data, int start, int limit, long size, long totalPage) {
		this.data = data;
		this.start = start;
		this.limit = limit;
		this.size = size;
		this.totalPage = totalPage;
	}
	
}
