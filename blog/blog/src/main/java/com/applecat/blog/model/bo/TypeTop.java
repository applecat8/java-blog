package com.applecat.blog.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TypeTop implements Comparable<TypeTop>{

    private String name;    

    //权值
    private int number;

    @Override
    public int compareTo(TypeTop arg0) {
        return arg0.getNumber() - this.number;
    }
}
