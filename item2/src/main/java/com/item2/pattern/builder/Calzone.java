package com.item2.pattern.builder;

public class Calzone extends Pizza {
	final private boolean sauceInside;
	
	public static class Builder extends Pizza.Builder<Builder> {
		private boolean sauceInside = false; // 기본값
		
		public Builder sauceInside() {
			this.sauceInside = true;
			return this;
		}
		
		@Override
		public Calzone build() {
			return new Calzone(this);
		}

		@Override
		protected Builder self() {
			return this;
		}
	}
	
	Calzone(Builder builder) {
		super(builder);
		this.sauceInside = builder.sauceInside;
	}

	public boolean getCalzoneSauceInside() {
		return this.sauceInside;
	}
}
