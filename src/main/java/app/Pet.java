package app;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pet {
    private String name;

    @Override
    public String toString() {
        return "Pet{" +
                ", name='" + name + '\'' +
                '}';
    }
}
