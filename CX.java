import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public final class CX {

    private static String[] args;
    private static Scanner scanner;

    public static void main(String[] args) {
        CX.args = args;
        CX.scanner = new Scanner(System.in);

        String fileIn = argOrInput("file", "请输入要操作的文件(带后缀): ");
        String fileOut = argOrInput("output", "请输入输出的文件名(带后缀): ");

        try {
            byte[] dataIn = Files.readAllBytes(Paths.get(fileIn));
            System.out.println("已读取文件数据");
            byte[] dataOut = cx(dataIn);
            Files.write(Paths.get(fileOut), dataOut);
            System.out.println("已写入数据");
        } catch (FileNotFoundException e) {
            System.out.println("未找到文件: '" + fileIn + "'!");
        } catch (IOException e) {
            System.out.println("在读取时发生了错误!");
        }
    }

    private static byte[] cx(final byte[] bytes) {
        final byte[] result = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            if (i % 2 != 0) result[i] = (byte) (bytes[i] ^ 0x64);
            else result[i] = result[i] = (byte) (bytes[i] ^ 0x32);
        }
        return result;
    }

    private static String argOrInput(String argName, String prompt) {
        for (String arg : args) {
            if (arg.startsWith(argName + "=") && arg.length() > argName.length() + 1) {
                return arg.split("=")[1];
            }
        }
        return input(prompt);
    }

    private static String input(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

}
