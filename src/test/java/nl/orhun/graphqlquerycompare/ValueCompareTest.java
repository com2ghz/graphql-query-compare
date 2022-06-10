package nl.orhun.graphqlquerycompare;

import graphql.language.IntValue;
import graphql.language.StringValue;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ValueCompareTest {

  @Test
  void equal() {
    StringValue value1 = StringValue.of("foo");
    StringValue value2 = StringValue.of("foo");
    boolean equal = ValueCompare.isEqual(value1, value2);
    assertThat(equal).isTrue();
  }

  @Test
  void unequal() {
    StringValue value1 = StringValue.of("foo");
    StringValue value2 = StringValue.of("bar");
    boolean equal = ValueCompare.isEqual(value1, value2);
    assertThat(equal).isFalse();
  }

  @Test
  void nullValue() {
    StringValue value1 = StringValue.of("foo");
    StringValue value2 = null;
    boolean equal = ValueCompare.isEqual(value1, value2);
    boolean invertedEqual = ValueCompare.isEqual(value2, value1);
    assertThat(equal).isFalse();
    assertThat(invertedEqual).isFalse();
  }

  @Test
  void bothNull() {
    StringValue value1 = null;
    StringValue value2 = null;
    boolean equal = ValueCompare.isEqual(value1, value2);
    assertThat(equal).isTrue();
  }

  @Test
  void valueTypeMismatch() {
    StringValue value1 = StringValue.of("foo");
    IntValue value2 = IntValue.of(1);

    assertThatThrownBy(() -> ValueCompare.isEqual(value1, value2)).isInstanceOf(IllegalArgumentException.class)
        .hasMessage(
            "Value types are not equal. Value1: class graphql.language.StringValue, value2: class graphql.language.IntValue");

  }

}
