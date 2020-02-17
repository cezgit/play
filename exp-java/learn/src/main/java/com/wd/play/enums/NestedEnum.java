package com.wd.play.enums;

import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

// Using a nested EnumMap to associate data with enum pairs
public class NestedEnum {

    public static void main(String[] args) {
        System.out.println(Phase.Transition.from(Phase.SOLID, Phase.LIQUID)); // MELT
    }

    /*
    The code to initialize the phase transition map is a bit complicated.
    The type of the map is Map<Phase, Map<Phase, Transition>>, which means “map from (source) phase to map from (destination) phase to transition.”
    This map-of-maps is initialized using a cascaded sequence of two collectors.
    The first collector groups the transitions by source phase, and the second creates an EnumMap with mappings from destination phase to transition.
    The merge function in the second collector ((x, y) -> y)) is unused; it is required only because we need to specify a map factory in order to get an EnumMap,
    and Collectors provides telescoping factories.
     */
    public enum Phase {
        SOLID, LIQUID, GAS, PLASMA;

        public enum Transition {
            MELT(SOLID, LIQUID), FREEZE(LIQUID, SOLID),
            BOIL(LIQUID, GAS),   CONDENSE(GAS, LIQUID),
            SUBLIME(SOLID, GAS), DEPOSIT(GAS, SOLID),
            IONIZE(GAS, PLASMA), DEIONIZE(PLASMA, GAS);

            private final Phase from;
            private final Phase to;

            Transition(Phase from, Phase to) {
                this.from = from;
                this.to = to;
            }
            // Initialize the phase transition map
            private static final Map<Phase, Map<Phase, Transition>> m = Stream.of(values())
                .collect(Collectors.groupingBy(t -> t.from,
                        () -> new EnumMap<>(Phase.class),
                        toMap(t -> t.to, t -> t, (x, y) -> y, () -> new EnumMap<>(Phase.class))));

            public static Transition from(Phase from, Phase to) {
                return m.get(from).get(to);
            }
        }
    }

}
