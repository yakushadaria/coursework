package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.util.List;

@Entity
@Data
public class Client {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        private String name;
        private String surname;
        private String contactNumber;
        private String email;

        @OneToMany(mappedBy = "client")
        @JsonIgnore
        private List<Order> orders;

        public void setId(Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }

}
