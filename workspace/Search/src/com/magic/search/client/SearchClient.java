package com.magic.search.client;

import java.util.List;

public interface SearchClient<P,R> {
	
	public List<R> doSearch(P p);

}
