package com.example.read.json.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeographyFilterDTO {

    private List<Integer> regionId;
    private List<Integer> areaId;
    private List<Integer> countryId;
    private List<String> terminal;
    private List<String> globalOperator;
    private List<String> locationId;
    private List<Integer> mercPlusId;
    private List<String> macroRegion;
    private List<Integer> iataId;

}