package com.ssafy.api.request.chart;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@ApiModel(value = "판매 금액",description = "시작일만 입력하면 해당 날짜 하나만 가져옴")
public class SalesGetRequest {
    @ApiModelProperty(name = "start",example = "2021-08-01")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate start;
    @ApiModelProperty(name = "end",example = "2021-08-09")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate end;
}
