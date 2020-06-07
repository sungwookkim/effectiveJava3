package com.item2.pattern.builder;

import java.util.Objects;

public class NyPizza extends Pizza {
	public enum Size { SMALL, MEDINUM, LARGE }
	final private Size size;
	
	public static class Builder extends Pizza.Builder<Builder> {
		final private Size size;
		
		public Builder(Size size) {
			this.size = Objects.requireNonNull(size);
		}
		
		@Override
		public NyPizza build() {
			return new NyPizza(this);					
		}

		@Override
		protected Builder self() {
			return this;
		}
	}
	
	NyPizza(Builder builder) {
		super(builder);
		this.size = builder.size;
	}

	public Size getNyPizzaSize() {
		return size;
	}
}
