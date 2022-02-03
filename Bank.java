import java.util.Scanner;

class FullException extends Exception {
    FullException(String s) {
        super(s);
    }
}

class CannotException extends Exception {
    CannotException(String s) {
        super(s);
    }
}

class EmptyException extends Exception {
    EmptyException(String s) {
        super(s);
    }
}

public class Bank {
    public static Gere[] block = new Gere[20];
    public static Gere_Mokhtasat[] coordinates = new Gere_Mokhtasat[10];
    public static int[] most = new int[20];

    public static String[] original_key = new String[100];
    public static String[] original_value = new String[100];
    public static String[] branch_key = new String[100];
    public static String[] branch_value = new String[100];
    public static String[] branch_original_key = new String[100];
    public static String[] branch_original_value = new String[100];
    public static int[] neighbourhood = new int[8];
    public static int ctr_original = 0;
    public static int ctr_branch = 0;
    public static int ctr_branch_original = 0;

    public static int big = 0;
    public static int index = 0;
    public static int x_mokht;
    public static int y_mokht;
    public static double little = 0;
    public static String asli;
    public static String asli_father;
    public static int x_nazdik;
    public static int y_nazdik;
    public static int R_nazdik;
    public static boolean is_find ;

    public static void insert_addN(String name) {
        Gere_Mahale gere_mahale = new Gere_Mahale(name, neighbourhood[0], neighbourhood[2], neighbourhood[1], neighbourhood[5]);
        if (Gere_Mahale.head == null) {
            Gere_Mahale.head = gere_mahale;
        } else {
            Gere_Mahale sar = Gere_Mahale.head;
            while (sar.next != null) {
                sar = sar.next;
            }
            sar.next = gere_mahale;
        }
    }

    public static void search_neighbourhood_addB(String name, int x, int y) {
        Gere_Mahale sar = Gere_Mahale.head;
        while (sar != null) {
            if ((x <= sar.x1 && x >= sar.x0) && (y <= sar.y0 && y >= sar.y1)) {
                Gere_Mahale gere_mahale = new Gere_Mahale(name, x, y);
                if (sar.down == null) {
                    sar.down = gere_mahale;
                } else {
                    gere_mahale.down = sar.down;
                    sar.down = gere_mahale;
                }
            }
            sar = sar.next;
        }
    }

    public static void search_neighbourhood_addBr(String name, String father_name, int x, int y) {
        Gere_Mahale sar = Gere_Mahale.head;
        while (sar != null) {
            if ((x <= sar.x1 && x >= sar.x0) && (y <= sar.y0 && y >= sar.y1)) {
                Gere_Mahale gere_mahale = new Gere_Mahale(name, father_name, x, y);
                if (sar.down == null) {
                    sar.down = gere_mahale;
                } else {
                    gere_mahale.down = sar.down;
                    sar.down = gere_mahale;
                }
            }
            sar = sar.next;
        }
    }

    public static void search_neighbourhood_listB(String name) {
        Gere_Mahale sar = Gere_Mahale.head;
        while (sar != null) {
            if (sar.name.equals(name)) {
                sar = sar.down;
                if (sar == null) {
                    System.out.println(ANSI_RED + "there isn't any bank here!" + ANSI_RESET);
                } else {
                    while (sar != null) {
                        System.out.println(ANSI_CYAN + "*" + ANSI_RESET);
                        System.out.println(" branch name: " + sar.name);
                        if (sar.father_name != null) {
                            System.out.println(" original branch: " + sar.father_name);
                        }
                        System.out.println(" x: " + sar.x_bank);
                        System.out.println(" y: " + sar.y_bank);
                        System.out.println(ANSI_YELLOW + "---------Done---------" + ANSI_RESET);
                        sar = sar.down;
                    }
                }
                break;
            }
            sar = sar.next;
        }
    }

    public static void availB(int x, int y, Node node, int R) {
        x_mokht = x;
        y_mokht = y;
        R_nazdik = R;
        printPreorder_availB(node);
    }

    public static void printPreorder_availB(Node node) {
        if (node == null) {
            return;
        }
        if (Math.sqrt(Math.pow(node.xy[0] - x_mokht, 2) + Math.pow(node.xy[1] - y_mokht, 2)) <= R_nazdik) {
            if (node.father_name != null) {
                System.out.println(ANSI_CYAN + "*" + ANSI_RESET);
                System.out.println(" branch name: " + node.name);
                System.out.println(" original bank: " + node.father_name);
                System.out.println(" x: " + node.xy[0]);
                System.out.println(" y: " + node.xy[1]);
                System.out.println(ANSI_YELLOW + "---------Done---------" + ANSI_RESET);

            } else {
                System.out.println(ANSI_CYAN + "*" + ANSI_RESET);
                System.out.println(" branch name: " + node.name);
                System.out.println(" x: " + node.xy[0]);
                System.out.println(" y: " + node.xy[1]);
                System.out.println(ANSI_YELLOW + "---------Done---------" + ANSI_RESET);
            }
        }
        printPreorder_availB(node.left);
        printPreorder_availB(node.right);
    }

