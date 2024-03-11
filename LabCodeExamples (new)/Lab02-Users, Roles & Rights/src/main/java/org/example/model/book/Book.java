package org.example.model.book;

import java.time.LocalDate;

public class Book {

  private Long id;
  private String author;
  private String title;
  private LocalDate publishedDate;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public LocalDate getPublishedDate() {
    return publishedDate;
  }

  public void setPublishedDate(LocalDate publishedDate) {
    this.publishedDate = publishedDate;
  }

  public int getAge() {
    int yearOfPublishing = publishedDate.getYear();
    int yearToday = LocalDate.now().getYear();
    return yearToday - yearOfPublishing;
  }
}
