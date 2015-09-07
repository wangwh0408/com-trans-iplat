package com.allscore.trans.iplat.core;

public abstract class AbstracCoreTransHandle implements CoreTransHandle {

	
	private CoreTransHandle nextCoreTransHandle;
	


	public CoreTransHandle getNextCoreTransHandle() {
		return nextCoreTransHandle;
	}

	public void setNextCoreTransHandle(CoreTransHandle nextCoreTransHandle) {
		this.nextCoreTransHandle = nextCoreTransHandle;
	}
	
	

}
