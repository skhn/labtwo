package io.cfapps.weektwo.repository;

/**
 * Database Access information and Dataset.
 */
public final class Dataset {

    private static final String URL = "https://bootcamp-training-files.cfapps.io/week2/week2-stocks.json";
    private static final String localDataset = "/Users/salmanazamkhan/Documents/labtwo/src/main/resources/dataset.json";


    public static String getLocalDataset() {
        return localDataset;
    }

    public static String getURL() {
        return URL;
    }
}
