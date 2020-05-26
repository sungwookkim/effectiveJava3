package com.item2.pattern;

public class JavaBeansPattern {
	/*
	 * 자바빈즈는 변수들을 불변으로 만들 수가 없다.
	 */
	private int servingSize = -1; // (ml, 1회 제공량) 필수; 기본값 없음
	private int servings = -1; // (회, 총 n회 제공량) 필수; 기본값 없음
	private int calories = 0; // (1회 제공량당) 선택
	private int fat = 0; // (g/1회 제공량) 선택
	private int sodium = 0; // (mg/1회 제공량) 선택
	private int carbohydrate = 0; // (g/1회 제공량) 선택
	
	public JavaBeansPattern() {}

	public int getServingSize() {
		return servingSize;
	}

	public void setServingSize(int servingSize) {
		this.servingSize = servingSize;
	}

	public int getServings() {
		return servings;
	}

	public void setServings(int servings) {
		this.servings = servings;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public int getFat() {
		return fat;
	}

	public void setFat(int fat) {
		this.fat = fat;
	}

	public int getSodium() {
		return sodium;
	}

	public void setSodium(int sodium) {
		this.sodium = sodium;
	}

	public int getCarbohydrate() {
		return carbohydrate;
	}

	public void setCarbohydrate(int carbohydrate) {
		this.carbohydrate = carbohydrate;
	}
	
	
}
