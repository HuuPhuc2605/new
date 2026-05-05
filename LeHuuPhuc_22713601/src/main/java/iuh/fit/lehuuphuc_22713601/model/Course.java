package iuh.fit.lehuuphuc_22713601.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String name;
    private String instructor;
    private String category;
    private int duration;
    private String description;
    private double price;
    @Column(name = "image_url")
    private String imageUrl;

}
