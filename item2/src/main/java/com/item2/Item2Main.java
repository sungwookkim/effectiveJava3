package com.item2;

import com.item2.pattern.JavaBeansPattern;
import com.item2.pattern.TelescopingConstructorPattern;

/**
 * <pre>
 * 생성자에 매개벼수가 많다면 빌더를 고려하라
 * </pre>
 * 
 * @author sinnakeWEB
 */
public class Item2Main {
    public static void main( String[] args ) {
        /**
         * <pre>
         * 정적 팩터리와 생성자에는 똑같은 제약이 하나 있다.
         * 선택적 매개변수가 많을 때 적절히 대응하기 어렵다는 점이다.
         * 이에 적절히 대응하기 위해서 프로그래머들은 점층적 생성자 패턴(telescoping constructor pattern)을 즐겨 사용했다.
         * 필수 매개변수만 받는 생성자, 필수 매개변수와 선택 매개변수 1개를 받는 생성자
         * 선택 매개변수를 2개까지 받는 생성자, ... 형태로 선택 매개변수를 전부 다 받는 생성자까지 늘려가는 방식이다.
         * </pre>
         */
    	
    	TelescopingConstructorPattern telescopingConstructorPattern =
    		/**
    		 * <pre>
    		 * 보통 이런 생성자는 사용자가 설정하길 원치 않는 매개변수까지 포함하기 쉽다.
    		 * 어쩔 수 없이 그런 매개변수에도 값을 지정해줘야 한다.
    		 * 아래 생성자는 fat에 0을 넘겼다.
    		 * </pre>
    		 */
    		new TelescopingConstructorPattern(240, 8, 100, 0, 35, 27);
    	
    	System.out.println(String.format("[TelescopingConstructorPattern]\n"
    			+ "servingSize : %d\nservings : %d\ncalories : %d\nfat : %d\nsodium : %d\ncarbohydrate : %d"
			, telescopingConstructorPattern.getServingSize()
			, telescopingConstructorPattern.getServings()
			, telescopingConstructorPattern.getCalories()
			, telescopingConstructorPattern.getFat()
			, telescopingConstructorPattern.getSodium()
			, telescopingConstructorPattern.getCarbohydrate()));
    	
    	/**
    	 * <pre>
    	 * 요약하면, 점층적 생성자 패턴도 쓸 수는 있지만, 매개변수 개수가 많아지면 클라이언트 코드를 작성하거나 읽기 어렵다.
    	 * - 코드를 읽을 때 각 값의 의미가 무엇인지 헷갈릴수 있다.
    	 * - 매개변수가 몇 개인지도 주의해서 세어 보아야 한다.
    	 * - 타입이 같은 매개변수가 연달아 늘어서 있으면 찾기 어려운 버그로 이어질 수 있다.
    	 * - 실수로 매개변수의 순서를 바꿔 건네줘도 컴파일러는 알아채지 못하고, 결국 런타임에 엉뚱한 동작을 하게 된다(아이템 51)
    	 * </pre>
    	 */
    	
    	/**
    	 * <pre>
    	 * 이번에는 선택 매개변수가 많을 때 활용할 수 있는 두 번째 대안인 자바빈즈 패턴(JavaBeans pattern)이다.
    	 * 매개변수가 없는 생성자로 객체를 만든 후, 세터(setter) 메서드들을 호출해 원하는 매개변수의 값을 설정하는 방식이다.
    	 * </pre>
    	 */
    	
    	/*
    	 * 위 TelescopingConstructorPattern에서와 다르게 fat를 강제로 설정하진 않는다.
    	 */
    	JavaBeansPattern javaBeansPattern = new JavaBeansPattern();
    	javaBeansPattern.setServingSize(240);
    	javaBeansPattern.setServings(8);
    	javaBeansPattern.setCalories(100);
    	javaBeansPattern.setSodium(35);
    	javaBeansPattern.setCarbohydrate(27);
    	
    	System.out.println(String.format("[JavaBeansPattern]\n"
    			+ "servingSize : %d\nservings : %d\ncalories : %d\nfat : %d\nsodium : %d\ncarbohydrate : %d"
			, javaBeansPattern.getServingSize()
			, javaBeansPattern.getServings()
			, javaBeansPattern.getCalories()
			, javaBeansPattern.getFat()
			, javaBeansPattern.getSodium()
			, javaBeansPattern.getCarbohydrate()));
    	
    	/**
    	 * <pre>
    	 * 단 자바빈즈 패턴은 심각한 단점을 지니고 있다.
    	 * 자바빈즈 패턴에서는 객체 하나를 만들려면 메서드를 여러 개 호출해야 하고, 객체가 완전히 생성되기 전까지는
    	 * 일관성(consistency)이 무너진 상태에 놓이게 된다.
    	 * 
    	 * 점층적 생성자 패턴에서는 매개변수들이 유효한지를 생성자에서만 확인하면 일관성을 유지할 수 있는데
    	 * 그 장치가 완전히 사라진 것이다.
    	 * 일관성이 깨진 객체가 만들어지면, 버그를 심은 코드와 그 버그 때문에 런타임에 문제를 겪는 코드가
    	 * 물리적으로 멀리 떨어져 있을 것이므로 디버깅도 만만치 않다.
    	 * 이처럼 일관성이 무너지는 문제 때문에
    	 * 		자바빈즈 패턴에서는 클래스를 불변(아이템 17)으로 만들수 없으며 스레드 안전성을 얻으려면 프로그래머가
    	 * 		추가 작업을 해줘야 한다.
    	 * </pre>
    	 */
    	
    }
}
