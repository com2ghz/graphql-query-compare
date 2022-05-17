package nl.orhun.graphqlquerycompare;

import graphql.language.Argument;

import java.util.List;
import java.util.Objects;

public class ArgumentCompare {

  public static boolean isEqual(Argument argument1, Argument argument2) {
    boolean nameEquals = Objects.equals(argument1.getName(), argument2.getName());
    boolean valueEquals = ValueCompare.isEqual(argument1.getValue(), argument2.getValue());

    return nameEquals && valueEquals;
  }

  public static boolean isEqual(List<Argument> arguments1, List<Argument> arguments2) {
    return arguments1.stream()
        .allMatch(argument1 -> arguments2.stream()
            .anyMatch(argument2 -> isEqual(argument1, argument2)));
  }
}
