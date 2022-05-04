package com.fc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements Serializable {
    private Integer uid;

    private String uname;

    private String upwd;

    private String urealname;

    private Date uredgt;

    private Integer urole;

    private String umark;

    private String uphone;

    private String uaddress;
}