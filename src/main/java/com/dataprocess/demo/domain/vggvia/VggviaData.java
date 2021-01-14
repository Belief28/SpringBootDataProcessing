package com.dataprocess.demo.domain.vggvia;

import lombok.Data;

import java.util.List;

@Data
public class VggviaData {
    private String filename;
    private Integer size;
    private List<Region> regions;
}
