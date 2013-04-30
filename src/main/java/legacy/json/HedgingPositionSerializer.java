package legacy.json;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import legacy.hedge.HedgingPosition;

import java.io.File;
import java.io.IOException;

import static com.google.common.base.Throwables.propagate;

public class HedgingPositionSerializer {
    private final Gson gson;

    public HedgingPositionSerializer() {
        gson = new GsonBuilder().create();
    }

    public HedgingPosition fromJson(File inputFile) {
        try {
            return gson.fromJson(Files.readFirstLine(inputFile, Charsets.UTF_8), HedgingPosition.class);
        } catch (IOException ioe) {
            propagate(ioe);
            return null; // unreachable code
        }
    }

    public void toJson(HedgingPosition hedgingPosition, File outputFile) {
        try {
            Files.write(gson.toJson(hedgingPosition),outputFile,Charsets.UTF_8);
        } catch (IOException ioe) {
            propagate(ioe);
        }
    }

}
