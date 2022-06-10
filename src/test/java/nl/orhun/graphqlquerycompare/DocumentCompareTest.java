package nl.orhun.graphqlquerycompare;

import graphql.language.Document;
import graphql.language.Field;
import graphql.language.OperationDefinition;
import graphql.language.SelectionSet;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DocumentCompareTest {

  @Test
  void equalDocument() {
    Field field = Field.newField("foo")
        .build();
    SelectionSet selectionSet = SelectionSet.newSelectionSet()
        .selection(field)
        .build();

    OperationDefinition operationDefinition = OperationDefinition.newOperationDefinition()
        .name("Query")
        .operation(OperationDefinition.Operation.QUERY)
        .selectionSet(selectionSet)
        .build();

    Document doc1 = Document.newDocument()
        .definition(operationDefinition)
        .build();
    Document doc2 = Document.newDocument()
        .definition(operationDefinition)
        .build();

    boolean equal = DocumentCompare.isEqual(doc1, doc2);

    assertThat(equal).isTrue();
  }

  @Test
  void unequalChildCount() {
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

    Document doc1 = Document.newDocument()
        .definition(operationDefinition1)
        .build();
    Document doc2 = Document.newDocument()
        .build();

    boolean equal = DocumentCompare.isEqual(doc1, doc2);

    assertThat(equal).isFalse();
  }

}
