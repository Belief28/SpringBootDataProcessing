package com.dataprocess.demo.domain.coco;

import lombok.Data;

import java.util.List;
@Data
public class CocoData {
    private List<Image> inages;
    private List<Category> categories;
    private List<Annotation> annotations;
}
