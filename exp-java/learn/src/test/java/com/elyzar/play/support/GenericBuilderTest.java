package com.elyzar.play.support;

import com.elyzar.play.support.util.GenericBuilder;
import com.elyzar.play.support.util.NamedPredicate;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GenericBuilderTest {

    @Test
    void build() {

        Person person = GenericBuilder.of(Person::new)
                .with(Person::setName, "Otto")
                .with(Person::setAge, 5).build();

        assertThat(person.getAge()).isEqualTo(5);
        assertThat(person.getName()).isEqualTo("Otto");
    }

    @Test
    void buildWithVerifier() {

        NamedPredicate<Person> ageVerifier = new NamedPredicate<>("isOlderThan3", p -> p.getAge() > 3);
        assertThrows(IllegalStateException.class, () -> {
            GenericBuilder.of(Person::new)
                    .withVerifiers(ageVerifier)
                    .with(Person::setName, "Otto").with(Person::setAge, 2).build();
        });
    }
}