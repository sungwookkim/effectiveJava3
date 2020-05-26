package com.item1;

public class NameClass {
	final private String name;	
	
	/**
	 * <pre>
	 * 생성자를 private으로 함으로서 객체 생성은 정적 팩터리 메서드로만 할 수 있게 유도한다.
	 * 단 생성자를 private로 했기 때문에 상속이 불가능한 클래스가 된다.
	 * </pre>
	 */
	NameClass() {
		this.name = "sinnake";
	}
	
	NameClass(String name) {
		this.name = name;
	}
	
	/**
	 * <pre>
	 * 기본 생성자 생성 정적 팩터리 매서드.
	 * </pre>
	 * 
	 * @return 기본 생성자를 실행한 객체를 반환한다.
	 */
	public static NameClass newInstance() {
		return new NameClass();
	}
	
	/**
	 * <pre>
	 * NameClass(String name) 생성자 생성 정적 팩터리 메서드.
	 * </pre>
	 * 
	 * @return default라고 값을 설정한 생성자를 반환한다.
	 */
	public static NameClass newDefaultNameInstance() {
		return new NameClass("default");
	}

	/**
	 * <pre>
	 * NameClass(String name) 생성자 생성 정적 팩터리 메서드.
	 * 
	 * name를 매개변수로 받아 생성한다.
	 * </pre>
	 * 
	 * @param name name을 설정할 이름 값.
	 * @return 외부로부터 전달받은 name 값으로 생성한 생성자를 반환한다.
	 */
	public static NameClass newNameInstance(String name) {
		return new NameClass(name);
	}
	
	public String getName() {
		return this.name;
	}
}