public enum Level{LOW,MEDIUM,HIGH;public static String advice(Level l){return switch(l){case LOW->"start with the basics";case MEDIUM->"keep practising";case HIGH->"treat this as a priority";};}}
