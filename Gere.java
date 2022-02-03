public class Gere {
    Gere next;
    String data;
    int x;
    int y;

    Gere(String data) {
        this.data = data;
    }

    Gere(String data, int x, int y) {
        this.data = data;
        this.x = x;
        this.y = y;
    }
}