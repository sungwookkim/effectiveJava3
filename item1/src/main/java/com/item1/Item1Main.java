package com.item1;

import com.item1.pizza.Pizza;
import com.item1.pizza.interfaces.Pizzaible;

/**
 * <pre>
 * item1 생성자 대신 정적 팩터리 메서드를 고려하라.
 * </pre>
 * 
 * @author sinnakeWEB
 */
public class Item1Main {
    public static void main( String[] args ) {
    	
    	/**
    	 * <pre>
    	 *  첫 번째, 이름을 가질 수 있다.
    	 *  생성자에 넘기는 매개변수와 생성자 자체만으로는 반환될 객체의 특성을 제대로 설명하지 못한다.
    	 *  반면 정적 팩터리는 이름만 잘 지으면 반환될 객체의 특성을 쉽게 묘사할 수 있다.
    	 *  
    	 *  한 클래스에 시그니처가 같은 생성자가 여러 개 필요하면 생성자를 정적 팩터리 메서드로 바꾸고 각각의 차이를
    	 *  잘 드러내는 이름을 지어주자.
    	 *  </pre> 
    	 */
        NameClass myName = NameClass.newInstance();
        System.out.println("newInstance : " + myName.getName());
        
        NameClass defaultName = NameClass.newDefaultNameInstance();
        System.out.println("newDefaultNameInstance : " + defaultName.getName());
        
        NameClass conName = NameClass.newNameInstance("sinnake default");
        System.out.println("newNameInstance : " + conName.getName());
        
        
        /**
         * <pre>
         * 두 번째, 호출될 때마다 인스턴스를 새로 생성하지는 않아도 된다.
         * 불변 클래스(아이템 17)는 인스턴스를 미리 만들어 놓거나 새로 생성한 인스턴스를 캐싱하여 재활용하는 식으로 불필요한 객체 생성을 피할 수 있다.
         * (Boolean.valueOf 메서드는 객체를 아예 생성하지 않는다.)
         * 따라서 생성 비용이 큰 객체가 자주 요청되는 상황이라면 성능을 상당히 끌어올려 준다. 
         * 플라이웨이트 패턴도 이와 비슷한 기법이라 할 수 있다.
         * 
         * 언제 어느 인스턴스를 살아 있게 할지를 철저히 통제할 수 있다.(이를 인스턴스 통제[instance-controlled] 클래스라 한다.)
         * 인스턴스를 통제하면
         * 	- 클래스를 싱글턴으로 만들 수 있다. (아이템3)
         * 	- 인스턴스화 불가로 만들 수도 있다. (아이템4) 
         * 	- 불변 값 클래스에서 동치인 인스턴스가 단 하나뿐 임을 보장할 수 있다(a == b일 때만 a.euals(b)가 성립)
         * 인스턴스 통제는 플라이웨이트 패턴의 근간이 되며, 열거 타입(아이템 34)은 인스턴스가 하나만 만들어짐을 보장한다.
         * </pre>
         */
        Caching notCaching = Caching.newInstance();
        System.out.println("caching name : " + notCaching.getCachingName());
        
        Caching caching = Caching.getInstance();
        System.out.println("caching name : " + caching.getCachingName());
        
        
        /**
         * <pre>
         * 세 번재, 반환 타입의 하위 타입 객체를 반환할 수 있는 능력이 있다.
         * - 반환할 객체의 클래스를 자유롭게 선택할 수 있게 하는 '엄청난 유연성'을 선물한다.
         * - API를 만들 때 이 유연성을 응용하면 구현 클래스를 공개하지 않고도 그 객체를 반환할 수 있어
         * API를 작게 유지할 수 있다.
         * - 이는 인터페이스를 정적 팩터리 메서드의 반환 타입으로 사용하는 인터페이스 기반 프레임워크(아이템 20)를
         * 만드는 핵심 기술이기도 하다.
         * 
         * </pre>
         */
        Pizzaible pizza = Pizza.newInstance();
        System.out.println("pizza name : " + pizza.getPizzaName());
        
        Pizzaible spinachPizza = Pizza.newSpinachPizza();
        System.out.println("spinach pizza name : " + spinachPizza.getPizzaName());
        
        Pizzaible bulgogiPizza = Pizza.newBulgogiPizza();
        System.out.println("bulgogi pizza name : " + bulgogiPizza.getPizzaName());
        
        
        /**
         * <pre>
         * 네 번째, 입력 매개변수에 따라 매번 다른 클래스의 객체를 반환할 수 있다.
         * 반환 타입의 하위 타입이기만 하면 어떤 클래스의 객체를 반환하든 상관없다.
         * 심지어 다음 릴리스에서는 또 다른 클래스의 객체를 반환해도 된다.
         * EnumSet 클래스(아이템 36)의 noneOf 메서드는 인수가 64개 이하면 RegularEnumSet의 인스턴스를
         * 이상이면 JumboEnumSet의 인스턴스를 반환한다.
         * 만약 다음 릴리스에서 RegularEnumSet의 사용 이점이 없어져 삭제하거나 다른 하위 타입으로 변경해도
         * 클라이언트는 팩터리가 건네주는 객체가 어느 클래스의 인스턴스인지 알 수도 없고 알 필요도 없기 떄문 이다.
         * 
		    public static <E extends Enum<E>> EnumSet<E> noneOf(Class<E> elementType) {
		        Enum<?>[] universe = getUniverse(elementType);
		        if (universe == null)
		            throw new ClassCastException(elementType + " not an enum");
		
		        if (universe.length <= 64)
		            return new RegularEnumSet<>(elementType, universe);
		        else
		            return new JumboEnumSet<>(elementType, universe);
		    }  
		    </pre>
         */
        
        /**
         * <pre>
         * 다섯 번째, 정적 팩터리 메서드를 작성하는 시점에는 반환할 객체의 클래스가 존재하지 않아도 된다.
         * - 서비스 제공자 프레임워크(service provider framework)를 만드는 근간이 된다.
         * - 대표적으로 JDBC(Java Database Connectivity)가 있다.
         * - 서비스 제공자 프레임워크에서의 제공자(provider)는 서비스의 구현체다. 그리고 이 구현체들을 클라이언트에
         * 제공하는 역할을 프레임워크가 통제하여, 클라이언트를 구현체로부터 분리해준다.
         * - 서비스 제공자 프레임워크는 3개의 핵심 컴포넌트로 이뤄진다.
         * 		1. 구현체의 동작을 정의하는 서비스 인터페이스(service interface)
         * 		2. 제공자가 구현체를 등록할 때 사용하는 제공자 등록 API(provider registration API)
         * 		3. 클라이언트가 서비스의 인스턴스를 얻을 때 사용하는 서비스 접근 API(service access API)
         * - 클라이언트는 서비스 접근 API를 사용할 때 원하는 구현체의 조건을 명시할 수 있다.
         * 조건을 명시하지 않으면 기본 구현체를 반환하거나 지원하는 구현체들을 하나씩 돌아가며 반환한다.
         * - 이 서비스 접근 API가 바로 서비스 제공자 프레임워크의 근간이라고 한 '유연한 정적 팩터리'의 실체다.
         * - 이 외 서비스 제공자 인터페이스(service provider interface)라는 네 번째 컴포넌트가 쓰이기도 한다.
         * 이 컴포넌트는 서비스 인터페이스의 인스턴스를 생성하는 팩터리 객체를 설명해준다.
         * - 서비스 제공자 인터페이스가 없다면 각 구현체를 인스턴스로 만들 때 리플렉션(아이템65)을 사용해야 한다.
         * - JDBC에서는
         * 		Connection이 서비스 인터페이스 역할을,
         * 		DriverManager.registerDrive가 제공자 등록 API 역할을,
         * 		DriverManager.getConnection이 서비스 접근 API 역할을,
         * 		Driver가 서비스 제공자 인터페이스 역할을
         * 수행한다.
         * - 서비스 제공자 프레임워크 패턴에는 여러 변형이 있다.
         * 		브리지 패턴(Bridge pattern)
         * 		의존 객체 주입(dependency injection, 의존성 주입) 프레임워크(아이템 5)
         * 도 강력한 서비스 제공자라고 생각할 수 있다.
         * - 자바 5부터는 java.util.ServiceLoader라는 범용 서비스 제공자 프레임워크가 제공되어 프레임워크를 직접 만든
         * 필요가 거의 없어졌다.
         * </pre>
         */
        
        /**
         * <pre>
         * 단점
         * 
         * 첫 번째, 상속을 하려면 public이나 protected 생성자가 필요하니 정적 팩터리 메서드만 제공하면 하위 클래스를 만들 수 없다.
         * 해당 제약은 상속보다 컴포지션을 사용(아이템 18)하도록 유도하고 불변 타입(아이템 17)으로 만들려면 이 제약을 지켜야 한다는 점에
         * 오히려 장점으로 받아들일 수도 있다.
         * 
         * 두 번째, 정적 팩터리 메서드는 프로그래머가 찾기 어렵다.
         * 생성자처럼 API 설명에 명확히 드러나지 않으니 사용자는 정적 팩터리 메서드 방식 클래스를 인스턴스화할 방법을 알아내야 한다.
         * 메서드 이름을 널리 알려진 규약을 따라 짓는 식으로 문제를 완화해줘야 한다.
         * 다음은 정적 팩터리 메서드에 흔히 사용하는 명명 방식들이다.
         * 
         * from : 매개변수를 하나 받아서 해당 타입의 인스턴스를 반환하는 형변환 메서드
         * 예) Date d = Date.form(instant);
         * 
         * of : 여러 매개변수를 받아 적합한 타입의 인스턴스를 반환하는 집계 메서드
         * 예) Set<Rank> faceCards = EnumSet.of(JACK, QUEEN, KING);
         * 
         * valueOf : from과 of의 더 자세한 버전
         * 예) BigInteger prime = BigInteger.valueOf(Integer.MAX_VALUE);
         * 
         * instance 혹은 getInstance : (매개변수를 받는다면) 매개변수로 명시한 인스턴스를 반환하지만, 같은 인스턴스임을 보장하지는 않는다.
         * 예) StackWalker luke = StackWalker.getInstance(options);
         * 
         * create 혹은 newInstance : instance 혹은 getInstance와 같지만, 매번 새로운 인스턴스를 생성해 반환함을 보장한다.
         * 예) Object newArray = Array.newInstance(classObject, arrayLen);
         * 
         * getType : getInstance와 같으나, 생성할 클래스가 아닌 다른 클래스에 팩터리 메서드를 정의할 때 쓴다.
         * "Type"은 팩터리 메서드가 반환할 객체의 타입이다.
         * 예) FileStore fs = Files.getFileStore(path);
         * 
         * newType : newInstance와 같으나, 생성할 클래스가 아닌 다른 클래스에 팩터리 메서드를 정의할 때 쓴다.
         * "Type"은 팩터리 메서드가 반환할 객체의 타입이다.
         * 예) BufferedReader br = Files.newBufferedReader(path);
         * 
         * type : getType과 newType의 간결한 버전
         * 예) List<Complaint> litany = Collections.list(legacyLitany);
         * </pre>
         */
    }
}