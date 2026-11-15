import javax.swing.JFileChooser;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
public class DataSaver
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        ArrayList<String> records = new ArrayList<>();
        boolean done = false;
        do {
            String firstName = SafeInput.getNonZeroLenString(in, "Enter first name");
            String lastName = SafeInput.getNonZeroLenString(in, "Enter last name");
            int idNum = SafeInput.getRangedInt(in, "Enter ID number", 0, 999999);
            String email = SafeInput.getNonZeroLenString(in, "Enter email");
            int birthYear = SafeInput.getRangedInt(in, "Enter birth year", 1900, 2026);
            String record = String.format("%s, %s, %06d, %s, %d", firstName, lastName, idNum, email, birthYear);
            records.add(record);
            done = !SafeInput.getYNConfirm(in, "Add another record?");
        } while (!done);
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        if (chooser.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) {
            System.out.println("No file selected. Nothing was saved.");
            System.exit(0);
        }
        Path file = chooser.getSelectedFile().toPath();
        try (BufferedWriter writer = Files.newBufferedWriter(file)){
            for (String rec : records) {
                writer.write(rec);
                writer.newLine();
            }
            System.out.println("Saved " + records.size() + " records to " + file.getFileName());
        }
        catch (IOException e) {
            System.out.println("Error writing the file: " + e.getMessage());
        }
    }
}
