package app.controller;

import app.model.Movie;
import app.repository.MovieRepository;


import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    @GetMapping
    public String listMovies(@RequestParam(name = "search", required = false) String searchPhrase, Model model) {
        List<Movie> movies;

        if (searchPhrase != null && !searchPhrase.isEmpty()) {
            movies = movieRepository.findByTitleContainingOrDescriptionContainingOrCategoryContainingIgnoreCase(searchPhrase, searchPhrase, searchPhrase);
        } else {
            movies = (List<Movie>) movieRepository.findAll();
        }

        model.addAttribute("movies", movies);
        return "list";
    }

    @GetMapping("/create")
    public String createMovieForm(Model model) {
        model.addAttribute("movie", new Movie());
        return "form";
    }

    @PostMapping("/create")
    public String createMovie(@ModelAttribute Movie movie) {
        if (movie.getId() == null) {
            movie.setId(UUID.randomUUID());
        }
        movieRepository.save(movie);
        return  "redirect:/movies";
    }

    @GetMapping("/edit/{id}")
    public String editMovieForm(@PathVariable UUID id, Model model) {
        Optional<Movie> movieOptional = movieRepository.findById(id);
        if (movieOptional.isPresent()) {
            model.addAttribute("movie", movieOptional.get());
            return "edit";
        } else {
            return "redirect:/movies";
        }
    }


    @PostMapping("/update")
    public String updateMovie(@ModelAttribute @Valid Movie movie, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit";

        }

        movieRepository.save(movie);
        return "redirect:/movies";
    }

    @PostMapping("/delete/{id}")
    public String deleteMovie(@PathVariable UUID id) {
        movieRepository.deleteById(id);
        return "redirect:/movies";
    }
}
