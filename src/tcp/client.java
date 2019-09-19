package tcp;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

class Client_awt extends Frame implements  WindowListener {
    TextField ip_adress, port, user_string_input, tf3, tf4, tf5, tf6;
    TextArea result;
    Label label_ip, label_port, label_word, label_result, la4;
    Socket sock = null;
    InputStream is = null;
    OutputStream os = null;

    public static void main(String args[]) {
        Client_awt c = new Client_awt();
        c.GUI();
    }

    private void GUI() {
// super("Клиент");
        setTitle("Client");
        ip_adress = new TextField("127.0.0.1");//ip adress клиента
        port = new TextField("1024");// port клиента
        user_string_input = new TextField();

        result = new TextArea();
        label_ip = new Label("IP");
        label_port = new Label("port");
        label_word = new Label("send word");
        label_result = new Label("result: ");
        la4 = new Label(" ");
        Button button_connect = new Button("connect ");
        Button button_send = new Button("send ");
        ip_adress.setBounds(200, 50, 70, 25);
        port.setBounds(330, 50, 70, 25);
        user_string_input.setBounds(150, 200, 150, 50);
        result.setBounds(50, 300, 250, 150);
        button_connect.setBounds(50, 50, 70, 25);
        button_send.setBounds(50, 200, 70, 25);
        label_ip.setBounds(180, 50, 25, 25);
        label_port.setBounds(290, 50, 150, 25);
        label_word.setBounds(150, 170, 150, 25);
        label_result.setBounds(50, 270, 150, 25);
       la4.setBounds(600, 10, 150, 25);
        add(ip_adress);
        add(port);
        add(user_string_input);
        add(button_connect);
        add(button_send);
        add(result);
        add(label_ip);
        add(label_port);
        add(label_word);
        add(label_result);
        add(la4);
        setSize(450, 480);
        setVisible(true);
        addWindowListener(this);
        button_connect.addActionListener(al);
        button_send.addActionListener(this::sendData);
        user_string_input.getText();

    }

    public void windowClosing(WindowEvent we) {
        if (sock != null && !sock.isClosed()) { // если сокет не пустой и сокет открыт
            try {
                sock.close(); // сокет  закрывается
            } catch (IOException e) {
            }
        }
        this.dispose();
    }

    public void windowActivated(WindowEvent we) {
    }

    ;

    public void windowClosed(WindowEvent we) {
    }

    ;

    public void windowDeactivated(WindowEvent we) {
    }

    ;

    public void windowDeiconified(WindowEvent we) {
    }

    ;

    public void windowIconified(WindowEvent we) {
    }

    ;

    public void windowOpened(WindowEvent we) {
    }

    ;

    public void sendData(ActionEvent e) {
        if (sock == null) {
            return;
        }
        try {

            is = sock.getInputStream(); // входной поток для чтения данных
            os = sock.getOutputStream();// выходной поток для записи данных
            String word = ""; //перменная,в которую записываются введенные числа
            word += user_string_input.getText();
            os.write(word.getBytes()); // отправляем введенные данные. Тип string переводим в byte
            byte[] bytes = new byte[1024];
            is.read(bytes); //получаем назад информацию,которую послал сервер
            String str = new String(bytes, StandardCharsets.UTF_8); // переводим тип byte в String
            result.append(str + "\n"); // в text area записываем полученные данные


        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                os.close();//закрытие выходного потока
                is.close();//закрытие входного потока
                sock.close();//закрытие сокета, выделенного для работы с сервером
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void connectToServer(ActionEvent e) {
    }

    ActionListener al = new ActionListener() { //событие на нажатие кнопки
        @Override
        public void actionPerformed(ActionEvent arg0) {
            try {
                sock = new Socket(InetAddress.getByName(ip_adress.getText()), Integer.parseInt(port.getText()));
                //создается сокет по ip адрессу и порту
            } catch (NumberFormatException e) {
            } catch (UnknownHostException e) {
            } catch (IOException e) {
            }
        }
    };
}

