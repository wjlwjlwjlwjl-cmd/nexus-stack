package com.nexus.nexusadminservice.domain.dto;

import lombok.Data;

@Data
public class AddrResultDTO {
    private String address;     //具体的详细地址
    private AdInfoDTO ad_info;  //城市地址详细信息
}
