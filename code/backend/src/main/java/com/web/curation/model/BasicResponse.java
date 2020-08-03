package com.web.curation.model;

import io.swagger.annotations.ApiModelProperty;


public class BasicResponse {
    @ApiModelProperty(value = "status", position = 1)
    public boolean status;
    @ApiModelProperty(value = "data", position = 2)
    public String data;
    @ApiModelProperty(value = "email", position = 3)
    public String email;
    @ApiModelProperty(value = "object", position = 4)
    public Object object;
}
