package nl.orhun.graphqlquerycompare;

import graphql.language.Argument;
import graphql.language.Field;
import graphql.language.SelectionSet;
import graphql.language.StringValue;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FieldCompareTest {

  @Test
  void equal() {
    Field field1 = Field.newField("foo")
        .build();
    Field field2 = Field.newField("foo")
        .build();
    boolean equal = FieldCompare.isEqual(field1, field2);
    assertThat(equal).isTrue();
  }

  @Test
  void unequalName() {
    Field field1 = Field.newField("foo")
        .build();
    Field field2 = Field.newField("bar")
        .build();
    boolean equal = FieldCompare.isEqual(field1, field2);
    assertThat(equal).isFalse();
  }

  @Test
  void unequalAlias() {
    Field field1 = Field.newField("foo")
        .build();
    Field field2 = Field.newField("foo")
        .alias("some Alias")
        .build();
    boolean equal = FieldCompare.isEqual(field1, field2);
    assertThat(equal).isFalse();
  }

  @Test
  void equalArguments() {
    Argument argument1 = Argument.newArgument()
        .value(StringValue.of("Arg"))
        .build();
    Field field1 = Field.newField("foo")
        .arguments(List.of(argument1))
        .build();
    Field field2 = Field.newField("foo")
        .arguments(List.of(argument1))
        .build();
    boolean equal = FieldCompare.isEqual(field1, field2);
    assertThat(equal).isTrue();
  }

  @Test
  void unequalArgumentSize() {
    Argument argument1 = Argument.newArgument()
        .value(StringValue.of("Arg"))
        .build();
    Field field1 = Field.newField("foo")
        .arguments(List.of(argument1))
        .build();
    Field field2 = Field.newField("foo")
        .build();
    boolean equal = FieldCompare.isEqual(field1, field2);
    boolean invertedEqual = FieldCompare.isEqual(field2, field1);
    assertThat(equal).isFalse();
    assertThat(invertedEqual).isFalse();
  }

  @Test
  void unequalArguments() {
    Argument argument1 = Argument.newArgument()
        .value(StringValue.of("Arg"))
        .build();
    Argument argument2 = Argument.newArgument()
        .value(StringValue.of("Whattt?"))
        .build();
    Field field1 = Field.newField("foo")
        .arguments(List.of(argument1))
        .build();
    Field field2 = Field.newField("foo")
        .arguments(List.of(argument2))
        .build();
    boolean equal = FieldCompare.isEqual(field1, field2);
    assertThat(equal).isFalse();
  }

  @Test
  void unequalSelectionSetSize() {
    SelectionSet selectionSet1 = SelectionSet.newSelectionSet()
        .build();
    Field field1 = Field.newField("foo")
        .selectionSet(selectionSet1)
        .build();
    Field field2 = Field.newField("foo")
        .build();
    boolean equal1 = FieldCompare.isEqual(field1, field2);
    boolean invertedEqual = FieldCompare.isEqual(field2, field1);
    assertThat(equal1).isFalse();
    assertThat(invertedEqual).isFalse();
  }

  @Test
  void unequalSelectionSet() {
    SelectionSet selectionSet1 = SelectionSet.newSelectionSet()
        .selection(Field.newField("hello")
            .build())
        .build();
    SelectionSet selectionSet2 = SelectionSet.newSelectionSet()
        .selection(Field.newField("world")
            .build())
        .build();
    Field field1 = Field.newField("foo")
        .selectionSet(selectionSet1)
        .build();
    Field field2 = Field.newField("foo")
        .selectionSet(selectionSet2)
        .build();
    boolean equal1 = FieldCompare.isEqual(field1, field2);
    boolean invertedEqual = FieldCompare.isEqual(field2, field1);
    assertThat(equal1).isFalse();
    assertThat(invertedEqual).isFalse();
  }

}
