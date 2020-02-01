import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * 遍历指定路径下的文件夹和文件。
 * 重点就两行。
 *         java.nio.file.Files.walkFileTree(PATH, finder);
 *          SimpleFileVisitor<Path> finder = new SimpleFileVisitor<Path>(){};
 */
public class FilesTraverse {

    public static Path PATH = Paths.get("/Users/linjiejun/Documents/WeChat Files");
    public static Path prePath = Paths.get("/Users/linjiejun/Documents");

    public static void main(String[] args) throws IOException {

        final List<File> files = new ArrayList<File>();
        final List<File> diractory = new ArrayList<File>();
        final List<String> dirsStrRelativePath = new ArrayList();
        final List<String> filesStrRelativePath = new ArrayList();
        SimpleFileVisitor<Path> finder = new SimpleFileVisitor<Path>() {

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                StringBuffer sbf = new StringBuffer();
                for (int i = prePath.getNameCount(); i < dir.getNameCount(); i++) {
                    sbf.append(dir.getName(i).toString() + File.separator);
                }
                dirsStrRelativePath.add(sbf.toString());
                return super.preVisitDirectory(dir, attrs);
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {


                StringBuffer sbf = new StringBuffer();
                for (int i = prePath.getNameCount(); i < file.getNameCount(); i++) {
                    sbf.append(file.getName(i).toString() + File.separator);
                }
                filesStrRelativePath.add(sbf.toString());

                return super.visitFile(file, attrs);
            }
        };

        //region getcurrent path
        String path = Paths.get("").toAbsolutePath().toString();
        System.out.println("Working Directory = " + path);
        //endregion

        java.nio.file.Files.walkFileTree(PATH, finder);

        //reigon dirToSend
        for (String s : dirsStrRelativePath) {
            String dirToSend = path + File.separator + s;
            System.out.println(s);
        }
        System.out.println("-----========---------=========");
        for (String s : filesStrRelativePath) {
            String filesToSend = path + File.separator + s;
            System.out.println(s);
        }
        //endregion
    }

    private static void createDir(String strPath) {
        try {
            Path newDir = Files.createDirectory(Paths.get(strPath));
        } catch (FileAlreadyExistsException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
