package com.example.net0backend.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class MyLocationRequest {
    @NotNull(message = "내 위치의 위도 값은 필수값 입니다.")
    @Range(min = 33, max = 43, message = "대한민국의 위도 값의 범위는 33 ~ 43 입니다.")
    private Double x;

    @NotNull(message = "내 위치의 경도 값은 필수값 입니다.")
    @Range(min = 124, max = 132, message = "대한민국의 경도 값의 범위는 124 ~ 132 입니다.")
    private Double y;

    public MyLocationRequest(Double x, Double y) {
        this.x = x;
        this.y = y;
    }
}
