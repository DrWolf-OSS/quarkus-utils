package it.drwolf.base.model.dtos.tuples;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.quarkus.runtime.annotations.RegisterForReflection;

@JsonSerialize
@RegisterForReflection
public record TupleOf5<A, B, C, D, E>(A value0, B value1, C value2, D value3, E value4) {
}
