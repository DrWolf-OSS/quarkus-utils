package it.drwolf.base.model.dtos.tuples;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.quarkus.runtime.annotations.RegisterForReflection;

@JsonSerialize
@RegisterForReflection
public record TupleOf4<A, B, C, D>(A value0, B value1, C value2, D value3) {
}
