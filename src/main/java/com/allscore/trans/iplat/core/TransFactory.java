package com.allscore.trans.iplat.core;

import java.io.Serializable;
import java.util.List;

public class TransFactory implements Serializable {
	


	/**
	 * 
	 */
	private static final long serialVersionUID = -8207227649014331671L;
	
	private List<CoreTransHandle> coreTransHandleList;

	public List<CoreTransHandle> getCoreTransHandleList() {
		return coreTransHandleList;
	}

	public void setCoreTransHandleList(List<CoreTransHandle> coreTransHandleList) {
		this.coreTransHandleList = coreTransHandleList;
	}
	
	
	
	
	

}
