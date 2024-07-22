package com.example.read.json.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ProjectRelationshipDTO {

    private boolean projectOwner;
    private boolean backupOwner;
    private boolean projectApprover;
    private boolean teamMembers;

}
