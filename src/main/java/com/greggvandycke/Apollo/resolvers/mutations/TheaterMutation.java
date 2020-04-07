package com.greggvandycke.Apollo.resolvers.mutations;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.greggvandycke.Apollo.models.Theater;
import com.greggvandycke.Apollo.service.TheaterService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TheaterMutation implements GraphQLMutationResolver {

    @Autowired
    private TheaterService theaterService;

    public Theater createTheater(String name, String location) {
        return theaterService.createTheater(name, location);
    }

    public boolean deleteTheater(long id) {
        theaterService.deleteById(id);
        return true;
    }

    public Theater updateTheater(long id, String name, String location) throws NotFoundException {
        return theaterService.updateTheater(id, name, location);
    }
}
