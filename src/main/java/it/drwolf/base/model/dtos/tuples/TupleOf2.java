package it.drwolf.base.model.dtos.tuples;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.quarkus.runtime.annotations.RegisterForReflection;

@JsonSerialize
@RegisterForReflection
public record TupleOf2<A, B>(A value0, B value1) {
}
