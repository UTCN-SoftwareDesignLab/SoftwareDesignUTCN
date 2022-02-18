package model;

import java.util.Calendar;
import java.util.Date;

public class Book {

  private Long id;

  private String author;
  private String title;
  private Date publishedDate;

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

  public Date getPublishedDate() {
    return publishedDate;
  }

  public void setPublishedDate(Date publishedDate) {
    this.publishedDate = publishedDate;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getAge() {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(publishedDate);
    int yearOfPublishing = calendar.get(Calendar.YEAR);
    calendar.setTime(new Date());
    int yearToday = calendar.get(Calendar.YEAR);

    return yearToday - yearOfPublishing;
  }
}
