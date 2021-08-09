package com.ssafy.api.request.chart;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@ApiModel("SalesGetRequest")
public class SalesGetRequest {
    @ApiModelProperty(name = "시작일",example = "2021-08-01",notes = "start 값만 있으면 해당 일의 판매 금액만 반환")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate start;
    @ApiModelProperty(name = "종료일",example = "2021-08-09")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate end;
}
