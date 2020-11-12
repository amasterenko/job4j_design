package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsFind {
    /**
     * Class for storing and validating the arguments of FindFiles.main()
     * Input arguments: -d c:/ -n *.txt [-m , -f , -r] -o log.txt
     * -d - start directory
     * -n - search pattern(file/mask/regexp)
     * -m - search by mask
     * -f - search by complete match
     * -r - search by regexp
     * -o - output file
     *
     * getPattern method returns the search pattern specified after -n if -f or -r parameters was found in args.
     * It returns regexp like ".*\.txt" if -m parameter was found in args.
     */
    private final String[] inputArgs;
    private final Map<String, String> parsedArgs = new HashMap<>();

    public ArgsFind(String[] inputArgs) {
        this.inputArgs = inputArgs;
    }

    public boolean validate() {
        if (inputArgs.length < 7) {
            return false;
        }
        String prevArg = "";
        for (String arg : inputArgs) {
            if (arg.equals("-m") || arg.equals("-f") || arg.equals("-r")) {
                parsedArgs.put("opt", arg);
                continue;
            }
            if (arg.equals("-d") || arg.equals("-n") || arg.equals("-o")) {
                prevArg = arg;
                continue;
            }
            switch (prevArg) {
                case "-d":
                    parsedArgs.put("dir", arg);
                    prevArg = "";
                    break;
                case "-n":
                    parsedArgs.put("pattern", arg);
                    prevArg = "";
                    break;
                case "-o":
                    parsedArgs.put("log", arg);
                    prevArg = "";
                    break;
                default:
            }
        }
        return parsedArgs.size() == 4;
    }

    public String getDir() {
        return parsedArgs.getOrDefault("dir", "");
    }

    public String getLogPath() {
        return parsedArgs.getOrDefault("log", "");
    }

    public String getPattern() {
        String rsl = "";
        switch (parsedArgs.getOrDefault("opt", "")) {
            case "-f" , "-r":
                rsl = parsedArgs.getOrDefault("pattern", "");
                break;
            case "-m":
                StringBuilder newString = new StringBuilder(parsedArgs.getOrDefault("pattern", "   "));
                rsl = newString.insert(0, ".").insert(2, "\\").toString();
                break;
            default:
        }
        return rsl;
    }

}
