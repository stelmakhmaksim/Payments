package com.epam.lab.payments;

import com.epam.lab.payments.dao.UserEntityRepository;
import com.epam.lab.payments.domain.UserEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;

/**
 * Created by Aleksandr_Goloshchap on 11/7/2017.
 */

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Payments {
    @Bean
    CommandLineRunner init(UserEntityRepository userEntityRepository) {
        return (evt) -> Arrays.asList(
                "jhoeller,dsyer,pwebb,ogierke,rwinch,mfisher,mpollack,jlong".split(","))
                .forEach(
                        a -> {
                            UserEntity userEntity = new UserEntity();
                            userEntity.setFirstName(a);
                            userEntity.setLastName("lastname");
                            userEntity.setPassword("1234");

                            userEntity = userEntityRepository.save(userEntity);
                        });
    }

    public static void main(String[] args) {
        SpringApplication.run(Payments.class, args);
    }
}

@RestController
@RequestMapping("/{userId}")
class BookmarkRestController {
    private final UserEntityRepository userEntityRepository;

    BookmarkRestController(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@PathVariable String userId) {
        return this.userEntityRepository
                .findById(Long.valueOf(userId))
                .map(account -> {
                    HttpHeaders httpHeaders = new HttpHeaders();
                    httpHeaders.setLocation(ServletUriComponentsBuilder
                            .fromCurrentRequest().path("/{id}")
                            .buildAndExpand().toUri());
                    return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
                }).get();
    }
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String userId) {
        super("could not find user '" + userId + "'.");
    }
}