package epam.lab.payments.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

/**
 * Created by Александр on 03.11.2017.
 */

@Entity
@Data
public class Card {
    @Id
    @GeneratedValue
    private Long id;

    private String cardholderName;
    private LocalDate expiration;
    private Integer securityCode;
}
