package sk.upjs.iot.musicbox;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MusicBoxApp {

  public static class Tone {
    String toneName;
    int length;
    public static final int TONE_CODE_SIZE = 3;

    public Tone(String toneName, int length) {
      this.toneName = toneName;
      this.length = length;
    }

    public String getToneName() {
      return toneName;
    }

    public void setToneName(String toneName) {
      this.toneName = toneName;
    }

    public int getLength() {
      return length;
    }

    public void setLength(int length) {
      this.length = length;
    }

    public int[] getToneCode(String toneName) {
      int[] code = null;
      if (toneName.equals("d1")) {
        int[] c = {1, 0, 0};
        code = Arrays.copyOf(c, c.length);
      } else if (toneName.equals("e1")) {
        int[] c = {0, 1, 0};
        code = Arrays.copyOf(c, c.length);
      } else if (toneName.equals("fis1")) {
        int[] c = {0, 0, 1};
        code = Arrays.copyOf(c, c.length);
      } else if (toneName.equals("g1")) {
        int[] c = {1, 1, 0};
        code = Arrays.copyOf(c, c.length);
      } else if (toneName.equals("a1")) {
        int[] c = {1, 0, 1};
        code = Arrays.copyOf(c, c.length);
      } else if (toneName.equals("h1")) {
        int[] c = {0, 1, 1};
        code = Arrays.copyOf(c, c.length);
      } else if (toneName.equals("c2")) {
        int[] c = {1, 1, 1};
        code = Arrays.copyOf(c, c.length);
      } else {
        int[] c = {0, 0, 0};
        code = Arrays.copyOf(c, c.length);
      }
      return code;
    }
  }

  public static void main(String[] args) throws FileNotFoundException, IOException {
    MusicBoxApp mba = new MusicBoxApp();
    List<Tone> tones = mba.createTonesList();
    createSheet(tones);
  }

  public List<Tone> createTonesList() {
    List<Tone> tones = new LinkedList<>();
    tones.add(new Tone("d1", 2));

    tones.add(new Tone("g1", 2));
    tones.add(new Tone("g1", 1));
    tones.add(new Tone("a1", 1));
    tones.add(new Tone("g1", 1));
    tones.add(new Tone("fis1", 1));

    tones.add(new Tone("e1", 2));
    tones.add(new Tone("e1", 2));
    tones.add(new Tone("e1", 2));

    tones.add(new Tone("a1", 2));
    tones.add(new Tone("a1", 1));
    tones.add(new Tone("h1", 1));
    tones.add(new Tone("a1", 1));
    tones.add(new Tone("g1", 1));

    tones.add(new Tone("fis1", 2));
    tones.add(new Tone("fis1", 2));
    tones.add(new Tone("fis1", 2));

    tones.add(new Tone("h1", 2));
    tones.add(new Tone("h1", 1));
    tones.add(new Tone("c2", 1));
    tones.add(new Tone("h1", 1));
    tones.add(new Tone("a1", 1));

    tones.add(new Tone("g1", 2));
    tones.add(new Tone("e1", 2));
    tones.add(new Tone("d1", 1));
    tones.add(new Tone("d1", 1));

    tones.add(new Tone("e1", 2));
    tones.add(new Tone("a1", 2));
    tones.add(new Tone("fis1", 2));

    tones.add(new Tone("g1", 2));
    return tones;
  }

  private static void createSheet(List<Tone> tones) {
    try (OutputStream fileOut = new FileOutputStream("Javatpoint.xlsx")) {
      Workbook wb = new XSSFWorkbook();
      Sheet sheet = wb.createSheet("Sheet");
      int rowNumber = 1;

      CellStyle style = wb.createCellStyle();
      style.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
      style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

      for (Tone tone : tones) {
        for (int lines = 0; lines < tone.getLength(); lines++) {
          Row row = sheet.createRow(rowNumber);
          row.setHeight((short) 600);
          rowNumber++;
          for (int j = 0; j < Tone.TONE_CODE_SIZE; j++) {
            if (tone.getToneCode(tone.getToneName())[j] == 1) {
              Cell cell = row.createCell(2 * j + 1);
              cell.setCellStyle(style);
            }
          }
        }
        Row row = sheet.createRow(rowNumber);
        rowNumber++;
        row.setHeight((short) 300);
      }

      wb.write(fileOut);
      wb.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
