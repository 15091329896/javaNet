package com.udp.bean;

import java.io.Serializable;

public class Book implements Serializable{
	private Long ID;
	private String name;
	private Double price;
	private String writer;

	public Book() {
	}

	
	
	public Book(Long iD, String name) {
		super();
		ID = iD;
		this.name = name;
	}


	public Book(Long iD, String name, Double price) {
		super();
		ID = iD;
		this.name = name;
		this.price = price;
	}



	public Book(Long iD, String name, Double price, String writer) {
		super();
		ID = iD;
		this.name = name;
		this.price = price;
		this.writer = writer;
	}




	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	@Override
	public String toString() {
		return "Book [ID=" + ID + ", name=" + name + ", price=" + price + ", writer=" + writer + "]";
	}

}
