package nl.orhun.graphqlquerycompare;

import graphql.language.EnumTypeDefinition;
import graphql.language.Field;
import graphql.language.OperationDefinition;
import graphql.language.SelectionSet;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DefinitionCompareTest {

  @Test
  void equal() {
    Field field = Field.newField("foo")
        .build();
    SelectionSet selectionSet = SelectionSet.newSelectionSet()
        .selection(field)
        .build();

    OperationDefinition operationDefinition1 = OperationDefinition.newOperationDefinition()
        .name("Query")
        .operation(OperationDefinition.Operation.QUERY)
        .selectionSet(selectionSet)
        .build();
    OperationDefinition operationDefinition2 = OperationDefinition.newOperationDefinition()
        .name("Query")
        .operation(OperationDefinition.Operation.QUERY)
        .selectionSet(selectionSet)
        .build();
    boolean equal = DefinitionCompare.isEqual(List.of(operationDefinition1), List.of(operationDefinition2));
    assertThat(equal).isTrue();
  }

  @Test
  void unequalSelectionSetSize() {
    OperationDefinition operationDefinition1 = OperationDefinition.newOperationDefinition()
        .name("Query")
        .operation(OperationDefinition.Operation.QUERY)
        .build();
    boolean equal = DefinitionCompare.isEqual(List.of(operationDefinition1), Collections.emptyList());
    assertThat(equal).isFalse();
  }

  @Test
  void unequalSelectionSet() {
    OperationDefinition operationDefinition1 = OperationDefinition.newOperationDefinition()
        .name("Query")
        .operation(OperationDefinition.Operation.QUERY)
        .build();
    OperationDefinition operationDefinition2 = OperationDefinition.newOperationDefinition()
        .name("Query")
        .build();
    boolean equal = DefinitionCompare.isEqual(List.of(operationDefinition1), List.of(operationDefinition2));
    assertThat(equal).isFalse();
  }

  @Test
  void invalidDefinition() {
    EnumTypeDefinition enumTypeDefinition = EnumTypeDefinition.newEnumTypeDefinition()
        .build();
    assertThatThrownBy(() -> DefinitionCompare.isEqual(List.of(enumTypeDefinition), List.of(enumTypeDefinition)))
        .isInstanceOf(IllegalStateException.class)
        .hasMessage(
            "Currently only operation class graphql.language.OperationDefinition is supported. Found operation1: class graphql.language.EnumTypeDefinition, operation2: class graphql.language.EnumTypeDefinition");
  }

}
