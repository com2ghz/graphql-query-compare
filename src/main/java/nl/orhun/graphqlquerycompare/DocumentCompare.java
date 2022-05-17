package nl.orhun.graphqlquerycompare;

import graphql.language.Document;
import graphql.language.OperationDefinition;

public class DocumentCompare {

  private static final RuntimeException EXCEPTION = new IllegalStateException("Document is null");

  static boolean isEqual(Document document1, Document document2) {
    OperationDefinition actualOperation = (OperationDefinition) document1.getChildren()
        .stream()
        .findFirst()
        .orElseThrow(() -> EXCEPTION);
    OperationDefinition expectedOperation = (OperationDefinition) document2.getChildren()
        .stream()
        .findFirst()
        .orElseThrow(() -> EXCEPTION);

    return SelectionPredicate.isEqual(actualOperation.getSelectionSet(), expectedOperation.getSelectionSet());
  }
}
