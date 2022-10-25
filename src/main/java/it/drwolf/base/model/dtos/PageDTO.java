package it.drwolf.base.model.dtos;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.quarkus.runtime.annotations.RegisterForReflection;

import java.util.List;

@JsonSerialize
@RegisterForReflection
public record PageDTO<T>(Integer page, Long count, List<T> data) { }
