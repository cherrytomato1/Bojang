package com.ssafy.api.response.sales;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.mapping.sales.SalesMapping;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@ApiModel("SalesUpdateResponse")
public class SalesUpdateResponse extends BaseResponseBody {
}
