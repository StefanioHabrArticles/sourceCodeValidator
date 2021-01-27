package com.company;

public class Main {

    public static void main(String[] args) {
        var className = "TradeAlgo";
        var sourceString = "public class TradeAlgo extends AbstractTradingAlgorithm{\n" +
                "@Override\n" +
                "    void handleTicker(Ticker ticker) throws Exception {\n" +
                "       System.out.println(\"TradeAlgo::handleTicker\");\n" +
                "    }\n" +
                "}\n";
        var validator = new Validator(className, sourceString + Utils.superClassSource);
        for (var message : validator.compile()) {
            System.out.println(message);
        }
        var result = validator.testRun("RUBHGD,100.1");
        System.out.println(result.success() + " " + result.getComment());
    }
}
