package com.devsuperior.movieflix.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.movieflix.entities.Movie;
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
	
	@Query(value = "SELECT obj FROM Movie obj WHERE obj.genre.id IN :genreId ORDER BY obj.title")
	Page<Movie> findByGenre(List<Long> genreId, Pageable pageable);
}
