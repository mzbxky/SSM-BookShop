package com.fc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order  implements Serializable {
    private String oid;

    private Float ototal;

    private Integer oamount;

    private Integer ostatus;

    private Integer opaytype;

    private Integer uid;

    private Date odatetime;

    private String orealname;

    private String ophone;

    private String oaddress;

}