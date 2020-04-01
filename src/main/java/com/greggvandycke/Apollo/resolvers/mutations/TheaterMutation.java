package com.greggvandycke.Apollo.resolvers.mutations;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.greggvandycke.Apollo.models.Theater;
import com.greggvandycke.Apollo.repositories.TheaterRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TheaterMutation implements GraphQLMutationResolver {

    private final TheaterRepository theaterRepository;

    public Theater createTheater(String name, String location) {
        Theater theater = new Theater();
        theater.setName(name);
        theater.setLocation(location);
        theaterRepository.save(theater);
        return theater;
    }

    public boolean deleteTheater(long id) {
        theaterRepository.deleteById(id);
        return true;
    }

    public Theater updateTheater(long id, String name, String location) throws NotFoundException {
        Optional<Theater> optionalTheater = theaterRepository.findById(id);

        if (optionalTheater.isPresent()) {
            Theater theater = optionalTheater.get();

            if(name != null) {
                theater.setName(name);
            }
            if(location != null) {
                theater.setLocation(location);
            }

            theaterRepository.save(theater);
            return theater;
        }

        throw new NotFoundException("No found movie to update!");
    }
}
