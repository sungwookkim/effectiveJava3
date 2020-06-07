package com.item2.pattern;

public class BuilderPattern {

	private final int servingSize;
	private final int servings;
	private final int calories;
	private final int fat;
	private final int sodium;
	private final int carbohydrate;
	
	public static class Builder {
		// 필수 매개변수
		private final int servingSize;
		private final int servings;
		
		// 선택 매개변수 - 기본값을 초기화한다.
		private int calories = 0;
		private int fat = 0;
		private int sodium = 0;
		private int carbohydrate = 0;
		
		public Builder(int servingsSize, int servings) {
			this.servingSize = servingsSize;
			this.servings = servings;
		}

		public Builder calories(int calories) {
			this.calories = calories;
			return this;
		}
		
		public Builder fat(int fat) {
			this.fat = fat;
			return this;
		}
		
		public Builder sodium(int sodium) {
			this.sodium = sodium;
			return this;
		}
		
		public Builder carbohydrate(int carbohydrate) {
			this.carbohydrate = carbohydrate;
			return this;
		}
		
		public BuilderPattern build() {
			return new BuilderPattern(this);
		}
	}
	
	private BuilderPattern(Builder builder) {
		servingSize = builder.servingSize;
		servings = builder.servings;
		calories = builder.calories;
		fat = builder.fat;
		sodium = builder.sodium;
		carbohydrate = builder.carbohydrate;
	}

	public int getServingSize() {
		return servingSize;
	}

	public int getServings() {
		return servings;
	}

	public int getCalories() {
		return calories;
	}

	public int getFat() {
		return fat;
	}

	public int getSodium() {
		return sodium;
	}

	public int getCarbohydrate() {
		return carbohydrate;
	}	
}
