package com.alura.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookDto(
        String title,
        Integer download_count,
        List<AuthorDto> authors,
        @JsonAlias("language") List<String> languages
) {
}
