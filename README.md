# graphql-query-compare
Extension library for [graphql-java](https://github.com/graphql-java/graphql-java) to compare GraphQL queries and check them for equality.
This is useful for testing purposes if you want to assert the GraphQL queries your application is making.

You can compare the queries on multiple levels:
- Document level
- Selection set
- Fields
- Arguments
- Values

## Usage:
```java
Document firstDocument = parser.parseDocument(readQuery("equalquerywithdifferentorderoffields/query1.graphql"));
Document secondDocument = parser.parseDocument(readQuery("equalquerywithdifferentorderoffields/query2.graphql"));

DocumentCompare.isEqual(firstDocument, secondDocument);
```

### So the following queries are considered equal:
```gherkin
query Query {
    vehicle(licenseplate: "AABBCC25", identification: "aaBBccDD1234") {
      brand
      year
      owner {
        firstName
        lastName
     }
  }
}
```

```gherkin
query Query {
vehicle(identification: "aaBBccDD1234", licenseplate: "AABBCC25") {
      owner {
        lastName
        firstName
     }
      year
      brand
  }
}
```
