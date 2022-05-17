package nl.orhun.graphqlquerycompare;

import graphql.language.Document;
import graphql.language.Node;

import java.util.List;

public class DocumentCompare {

  public static boolean isEqual(Document document1, Document document2) {
    List<Node> nodeList1 = document1.getChildren();
    List<Node> nodeList2 = document1.getChildren();
    if (nodeList1.size() != nodeList2.size()) {
      return false;
    }
    return DefinitionCompare.isEqual(document1.getDefinitions(), document2.getDefinitions());
  }
}
