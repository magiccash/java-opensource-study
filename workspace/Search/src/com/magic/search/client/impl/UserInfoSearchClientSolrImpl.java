package com.magic.search.client.impl;

import java.util.List;

import com.magic.search.bean.param.UserInfoParamBean;
import com.magic.search.bean.result.UserInfoResultBean;
import com.magic.search.client.UserInfoSearchClient;

public class UserInfoSearchClientSolrImpl extends SearchClientImpl<UserInfoParamBean, UserInfoResultBean> implements
		UserInfoSearchClient<UserInfoParamBean,UserInfoResultBean> {

	public UserInfoSearchClientSolrImpl(String serviceUrl) {
		super(serviceUrl);
	}

	@Override
	public List<UserInfoResultBean> doSearch(UserInfoParamBean p) {
		
		
	
		return null;
	}
}
