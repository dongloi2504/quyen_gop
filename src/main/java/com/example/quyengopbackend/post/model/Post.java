package com.example.quyengopbackend.post.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "post")
@Getter @Setter @NoArgsConstructor @Entity
public class Post {
    @Id
    private String id;

    private String name;

    private String imgUrl;

    private String contact;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // Construct Plan by DTO
    public Post(String name, String imgUrl, String contact) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.contact = contact;
    }
}
