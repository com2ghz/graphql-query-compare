package nl.orhun.graphqlquerycompare;

import graphql.language.Field;
import graphql.language.SelectionSet;

public class SelectionPredicate {

  public static boolean isEqual(SelectionSet selectionSet1, SelectionSet selectionSet2) {
    return selectionSet1.getSelections()
        .stream()
        .map(selection -> (Field) selection)
        .allMatch(field1 -> selectionSet2.getSelections()
            .stream()
            .map(selection -> (Field) selection)
            .anyMatch(field2 -> FieldCompare.isEqual(field1, field2)));
  }
}
