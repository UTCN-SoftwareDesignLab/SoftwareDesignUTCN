package org.example.repository;

import java.util.ArrayList;
import java.util.List;

public class Cache<T> {

  private List<T> storage = new ArrayList<>();

  public void save(List<T> entities) {
    this.storage = entities;
  }

  public boolean hasResult() {
    return !this.storage.isEmpty();
  }

  public List<T> load() {
    return this.storage;
  }

  public void invalidateCache() {
    this.storage.clear();
  }

  public void add(T book) {
    storage.add(book);
  }
}
