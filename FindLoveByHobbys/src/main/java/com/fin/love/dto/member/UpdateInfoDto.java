package com.fin.love.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateInfoDto {
    private String id;
    private String password;
    private String email;
    private String phone;
    private String address;


}
