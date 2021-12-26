package com.hibernate.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// Generated Dec 25, 2021, 11:10:37 PM by Hibernate Tools 5.4.30.Final

/**
 * Book generated by hbm2java
 */
@Entity
@Table(name = "book")
public class Book implements java.io.Serializable {

	private Integer id;
	private String title;
	private String author;
	private String genre;
	private int year;

	public Book() {
	}

	public Book(String title, String author, String genre, int year) {
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.year = year;
	}

	@Id	 
    @Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return this.genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
