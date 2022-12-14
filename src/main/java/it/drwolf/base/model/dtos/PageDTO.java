package it.drwolf.base.model.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.quarkus.runtime.annotations.RegisterForReflection;

import java.util.List;

@JsonSerialize
@RegisterForReflection
public record PageDTO<T>(Integer page, Integer size, Long count, List<T> data) { }