    public static void search_nearB(int x, int y, Node node) {
        x_mokht = x;
        y_mokht = y;
        x_nazdik=node.xy[0];
        y_nazdik=node.xy[1];
        asli=node.name;
        asli_father=node.father_name;
        little = Math.sqrt(Math.pow(Node.rishe.xy[0] - x, 2) + Math.pow(Node.rishe.xy[1] - y, 2));
        printPreorder_nearB(node);
    }

    public static void printPreorder_nearB(Node node) {
        if (node == null) {
            return;
        }
        if (Math.sqrt(Math.pow(node.xy[0] - x_mokht, 2) + Math.pow(node.xy[1] - y_mokht, 2)) < little) {
            little = Math.sqrt(Math.pow(node.xy[0] - x_mokht, 2) + Math.pow(node.xy[1] - y_mokht, 2));
            asli = node.name;
            x_nazdik = node.xy[0];
            y_nazdik = node.xy[1];
            asli_father=null;
            if (node.father_name != null) {
                asli_father = node.father_name;
            }
        }
        printPreorder_nearB(node.left);
        printPreorder_nearB(node.right);
    }

    public static void search_nearBr(int x, int y, String parent, Node node) {
        x_mokht = x;
        y_mokht = y;
        int num=0;
        for (int i = 0; i < parent.length(); i++) {
            num += parent.charAt(i);
        }

        int key = num % 20;
        //Gere gere = block[key];
        if (block[key].next==null){
            is_find=false;
        }
        else {
            is_find=true;
            if (!node.name.equals(parent) && node.father_name!=null) {
                x_nazdik=node.xy[0];
                y_nazdik=node.xy[1];
                asli_father = parent;
                little = Math.sqrt(Math.pow(Node.rishe.xy[0] - x, 2) + Math.pow(Node.rishe.xy[1] - y, 2));
            }
            else {
                asli_father = parent;
                little = 1000000;
            }
            printPreorder_nearBr(node);
        }
    }

    public static void printPreorder_nearBr(Node node) {
        if (node == null) {
            return;
        }
        if (node.father_name != null ) {
            if (node.father_name.equals(asli_father) && !node.name.equals(asli_father)) {
                if (Math.sqrt(Math.pow(node.xy[0] - x_mokht, 2) + Math.pow(node.xy[1] - y_mokht, 2)) < little) {
                    little = Math.sqrt(Math.pow(node.xy[0] - x_mokht, 2) + Math.pow(node.xy[1] - y_mokht, 2));
                    asli=node.name;
                    x_nazdik = node.xy[0];
                    y_nazdik = node.xy[1];
                }
            }
        }
        printPreorder_nearB(node.left);
        printPreorder_nearB(node.right);
    }

    public static void insert_addB(String name, int num, int x, int y) {
        Gere gere = new Gere(name);
        int key = num % 20;
        block[key] = gere;
        int ctr = most[key];
        most[key] = ctr + 1;
        if (most[key] > big) {
            big++;
            index = key;
        }
        int value = x % 10;
        Gere_Mokhtasat gere_mokhtasat = new Gere_Mokhtasat(y);
        if (coordinates[value] == null) {
            coordinates[value] = gere_mokhtasat;
        } else {
            Gere_Mokhtasat sec = coordinates[value];
            while (sec.next != null) {
                sec = sec.next;
            }
            sec.next = gere_mokhtasat;
        }
    }

    public static void insert_addBr(int num, String branch_name, int x, int y) {
        Gere gere = new Gere(branch_name, x, y);
        int key = num % 20;
        Gere sec = block[key];
        while (sec.next != null) {
            sec = sec.next;
        }
        sec.next = gere;
        int ctr = most[key];
        most[key] = ctr + 1;
        if (most[key] > big) {
            big++;
            index = key;
        }

        int value = x % 10;
        Gere_Mokhtasat gere_mokhtasat = new Gere_Mokhtasat(y);
        if (coordinates[value] == null) {
            coordinates[value] = gere_mokhtasat;
        } else {
            Gere_Mokhtasat node = coordinates[value];
            while (node.next != null) {
                node = node.next;
            }
            node.next = gere_mokhtasat;
        }
    }

