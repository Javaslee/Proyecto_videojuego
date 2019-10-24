package mx.itesm.team4;

import java.util.Random;

public class RandomUtil {
    public static EnemigoTipo getRandomEnemyType() {
        RandomEnum<EnemigoTipo> randomEnum = new RandomEnum<EnemigoTipo>(EnemigoTipo.class);
        return randomEnum.random();
    }

    /**
     * @see [Stack Overflow](http://stackoverflow.com/a/1973018)
     * @param <E>
     */

    private static class RandomEnum<E extends Enum> {

        private static final Random RND = new Random();
        private final E[] values;

        public RandomEnum(Class<E> token) {
            values = token.getEnumConstants();
        }

        public E random() {
            return values[RND.nextInt(values.length)];
        }
    }
}
