public class Gere_Mahale {
    public static Gere_Mahale head;
    Gere_Mahale next;
    Gere_Mahale down;
    String name;
    String father_name;
    int x0;
    int x1;
    int y0;
    int y1;
    int x_bank;
    int y_bank;

    Gere_Mahale(String name, int x0, int x1, int y0, int y1) {
        this.name = name;
        this.x0 = x0;
        this.x1 = x1;
        this.y0 = y0;
        this.y1 = y1;
    }

    Gere_Mahale(String name, int x_bank, int y_bank) {
        this.name = name;
        this.x_bank = x_bank;
        this.y_bank = y_bank;
    }

    Gere_Mahale(String name, String father_name, int x_bank, int y_bank) {
        this.name = name;
        this.father_name = father_name;
        this.x_bank = x_bank;
        this.y_bank = y_bank;
    }
}