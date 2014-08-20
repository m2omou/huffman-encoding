import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputHelper {

    // Length of outputStr must be multiple of 8;
    static void writeBinStrToFile(String outputStr, String outputFileName) {

        int strLen = outputStr.length();
        if (strLen % 8 != 0) {
            System.err
                    .printf("Length of outputStr must a multiple of 8! Tried to write binary string: %s\n",
                            outputStr);
            System.exit(1);
        }

        byte[] toWrite = new byte[strLen / 8];
        for (int i = 0; i < outputStr.length() / 8; i++) {
            toWrite[i] = (byte) Integer.parseInt(
                    outputStr.substring(i * 8, (i + 1) * 8), 2);
        }

        FileOutputStream output;
        try {
            output = new FileOutputStream(outputFileName, true);
            output.write(toWrite);
            output.close();
        } catch (FileNotFoundException e) {
            System.err.printf("Can't find file %s", outputFileName);
        } catch (IOException e) {
            System.err.println("Error with writing to output file");
        }
    }
}