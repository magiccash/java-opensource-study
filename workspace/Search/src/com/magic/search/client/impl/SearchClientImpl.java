package com.magic.search.client.impl;

import java.util.List;

import com.magic.search.client.SearchClient;

public class SearchClientImpl<P,R> implements SearchClient<P, R>{

	protected String serviceUrl = null;
	
	public SearchClientImpl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}
	
	@Override
	public List<R> doSearch(P p) {
		
		return null;
	}

}
