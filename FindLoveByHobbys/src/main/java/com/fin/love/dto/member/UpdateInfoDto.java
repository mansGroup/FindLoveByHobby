package com.fin.love.dto.member;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UpdateInfoDto {
    private String id;
    private String password;
    private String email;
    private String phone;
    private String address;


}
