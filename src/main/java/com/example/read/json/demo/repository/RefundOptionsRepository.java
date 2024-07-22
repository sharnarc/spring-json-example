package com.example.read.json.demo.repository;

import com.example.read.json.demo.model.RefundOptions;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

@Service
public class RefundOptionsRepository {

    private final RefundOptions refundOptions;
    public RefundOptionsRepository(@Value("${spring.application.optionsService.file}") Resource resourceFile) throws IOException {
        this.refundOptions = resourceFile.exists() ?
                new ObjectMapper().readValue(resourceFile.getContentAsByteArray(), RefundOptions.class) :
                RefundOptions.builder().refundDataOptions(List.of()).build();
    }

    public Mono<RefundOptions> findAll() {
        return Mono.fromCallable(() -> refundOptions);
    }

}
