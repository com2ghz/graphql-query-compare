package nl.orhun.graphqlquerycompare;

import graphql.language.Definition;
import graphql.language.OperationDefinition;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class DefinitionCompare {
    static boolean isEqual(List<Definition> definitions1, List<Definition> definitions2) {
        if (definitions1.size() != definitions2.size()) {
            return false;
        }

        return definitions1.stream()
                .allMatch(definition1 -> definitions2.stream()
                        .anyMatch(definition2 -> DefinitionCompare.isEqual(definition1, definition2)));
    }

    private static boolean isEqual(Definition definition1, Definition definition2) {
        if (!(definition1 instanceof OperationDefinition) || !(definition2 instanceof OperationDefinition)) {
            throw new IllegalStateException(
                    String.format("Currently only operation %s is supported. Found operation1: %s, operation2: %s",
                            OperationDefinition.class, definition1.getClass(), definition2.getClass()));
        }

        OperationDefinition operationDefinition1 = (OperationDefinition) definition1;
        OperationDefinition operationDefinition2 = (OperationDefinition) definition2;
        boolean operationDefinitionEqual = operationDefinition1.isEqualTo(operationDefinition2);
        if (!operationDefinitionEqual) {
            return false;
        }

        return SelectionCompare.isEqual(operationDefinition1.getSelectionSet(), operationDefinition2.getSelectionSet());
    }
}
