package ua.com.yaminsky.bank;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.com.yaminsky.bank.view.MyView;

@SpringBootApplication(scanBasePackages = "ua.com.yaminsky")
@AllArgsConstructor
public class BankApplication implements CommandLineRunner {
    private MyView view;

    public static void main(String[] args) {
        SpringApplication.run(BankApplication.class, args);
    }


    @Override
    public void run(String... args) {
        view.show();
    }
}
