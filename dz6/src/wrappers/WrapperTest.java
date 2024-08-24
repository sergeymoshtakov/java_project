package wrappers;

public class WrapperTest {
    public static void main(String[] args) {
        // 2
        // int
        Integer i1 = 10;
        Integer i2 = Integer.valueOf(10);
        Integer i3 = new Integer(10);
        Integer i4 = Integer.parseInt("10");

        // double
        Double d1 = 10.5;
        Double d2 = Double.valueOf(10.5);
        Double d3 = new Double(10.5);
        Double d4= Double.parseDouble("10.5");

        // boolean
        Boolean b1 = true;
        Boolean b2 = Boolean.valueOf(true);
        Boolean b3 = new Boolean(true);
        Boolean b4 = Boolean.parseBoolean("true");

        // char
        Character c1 = 'A';
        Character c2 = Character.valueOf('A');
        Character c3 = new Character('A');

        // float
        Float f1 = 3.14f;
        Float f2 = Float.valueOf(3.14f);
        Float f3 = new Float(3.14f);
        Float f4 = Float.parseFloat("3.14");

        // short
        Short s1 = 1;
        Short s2 = Short.valueOf("1");
        Short s3 = new Short("1");
        Short s4 = Short.parseShort("1");

        // byte
        Byte byte1 = 1;
        Byte byte2 = Byte.valueOf("1");
        Byte byte3 = new Byte("1");
        Byte byte4 = Byte.parseByte("1");

        // long
        Long l1 = 1L;
        Long l2 = Long.valueOf(1L);
        Long l3 = new Long(1L);
        Long l4 = Long.parseLong("1");

        // 3
        Double myDouble = 12.34;
        byte b = myDouble.byteValue();
        short s = myDouble.shortValue();
        int i = myDouble.intValue();
        float f = myDouble.floatValue();
        long l = myDouble.longValue();

        // 4
        Double zero = 0.0;
        Double nonZero = 10.5;

        Double nanValue = nonZero / zero;

        Double infinityValue = zero / zero;

        System.out.println("nanValue: " + nanValue);
        System.out.println("infinityValue: " + infinityValue);

        if (nanValue.isNaN()) {
            System.out.println("Переменная nanValue = NaN");
        }
        if (nanValue.isInfinite()) {
            System.out.println("Переменная nanValue = Infinity");
        }

        if (infinityValue.isNaN()) {
            System.out.println("Переменная infinityValue = NaN");
        }
        if (infinityValue.isInfinite()) {
            System.out.println("Переменная infinityValue = Infinity");
        }

        // 5
        Long long1 = 120L;
        Long long2 = 120L;

        System.out.printf("Result of comparison of long1 to long2 (120): %b", long1 == long2);

        System.out.println();

        long1 = 1200L;
        long2 = 1200L;

        System.out.printf("Result of comparison of long1 to long2 (1200): %b", long1 == long2);
    }
}
