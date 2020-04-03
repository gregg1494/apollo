package com.greggvandycke.Apollo.resolvers.queries;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.greggvandycke.Apollo.models.Theater;
import com.greggvandycke.Apollo.repositories.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TheaterQuery implements GraphQLQueryResolver {

    private final TheaterRepository theaterRepository;

    public List<Theater> theaters() {
        return theaterRepository.findAll();
    }
}
