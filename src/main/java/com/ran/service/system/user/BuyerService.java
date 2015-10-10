package com.ran.service.system.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ran.dao.DaoSupport;

@Service("buyerService")
public class BuyerService extends UserService{
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
}
