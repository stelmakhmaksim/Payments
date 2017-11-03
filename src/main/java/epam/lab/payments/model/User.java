package epam.lab.payments.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Александр on 03.11.2017.
 */

@Entity
@Data
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String email;
}
