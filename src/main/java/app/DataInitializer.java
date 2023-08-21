package app;


import app.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final MovieRepository movieRepository;

    @EventListener
    public void atStart(ContextRefreshedEvent ev){
        System.out.println(movieRepository.findAll());
    }
}
