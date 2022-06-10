package nl.orhun.graphqlquerycompare;

import graphql.language.Argument;
import graphql.language.StringValue;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ArgumentCompareTest {

  @Test
  void equal() {
    Argument argument1 = Argument.newArgument("val1", StringValue.of("boo"))
        .build();
    Argument argument2 = Argument.newArgument("val1", StringValue.of("boo"))
        .build();
    boolean isEqual = ArgumentCompare.isEqual(List.of(argument1), List.of(argument2));
    assertThat(isEqual).isTrue();
  }

  @Test
  void unequalSize() {
    Argument argument1 = Argument.newArgument("val1", StringValue.of("boo"))
        .build();
    boolean isEqual = ArgumentCompare.isEqual(List.of(argument1), Collections.emptyList());
    assertThat(isEqual).isFalse();
  }

  @Test
  void unequalName() {
    Argument argument1 = Argument.newArgument("val1", StringValue.of("boo"))
        .build();
    Argument argument2 = Argument.newArgument("val2", StringValue.of("boo"))
        .build();
    boolean isEqual = ArgumentCompare.isEqual(List.of(argument1), List.of(argument2));
    assertThat(isEqual).isFalse();
  }

  @Test
  void unequalValue() {
    Argument argument1 = Argument.newArgument("val1", StringValue.of("boo"))
        .build();
    Argument argument2 = Argument.newArgument("val1", StringValue.of("bar"))
        .build();
    boolean isEqual = ArgumentCompare.isEqual(List.of(argument1), List.of(argument2));
    assertThat(isEqual).isFalse();
  }
}
