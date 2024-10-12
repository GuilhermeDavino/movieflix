package com.devsuperior.movieflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.repositories.UserRepository;


@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepository repository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired 
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public ReviewDTO insert(ReviewDTO dto) {
		Review entity = new Review();
		
		Movie movie = movieRepository.getReferenceById(dto.getMovieId());
		entity.setMovie(movie);
		UserDTO user = userService.getProfile();
		entity.setUser(userRepository.getReferenceById(user.getId()));
		
		
		entity.setText(dto.getText());
		entity = repository.save(entity);
		return entityToDto(entity, dto);
	}
	
	private ReviewDTO entityToDto(Review entity, ReviewDTO dto) {
		dto.setId(entity.getId());
		dto.setMovieId(entity.getMovie().getId());
		dto.setUserId(entity.getUser().getId());
		dto.setUserEmail(entity.getUser().getEmail());
		dto.setUserName(entity.getUser().getUsername());
		dto.setText(entity.getText());
		return dto;
	}
}
