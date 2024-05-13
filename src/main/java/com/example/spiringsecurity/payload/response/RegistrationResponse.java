package com.example.spiringsecurity.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationResponse {


    private String token;
   private boolean isPresent;

    public RegistrationResponse(boolean isPresent){
        this.isPresent=isPresent;
    }

}
