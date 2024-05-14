package com.example.spiringsecurity.payload.request;

import com.example.spiringsecurity.model.Role;
import lombok.Data;

@Data
public class UpdateRequest {

    private Long id;
    private String name;
    private String email;
    private String designation;
    private String deptMstCode;
    private Role role;
}
