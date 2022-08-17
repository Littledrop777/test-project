package by.exlab.shire_test.mapper;

public interface Mapper<F, T> {

  T map(F object);
}
