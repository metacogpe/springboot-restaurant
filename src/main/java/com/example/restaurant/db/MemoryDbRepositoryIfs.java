package com.example.restaurant.db;

import java.util.List;
import java.util.Optional;

public interface MemoryDbRepositoryIfs<T> {  //generic으로 타입 지정

    Optional<T> findById(int index); // 해당 타입에 맞게 리턴하는 옵셔널한 메소드
    T save(T entity);                //save method
    void deleteById(int index);
    List<T> listAll();

}
