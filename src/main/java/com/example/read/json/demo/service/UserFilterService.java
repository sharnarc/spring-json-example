package com.example.read.json.demo.service;

import com.example.read.json.demo.dto.FiltersDTO;
import reactor.core.publisher.Mono;

import java.util.List;

public interface UserFilterService {

    Mono<List<FiltersDTO>> saveFirstFilters(List<FiltersDTO> filtersDTOS, String userEmail, String businessUnit);
    Mono<List<FiltersDTO>> getFilters(String filterId, String userEmail, String businessUnit);

}
