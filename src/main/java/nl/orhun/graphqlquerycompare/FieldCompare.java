package nl.orhun.graphqlquerycompare;

import graphql.language.Field;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class FieldCompare {

    static boolean isEqual(Field field1, Field field2) {
        boolean nameEqual = Objects.equals(field1.getName(), field2.getName());
        boolean aliasEqual = Objects.equals(field1.getAlias(), field2.getAlias());
        boolean argumentsEqual = checkArguments(field1, field2);

        boolean hasSelectionSet1 = field1.getSelectionSet() != null;
        boolean hasSelectionSet2 = field2.getSelectionSet() != null;
        if (!hasSelectionSet1 && !hasSelectionSet2) {
            return nameEqual && aliasEqual && argumentsEqual;
        }
        if (hasSelectionSet1 != hasSelectionSet2) {
            return false;
        }

        boolean selectionSetEqual = SelectionCompare.isEqual(field1.getSelectionSet(), field2.getSelectionSet());

        return nameEqual && aliasEqual && argumentsEqual && selectionSetEqual;
    }

    private static boolean checkArguments(Field field1, Field field2) {
        boolean field1HasArguments1 = !field1.getArguments()
                .isEmpty();
        boolean field1HasArguments2 = !field2.getArguments()
                .isEmpty();
        if (field1HasArguments1 != field1HasArguments2) {
            return false;
        }

        if (field1HasArguments1) {
            return ArgumentCompare.isEqual(field1.getArguments(), field2.getArguments());
        }
        return true;
    }

}
