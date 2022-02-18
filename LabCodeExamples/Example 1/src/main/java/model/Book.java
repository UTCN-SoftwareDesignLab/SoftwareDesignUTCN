package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Book {

  private Long id;

  private String author;
  private String title;
  private LocalDate publishedDate;

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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getAge() {
    return (int) ChronoUnit.YEARS
        .between(publishedDate.withMonth(1).atStartOfDay(), LocalDate.now().atStartOfDay());
  }
}
