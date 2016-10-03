package redis;

import java.io.File;
import net.whitbeck.rdbparser.*;

public class RdbFilePrinter {

  public static void printRdbFile(File file) throws Exception {
    try (RdbParser parser = new RdbParser(file)) {
      Entry e;
      while ((e = parser.readNext()) != null) {
        switch (e.getType()) {

        case DB_SELECT:
          System.out.println("Processing DB: " + ((DbSelect)e).getId());
          System.out.println("------------");
          break;

        case EOF:
          System.out.print("End of file. Checksum: ");
          for (byte b : ((Eof)e).getChecksum()) {
            System.out.print(String.format("%02x", b & 0xff));
          }
          System.out.println();
          System.out.println("------------");
          break;

        case KEY_VALUE_PAIR:
          System.out.println("Key value pair");
          KeyValuePair kvp = (KeyValuePair)e;
          System.out.println("Key: " + new String(kvp.getKey(), "ASCII"));
          if (kvp.hasExpiry()) {
            System.out.println("Expiry (ms): " + kvp.getExpiryMillis());
          }
          System.out.println("Value type: " + kvp.getValueType());
          System.out.print("Values: ");
          for (byte[] val : kvp.getValues()) {
            System.out.print(new String(val, "ASCII") + " ");
          }
          System.out.println();
          System.out.println("------------");
          break;
        }
      }
    }
  }
}