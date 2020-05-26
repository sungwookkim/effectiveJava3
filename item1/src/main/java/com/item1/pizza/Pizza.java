package com.item1.pizza;

import com.item1.pizza.interfaces.Pizzaible;

public class Pizza implements Pizzaible {

	Pizza() {}
	
	public String getPizzaName() {
		return "pizza";
	}
	
	public static Pizzaible newInstance() {
		return new Pizza();
	}
	
	public static Pizzaible newSpinachPizza() {
		return new Pizzaible() {
			
			public String getPizzaName() {
				return "spinachPizza";
			}
		};
	}
	
	public static Pizzaible newBulgogiPizza() {
		return new Pizzaible() {
			
			public String getPizzaName() {
				return "bulgogiPizza";
			}
		};
	}
}