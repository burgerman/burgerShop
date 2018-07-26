package org.lanqiao.tjut.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.lanqiao.tjut.bean.TBOperatorBean;
import org.lanqiao.tjut.dao.OpLoginDao;
import org.lanqiao.tjut.service.OpLoginService;
import org.springframework.stereotype.Service;

@Service("operS")
public class OpLoginServiceImpl implements OpLoginService{
	
	
	@Resource
	private OpLoginDao operD;
	
	@Override
	public List<TBOperatorBean> getOperLoginInfo(TBOperatorBean operB) {
		 
		List<TBOperatorBean> lstOper = operD.getOperLoginInfo(operB);
	
		 return lstOper;
	}

}