    public static void delBr(String branch_name, int num) {
        int key = num % 20;
        Gere gere = block[key];
        Gere help = block[key];
        int j = 0;
        while (gere != null) {
            if (gere.data.equals(branch_name)) {
                help.next = gere.next;
                System.out.println(ANSI_YELLOW + "---------Done---------" + ANSI_RESET);
                break;
            }
            if (j >= 1) {
                help = help.next;
            }
            gere = gere.next;
            j++;
        }
        int ctr = most[key];
        most[key] = ctr - 1;
        if (ctr == big) {
            big--;
            index = key;
        }

    }

    public static void delete_coort(int x, int y) {
        int value = x % 10;
        Gere_Mokhtasat gere_mokhtasat = coordinates[value];
        Gere_Mokhtasat help = coordinates[value];
        int j = 0;
        if (coordinates[value].data==y){
            coordinates[value]=coordinates[value].next;
        }
        else {
            while (gere_mokhtasat != null) {
                if (gere_mokhtasat.data==y) {
                    help.next = gere_mokhtasat.next;
                    break;
                }
                if (j >= 1) {
                    help = help.next;
                }
                gere_mokhtasat = gere_mokhtasat.next;
                j++;
            }
        }
    }

    public static void delBr_matrix(int x, int y, String name) {
        Gere_Mahale sar = Gere_Mahale.head;
        while (sar != null) {
            if ((x <= sar.x1 && x >= sar.x0) && (y <= sar.y0 && y >= sar.y1)) {
                Gere_Mahale g = sar;
                Gere_Mahale help = sar;
                g = g.down;
                while (g != null) {
                    if (g.name.equals(name)) {
                        if (g.down == null) {
                            help.down = null;
                        } else {
                            help.down = g.down;
                        }
                        break;
                    }
                    g = g.down;
                    help = help.down;
                }
            }
            sar = sar.next;
        }
    }

    public static boolean searchCoordinates(int x, int y) {
        int key = x % 10;
        Gere_Mokhtasat gere_mokhtasat = coordinates[key];
        while (gere_mokhtasat != null) {
            if (gere_mokhtasat.data == y) {
                return false;
            }
            gere_mokhtasat = gere_mokhtasat.next;
        }
        return true;
    }

    public static void listBrs(String name) {
        int num = 0;
        for (int i = 0; i < name.length(); i++) {
            num += name.charAt(i);
        }
        int key = num % 20;
        Gere gere = block[key];
        gere = gere.next;
        if (gere == null) {
            System.out.println(ANSI_RED + "This bank doesn't have any branches!!!" + ANSI_RESET);
        }
        while (gere != null) {
            System.out.println(ANSI_CYAN + "*" + ANSI_RESET);
            System.out.println(" branch name: " + gere.data);
            System.out.println(" x: " + gere.x);
            System.out.println(" y: " + gere.y);
            System.out.println(ANSI_YELLOW + "--------------------" + ANSI_RESET);
            gere = gere.next;
        }
    }

    public static void print_most() {
        Gere gere = block[index];
        if (big == 1) {
            System.out.println(ANSI_CYAN + "* " + ANSI_RESET + gere.data + ANSI_CYAN + " * " + ANSI_RESET + "has the most branches with " + big + " branch");
        } else {
            System.out.println(ANSI_CYAN + "* " + ANSI_RESET + gere.data + ANSI_CYAN + " * " + ANSI_RESET + "has the most branches with " + big + " branches");
        }
    }

    public static void swap(Node p1, Node p2) {
        for (int i = 0; i < 2; i++) {
            int temp = p1.xy[i];
            p1.xy[i] = p2.xy[i];
            p2.xy[i] = temp;
        }
        String temp = p1.name;
        p1.name = p2.name;
        p2.name = temp;

        temp = p1.father_name;
        p1.father_name = p2.father_name;
        p2.father_name = temp;
    }

    public static Node delete(Node root, int[] point, int k) {
        if (root == null) {
            return null;
        }
        int cd = k % 2;
        if (PointsSame(root.xy, point)) {
            if (root.right != null) {
                Node min = findMin(root.right, cd,0);
                swap(root, min);
                root.right = delete(root.right,  min.xy, k + 1);
            } else if (root.left != null) {
                Node min = findMin(root.left, cd,0);
                swap(root, min);
                root.right = delete(root.left, min.xy, k + 1);
            } else {
                return null;
            }
            return root;
        }
        if (point[cd] < root.xy[cd]) {
            root.left = delete(root.left,  point, k + 1);
        } else {
            root.right = delete(root.right, point, k + 1);
        }
        return root;
    }

