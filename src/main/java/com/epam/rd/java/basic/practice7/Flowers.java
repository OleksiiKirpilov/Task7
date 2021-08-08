package com.epam.rd.java.basic.practice7;

import java.util.ArrayList;
import java.util.List;

public class Flowers {

    private static final String ERR_ILLEGAL_ENUM = "Illegal enum value!";

    public List<Flower> flowers;

    public Flowers() {
        flowers = new ArrayList<>();
    }


    public static class Flower {

        public String name;
        public Soil soil;
        public String origin;
        public VisualParameters visualParameters;
        public GrowingTips growingTips;
        public Multiplying multiplying;


        public enum Soil {
            SOIL1("подзолистая"),
            SOIL2("грунтовая"),
            SOIL3("дерново-подзолистая");

            private final String text;

            Soil(final String text) {
                this.text = text;
            }

            @Override
            public String toString() {
                return text;
            }

            public static Soil findValue(String s) {
                for (Soil v : values()) {
                    if (v.toString().equals(s)) {
                        return v;
                    }
                }
                throw new IllegalArgumentException(ERR_ILLEGAL_ENUM);
            }
         }


        public static class VisualParameters {

            public String stemColour;
            public String leafColour;
            public AveLenFlower aveLenFlower;

            public static class AveLenFlower {
                public int value;
                public String measure = "cm";
            }

        }

        public static class GrowingTips {

            public Temperature temperature;
            public Lighting lighting;
            public Watering watering;

            public static class Temperature {

                public int value;
                public String measure = "celcius";

            }

            public static class Lighting {

                public LightRequiring lightRequiring;

                public enum LightRequiring {
                    YES("yes"),
                    NO("no");

                    private final String text;

                    LightRequiring(final String text) {
                        this.text = text;
                    }

                    @Override
                    public String toString() {
                        return text;
                    }

                    public static LightRequiring findValue(String s) {
                        for (LightRequiring v : values()) {
                            if (v.toString().equals(s)) {
                                return v;
                            }
                        }
                        throw new IllegalArgumentException(ERR_ILLEGAL_ENUM + " " + s);
                    }

                }
            }

            public static class Watering {

                public int value;
                public String measure = "mlPerWeek";

            }

        }

        public enum Multiplying {
            MULT1("листья"),
            MULT2("черенки"),
            MULT3("семена");

            private final String text;

            Multiplying(final String text) {
                this.text = text;
            }

            @Override
            public String toString() {
                return text;
            }

            public static Multiplying findValue(String s) {
                for (Multiplying v : values()) {
                    if (v.toString().equals(s)) {
                        return v;
                    }
                }
                throw new IllegalArgumentException(ERR_ILLEGAL_ENUM);
            }

        }

    }


}
