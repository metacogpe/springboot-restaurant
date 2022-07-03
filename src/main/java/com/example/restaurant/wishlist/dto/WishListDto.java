// WishListEntity의 데이터 필드와 유사한 데이터 구조의 Dto 생성
// 데이터베이스 필드명 변경 시에 frontend의 변수명 변화 유발의 영향이 없도록 Dto로 분리 구성 : 중간에 변환하는 과정으로 해소
package com.example.restaurant.wishlist.dto;

import com.example.restaurant.db.MemoryDbEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class WishListDto {
    private int index;
    private String title;               // 음식명
    private String category;
    private String address;
    private String roadAddress;         // 도로명 주소
    private String homePageLink;
    private String imageLink;
    private boolean  isVisit;
    private int visitCount;
    private LocalDateTime lastVistData;

}
