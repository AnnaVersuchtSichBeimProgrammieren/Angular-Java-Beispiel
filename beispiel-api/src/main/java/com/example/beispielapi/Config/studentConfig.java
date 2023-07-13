package com.example.beispielapi.Config;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.beispielapi.Models.Student;
import com.example.beispielapi.Repositories.StudentRepository;

@Configuration
public class studentConfig {
    
    @Bean
    CommandLineRunner studentCommandLineRunner(StudentRepository repository){
        return args -> {
            Student anna = new Student(
                "Anna",
                "annabusch01@gmx.de",
                LocalDate.of(2001, Month.FEBRUARY, 7)
            );
            Student robin = new Student(
                "Robin",
                "robin@gmx.de",
                LocalDate.of(2000, Month.DECEMBER, 25)
            );

            repository.saveAll(List.of(anna, robin));
        };
    }
}
