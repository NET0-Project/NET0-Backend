package com.example.net0backend.controller;

import com.example.net0backend.dto.request.MyLocationRequest;
import com.example.net0backend.dto.response.NearStoreResponse;
import com.example.net0backend.service.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/store")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @GetMapping("/list")
    public ResponseEntity<List<NearStoreResponse>> getNearStoreList(@Valid MyLocationRequest myLocationRequest) {
        log.info("내 위치의 위도: {}, 경도: {}", myLocationRequest.getX(), myLocationRequest.getY());
        List<NearStoreResponse> nearStoreList = storeService.getNearStoreList(myLocationRequest);
        return ResponseEntity.ok(nearStoreList);
    }

}
