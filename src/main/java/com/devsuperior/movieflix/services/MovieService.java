package com.devsuperior.movieflix.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository repository;
	
	@Autowired
	private GenreRepository genreRepository;
	
	public MovieDetailsDTO findById(Long id) {
		Movie entity = repository.findById(id).orElseThrow(() -> 
		new ResourceNotFoundException("Id inválido"));
		MovieDetailsDTO dto = new MovieDetailsDTO(entity);
		return dto;
	}
	
	public Page<MovieDetailsDTO> findByGenre(List<Long> genreId, Pageable pageable) {
		if(genreId.isEmpty() || genreId.get(0) == 0) {
			List<Genre> genres = genreRepository.findAll();
			for(Genre obj : genres) {
				genreId.add(obj.getId());
			}
			
		}
		Page<Movie> entity = repository.findByGenre(genreId, pageable);
		Page<MovieDetailsDTO> pageDTO = entity.map(x -> new MovieDetailsDTO(x));
		
		return pageDTO;
	}
}
