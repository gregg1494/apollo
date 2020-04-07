package com.greggvandycke.Apollo.service;

import com.greggvandycke.Apollo.models.Theater;
import com.greggvandycke.Apollo.repositories.TheaterRepository;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TheaterService {

	private final TheaterRepository theaterRepository;

	@Transactional
	public Theater createTheater(String name, String location) {
		Theater theater = new Theater();
		theater.setName(name);
		theater.setLocation(location);
		theaterRepository.save(theater);
		return theater;
	}

	@Transactional(readOnly = true)
	public List<Theater> theaters(){
		return theaterRepository.findAll();
	}

	@Transactional
	public void deleteById(long id) {
		theaterRepository.deleteById(id);
	}

	@Transactional
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

		throw new NotFoundException("No found theater to update!");
	}

	@Transactional(readOnly = true)
	public Theater getTheater(long id) {
		Optional<Theater> optionalTheater = theaterRepository.findById(id);
		return optionalTheater.orElse(null);
	}
}
