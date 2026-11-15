import javax.swing.JFileChooser;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
public class FileInspector
{
    public static void main(String[] args)
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
            System.out.println("No file selected. Exiting.");
            System.exit(0);
        }
        Path file = chooser.getSelectedFile().toPath();
        try {
            List<String> lines = Files.readAllLines(file);
            System.out.println("----- File contents -----");
            for (String line : lines) {
                System.out.println(line);
            }
            System.out.println("----- Report -----");
            System.out.println("File name: " + file.getFileName().toString());
            System.out.println("Line count: " + lines.size());
        }
        catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
}
