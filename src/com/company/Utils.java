package com.company;

public class Utils {
    public static String superClassName = "AbstractTradingAlgorithm";
    public static String superClassSource = "\nabstract class AbstractTradingAlgorithm {\n" +
            "\n" +
            "    abstract void handleTicker(Ticker ticker) throws Exception;\n" +
            "\n" +
            "    public void receiveTick(String tick) throws Exception {\n" +
            "        handleTicker(Ticker.parse(tick));\n" +
            "    }\n" +
            "\n" +
            "    static class Ticker {\n" +
            "        String pair;\n" +
            "        double price;\n" +
            "\n" +
            "       static Ticker parse(String tick) {\n" +
            "           Ticker ticker = new Ticker();\n" +
            "           String[] tickerSplit = tick.split(\",\");\n" +
            "           ticker.pair = tickerSplit[0];\n" +
            "           ticker.price = Double.valueOf(tickerSplit[1]);\n" +
            "           return ticker;\n" +
            "       }\n" +
            "\n" +
            "    }\n" +
            "\n" +
            "}";
}
