// Service를 포함하는 잘 만들어진 백엔드 기능을 연결하기 위해 Controller 생성
package com.example.restaurant.controller;

import com.example.restaurant.wishlist.dto.WishListDto;
import com.example.restaurant.wishlist.service.WishListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/restaurant")
@RequiredArgsConstructor
@Slf4j
public class ApiController {

    // 서비스를 연결
    private final WishListService wishListService;

    @GetMapping("/search")
    public WishListDto search(@RequestParam String query){
        return wishListService.search(query);
    }
    // 위시리스트를 추가하는 기능 만들기 : POST방식, 추가하는 add()메소스 생성
    // 위시리스트 데이터 wishListDto를 받아서 저장
    @PostMapping("")
    public WishListDto add(@RequestBody WishListDto wishListDto){
        log.info("{}", wishListDto);  // 원하는 데이터인지 점검하기 위해 로그로 출력
        return   wishListService.add(wishListDto);  // dto 데이터가 오면 서비스로 넘기는 동작 수행
    }
}
