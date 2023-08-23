package com.example.quyengopbackend.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Data
public class PostDto {
    private String name;
    private String imgUrl;
    private String contact;
    private String userName;
}
