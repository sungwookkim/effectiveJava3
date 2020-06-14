package com.item2;

import com.item2.pattern.BuilderPattern;
import com.item2.pattern.JavaBeansPattern;
import com.item2.pattern.TelescopingConstructorPattern;
import com.item2.pattern.builder.Calzone;
import com.item2.pattern.builder.NyPizza;
import com.item2.pattern.builder.NyPizza.Size;
import com.item2.pattern.builder.Pizza.Topping;

/**
 * <pre>
 * 생성자에 매개변수가 많다면 빌더를 고려하라
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
    	
    	System.out.println(String.format("\n[TelescopingConstructorPattern]\n"
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
    	
    	System.out.println(String.format("\n[JavaBeansPattern]\n"
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
    	
    	/**
    	 * <pre>
    	 * 위 2개의 패턴의 대안으로 점층적 생성자 패턴과 자바빈즈 패턴의 가독성을 겸비한 빌터 패턴이다.
    	 * 		1. 클라이언트는 필요한 객체를 직접 만드는 대신, 필수 매개변수만으로 생성자(혹은 정적 팩터리)를 호출해 빌더 객체를 얻는다. 
    	 * 		2. 그런 다음 빌더 객체가 제공하는 일종의 세터 메서드들로 원하는 선택 매개변수들을 설정한다.
    	 * 		3. 마지막으로 매개변수가 없는 build 메서드를 호출해 드디어 우리에게 필요한(보통 불변인) 객체를 얻는다.
    	 * 빌더는 생성할 클래스 안에 정적 멤버 클래스로 만들어두는게 보통이다.
    	 * </pre>
    	 */
    	
    	/**
    	 * <pre>
    	 * BuilderPattern 클래스는 불변이며, 모든 매개변수의 기본값들을 한곳에 모아뒀다.
    	 * 빌더의 세터 메서드들은 빌더 자신을 반환하기 떄문에 연쇄적으로 호출할 수 있다.
    	 * 이런 방식을 메서드 호출이 흐르듯 연결된다는 뜻으로 플루언트 API(fluent API) 혹은 메서드 연쇄(method chaining)라 한다.    	 
    	 * </pre>
    	 */
    	BuilderPattern builderPattern = new BuilderPattern.Builder(240, 8)
    		.calories(100).sodium(35).carbohydrate(27).build();
    	
    	System.out.println(String.format("\n[BuilderPattern]\n"
    			+ "servingSize : %d\nservings : %d\ncalories : %d\nfat : %d\nsodium : %d\ncarbohydrate : %d"
			, builderPattern.getServingSize()
			, builderPattern.getServings()
			, builderPattern.getCalories()
			, builderPattern.getFat()
			, builderPattern.getSodium()
			, builderPattern.getCarbohydrate()));
    	
    	/**
    	 * <pre>
    	 * 빌더 패턴은 (파이썬과 스칼라에 있는) 명명된 선택적 매개변수(named optional parameters)를 흉내 낸 것이다.
    	 * - 잘못된 매개변수를 최대한 일찍 발견하려면 빌더의 생성자와 메서드에서 입력 매개변수를 검사한다.
    	 * - build 메서드가 호출하는 생성자에서 여러 매개변수에 걸친 불변식(invariant)을 검사한다.
    	 * - 공격에 대비해 이런 불변식을 보장하려면 빌더로부터 매개변수를 복사한 후 해당 객체 필드들도 검사해야 한다.(아이템 50)
    	 * - 검사해서 잘못된 점을 발견하면 어떤 매개변수가 잘못되었는지를 자세히 알려주는 메세지를 담아 IllegalArgumentException을 던지면 된다.(아이템 75)
    	 * 
    	 * 옮긴이
    	 * 불변(immutalbe 혹은 immutability)은 어떤 변경도 허용하지 않는다는 뜻이다.
    	 * 주로 변경을 허용하는 가변(mutalbe) 객체와 구분하는 용도로 쓰인다.
    	 * 대표적으로 String 객체는 한번 만들어지면 절대 값을 바꿀 수 없는 불변 객체다.
    	 * 한편, 불변식(invariant)은 프로그램이 실행되는 동안, 혹은 정해진 기간 동안 반드시 만족해야 하는 조건을 말한다.
    	 * 다시 말해 변경을 허용할 수는 있으나 주어진 조건 내에서만 허용한다는 뜻이다.
    	 * 예컨대 리스트의 크기는 반드시 0 이상이어야 하니, 만약 한순간이라도 음수 값이 된다면 불변식이 깨진 것이다.
    	 * 또한, 기간을 표현하는 Period 클래스에서 start 필드의 값은 반드시 end필드의 값보다 앞서야 하므로, 두 값이 역전되면
    	 * 역시 불변식이 깨진 것이다.(아이템 50 참조)
    	 * 따라서 가변 객체에도 불변식은 존재할 수 있으며, 넓게 보면 불변은 불변식의 극단적인 예라 할 수 있다.
    	 * </pre>
    	 */
    	
    	/**
    	 * <pre>
    	 * 빌더 패턴은 계층적으로 설계된 클래스와 함께 쓰기에 좋다.
    	 * 각 계층의 클래스에 관련 빌더를 멤버로 정의하자.
    	 * 추상 클래스는 추상 빌더를, 구체 클래스(concrete class)는 구체 빌더를 갖게 한다.
    	 * 
    	 * 자세한 내용은 각 클래스에 작성된 주석을 참고.
    	 * 
    	 * 빌더 패턴은 상당히 유연하다. 
    	 * 		- 빌더 하나로 여러 객체를 순회하면서 만들 수 있고,
    	 * 		- 빌더에 넘기는 매개변수에 따라 다른 객체를 만들 수도 있다.
    	 * 		- 객체마다 부여되는 일련번호와 같은 특정 필드는 빌더가 알아서 채우도록 할 수도 있다.
    	 * 빌더 패턴에 장점만 있는 것은 아니다. 
    	 * 		- 객체를 만들려면, 그에 앞서 빌더부터 만들어야 한다.
    	 * 		- 빌더 생성 비용이 크지는 않지만 성능에 민감한 상황에서는 문제가 될 수 있다.
    	 * 		- 또한 점층적 생성자 패턴보다는 코드가 장황해서 매개변수가 4개 이상은 되여야 값어치를 한다.
    	 * 		하지만 API는 시간이 지날수록 매개변수가 많아지는 경향이 있음을 명심하자.
    	 * 		- 생성자나 정적 팩터리 방식으로 시작했다가 나중에 매개변수가 많아지면 빌더 패턴으로 전환할 수도 있지만,
    	 * 		이전에 만들어둔 생성자와 정적 팩터리가 도드라져 보일 것이다. 
    	 * 		그러니 애초에 빌더로 시작하는 편이 나을 때가 많다.
    	 * 
    	 * 핵심정리
    	 * 생성자나 정적 팩터리가 처리해야 할 매개변수가 많다면 빌더 패턴을 선택하는 겟 더 낫다.
    	 * 매개변수 중 다수가 필수가 아니거나 같은 타입이면 특히 더 그렇다.
    	 * 빌더는 점층적 생성자보다 클라이언트 코드를 읽고 쓰기가 훨씬 간결하고, 자바빈즈보다 훨씬 안전하다.
    	 * </pre>
    	 */
    	NyPizza nyPizza = new NyPizza.Builder(Size.SMALL)
    		/**
    		 * 생성자로는 누릴 수 없는 사소한 이점으로, 빌더를 이용하면 가변인수(varargs) 매개변수를 여러 개 사용할 수 있다.
    		 * 각각을 적절한 메서드로 나눠 선언하면 된다. 
    		 * 아니면 메서드를 여러 번 호출하도록 하고 각 호출 때 넘겨진 매개변수들을 하나의 필드로 모을 수도 있다.
    		 * 아래 addTopping 메서드가 이렇게 구현한 예이다.
    		 */
    		.addTopping(Topping.SAUSAGE)
    		.addTopping(Topping.ONION).build();

    	Calzone calzone = new Calzone.Builder()
			.addTopping(Topping.HAM)
			.sauceInside().build();

    	System.out.println(String.format("\n[NyPizza BuilderPattern] NyPizzaSize : %s, PizzaToppings : %s"
			, nyPizza.getNyPizzaSize()
			, nyPizza.getPizzaToppings()));

    	System.out.println(String.format("\n[Calzone BuilderPattern] sauceInside : %s, PizzaToppings : %s"
			, calzone.getCalzoneSauceInside()
			, calzone.getPizzaToppings()));
    }
}
