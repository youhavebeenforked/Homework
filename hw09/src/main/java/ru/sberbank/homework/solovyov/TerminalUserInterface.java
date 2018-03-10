package ru.sberbank.homework.solovyov;

import ru.sberbank.homework.solovyov.exceptions.*;

import java.util.Scanner;

public class TerminalUserInterface {

    public void run() {
        Terminal terminal = new TerminalImpl();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                while (!terminal.getPinAcceptanceStatus()) {
                    System.out.println("Введте пин");
                    terminal.requestPinValidation(scanner.nextLine());
                }
                System.out.println("Выберите действие:\n" +
                        "1. Проверить состояние счета\n" +
                        "2. Снять сумму\n" +
                        "3. Внести сумму\n" +
                        "4. Выход");
                int selectedItem = scanner.nextInt();
                scanner.nextLine();
                switch (selectedItem) {
                    case 1:
                        System.out.println("На вашем счете: " + (terminal.checkAccount()));
                        break;
                    case 2:
                        System.out.println("Введите сумму для снятия кратную 100");
                        int outputCashAmount = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("На вашем счете: " + terminal.getCash(outputCashAmount));
                        break;
                    case 3:
                        System.out.println("Введите сумму для пополнения кратную 100");
                        int inputCashAmount = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("На вашем счете: " + terminal.putCash(inputCashAmount));
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Операция не распознана");
                }
            } catch (AccountIsLockedException accountIsLockedEx) {
                System.out.println("Аккаунт заблокирован. Повторите попытку через " +
                        Integer.parseInt(accountIsLockedEx.getMessage()) / 1000 + " секунд");
            } catch (AccountLoginException accountLoginEx) {
                System.out.println("Попытка входа без ввода ПИН");
            } catch (AccountFundsException accountFundsEx) {
                System.out.println("Недостаточно средств на счете");
            } catch (NumberFormatException numberFormatEx) {
                System.out.println("Неверный формат ПИН. Формат ПИН: ****");
            } catch (ServerConnectionException serverConnectionEx) {
                System.out.println("Нет связи с сервером");
            } catch (MoneyAmountException maEx) {
                System.out.println("Введена неверная сумма");
            }
        }

    }
}
