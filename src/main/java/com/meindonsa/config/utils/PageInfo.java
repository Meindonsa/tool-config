package com.meindonsa.config.utils;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private int size;
    private int index;
    private Long total;
}
