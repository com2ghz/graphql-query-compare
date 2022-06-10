package nl.orhun.graphqlquerycompare;

import graphql.language.Field;
import graphql.language.SelectionSet;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class SelectionCompareTest {

  @Test
  void equal() {
    Field field = Field.newField("foo")
        .build();
    SelectionSet selectionSet1 = SelectionSet.newSelectionSet()
        .selection(field)
        .build();
    SelectionSet selectionSet2 = SelectionSet.newSelectionSet()
        .selection(field)
        .build();

    boolean equal = SelectionCompare.isEqual(selectionSet1, selectionSet2);
    assertThat(equal).isTrue();
  }

  @Test
  void unequalSelectionList() {
    Field field = Field.newField("foo")
        .build();
    SelectionSet selectionSet1 = SelectionSet.newSelectionSet()
        .selection(field)
        .build();
    SelectionSet selectionSet2 = SelectionSet.newSelectionSet()
        .build();

    boolean equal = SelectionCompare.isEqual(selectionSet1, selectionSet2);
    assertThat(equal).isFalse();
  }

}
