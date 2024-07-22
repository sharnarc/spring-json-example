package com.example.read.json.demo.handler;

import com.example.read.json.demo.dto.FiltersDTO;
import com.example.read.json.demo.service.UserFilterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserFilterHandler {

    public static final String USER_EMAIL = "userEmail";
    public static final String BUSINESS_UNIT = "businessUnit";
    public static final String FILTER_ID = "filterId";
    private final UserFilterService filtersService;


    public Mono<ServerResponse> saveFirstFilters(ServerRequest serverRequest) {
        return serverRequest.bodyToFlux(FiltersDTO.class).collectList()
            .flatMap(filterRequestDTO -> filtersService.saveFirstFilters(filterRequestDTO, serverRequest.pathVariable(USER_EMAIL),
                    serverRequest.pathVariable(BUSINESS_UNIT))
                .flatMap(
                    filtersResponseDTO -> ServerResponse.created(URI.create("/service/filters")).bodyValue(filtersResponseDTO)));

    }


    public Mono<ServerResponse> getFilters(ServerRequest serverRequest) {
        log.info("FiltersHandler : getFilter : validatedRequestParam");
        return Mono.just(serverRequest).flatMap(validatedRequest -> {
                String filterId = serverRequest.queryParam(FILTER_ID).orElse(null);
                return filtersService.getFilters(filterId, serverRequest.pathVariable(USER_EMAIL),
                        serverRequest.pathVariable(BUSINESS_UNIT))
                    .flatMap(filterDTOS -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(filterDTOS));
            }
        );
    }

}
