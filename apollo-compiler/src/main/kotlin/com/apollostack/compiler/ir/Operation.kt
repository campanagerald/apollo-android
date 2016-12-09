package com.apollostack.compiler.ir

import com.apollostack.compiler.InterfaceTypeSpecBuilder
import com.apollostack.compiler.resolveNestedTypeNameDuplication
import com.squareup.javapoet.TypeSpec

data class Operation(
    val operationName: String,
    val operationType: String,
    val variables: List<Variable>,
    val source: String,
    val fields: List<Field>
) : CodeGenerator {
  override fun toTypeSpec(fragments: List<Fragment>): TypeSpec {
    // TODO: This is a bit weird that we can' call field.toTypeSpec directly, also we need to be
    // able to inject the parameters directly (including the list of fragments), so we use
    // FieldTypeSpecBuilder directly.
    return InterfaceTypeSpecBuilder().build(operationName, fields, fragments, emptyList())
        .resolveNestedTypeNameDuplication(emptyList())
  }
}