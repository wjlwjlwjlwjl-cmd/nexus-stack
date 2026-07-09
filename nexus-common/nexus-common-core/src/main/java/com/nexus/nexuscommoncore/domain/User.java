package com.nexus.nexuscommoncore.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private String name;
    private int age;
    private String nickname;
    private String nation;
    private String school;
}
