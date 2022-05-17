package nl.orhun.graphqlquerycompare;

import graphql.language.Document;
import graphql.parser.Parser;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CompareTest {

  private final Parser parser = new Parser();

  @Test
  void equalQuery() {
    Document actualDocument = parser.parseDocument(readQuery("equalquery/query.graphql"));

    assertTrue(DocumentCompare.isEqual(actualDocument, actualDocument));
  }

  @Test
  void equalQueryWithDifferentOrderOfSelectionsAndFields() {
    Document firstDocument = parser.parseDocument(readQuery("equalquerywithdifferentorderoffields/query1.graphql"));
    Document secondDocument = parser.parseDocument(readQuery("equalquerywithdifferentorderoffields/query2.graphql"));

    assertTrue(DocumentCompare.isEqual(firstDocument, secondDocument));
  }

  @Test
  void unequalQueryMissingSelection() {
    Document firstDocument = parser.parseDocument(readQuery("unequalquerymissingselection/query1.graphql"));
    Document secondDocument = parser.parseDocument(readQuery("unequalquerymissingselection/query2.graphql"));

    assertFalse(DocumentCompare.isEqual(firstDocument, secondDocument));
  }

  @Test
  void queryWithMismatchFieldValue() {
    Document firstDocument = parser.parseDocument(readQuery("querywithmismatchfieldvalue/query1.graphql"));
    Document secondDocument = parser.parseDocument(readQuery("querywithmismatchfieldvalue/query2.graphql"));

    assertFalse(DocumentCompare.isEqual(firstDocument, secondDocument));
  }


  private String readQuery(String resourceFileName) {
    try {
      URI uri = getClass().getClassLoader()
          .getResource(resourceFileName)
          .toURI();
      return Files.readString(Path.of(uri));
    } catch (URISyntaxException | IOException e) {
      throw new RuntimeException(e);
    }
  }
}
