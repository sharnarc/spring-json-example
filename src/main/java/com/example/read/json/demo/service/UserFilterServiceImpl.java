package com.example.read.json.demo.service;

import com.example.read.json.demo.dto.FiltersDTO;
import com.example.read.json.demo.entity.UserFilter;
import com.example.read.json.demo.repository.UserFilterRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class UserFilterServiceImpl implements UserFilterService {

    private final UserFilterRepository userFilterRepository;
    private final ObjectMapper mapper;


    @Override
    public Mono<List<FiltersDTO>> saveFirstFilters(List<FiltersDTO> filtersDTOS, String userEmail, String businessUnit) {
        log.debug("FiltersService : saveFilters : createNewFilters");
        return userFilterRepository.save(mapToFilterEntity(filtersDTOS, userEmail, businessUnit))
            .map(response -> filtersDTOS);
    }


    private void createFilterIdForNewFilter(List<FiltersDTO> filtersDTOS) {
        log.debug("FiltersService : saveFilters : createFilterIdForNewFilter");
        filtersDTOS.stream()
            .filter(filtersRequestDTO -> filtersRequestDTO.getFilterId() == null)
            .forEach(filtersRequestDTO -> filtersRequestDTO.setFilterId(UUID.randomUUID().toString().replace("-", "")));
    }


    private UserFilter mapToFilterEntity(List<FiltersDTO> filtersDTOS, String userEmail, String businessUnit) {
        log.info("FiltersService : saveFilters : mapToFilterEntity");
        createFilterIdForNewFilter(filtersDTOS);
        return UserFilter.builder()
            .filterdata(mapFiltersToJSONString(filtersDTOS))
            .createddt(LocalDate.now())
            .updateddt(LocalDate.now())
            .businessunit(businessUnit.toUpperCase())
            .useremail(userEmail)
            .build();
    }


    private String mapFiltersToJSONString(List<FiltersDTO> filtersDTOS) {
        log.debug("FiltersService : saveFilters : mapToFilterEntity : mapFiltersToJSONString");
        try {
            return mapper.writeValueAsString(filtersDTOS);
        } catch (JsonProcessingException error) {
            throw new RuntimeException("ERROR_PROCESSING_JSON", error);
        }
    }


    public Mono<List<FiltersDTO>> getFilters(String filterId, String userEmail, String businessUnit) {
        log.info("FiltersService : getFilters");
        return userFilterRepository.findByUseremailAndBusinessunit(userEmail, businessUnit)
            .flatMap(filter -> getFiltersData(filterId, filter));
    }


    private Mono<List<FiltersDTO>> getFiltersData(String filterId, UserFilter filter) {
        log.info("FiltersService : getFilters : getFilterData");
        try {
            List<FiltersDTO> filters = mapper.readValue(filter.getFilterdata(), new TypeReference<>() {
            });
            if (filterId != null) {
                FiltersDTO filterDTO = filters.stream()
                    .filter(f -> f.getFilterId().equals(filterId))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No filter found for the given filterId"));
                return Mono.just(List.of(filterDTO));
            } else {
                return Mono.just(filters)
                    .switchIfEmpty(Mono.error(new RuntimeException("No filter found for the given emailId and businessUnit")));
            }
        } catch (JsonProcessingException error) {
            throw new RuntimeException("ERROR_PROCESSING_JSON", error);
        }
    }

}
