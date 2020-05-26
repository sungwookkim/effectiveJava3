package com.item1;

public class Caching {
	final private String cachingName;
	final private static Caching CACHING = new Caching("caching");
	
	Caching() {
		this.cachingName = "not caching";
	}
	
	Caching(String cachingName) {
		this.cachingName = cachingName;
	}
	
	/**
	 * <pre>
	 * 기본 생성자 정적 팩터리 메서드.
	 * </pre>
	 * 
	 * @return 기본 생성자로 생성한 객체를 반환한다.
	 */
	public static Caching newInstance() {
		return new Caching();
	}
	
	/**
	 * <pre>
	 * 캐싱되어 있는 CACHING 객체를 반환하는 정적 팩터리 메서드.
	 * </pre>
	 * 
	 * @return CACHING 객체를 반환한다.
	 */
	public static Caching getInstance() {
		return CACHING;
	}

	public String getCachingName() { return cachingName; }
}
