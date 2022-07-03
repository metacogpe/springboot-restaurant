// Service를 포함하는 잘 만들어진 백엔드 기능을 연결하기 위해 Controller 생성
package com.example.restaurant.controller;

import com.example.restaurant.wishlist.dto.WishListDto;
import com.example.restaurant.wishlist.service.WishListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.ls.LSException;

import java.util.List;

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

    // dto받아서 add시키는 것까지 완료 후에 add되었는지 전체 리스트 확인 : POST했던 모든 리스트 확인
    @GetMapping("/all")
    public List<WishListDto> findAll(){
        return wishListService.findAll();
    }

    // 삭제 기능
    @DeleteMapping("/{index}")
    public void delete(@PathVariable int index){
        wishListService.delete(index);
    }

    // 방문 여부 관리
    @PostMapping("/{index}")
    public void addVisit(@PathVariable int index){
        wishListService.addVisit(index);
    }
}
