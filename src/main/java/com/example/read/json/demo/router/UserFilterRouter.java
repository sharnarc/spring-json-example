package com.example.read.json.demo.router;

import com.example.read.json.demo.handler.UserFilterHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
@Configuration
public class UserFilterRouter {
    public static final String FILTERS_BUSINESS_UNIT_USER_EMAIL = "/api/filters/{businessUnit}/{userEmail}";


    @Bean
    public RouterFunction<ServerResponse> filtersRoutes(UserFilterHandler filtersHandler) {

        return RouterFunctions.route()
            .POST(FILTERS_BUSINESS_UNIT_USER_EMAIL, filtersHandler::saveFirstFilters)
            //.PUT(FILTERS_BUSINESS_UNIT_USER_EMAIL, filtersHandler::updateFilter)
            .GET(FILTERS_BUSINESS_UNIT_USER_EMAIL, filtersHandler::getFilters)
          //  .DELETE(FILTERS_BUSINESS_UNIT_USER_EMAIL, filtersHandler::deleteFilter)
            .build();
    }

}
