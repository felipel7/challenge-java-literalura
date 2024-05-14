package com.alura.literalura.dto;

import com.alura.literalura.model.Language;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookDto(
        String title,
        Integer download_count,
        List<AuthorDto> authors,
        List<String> languages
) {
}
