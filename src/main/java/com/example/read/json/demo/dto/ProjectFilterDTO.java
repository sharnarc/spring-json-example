package com.example.read.json.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectFilterDTO {

    private Integer projectId;

    private String approvalStatus;

    @JsonProperty("projectRelationship")
    private ProjectRelationshipDTO projectRelationship;

    private List<Integer> implementationLevelId;

    @JsonProperty("category")
    private CategoryFilterDTO categoryFilterDTO;

    @JsonProperty("geography")
    private GeographyFilterDTO geographyFilterDTO;


}
