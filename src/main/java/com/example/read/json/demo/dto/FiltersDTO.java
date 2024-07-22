package com.example.read.json.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FiltersDTO {

    private String name;
    private String filterId;
    private ProjectFilterDTO filter;
    private boolean isDefault;

}