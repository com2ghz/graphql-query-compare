package nl.orhun.graphqlquerycompare;

import graphql.language.Value;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class ValueCompare {

    static boolean isEqual(Value<?> value1, Value<?> value2) {
        boolean hasValue1 = value1 != null;
        boolean hasValue2 = value2 != null;
        if (hasValue1 != hasValue2) {
            return false;
        }
        if (!hasValue1) {
            return true;
        }
        if (value1.getClass() != value2.getClass()) {
            throw new IllegalArgumentException(
                    String.format("Value types are not equal. Value1: %s, value2: %s", value1.getClass(), value2.getClass()));
        }
        return value1.isEqualTo(value2);
    }
}
