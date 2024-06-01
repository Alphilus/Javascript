package com.example.demo.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateEpisodeRequest {
    String name;
    Integer duration;
    String videoUrl;
    Integer displayOrder;
}
