package nl.orhun.graphqlquerycompare;

import graphql.language.Field;
import graphql.language.Selection;
import graphql.language.SelectionSet;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class SelectionCompare {

  static boolean isEqual(SelectionSet selectionSet1, SelectionSet selectionSet2) {
    List<Selection> selections1 = selectionSet1.getSelections();
    List<Selection> selections2 = selectionSet2.getSelections();

    if (selections1.size() != selections2.size()) {
      return false;
    }
    return selections1.stream()
        .map(selection -> (Field) selection)
        .allMatch(field1 -> selections2.stream()
            .map(selection -> (Field) selection)
            .anyMatch(field2 -> FieldCompare.isEqual(field1, field2)));
  }
}
