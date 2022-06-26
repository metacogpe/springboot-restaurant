package com.example.restaurant.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// MemoryDbEntity를 상속받은 제네릭 타입 필요
abstract public class MemoryDbRepositoryAbstract<T extends MemoryDbEntity> implements MemoryDbRepositoryIfs<T> {

    private final List<T> db = new ArrayList<>();
    private int index = 0;

    @Override
    public Optional<T> findById(int index) {
        return db.stream().filter(it -> it.getIndex() == index).findFirst();// MemoryDbEntity의 index의 데이터를 옵셔널하게 리턴
    }

    @Override
    public T save(T entity) {
        var optinalEntity = db.stream().filter(it -> it.getIndex() == entity.getIndex()).findFirst();

        if(optinalEntity.isEmpty()){
            //db에 데이터가 없는 경우 : 저장
            index++;
            entity.setIndex(index);  //인덱스 지정
            db.add(entity);          //db에 저장
            return entity;

        }else{
            //db에 데이터가 있는 경우 : 업데이트
            var preIndex = optinalEntity.get().getIndex();  // 이미 존재하는 인덱스 가져오기
            entity.setIndex(preIndex);

            deleteById(preIndex);                               // 기존 데이터 지우기
            db.add(entity);                                     // 새로 받은 엔티티를 저장
            return entity;

        }

    }

    @Override
    public void deleteById(int index) {
        var optionalEntity = db.stream().filter(it -> it.getIndex() == index).findFirst();
        if(optionalEntity.isPresent()){
            db.remove(optionalEntity.get());
        }
    }

    @Override
    public List<T> listAll() {
        return db;
    }
}
