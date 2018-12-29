package com.yanjun.anno.demo.annodemo.data;

import com.yanjun.anno.demo.annodemo.anno.EasterEgg;
import com.yanjun.anno.demo.annodemo.anno.JsonField;

public class Car {

	@JsonField("manufacturer")
	private final String make;
	
	@JsonField
	private final String model;
	
	private final String year;
	
	public Car (String make, String model, String year) {
		this.make = make;
		this.model = model;
		this.year = year;
	}
	
	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}
	
	public String getYear() {
		return year;
	}

	@Override
	public String toString() {
		return year + " " + make + " " + model;
	}

	@EasterEgg("HoHoHo")
	public void joyousShow() {
		System.out.println(toString());
	}
}
