package com.dataprocess.demo.domain.coco;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
public class Annotation {
    private List<List<BigDecimal>> segmentation;
    private Integer iscrowd;
    private Integer image_id;
    private List<BigDecimal> bboxl;
    private BigDecimal area;
    private Integer category_id;
    private Integer id;
}
