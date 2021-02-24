package application.booklibrary;

import org.fluttercode.datafactory.impl.DataFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Year;

@Configuration
public class LoadBooks {
    private static final Logger log = LoggerFactory.getLogger(LoadBooks.class);

    @Bean
    CommandLineRunner initDatabase(LibraryRepository repository) {
        return args -> {
            DataFactory df = new DataFactory();

            String[] values = {"Fantasy", "Romance", "History", "Science Fiction"};
            for(int i = 0; i < 1000; i++) {
                int year = df.getNumberBetween(2000,2021);
                String author = df.getLastName();
                String title = df.getRandomText(10,20);
                repository.save(new Book(title,author,year));
            }

            repository.findAll().forEach(book -> log.info("Preloaded " + book));
        };
    }
}
