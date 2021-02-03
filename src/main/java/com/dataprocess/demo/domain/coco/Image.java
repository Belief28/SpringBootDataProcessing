package com.dataprocess.demo.domain.coco;

import lombok.Data;

@Data
public class Image {
    private Integer height;
    private Integer width;
    private Integer id;
    private String file_name;
}
