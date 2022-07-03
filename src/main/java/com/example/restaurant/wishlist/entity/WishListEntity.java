// db에 어떤 것들을 저장할 것인지 결정
package com.example.restaurant.wishlist.entity;

import com.example.restaurant.db.MemoryDbEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class WishListEntity extends MemoryDbEntity {

    private String title;               // 음식명
    private String category;
    private String address;
    private String roadAddress;         // 도로명 주소
    private String homePageLink;
    private String imageLink;
    private boolean  isVisit;
    private int visitCount;
    private LocalDateTime lastVistDate;

}
