package repositories;

import java.util.List;

public class Cache<T> {

  private List<T> storage;

  public void save(List<T> entities) {
    this.storage = entities;
  }

  public boolean hasResult() {
    return this.storage != null;
  }

  public List<T> load() {
    return this.storage;
  }

  public void invalidateCache() {
    this.storage = null;
  }

}
