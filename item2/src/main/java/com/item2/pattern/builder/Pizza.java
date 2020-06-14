package com.item2.pattern.builder;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public abstract class Pizza {
	public enum Topping { HAM, MUSHROOM, ONION, PEPPER, SAUSAGE }
	final private Set<Topping> toppings;
	
	/**
	 * <pre>
	 * 재귀적 타입 한정(아이템30)을 이용하는 제네릭 타입이다.
	 * 여기에 추상 메서드인 self를 더해 하위 클래스에서 형변환하지 않고도
	 * 메서드 연쇄를 지원할 수 있다.
	 * self 타입이 없는 자바를 위한 이 우회 방법을 시뮬레이트한 셀프 타입(simulated self-type) 관용구라 한다.
	 * 
	 * 해당 추상 빌더를 가지고 크기(size) 매개변수를 필수로 받는 뉴욕 피자
	 * 소스를 안에 넣을지 선택(sauceInside)하는 매개변수를 필수로 받는 갈초네 피자를 구현한다. 
	 * </pre>
	 * 
	 * @author sinnakeWEB
	 *
	 * @param <T> Pizza 추상 빌더를 구현할 구현 클래스 타입
	 */
	abstract static class Builder<T extends Builder<T>> {
		EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);
		
		public T addTopping(Topping topping) {
			toppings.add(Objects.requireNonNull(topping));
			return self();
		}
		
		/**
		 * <pre>
		 * 각 하위 클래스의 빌더가 정의한 build 메서드는 해당하는 구체 하위 클래스를 반환하도록 선언한다.
		 * NyPizza.Builder는 NyPizza를 반환하고, Calzone.Builder는 Calzone를 반환한다는 뜻이다.
		 * 하위 클래스의 메서드가 상위 클래스의 메서드를 정의한 반환 타입이 아닌, 그 하위 타입을 반환하는 기능을
		 * 공변 반환 타이핑(covariant return typing)이라 한다.
		 * 이 기능을 이용하면 클라이언트가 형변환에 신경 쓰지 않고도 빌더를 사용할 수 있다.
		 * </pre>
		 * @return 하위 타입을 반환하는 공변 반환 타이핑
		 */
		abstract Pizza build();
		
		/*
		 * 하위 클래스는 이 메서드를 재정의(overriding)하여 "this"를 반환하도록 해야 한다.
		 */
		protected abstract T self();
	}
	
	Pizza(Builder<?> builder){
		this.toppings = builder.toppings.clone();
	}

	public Set<Topping> getPizzaToppings() {
		return toppings;
	}
}
