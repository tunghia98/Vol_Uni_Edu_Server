package com.j2ee.vol_uni_edu.models;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
