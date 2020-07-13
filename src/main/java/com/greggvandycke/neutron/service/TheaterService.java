package com.greggvandycke.neutron.service;

import com.greggvandycke.neutron.models.Theater;
import com.greggvandycke.neutron.repositories.TheaterRepository;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@GraphQLApi
@Service
@AllArgsConstructor
public class TheaterService {

	private final TheaterRepository theaterRepository;

	@GraphQLMutation
	public Theater createTheater(String name, String location) {
		Theater theater = new Theater();
		theater.setName(name);
		theaterRepository.save(theater);
		return theater;
	}

	@GraphQLQuery
	public List<Theater> theaters(){
		return theaterRepository.findAll();
	}

	@GraphQLMutation
	public void deleteTheater(long id) {
		theaterRepository.deleteById(id);
	}

	@GraphQLMutation
	public Theater updateTheater(long id, String name, String location) throws NotFoundException {
		Optional<Theater> optionalTheater = theaterRepository.findById(id);

		if (optionalTheater.isPresent()) {
			Theater theater = optionalTheater.get();

			if(name != null) {
				theater.setName(name);
			}

			theaterRepository.save(theater);
			return theater;
		}

		throw new NotFoundException("No found theater to update!");
	}

	@GraphQLQuery
	public Theater getTheater(long id) {
		Optional<Theater> optionalTheater = theaterRepository.findById(id);
		return optionalTheater.orElse(null);
	}
}
