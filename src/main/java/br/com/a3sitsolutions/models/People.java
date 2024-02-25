package br.com.a3sitsolutions.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class People extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank(message="Name may not be blank")
    private String name;

    @Column
    @NotBlank(message="Email may not be blank")
    private String email;

    @Column
    @NotBlank(message="Phone may not be blank")
    private String phone;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
