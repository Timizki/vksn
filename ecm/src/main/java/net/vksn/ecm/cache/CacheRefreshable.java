package net.vksn.ecm.cache;

public interface CacheRefreshable {
	
	/**
	 * Refresh cache
	 * 
	 * @return result of cache refresh false if refresh failed true otherwise
	 */
	boolean refreshCache();

}
