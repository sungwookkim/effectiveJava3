package com.item1;

/**
 * item1 생성자 대신 정적 팩터리 메서드를 고려하라.
 *  
 */
public class App {
    public static void main( String[] args ) {
    	/**
    	 *  첫 번째, 이름을 가질 수 있다.
    	 *  생성자에 넘기는 매개변수와 생성자 자체만으로는 반환될 객체의 특성을 제대로 설명하지 못한다.
    	 *  반면 정적 팩터리는 이름만 잘 지으면 반환될 객체의 특성을 쉽게 묘사할 수 있다.
    	 *  
    	 *  한 클래스에 시그니처가 같은 생성자가 여러 개 필요하면 생성자를 정적 팩터리 메서드로 바꾸고 각각의 차이를
    	 *  잘 드러내는 이름을 지어주자. 
    	 */
        NameClass myName = NameClass.newInstance();
        System.out.println("newInstance : " + myName.getName());
        
        NameClass defaultName = NameClass.newDefaultNameInstance();
        System.out.println("newDefaultNameInstance : " + defaultName.getName());
        
        NameClass conName = NameClass.newNameInstance("sinnake default");
        System.out.println("newNameInstance : " + conName.getName());
        
        
        /**
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
         */
        Caching notCaching = Caching.newInstance();
        System.out.println("caching name : " + notCaching.getCachingName());
        
        Caching caching = Caching.getInstance();
        System.out.println("caching name : " + caching.getCachingName());
        
        
        /**
         * 세 번재, 반환 타입의 하위 타입 객체를 반환할 수 있는 능력이 있다.
         * 반환할 객체의 클래스를 자유롭게 선택할 수 있게 하는 '엄청난 유연성'을 선물한다.
         */
        Pizzaible pizza = Pizza.newPizzaInstance();
        System.out.println("pizza name : " + pizza.getPizzaName());
        
        Pizzaible spinachPizza = Pizza.newSpinachPizzaInstance();
        System.out.println("spinach pizza name : " + spinachPizza.getPizzaName());
        
        Pizzaible bulgogiPizza = Pizza.newBulgogiPizzaInstance();
        System.out.println("bulgogi pizza name : " + bulgogiPizza.getPizzaName());
        
        
        /**
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
         */
        
    }
}

class NameClass {
	final private String name;	
	
	/**
	 * 생성자를 private으로 함으로서 객체 생성은 정적 팩터리 메서드로만 할 수 있게 유도한다.
	 * 단 생성자를 private로 했기 때문에 상속이 불가능한 클래스가 된다.
	 */
	NameClass() {
		this.name = "sinnake";
	}
	
	NameClass(String name) {
		this.name = name;
	}
	
	/**
	 * 기본 생성자 생성 정적 팩터리 매서드.
	 * 
	 * @return 기본 생성자를 실행한 객체를 반환한다.
	 */
	public static NameClass newInstance() {
		return new NameClass();
	}
	
	/**
	 * NameClass(String name) 생성자 생성 정적 팩터리 메서드.
	 * 
	 * @return default라고 값을 설정한 생성자를 반환한다.
	 */
	public static NameClass newDefaultNameInstance() {
		return new NameClass("default");
	}

	/**
	 * NameClass(String name) 생성자 생성 정적 팩터리 메서드.
	 * 
	 * name를 매개변수로 받아 생성한다.
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

class Caching {
	final private String cachingName;
	final private static Caching CACHING = new Caching("caching");
	
	Caching() {
		this.cachingName = "not caching";
	}
	
	Caching(String cachingName) {
		this.cachingName = cachingName;
	}
	
	/**
	 * 기본 생성자 정적 팩터리 메서드.
	 * 
	 * @return 기본 생성자로 생성한 객체를 반환한다.
	 */
	public static Caching newInstance() {
		return new Caching();
	}
	
	/**
	 * 캐싱되어 있는 CACHING 객체를 반환하는 정적 팩터리 메서드.
	 * 
	 * @return CACHING 객체를 반환한다.
	 */
	public static Caching getInstance() {
		return CACHING;
	}

	public String getCachingName() { return cachingName; }
}

interface Pizzaible {
	public String getPizzaName();
}

class Pizza implements Pizzaible {

	Pizza() {}
	
	public String getPizzaName() {
		return "pizza";
	}
	
	public static Pizzaible newPizzaInstance() {
		return new Pizza();
	}
	
	public static Pizzaible newSpinachPizzaInstance() {
		return new Pizzaible() {
			
			public String getPizzaName() {
				return "spinachPizza";
			}
		};
	}
	
	public static Pizzaible newBulgogiPizzaInstance() {
		return new Pizzaible() {
			
			public String getPizzaName() {
				return "bulgogiPizza";
			}
		};
	}
}

