package tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class server_awt {


    static int countclients = 0;//счетчик подключившихся клиентов

    public static void main(String[] args) throws IOException {
        ServerSocket sock = null;
        InputStream is = null;
        OutputStream os = null;
        try {
            sock = new ServerSocket(1024); //создаем серверный сокет работающий локально по порту 1024
            while (true) { //бесконечный цикл для возможности подключения последовательно нескольних клиентов
                Socket client = sock.accept(); //сработает, когда клиент подключится,
//                для него выделится отдельный сокет client
                countclients++; //количество подключившихся клиентов увеличивается на 1
                System.out.println("=======================================");
                System.out.println("Client " + countclients + " connected");
                is = client.getInputStream(); //получили входной поток для чтения данных
                os = client.getOutputStream();//получили выходной поток для записи данных
                boolean flag = true;
                while (flag) {
                    byte[] bytes = new byte[1024];
                    is.read(bytes); //чтение иформации, посланной клиентом, из вхоного потока в массив bytes[]
                    String input_string = new String(bytes, StandardCharsets.UTF_8); // переводим тип byte в тип String
                    String[] substrings = input_string.split(" ");// разбиваем на подстроки каждое слово
                    substrings[0]= substrings[0].replaceAll("[^a-zA-Z0-9]","");// заменяем все несимволы на ""
                    substrings[0] = new  StringBuilder(substrings[0]).reverse().toString();
                    os.write(substrings[0].getBytes()); // отправляем клиенту информацию

                }
            }
        } catch (Exception e) {
            System.out.println("Error " + e.toString());
        } finally {
            is.close();//закрытие входного потока
            os.close();//закрытие входного потока
            sock.close();//закрытие сокета, выделенного для работы с подключившимся клиентом
            System.out.println("Client " + countclients + " disconnected");
        }
    }

}