    public static void delete(int x, int y, Node node) {
        int[] point = new int[]{x,y};
        delete(node, point, 0);
    }

    public static Node minNode(Node x, Node y, Node z, int d) {
        Node node = x;
        if (y != null && y.xy[d] < node.xy[d]){
            node = y;
        }
        if (z != null && z.xy[d] < node.xy[d]){
            node = z;
        }
        return node;
    }

    public static Node findMin(Node root, int d, int k) {
        if (root == null) {
            return null;
        }
        int cd = k % 2;
        if (cd == d) {
            if (root.left == null) {
                return root;
            }
            return findMin(root.left, d, k + 1);
        }
        Node y = findMin(root.left, d, k + 1);
        Node z = findMin(root.right, d, k + 1);
        return minNode(root, y, z, d);
    }

    public static boolean PointsSame(int[] point1, int[] point2) {
        for (int i = 0; i < 2; i++) {
            if (point1[i] != point2[i]) {
                return false;
            }
        }
        return true;
    }

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[35m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_Blue = "\u001B[34m";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String cmd, x, y, name, branch_name, R;
        boolean flag = false;
        int num = 0;
        String[] split;
        Tree t = new Tree();
        System.out.println(ANSI_Blue +"Welcome,\n" + "Enter your cmd:" + ANSI_RESET);

        while (scanner.hasNext()) {

            cmd = scanner.nextLine();
            split = cmd.split(" ");

            if (split[0].equals("addN")) {
                for (int i = 0; i < neighbourhood.length; i++) {
                    neighbourhood[i] = Integer.parseInt(split[i + 1]);
                }
                name = split[9];
                insert_addN(name);
                System.out.println(ANSI_YELLOW + "---------Done---------" + ANSI_RESET);
            }  // Done

            else if (split[0].equals("addB")) {
                x = split[1];
                y = split[2];
                name = split[3];
                if (!searchCoordinates(Integer.parseInt(x), Integer.parseInt(y))) {
                    try {
                        throw new FullException(ANSI_RED + "At this point there is already a bank,Try again!" + ANSI_RESET);
                    } catch (FullException e) {
                        System.out.println(e);
                    }
                } else {
                    for (int i = 0; i < name.length(); i++) {
                        num += name.charAt(i);
                    }
                    insert_addB(name, num, Integer.parseInt(x), Integer.parseInt(y));
                    original_key[ctr_original] = x + "," + y;
                    original_value[ctr_original] = name;
                    ctr_original++;

                    search_neighbourhood_addB(name, Integer.parseInt(x), Integer.parseInt(y));

                    int[] xy = new int[]{Integer.parseInt(x), Integer.parseInt(y)};
                    Node.rishe = Tree.add(Node.rishe, xy, name, null, 0);
                    System.out.println(ANSI_YELLOW + "---------Done---------" + ANSI_RESET);
                }

            }  // Done

            else if (split[0].equals("addBr")) {
                x = split[1];
                y = split[2];
                branch_name = split[3];
                name = split[4];
                if (!searchCoordinates(Integer.parseInt(x), Integer.parseInt(y))) {
                    try {
                        throw new FullException(ANSI_RED + "At this point there is already a bank,Try again!" + ANSI_RESET);
                    } catch (FullException E) {
                        System.out.println(E);
                    }
                } else {
                    for (int i = 0; i < name.length(); i++) {
                        num += name.charAt(i);
                    }
                    insert_addBr(num, branch_name, Integer.parseInt(x), Integer.parseInt(y));
                    branch_key[ctr_branch] = x + "," + y;
                    branch_value[ctr_branch] = branch_name;
                    ctr_branch++;
                    branch_original_key[ctr_branch_original] = branch_name;
                    branch_original_value[ctr_branch_original] = name;
                    ctr_branch_original++;
                    search_neighbourhood_addBr(branch_name, name, Integer.parseInt(x), Integer.parseInt(y));
                    int[] xy = new int[]{Integer.parseInt(x), Integer.parseInt(y)};
                    t.rishe = t.add(Node.rishe, xy, name, branch_name, 0);
                    System.out.println(ANSI_YELLOW + "---------Done---------" + ANSI_RESET);
                }

            } // Done

            else if (split[0].equals("delBr")) {
                x = split[1];
                y = split[2];
                String mokhtasat = x + "," + y;

                boolean is_find = searchCoordinates(Integer.parseInt(x), Integer.parseInt(y));
                if (is_find) {
                    try {
                        throw new EmptyException(ANSI_RED + "We don't have this bank!" + ANSI_RESET);
                    } catch (Exception E) {
                        System.out.println(E);
                    }
                }

                int i;
                for (i = 0; i < ctr_original; i++) {
                    if (original_key[i].equals(mokhtasat)) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    try {
                        throw new CannotException(ANSI_RED + "You don't have permission!" + ANSI_RESET);
                    } catch (CannotException C) {
                        System.out.println(C);
                    }
                } else {
                    flag = false;
                    int j;
                    for (j = 0; j < ctr_branch; j++) {
                        if (branch_key[j].equals(mokhtasat)) {
                            flag = true;
                            break;
                        }
                    }
                    if (flag) {
                        String search = branch_value[j];
                        int k;
                        for (k = 0; k < ctr_branch_original; k++) {
                            if (branch_original_key[k].equals(search)) {
                                break;
                            }
                        }
                        for (i = 0; i < branch_original_value[k].length(); i++) {
                            num += branch_original_value[k].charAt(i);
                        }
                        delBr(search, num);
                        branch_key[j] = null;
                        branch_value[j] = null;
                        branch_original_key[k] = null;
                        branch_original_value[k] = null;
                        delBr_matrix(Integer.parseInt(x), Integer.parseInt(y), search);
                        delete_coort(Integer.parseInt(x), Integer.parseInt(y));
                        delete(Integer.parseInt(x), Integer.parseInt(y),Node.rishe);
                    } else {
                        try {
                            throw new EmptyException(ANSI_RED + "We don't have this bank!" + ANSI_RESET);
                        } catch (Exception E) {
                            System.out.println(E);
                        }
                    }
                }
            } // Done

            else if (split[0].equals("listB")) {
                name = split[1];
                search_neighbourhood_listB(name);
            } // Done

            else if (split[0].equals("listBrs")) {
                name = split[1];
                listBrs(name);
                System.out.println(ANSI_YELLOW + "---------Done---------" + ANSI_RESET);
            } // Done

            else if (split[0].equals("nearB")) {
                x = split[1];
                y = split[2];
                search_nearB(Integer.parseInt(x), Integer.parseInt(y), Node.rishe);
                if (asli_father != null) {
                    System.out.println(ANSI_CYAN + "*" + ANSI_RESET);
                    System.out.println(" bank name: " + asli_father);
                    System.out.println(" original bank: " + asli);
                    System.out.println(" x: " + x_nazdik);
                    System.out.println(" y: " + y_nazdik);
                    System.out.println(ANSI_YELLOW + "---------Done---------" + ANSI_RESET);
                } else {
                    System.out.println(ANSI_CYAN + "*" + ANSI_RESET);
                    System.out.println(" bank name: " + asli);
                    System.out.println(" x: " + x_nazdik);
                    System.out.println(" y: " + y_nazdik);
                    System.out.println(ANSI_YELLOW + "---------Done---------" + ANSI_RESET);
                }
            }  // Done

            else if (split[0].equals("nearBr")) {
                x = split[1];
                y = split[2];
                name = split[3];
                search_nearBr(Integer.parseInt(x), Integer.parseInt(y), name, Node.rishe);
                if (!is_find){
                    try {
                        throw new EmptyException(ANSI_RED + "There isn't any bank!" + ANSI_RESET);
                    } catch (Exception E) {
                        System.out.println(E);
                    }
                }
                else {
                    System.out.println(ANSI_CYAN + "*" + ANSI_RESET);
                    System.out.println(" bank name: " + asli_father);
                    System.out.println(" x: " + x_nazdik);
                    System.out.println(" y: " + y_nazdik);
                    System.out.println(ANSI_YELLOW + "---------Done---------" + ANSI_RESET);
                }

            }   // Done

            else if (split[0].equals("availB")) {
                x = split[1];
                y = split[2];
                R = split[3];
                availB(Integer.parseInt(x), Integer.parseInt(y), Node.rishe, Integer.parseInt(R));
            }  // Done

            else if (split[0].equals("mostBrs")) {
                print_most();
            }  // Done

            else if (split[0].equals("exit")){
                System.out.println(ANSI_Blue+"Bye Bye"+ANSI_RESET);
                break;
            }  // Done

            else {
                System.out.println(ANSI_RED + "We don't have this cmd, please try again" + ANSI_RESET);
            } // Done

            num = 0;
            flag = false;

        }
    }
}