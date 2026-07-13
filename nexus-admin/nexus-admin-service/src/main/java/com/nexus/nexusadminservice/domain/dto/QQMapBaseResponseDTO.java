package com.nexus.nexusadminservice.domain.dto;

import lombok.Data;

@Data
public class QQMapBaseResponseDTO {
    private Integer stauts;         //0 表示成功
    private String message;         //响应消息
    private String request_id;      //请求id
}
