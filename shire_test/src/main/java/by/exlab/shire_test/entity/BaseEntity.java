package by.exlab.shire_test.entity;

import java.io.Serializable;

public interface BaseEntity<T extends Serializable> {

  T getId();
}
