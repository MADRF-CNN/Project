import java.util.Scanner;
public class pj1 {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        String Map[][] = {{"6虎 ", "    ", "8象 ", "    ", "    ", "    ", "鼠1 ", "    ", "狮7 "},
                {"    ", "2猫 ", "    ", " 水 ", " 水 ", " 水 ", "    ", "狗4 ", "    "},
                {" 陷 ", "    ", "3狼 ", " 水 ", " 水 ", " 水 ", "豹5 ", "    ", " 陷 "},
                {" 家 ", " 陷 ", "    ", "    ", "    ", "    ", "    ", " 陷 ", " 家 "},
                {" 陷 ", "    ", "5豹 ", " 水 ", " 水 ", " 水 ", "狼3 ", "    ", " 陷 "},
                {"    ", "4狗 ", "    ", " 水 ", " 水 ", " 水 ", "    ", "猫2 ", "    "},
                {"7狮 ", "    ", "1鼠 ", "    ", "    ", "    ", "象8 ", "    ", "虎6 "}};
        boolean player = true;
        int i = 0;
        int j = 0;
        for (i = 0; i < 7; i++) {
            for (j = 0; j < 9; j++) {
                System.out.print(Map[i][j]);
            }
            System.out.print("\n");
        }
        Game:
        while (true) {
            System.out.print("\n"+"next move :"+"\n");
            String nextPosition = scanner.next();
            if (nextPosition.equals("help")) {
                System.out.print("狮虎跳河法：狮虎在小河边时，可以纵横对直跳过小河，且能把小河对岸的敌方较小的兽类吃掉，但是如果对方老鼠在河里，把跳的路线阻隔就不能跳，若对岸是对方比自己战斗力前的兽，也不可以跳过小河；\n" +
                        "鼠游过河法：鼠是唯一可以走入小河的兽，走法同陆地上一样，每次走一格，上下左右均可，而且，陆地上的其他兽不可以吃小河中的鼠，小河中的鼠也不能吃陆地上的象，鼠类互吃不受小河影响。\n" +
                        "\n" +
                        "**斗兽棋的吃法**\n" +
                        "斗兽棋吃法分普通吃法和特殊此法，普通吃法是按照兽的战斗力强弱，强者可以吃弱者。\n" +
                        "特殊吃法如下：\n" +
                        "1、鼠吃象法：八兽的吃法除按照战斗力强弱次序外，惟鼠能吃象，象不能吃鼠。\n" +
                        "2、互吃法：凡同类相遇，可互相吃。\n" +
                        "3、陷阱：棋盘设陷阱，专为限制敌兽的战斗力（自己的兽，不受限制），敌兽走入陷阱，即失去战斗力，本方的任意兽类都可以吃去陷阱里的兽类。\n" +
                        "综合普通吃法和特殊吃法，将斗兽棋此法总结如下：\n" +
                        "\n" +
                        "鼠可以吃象、鼠\n" +
                        "猫可以吃猫、鼠；\n" +
                        "狼可以吃狼、猫、鼠；\n" +
                        "狗可以吃狗、狼、猫、鼠；\n" +
                        "豹可以吃豹、狗、狼、猫、鼠；\n" +
                        "虎可以吃虎、豹、狗、狼、猫、鼠；\n" +
                        "狮可以吃狮、虎、豹、狗、狼、猫、鼠；\n" +
                        "象可以吃象、狮、虎、豹、狗、狼、猫；" +
                        "1. 任何一方的兽走入敌方的兽穴就算胜利（自己的兽类不可以走入自己的兽穴）；\n" +
                        "2. 任何一方的兽被吃光就算失败，对方获胜\n" +
                        "3. 任何一方所有活着的兽被对方困住，均不可移动时，就算失败，对方获胜；" +
                        "我们使用数字1-8来表示动物，wasd代表上下左右。");
                continue Game;
            }
            if (nextPosition.equals("exit")){
                break Game;
            }
            if (!nextPosition.substring(0, 1).equals("1") && !nextPosition.substring(0, 1).equals("2") && !nextPosition.substring(0, 1).equals("3") &&
                    !nextPosition.substring(0, 1).equals("4") && !nextPosition.substring(0, 1).equals("5") && !nextPosition.substring(0, 1).equals("6") &&
                    !nextPosition.substring(0, 1).equals("7") && !nextPosition.substring(0, 1).equals("8")) {
                System.out.print("输入不正确，请重新输入");
                continue Game;
            } else if (nextPosition.length() > 2) {
                System.out.print("输入不正确，请重新输入");
                continue Game;
            } else if (!nextPosition.substring(1, 2).equals("w") && !nextPosition.substring(1, 2).equals("a") && !nextPosition.substring(1, 2).equals("s") &&
                    !nextPosition.substring(1, 2).equals("d")) {
                System.out.print("输入不正确，请重新输入");
                continue Game;
            }
            player = Move(nextPosition, Map, i, j, player);

            if (!Map[3][0].equals(" 家 ")){
                System.out.print("右方胜利");
            } else if (!Map[3][8].equals(" 家 ")){
                System.out.print("左方胜利");
            }


        }
    }

    //下面写的是找用户输入的棋子的方法
    private static int[] Search(String[][] Map, String nextPosition, boolean player) {
        String m = nextPosition.substring(0, 1);
        int[] record = new int[2];//这个数组用来记录找到的棋子的位置
        int i;
        int j;
        for (i = 0; i < 7; i++) {
            for (j = 0; j < 9; j++) {
                if (player && Map[i][j].substring(0, 1).equals(m)) {
                    record[0] = i;
                    record[1] = j;
                    return record;
                } else if (!player && Map[i][j].substring(1, 2).equals(m)) {
                    record[0] = i;
                    record[1] = j;
                    return record;
                }
            }
        }
        return record;
    }

    //以下是移动棋子的方法
    private static boolean Move(String nextPosition, String Map[][], int i, int j, boolean player) {
        String n = nextPosition.substring(1, 2);//n是用户输入的字符串的第二位
        i = Search(Map, nextPosition, player)[0];
        j = Search(Map, nextPosition, player)[1];

        //制定w，a，s，d的含义
        switch (n) {
            case "w": {
                //写的是老鼠的移动方法
                String h = " 水 ";
                if (player && (nextPosition.substring(0, 1).equals("1"))) {
                    if (Search(Map, nextPosition, player)[0] - 1 < 0) {
                        System.out.print("出界了，请重新输入");
                    } else if (i >= 4&&Map[Search(Map, nextPosition, player)[0] - 1][Search(Map, nextPosition, player)[1]] == Map[3][0]) {
                        System.out.print("不能走进自己的家");
                    } else if (Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("1鼠 ")) {
                        if (!Map[i - 1][j].equals("    ")) {
                            if (Map[i - 1][j].substring(0, 1).equals("1") || Map[i - 1][j].substring(0, 1).equals("2") || Map[i - 1][j].substring(0, 1).equals("3") ||
                                    Map[i - 1][j].substring(0, 1).equals("4") || Map[i - 1][j].substring(0, 1).equals("5") || Map[i - 1][j].substring(0, 1).equals("6") ||
                                    Map[i - 1][j].substring(0, 1).equals("7") || Map[i - 1][j].substring(0, 1).equals("8")) {
                                System.out.print("不能吃自己的动物啊");
                            } else if (Map[i - 1][j].equals(" 水 ") && Map[i - 2][j].equals(" 水 ")) {
                                Map[i - 1][j] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else if (Map[i - 1][j].equals(" 水 ") && !Map[i - 2][j].equals(" 水 ")) {
                                Map[i - 1][j] = Map[i][j];
                                Map[i][j] = h;
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else if (i < 6 && !Map[i - 1][j].equals(" 水 ") && (Map[i + 1][j].equals(" 水 ") || Map[i + 1][j].equals(" 鼠1 "))) {
                                Map[i - 1][j] = Map[i][j];
                                Map[i][j] = h;
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else if ((Integer.parseInt(Map[i][j].substring(0, 1)) == 1 && Integer.parseInt(Map[i - 1][j].substring(1, 2)) == 8)
                                    ||(Integer.parseInt(Map[i][j].substring(0, 1)) == 1 && Integer.parseInt(Map[i - 1][j].substring(1, 2)) == 1)) {
                                Map[i - 1][j] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else {
                                System.out.print("不能这么走");
                            }
                        }  else {
                            Map[i - 1][j] = Map[i][j];
                            Map[i][j] = "    ";
                            for (i = 0; i < 7; i++) {
                                for (j = 0; j < 9; j++) {
                                    System.out.print(Map[i][j]);
                                }
                                System.out.print("\n");
                            }
                            player = !player;
                        }
                    }
                } else if (!player && nextPosition.substring(0, 1).equals("1")) {
                    if (Search(Map, nextPosition, player)[0] - 1 < 0) {
                        System.out.print("出界了，请重新输入");
                    } else if (i >= 4&&Map[Search(Map, nextPosition, player)[0] - 1][Search(Map, nextPosition, player)[1]] == Map[3][8]) {
                        System.out.print("不能走进自己的家");
                    } else if (Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("鼠1 ")) {
                        if (!Map[i - 1][j].equals("    ")) {
                            if (Map[i - 1][j].substring(1, 2).equals("1") || Map[i - 1][j].substring(1, 2).equals("2") || Map[i - 1][j].substring(1, 2).equals("3") ||
                                    Map[i - 1][j].substring(1, 2).equals("4") || Map[i - 1][j].substring(1, 2).equals("5") || Map[i - 1][j].substring(1, 2).equals("6") ||
                                    Map[i - 1][j].substring(1, 2).equals("7") || Map[i - 1][j].substring(1, 2).equals("8")) {
                                System.out.print("不能吃自己的动物啊");
                            } else if (Map[i - 1][j].equals(" 水 ") && Map[i - 2][j].equals(" 水 ")) {
                                Map[i - 1][j] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else if (Map[i - 1][j].equals(" 水 ") && !Map[i - 2][j].equals(" 水 ")) {
                                Map[i - 1][j] = Map[i][j];
                                Map[i][j] = h;
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else if (!Map[i - 1][j].equals(" 水 ") && Map[i + 1][j].equals(" 水 ")) {
                                Map[i - 1][j] = Map[i][j];
                                Map[i][j] = h;
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else if ((Integer.parseInt(Map[i][j].substring(1, 2)) == 1 && Integer.parseInt(Map[i - 1][j].substring(0, 1)) == 8)
                                    ||(Integer.parseInt(Map[i][j].substring(1, 2)) == 1 && Integer.parseInt(Map[i - 1][j].substring(0, 1)) == 1)) {
                                Map[i - 1][j] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else {
                                System.out.print("不能这么走");
                            }
                        }  else {
                            Map[i - 1][j] = Map[i][j];
                            Map[i][j] = "    ";
                            for (i = 0; i < 7; i++) {
                                for (j = 0; j < 9; j++) {
                                    System.out.print(Map[i][j]);
                                }
                                System.out.print("\n");
                            }
                            player = !player;
                        }
                    }
                }
                //狮虎跳河的部分
                if (player && (nextPosition.substring(0, 1).equals("6") || nextPosition.substring(0, 1).equals("7"))) {
                    if (Search(Map, nextPosition, player)[0] - 1 < 0) {
                        System.out.print("出界了，请重新输入");
                    } else if (i >= 4&&Map[Search(Map, nextPosition, player)[0] - 1][Search(Map, nextPosition, player)[1]] == Map[3][0]) {
                        System.out.print("不能走进自己的家");
                    } else if (Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("6虎 ")) {
                        if (!Map[i - 1][j].equals("    ")&&!Map[i - 1][j].equals(" 水 ")&&!Map[i - 1][j].equals(" 陷 ")) {
                            if (Map[i - 1][j].substring(0, 1).equals("1") || Map[i - 1][j].substring(0, 1).equals("2") || Map[i - 1][j].substring(0, 1).equals("3") ||
                                    Map[i - 1][j].substring(0, 1).equals("4") || Map[i - 1][j].substring(0, 1).equals("5") || Map[i - 1][j].substring(0, 1).equals("6") ||
                                    Map[i - 1][j].substring(0, 1).equals("7") || Map[i - 1][j].substring(0, 1).equals("8")) {
                                System.out.print("不能吃自己的动物啊");
                            } else if (Integer.parseInt(Map[i][j].substring(0, 1)) >= Integer.parseInt(Map[i - 1][j].substring(1, 2))) {
                                Map[i - 1][j] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else {
                                System.out.print("不能这么走");
                            }
                        } else if (Map[i - 1][j].equals(" 水 ")) {
                            if (Map[i - 3][j].substring(1, 2).equals("1") || Map[i - 3][j].substring(1, 2).equals("2") ||
                                    Map[i - 3][j].substring(1, 2).equals("3") || Map[i - 3][j].substring(1, 2).equals("4") ||
                                    Map[i - 3][j].substring(1, 2).equals("5") || Map[i - 3][j].equals("    ")|| Map[i - 3][j].substring(1, 2).equals("6")) {
                                Map[i - 3][j] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else if (Map[i - 3][j].substring(0, 1).equals("8")|| Map[i - 3][j].substring(0, 1).equals("7")) {
                                System.out.print("不能这么走，请重新输入");
                            } 
                        } else {
                            Map[i - 1][j] = Map[i][j];
                            Map[i][j] = "    ";
                            for (i = 0; i < 7; i++) {
                                for (j = 0; j < 9; j++) {
                                    System.out.print(Map[i][j]);
                                }
                                System.out.print("\n");
                            }
                            player = !player;
                        }
                    } else if (Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("7狮 ")) {
                        if (!Map[i - 1][j].equals("    ")&&!Map[i - 1][j].equals(" 水 ")&&!Map[i - 1][j].equals(" 陷 ")) {
                            if (Map[i - 1][j].substring(0, 1).equals("1") || Map[i - 1][j].substring(0, 1).equals("2") || Map[i - 1][j].substring(0, 1).equals("3") ||
                                    Map[i - 1][j].substring(0, 1).equals("4") || Map[i - 1][j].substring(0, 1).equals("5") || Map[i - 1][j].substring(0, 1).equals("6") ||
                                    Map[i - 1][j].substring(0, 1).equals("7") || Map[i - 1][j].substring(0, 1).equals("8")) {
                                System.out.print("不能吃自己的动物啊");
                            } else if (Integer.parseInt(Map[i][j].substring(0, 1)) >= Integer.parseInt(Map[i - 1][j].substring(1, 2))) {
                                Map[i - 1][j] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else {
                                System.out.print("不能这么走");
                            }
                        } else if (Map[i - 1][j].equals(" 水 ")) {
                            if (Map[i - 3][j].substring(1, 2).equals("1") || Map[i - 3][j].substring(1, 2).equals("2") ||
                                    Map[i - 3][j].substring(1, 2).equals("3") || Map[i - 3][j].substring(1, 2).equals("4") ||
                                    Map[i - 3][j].substring(1, 2).equals("5") || Map[i - 3][j].substring(1, 2).equals("6") ||
                                    Map[i - 3][j].equals("    ")|| Map[i - 3][j].substring(1, 2).equals("7")) {
                                Map[i - 3][j] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else if (Map[i - 3][j].substring(0, 1).equals("8")) {
                                System.out.print("不能这么走，请重新输入");
                            }
                        } else {
                            Map[i - 1][j] = Map[i][j];
                            Map[i][j] = "    ";
                            for (i = 0; i < 7; i++) {
                                for (j = 0; j < 9; j++) {
                                    System.out.print(Map[i][j]);
                                }
                                System.out.print("\n");
                            }
                            player = !player;
                        }
                    }
                } else if (!player && (nextPosition.substring(0, 1).equals("6") || nextPosition.substring(0, 1).equals("7"))) {
                    if (Search(Map, nextPosition, player)[0] - 1 < 0) {
                        System.out.print("出界了，请重新输入");
                    } else if (i >= 4&&Map[Search(Map, nextPosition, player)[0] - 1][Search(Map, nextPosition, player)[1]] == Map[3][8]) {
                        System.out.print("不能走进自己的家");
                    } else if (Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("虎6 ")) {
                        if (!Map[i - 1][j].equals("    ")&&!Map[i - 1][j].equals(" 水 ")&&!Map[i - 1][j].equals(" 陷 ")) {
                            if (Map[i - 1][j].substring(1, 2).equals("1") || Map[i - 1][j].substring(1, 2).equals("2") || Map[i - 1][j].substring(1, 2).equals("3") ||
                                    Map[i - 1][j].substring(1, 2).equals("4") || Map[i - 1][j].substring(1, 2).equals("5") || Map[i - 1][j].substring(1, 2).equals("6") ||
                                    Map[i - 1][j].substring(1, 2).equals("7") || Map[i - 1][j].substring(1, 2).equals("8")) {
                                System.out.print("不能吃自己的动物啊");
                            } else if (Integer.parseInt(Map[i][j].substring(1, 2)) >= Integer.parseInt(Map[i - 1][j].substring(1, 2))) {
                                Map[i - 1][j] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else {
                                System.out.print("不能这么走");
                            }
                        } else if (Map[i - 1][j].equals(" 水 ")) {
                            if (Map[i - 3][j].substring(0, 1).equals("1") || Map[i - 3][j].substring(0, 1).equals("2") ||
                                    Map[i - 3][j].substring(0, 1).equals("3") || Map[i - 3][j].substring(0, 1).equals("4") ||
                                    Map[i - 3][j].substring(0, 1).equals("5") || Map[i - 3][j].equals("    ")|| Map[i - 3][j].substring(0, 1).equals("6")) {
                                Map[i - 3][j] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else if (Map[i - 3][j].substring(0, 1).equals("8")||Map[i - 3][j].substring(0, 1).equals("7")) {
                                System.out.print("不能这么走，请重新输入");
                            }
                        } else {
                            Map[i - 1][j] = Map[i][j];
                            Map[i][j] = "    ";
                            for (i = 0; i < 7; i++) {
                                for (j = 0; j < 9; j++) {
                                    System.out.print(Map[i][j]);
                                }
                                System.out.print("\n");
                            }
                            player = !player;
                        }
                    }
                } else if (Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("狮7 ")) {
                    if (!Map[i - 1][j].equals("    ")&&!Map[i - 1][j].equals(" 水 ")&&!Map[i - 1][j].equals(" 陷 ")) {
                        if (Map[i - 1][j].substring(1, 2).equals("1") || Map[i - 1][j].substring(1, 2).equals("2") || Map[i - 1][j].substring(1, 2).equals("3") ||
                                Map[i - 1][j].substring(1, 2).equals("4") || Map[i - 1][j].substring(1, 2).equals("5") || Map[i - 1][j].substring(1, 2).equals("6") ||
                                Map[i - 1][j].substring(1, 2).equals("7") || Map[i - 1][j].substring(1, 2).equals("8")) {
                            System.out.print("不能吃自己的动物啊");
                        } else if (Integer.parseInt(Map[i][j].substring(1, 2)) >= Integer.parseInt(Map[i - 1][j].substring(0, 1))) {
                            Map[i - 1][j] = Map[i][j];
                            Map[i][j] = "    ";
                            for (i = 0; i < 7; i++) {
                                for (j = 0; j < 9; j++) {
                                    System.out.print(Map[i][j]);
                                }
                                System.out.print("\n");
                            }
                            player = !player;
                        } else {
                            System.out.print("不能这么走");
                        }
                    } else if (Map[i - 1][j].equals(" 水 ")) {
                        if (Map[i - 3][j].substring(0, 1).equals("1") || Map[i - 3][j].substring(0, 1).equals("2") ||
                                Map[i - 3][j].substring(0, 1).equals("3") || Map[i - 3][j].substring(0, 1).equals("4") ||
                                Map[i - 3][j].substring(0, 1).equals("5") || Map[i - 3][j].substring(0, 1).equals("6") ||
                                Map[i - 3][j].equals("    ")|| Map[i - 3][j].substring(0, 1).equals("7")) {
                            Map[i - 3][j] = Map[i][j];
                            Map[i][j] = "    ";
                            for (i = 0; i < 7; i++) {
                                for (j = 0; j < 9; j++) {
                                    System.out.print(Map[i][j]);
                                }
                                System.out.print("\n");
                            }
                            player = !player;
                        } else if (Map[i - 3][j].substring(0, 1).equals("8")) {
                            System.out.print("不能这么走，请重新输入");
                        }
                    } else {
                        Map[i - 1][j] = Map[i][j];
                        Map[i][j] = "    ";
                        for (i = 0; i < 7; i++) {
                            for (j = 0; j < 9; j++) {
                                System.out.print(Map[i][j]);
                            }
                            System.out.print("\n");
                        }
                        player = !player;
                    }
                }
                if (player && (nextPosition.substring(0, 1).equals("2") || nextPosition.substring(0, 1).equals("3") || nextPosition.substring(0, 1).equals("4") || nextPosition.substring(0, 1).equals("2") || nextPosition.substring(0, 1).equals("3") ||
                        nextPosition.substring(0, 1).equals("4") || nextPosition.substring(0, 1).equals("5") || nextPosition.substring(0, 1).equals("8"))) {
                    if (Search(Map, nextPosition, player)[0] - 1 < 0) {
                        System.out.print("出界了，请重新输入");
                    } else if (i >= 4&&Map[Search(Map, nextPosition, player)[0] - 1][Search(Map, nextPosition, player)[1]] == Map[3][0]) {
                        System.out.print("不能走进自己的家");
                    } else if (Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("2猫 ") ||
                            Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("3狼 ") ||
                            Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("4狗 ") ||
                            Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("5豹 ") ||
                            Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("8象 ")) {
                        if (nextPosition.substring(1, 2).equals("w") && Map[Search(Map, nextPosition, player)[0] - 1][Search(Map, nextPosition, player)[1]].equals(" 水 ")) {
                            System.out.print("这只动物不能进水");
                        } else {
                            if (!Map[i - 1][j].equals("    ")&&!Map[i - 1][j].equals(" 陷 ")) {
                                if (Map[i - 1][j].substring(0, 1).equals("1") || Map[i - 1][j].substring(0, 1).equals("2") || Map[i - 1][j].substring(0, 1).equals("3") ||
                                        Map[i - 1][j].substring(0, 1).equals("4") || Map[i - 1][j].substring(0, 1).equals("5") || Map[i - 1][j].substring(0, 1).equals("6") ||
                                        Map[i - 1][j].substring(0, 1).equals("7") || Map[i - 1][j].substring(0, 1).equals("8")) {
                                    System.out.print("不能吃自己的动物啊");
                                } else if (Integer.parseInt(Map[i][j].substring(0, 1)) >= Integer.parseInt(Map[i - 1][j].substring(1, 2))) {
                                    Map[i - 1][j] = Map[i][j];
                                    Map[i][j] = "    ";
                                    for (i = 0; i < 7; i++) {
                                        for (j = 0; j < 9; j++) {
                                            System.out.print(Map[i][j]);
                                        }
                                        System.out.print("\n");
                                    }
                                    player = !player;
                                } else {
                                    System.out.print("不能这么走");
                                }
                            } else {
                                Map[i - 1][j] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            }
                        }
                    }
                } else if (!player && (nextPosition.substring(0, 1).equals("2") || nextPosition.substring(0, 1).equals("3") ||
                        nextPosition.substring(0, 1).equals("4") || nextPosition.substring(0, 1).equals("5") || nextPosition.substring(0, 1).equals("8"))) {
                    if (Search(Map, nextPosition, player)[0] - 1 < 0) {
                        System.out.print("出界了，请重新输入");
                    } else if (i >= 4&&Map[Search(Map, nextPosition, player)[0] - 1][Search(Map, nextPosition, player)[1]] == Map[3][8]) {
                        System.out.print("不能走进自己的家");
                    } else if (Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("猫2 ") ||
                            Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("狼3 ") ||
                            Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("狗4 ") ||
                            Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("豹5 ") ||
                            Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("象8 ")) {
                        if (nextPosition.substring(1, 2).equals("w") && Map[Search(Map, nextPosition, player)[0] - 1][Search(Map, nextPosition, player)[1]].equals(" 水 ")) {
                            System.out.print("这只动物不能进水");
                        } else {
                            if (!Map[i - 1][j].equals("    ")&&!Map[i - 1][j].equals(" 陷 ")) {
                                if (Map[i - 1][j].substring(1, 2).equals("1") || Map[i - 1][j].substring(1, 2).equals("2") || Map[i - 1][j].substring(1, 2).equals("3") ||
                                        Map[i - 1][j].substring(1, 2).equals("4") || Map[i - 1][j].substring(1, 2).equals("5") || Map[i - 1][j].substring(1, 2).equals("6") ||
                                        Map[i - 1][j].substring(1, 2).equals("7") || Map[i - 1][j].substring(1, 2).equals("8")) {
                                    System.out.print("不能吃自己的动物啊");
                                } else if (Integer.parseInt(Map[i][j].substring(1, 2)) >= Integer.parseInt(Map[i - 1][j].substring(0, 1))) {
                                    Map[i - 1][j] = Map[i][j];
                                    Map[i][j] = "    ";
                                    for (i = 0; i < 7; i++) {
                                        for (j = 0; j < 9; j++) {
                                            System.out.print(Map[i][j]);
                                        }
                                        System.out.print("\n");
                                    }
                                    player = !player;
                                } else {
                                    System.out.print("不能这么走");
                                }
                            } else {
                                Map[i - 1][j] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            }
                        }
                    }
                }
            }
            break;
            case "a": {
                String h = " 水 ";
                if (player && (nextPosition.substring(0, 1).equals("1"))) {
                    if (Search(Map, nextPosition, player)[1] - 1 < 0) {
                        System.out.print("出界了，请重新输入");
                    } else if (i == 1&&Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1] - 1] == Map[3][0]) {
                        System.out.print("不能走进自己的家");
                    } else if (Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("1鼠 ")) {
                        if (!Map[i][j - 1].equals("    ")) {
                            if (Map[i][j - 1].substring(0, 1).equals("1") || Map[i][j - 1].substring(0, 1).equals("2") || Map[i][j - 1].substring(0, 1).equals("3") ||
                                    Map[i][j - 1].substring(0, 1).equals("4") || Map[i][j - 1].substring(0, 1).equals("5") || Map[i][j - 1].substring(0, 1).equals("6") ||
                                    Map[i][j - 1].substring(0, 1).equals("7") || Map[i][j - 1].substring(0, 1).equals("8")) {
                                System.out.print("不能吃自己的动物啊");
                            } else if (Map[i][j - 1].equals(" 水 ") && Map[i][j - 2].equals(" 水 ") && Map[i][j - 3].equals(" 水 ")) {
                                Map[i][j - 1] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else if (Map[i][j - 1].equals(" 水 ") && Map[i][j - 2].equals(" 水 ") && !Map[i][j - 3].equals(" 水 ")) {
                                Map[i][j - 1] = Map[i][j];
                                Map[i][j] = h;
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else if (Map[i][j - 1].equals(" 水 ") && Map[i][j + 1].equals(" 水 ")) {
                                Map[i][j - 1] = Map[i][j];
                                Map[i][j] = h;
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else if (Map[i][j + 1].equals(" 水 ") && Map[i][j + 2].equals(" 水 ")) {
                                Map[i][j - 1] = Map[i][j];
                                Map[i][j] = h;
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else if ((Integer.parseInt(Map[i][j].substring(0, 1)) == 1 && Integer.parseInt(Map[i][j - 1].substring(1, 2)) == 8)
                                    ||(Integer.parseInt(Map[i][j].substring(0, 1)) == 1 && Integer.parseInt(Map[i][j - 1].substring(1, 2)) == 1)) {
                                Map[i][j - 1] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else {
                                System.out.print("不能这么走");
                            }
                        }  else {
                            Map[i][j - 1] = Map[i][j];
                            Map[i][j] = "    ";
                            for (i = 0; i < 7; i++) {
                                for (j = 0; j < 9; j++) {
                                    System.out.print(Map[i][j]);
                                }
                                System.out.print("\n");
                            }
                            player = !player;
                        }
                    }
                } else if (!player && (nextPosition.substring(0, 1).equals("1"))) {
                    if (Search(Map, nextPosition, player)[1] - 1 < 0) {
                        System.out.print("出界了，请重新输入");
                    } else if (i == 1&&Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1] - 1] == Map[3][8]) {
                        System.out.print("不能走进自己的家");
                    } else if (Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("鼠1 ")) {
                        if (!Map[i][j - 1].equals("    ")) {
                            if (Map[i][j - 1].substring(1, 2).equals("1") || Map[i][j - 1].substring(1, 2).equals("2") || Map[i][j - 1].substring(1, 2).equals("3") ||
                                    Map[i][j - 1].substring(1, 2).equals("4") || Map[i][j - 1].substring(1, 2).equals("5") || Map[i][j - 1].substring(1, 2).equals("6") ||
                                    Map[i][j - 1].substring(1, 2).equals("7") || Map[i][j - 1].substring(1, 2).equals("8")) {
                                System.out.print("不能吃自己的动物啊");
                            } else if (Map[i][j - 1].equals(" 水 ") && Map[i][j - 2].equals(" 水 ") && Map[i][j - 3].equals(" 水 ")) {
                                Map[i][j - 1] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else if (Map[i][j - 1].equals(" 水 ") && Map[i][j - 2].equals(" 水 ") && !Map[i][j - 3].equals(" 水 ")) {
                                Map[i][j - 1] = Map[i][j];
                                Map[i][j] = h;
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else if (Map[i][j - 1].equals(" 水 ") && Map[i][j + 1].equals(" 水 ")) {
                                Map[i][j - 1] = Map[i][j];
                                Map[i][j] = h;
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else if (Map[i][j + 1].equals(" 水 ") && Map[i][j + 2].equals(" 水 ")) {
                                Map[i][j - 1] = Map[i][j];
                                Map[i][j] = h;
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            }
                            else if ((Integer.parseInt(Map[i][j].substring(1, 2)) == 1 && Integer.parseInt(Map[i][j - 1].substring(0, 1)) == 8)
                                    ||(Integer.parseInt(Map[i][j].substring(1,2)) == 1 && Integer.parseInt(Map[i][j - 1].substring(0,1)) == 1)) {
                                Map[i][j - 1] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else {
                                System.out.print("不能这么走");
                            }
                        }  else {
                            Map[i][j - 1] = Map[i][j];
                            Map[i][j] = "    ";
                            for (i = 0; i < 7; i++) {
                                for (j = 0; j < 9; j++) {
                                    System.out.print(Map[i][j]);
                                }
                                System.out.print("\n");
                            }
                            player = !player;
                        }
                    }
                }
                //狮虎跳河的部分
                if (player && (nextPosition.substring(0, 1).equals("6") || nextPosition.substring(0, 1).equals("7"))) {
                    if (Search(Map, nextPosition, player)[1] - 1 < 0) {
                        System.out.print("出界了，请重新输入");
                    } else if (i == 1&&Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1] - 1] == Map[3][0]) {
                        System.out.print("不能走进自己的家");
                    } else if (Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("6虎 ")) {
                        if (!Map[i][j - 1].equals("    ")&&!Map[i][j - 1].equals(" 水 ")&&!Map[i][j - 1].equals(" 陷 ")) {
                            if (Map[i][j - 1].substring(0, 1).equals("1") || Map[i][j - 1].substring(0, 1).equals("2") || Map[i][j - 1].substring(0, 1).equals("3") ||
                                    Map[i][j - 1].substring(0, 1).equals("4") || Map[i][j - 1].substring(0, 1).equals("5") || Map[i][j - 1].substring(0, 1).equals("6") ||
                                    Map[i][j - 1].substring(0, 1).equals("7") || Map[i][j - 1].substring(0, 1).equals("8")) {
                                System.out.print("不能吃自己的动物啊");
                            } else if (Integer.parseInt(Map[i][j].substring(0, 1)) >= Integer.parseInt(Map[i][j - 1].substring(1, 2))) {
                                Map[i][j - 1] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else {
                                System.out.print("不能这么走");
                            }
                        } else if (Map[i][j - 1].equals(" 水 ")) {
                            if (Map[i][j - 4].substring(0, 1).equals("1") || Map[i][j - 4].substring(0, 1).equals("2") ||
                                    Map[i][j - 4].substring(0, 1).equals("3") || Map[i][j - 4].substring(0, 1).equals("4") ||
                                    Map[i][j - 4].substring(0, 1).equals("5") || Map[i][j - 4].equals("    ")|| Map[i][j - 4].substring(0, 1).equals("6")) {
                                Map[i][j - 4] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else if (Map[i][j - 4].substring(0, 1).equals("7") || Map[i][j - 4].substring(0, 1).equals("8")) {
                                System.out.print("不能这么走，请重新输入");
                            }
                        }else {
                            Map[i][j - 1] = Map[i][j];
                            Map[i][j] = "    ";
                            for (i = 0; i < 7; i++) {
                                for (j = 0; j < 9; j++) {
                                    System.out.print(Map[i][j]);
                                }
                                System.out.print("\n");
                            }
                            player = !player;
                        }
                    } else if (Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("7狮 ")) {
                        if (!Map[i][j - 1].equals("    ")&&!Map[i][j - 1].equals(" 水 ")&&!Map[i][j - 1].equals(" 陷 ")) {
                            if (Map[i][j - 1].substring(0, 1).equals("1") || Map[i][j - 1].substring(0, 1).equals("2") || Map[i][j - 1].substring(0, 1).equals("3") ||
                                    Map[i][j - 1].substring(0, 1).equals("4") || Map[i][j - 1].substring(0, 1).equals("5") || Map[i][j - 1].substring(0, 1).equals("6") ||
                                    Map[i][j - 1].substring(0, 1).equals("7") || Map[i][j - 1].substring(0, 1).equals("8")) {
                                System.out.print("不能吃自己的动物啊");
                            } else if (Integer.parseInt(Map[i][j].substring(0, 1)) >= Integer.parseInt(Map[i][j - 1].substring(1, 2))) {
                                Map[i][j - 1] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else {
                                System.out.print("不能这么走");
                            }
                        } else if (Map[i][j - 1].equals(" 水 ")) {
                            if (Map[i][j - 4].substring(0, 1).equals("1") || Map[i][j - 4].substring(0, 1).equals("2") ||
                                    Map[i][j - 4].substring(0, 1).equals("3") || Map[i][j - 4].substring(0, 1).equals("4") ||
                                    Map[i][j - 4].substring(0, 1).equals("5") || Map[i][j - 4].equals("    ")|| Map[i][j - 4].substring(0, 1).equals("7")) {
                                Map[i][j - 4] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else if (Map[i][j - 4].substring(0, 1).equals("8")) {
                                System.out.print("不能这么走，请重新输入");
                            }
                        }else {
                            Map[i][j - 1] = Map[i][j];
                            Map[i][j] = "    ";
                            for (i = 0; i < 7; i++) {
                                for (j = 0; j < 9; j++) {
                                    System.out.print(Map[i][j]);
                                }
                                System.out.print("\n");
                            }
                            player = !player;
                        }
                    }
                } else if (!player && (nextPosition.substring(0, 1).equals("6") || nextPosition.substring(0, 1).equals("7"))) {
                    if (Search(Map, nextPosition, player)[1] - 1 < 0) {
                        System.out.print("出界了，请重新输入");
                    } else if (i == 1&&Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1] - 1] == Map[3][8]) {
                        System.out.print("不能走进自己的家");
                    } else if (Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("虎6 ")) {
                        if (!Map[i][j - 1].equals("    ")&&!Map[i][j - 1].equals(" 水 ")&&!Map[i][j - 1].equals(" 陷 ")) {
                            if (Map[i][j - 1].substring(1, 2).equals("1") || Map[i][j - 1].substring(1, 2).equals("2") || Map[i][j - 1].substring(1, 2).equals("3") ||
                                    Map[i][j - 1].substring(1, 2).equals("4") || Map[i][j - 1].substring(1, 2).equals("5") || Map[i][j - 1].substring(1, 2).equals("6") ||
                                    Map[i][j - 1].substring(1, 2).equals("7") || Map[i][j - 1].substring(1, 2).equals("8")) {
                                System.out.print("不能吃自己的动物啊");
                            } else if (Integer.parseInt(Map[i][j].substring(1, 2)) >= Integer.parseInt(Map[i][j - 1].substring(0, 1))) {
                                Map[i][j - 1] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else {
                                System.out.print("不能这么走");
                            }
                        } else if (Map[i][j - 1].equals(" 水 ")) {
                            if (Map[i][j - 4].substring(0, 1).equals("1") || Map[i][j - 4].substring(0, 1).equals("2") ||
                                    Map[i][j - 4].substring(0, 1).equals("3") || Map[i][j - 4].substring(0, 1).equals("4") ||
                                    Map[i][j - 4].substring(0, 1).equals("5") || Map[i][j - 4].equals("    ")|| Map[i][j - 4].substring(0, 1).equals("6")) {
                                Map[i][j - 4] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else if (Map[i][j - 4].substring(0, 1).equals("7") || Map[i][j - 4].substring(0, 1).equals("8")) {
                                System.out.print("不能这么走，请重新输入");
                            }
                        } else {
                            Map[i][j - 1] = Map[i][j];
                            Map[i][j] = "    ";
                            for (i = 0; i < 7; i++) {
                                for (j = 0; j < 9; j++) {
                                    System.out.print(Map[i][j]);
                                }
                                System.out.print("\n");
                            }
                            player = !player;
                        }
                    } else if (Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("狮7 ")) {
                        if (!Map[i][j - 1].equals("    ")&&!Map[i][j - 1].equals(" 水 ")&&!Map[i][j - 1].equals(" 陷 ")) {
                            if (Map[i][j - 1].substring(1, 2).equals("1") || Map[i][j - 1].substring(1, 2).equals("2") || Map[i][j - 1].substring(1, 2).equals("3") ||
                                    Map[i][j - 1].substring(1, 2).equals("4") || Map[i][j - 1].substring(1, 2).equals("5") || Map[i][j - 1].substring(1, 2).equals("6") ||
                                    Map[i][j - 1].substring(1, 2).equals("7") || Map[i][j - 1].substring(1, 2).equals("8")) {
                                System.out.print("不能吃自己的动物啊");
                            } else if (Integer.parseInt(Map[i][j].substring(1, 2)) >= Integer.parseInt(Map[i][j - 1].substring(0, 1))) {
                                Map[i][j - 1] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else {
                                System.out.print("不能这么走");
                            }
                        } else if (Map[i][j - 1].equals(" 水 ")) {
                            if (Map[i][j - 4].substring(0, 1).equals("1") || Map[i][j - 4].substring(0, 1).equals("2") ||
                                    Map[i][j - 4].substring(0, 1).equals("3") || Map[i][j - 4].substring(0, 1).equals("4") ||
                                    Map[i][j - 4].substring(0, 1).equals("5") || Map[i][j - 4].equals("    ")|| Map[i][j - 4].substring(0, 1).equals("7")) {
                                Map[i][j - 4] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else if (Map[i][j - 4].substring(0, 1).equals("8")) {
                                System.out.print("不能这么走，请重新输入");
                            }
                        } else {
                            Map[i][j - 1] = Map[i][j];
                            Map[i][j] = "    ";
                            for (i = 0; i < 7; i++) {
                                for (j = 0; j < 9; j++) {
                                    System.out.print(Map[i][j]);
                                }
                                System.out.print("\n");
                            }
                            player = !player;
                        }
                    }
                }
                if (player && (nextPosition.substring(0, 1).equals("2") || nextPosition.substring(0, 1).equals("3") ||
                        nextPosition.substring(0, 1).equals("4") || nextPosition.substring(0, 1).equals("5") ||
                        nextPosition.substring(0, 1).equals("8"))) {
                    if (Search(Map, nextPosition, player)[1] - 1 < 0) {
                        System.out.print("出界了，请重新输入");
                    } else if (i == 1&&Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1] - 1] == Map[3][0]) {
                        System.out.print("不能走进自己的家");
                    } else if (Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("2猫 ") ||
                            Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("3狼 ") ||
                            Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("4狗 ") ||
                            Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("5豹 ") ||
                            Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("8象 ")) {
                        if (nextPosition.substring(1, 2).equals("a") && Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1] - 1].equals(" 水 ")) {
                            System.out.print("这只动物不能进水");
                        } else {
                            if (!Map[i][j - 1].equals("    ")&&!Map[i][j - 1].equals(" 陷 ")) {
                                if (Map[i][j - 1].substring(0, 1).equals("1") || Map[i][j - 1].substring(0, 1).equals("2") || Map[i][j - 1].substring(0, 1).equals("3") ||
                                        Map[i][j - 1].substring(0, 1).equals("4") || Map[i][j - 1].substring(0, 1).equals("5") || Map[i][j - 1].substring(0, 1).equals("6") ||
                                        Map[i][j - 1].substring(0, 1).equals("7") || Map[i][j - 1].substring(0, 1).equals("8")) {
                                    System.out.print("不能吃自己的动物啊");
                                } else if (Integer.parseInt(Map[i][j].substring(0, 1)) >= Integer.parseInt(Map[i][j - 1].substring(1, 2))) {
                                    Map[i][j - 1] = Map[i][j];
                                    Map[i][j] = "    ";
                                    for (i = 0; i < 7; i++) {
                                        for (j = 0; j < 9; j++) {
                                            System.out.print(Map[i][j]);
                                        }
                                        System.out.print("\n");
                                    }
                                    player = !player;
                                } else {
                                    System.out.print("不能这么走");
                                }
                            } else {
                                Map[i][j - 1] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            }
                        }
                    }
                } else if (!player && (nextPosition.substring(0, 1).equals("2") || nextPosition.substring(0, 1).equals("3") ||
                        nextPosition.substring(0, 1).equals("4") || nextPosition.substring(0, 1).equals("5") ||
                        nextPosition.substring(0, 1).equals("8"))) {
                    if (Search(Map, nextPosition, player)[1] - 1 < 0) {
                        System.out.print("出界了，请重新输入");
                    } else if (i == 1&&Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1] - 1] == Map[3][8]) {
                        System.out.print("不能走进自己的家");
                    }else if (Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("猫2 ") ||
                            Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("狼3 ") ||
                            Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("狗4 ") ||
                            Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("豹5 ") ||
                            Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("象8 ")) {
                        if (nextPosition.substring(1, 2).equals("a") && Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1] - 1].equals(" 水 ")) {
                            System.out.print("这只动物不能进水");
                        } else {
                            if (!Map[i][j - 1].equals("    ")&&!Map[i][j - 1].equals(" 陷 ")) {
                                if (Map[i][j - 1].substring(1, 2).equals("1") || Map[i][j - 1].substring(1, 2).equals("2") || Map[i][j - 1].substring(1, 2).equals("3") ||
                                        Map[i][j - 1].substring(1, 2).equals("4") || Map[i][j - 1].substring(1, 2).equals("5") || Map[i][j - 1].substring(1, 2).equals("6") ||
                                        Map[i][j - 1].substring(1, 2).equals("7") || Map[i][j - 1].substring(1, 2).equals("8")) {
                                    System.out.print("不能吃自己的动物啊");
                                } else if (Integer.parseInt(Map[i][j].substring(1, 2)) >= Integer.parseInt(Map[i][j - 1].substring(0, 1))) {
                                    Map[i][j - 1] = Map[i][j];
                                    Map[i][j] = "    ";
                                    for (i = 0; i < 7; i++) {
                                        for (j = 0; j < 9; j++) {
                                            System.out.print(Map[i][j]);
                                        }
                                        System.out.print("\n");
                                    }
                                    player = !player;
                                } else {
                                    System.out.print("不能这么走");
                                }
                            } else {
                                Map[i][j - 1] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            }
                        }
                    }
                }
            }
            break;
            case "s": {
                //写的是老鼠的移动方法
                String h = " 水 ";
                if (player && (nextPosition.substring(0, 1).equals("1"))) {
                    if (Search(Map, nextPosition, player)[0] + 1 > 6) {
                        System.out.print("出界了，请重新输入");
                    } else if (i <= 3&&Map[Search(Map, nextPosition, player)[0] + 1][Search(Map, nextPosition, player)[1]] == Map[3][0]) {
                        System.out.print("不能走进自己的家");
                    } else if (Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("1鼠 ")) {
                        if (!Map[i + 1][j].equals("    ")) {
                            if (Map[i + 1][j].substring(0, 1).equals("1") || Map[i + 1][j].substring(0, 1).equals("2") || Map[i + 1][j].substring(0, 1).equals("3") ||
                                    Map[i + 1][j].substring(0, 1).equals("4") || Map[i + 1][j].substring(0, 1).equals("5") || Map[i + 1][j].substring(0, 1).equals("6") ||
                                    Map[i + 1][j].substring(0, 1).equals("7") || Map[i + 1][j].substring(0, 1).equals("8")) {
                                System.out.print("不能吃自己的动物啊");
                            }else if (Map[i + 1][j].equals(" 水 ") && Map[i + 2][j].equals(" 水 ")) {
                                Map[i + 1][j] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else if (Map[i + 1][j].equals(" 水 ") && !Map[i + 2][j].equals(" 水 ")) {
                                Map[i + 1][j] = Map[i][j];
                                Map[i][j] = h;
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else if (i > 0 && !Map[i + 1][j].equals(" 水 ") && (Map[i - 1][j].equals(" 水 ") || Map[i - 1][j].equals(" 鼠1 "))) {
                                Map[i + 1][j] = Map[i][j];
                                Map[i][j] = h;
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            }
                            else if ((Integer.parseInt(Map[i][j].substring(0, 1)) == 1 && Integer.parseInt(Map[i + 1][j].substring(1, 2)) == 8)
                                    ||(Integer.parseInt(Map[i][j].substring(0, 1)) == 1 && Integer.parseInt(Map[i + 1][j].substring(1, 2)) == 1)) {
                                Map[i + 1][j] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else {
                                System.out.print("不能这么走");
                            }
                        }  else {
                            Map[i + 1][j] = Map[i][j];
                            Map[i][j] = "    ";
                            for (i = 0; i < 7; i++) {
                                for (j = 0; j < 9; j++) {
                                    System.out.print(Map[i][j]);
                                }
                                System.out.print("\n");
                            }
                            player = !player;
                        }
                    }
                } else if (!player && nextPosition.substring(0, 1).equals("1")) {
                    if (Search(Map, nextPosition, player)[0] + 1 > 6) {
                        System.out.print("出界了，请重新输入");
                    } else if (i <= 3&&Map[Search(Map, nextPosition, player)[0] + 1][Search(Map, nextPosition, player)[1]] == Map[3][8]) {
                        System.out.print("不能走进自己的家");
                    } else if (Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("鼠1 ")) {
                        if (!Map[i + 1][j].equals("    ")) {
                            if (Map[i + 1][j].substring(1, 2).equals("1") || Map[i + 1][j].substring(1, 2).equals("2") || Map[i + 1][j].substring(1, 2).equals("3") ||
                                    Map[i + 1][j].substring(1, 2).equals("4") || Map[i + 1][j].substring(1, 2).equals("5") || Map[i + 1][j].substring(1, 2).equals("6") ||
                                    Map[i + 1][j].substring(1, 2).equals("7") || Map[i + 1][j].substring(1, 2).equals("8")) {
                                System.out.print("不能吃自己的动物啊");
                            } else if (Map[i + 1][j].equals(" 水 ") && Map[i + 2][j].equals(" 水 ")) {
                                Map[i + 1][j] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else if (Map[i + 1][j].equals(" 水 ") && !Map[i + 2][j].equals(" 水 ")) {
                                Map[i + 1][j] = Map[i][j];
                                Map[i][j] = h;
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else if (i >= 1 && !Map[i + 1][j].equals(" 水 ") && Map[i - 1][j].equals(" 水 ")) {
                                Map[i + 1][j] = Map[i][j];
                                Map[i][j] = h;
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            }
                            else if ((Integer.parseInt(Map[i][j].substring(1, 2)) == 1 && Integer.parseInt(Map[i + 1][j].substring(0, 1)) == 8)
                                    ||(Integer.parseInt(Map[i][j].substring(1, 2)) == 1 && Integer.parseInt(Map[i + 1][j].substring(0, 1)) == 1)) {
                                Map[i + 1][j] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else {
                                System.out.print("不能这么走");
                            }
                        }  else {
                            Map[i + 1][j] = Map[i][j];
                            Map[i][j] = "    ";
                            for (i = 0; i < 7; i++) {
                                for (j = 0; j < 9; j++) {
                                    System.out.print(Map[i][j]);
                                }
                                System.out.print("\n");
                            }
                            player = !player;
                        }
                    }
                }
                //狮虎跳河的部分
                if (player && (nextPosition.substring(0, 1).equals("6") || nextPosition.substring(0, 1).equals("7"))) {
                    if (Search(Map, nextPosition, player)[0] + 1 > 6) {
                        System.out.print("出界了，请重新输入");
                    } else if (i <= 3&&Map[Search(Map, nextPosition, player)[0] + 1][Search(Map, nextPosition, player)[1]] == Map[3][0]) {
                        System.out.print("不能走进自己的家");
                    } else if (Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("6虎 ")) {
                        if (!Map[i + 1][j].equals("    ")&&!Map[i + 1][j].equals(" 水 ")&&!Map[i + 1][j].equals(" 陷 ")) {
                            if (Map[i + 1][j].substring(0, 1).equals("1") || Map[i + 1][j].substring(0, 1).equals("2") || Map[i + 1][j].substring(0, 1).equals("3") ||
                                    Map[i + 1][j].substring(0, 1).equals("4") || Map[i + 1][j].substring(0, 1).equals("5") || Map[i + 1][j].substring(0, 1).equals("6") ||
                                    Map[i + 1][j].substring(0, 1).equals("7") || Map[i + 1][j].substring(0, 1).equals("8")) {
                                System.out.print("不能吃自己的动物啊");
                            } else if (Integer.parseInt(Map[i][j].substring(0, 1)) >= Integer.parseInt(Map[i + 1][j].substring(1, 2))) {
                                Map[i + 1][j] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else {
                                System.out.print("不能这么走");
                            }
                        } else if (i <=5&&Map[i + 1][j].equals(" 水 ")) {
                            if (Map[i + 3][j].substring(0, 1).equals("1") || Map[i + 3][j].substring(0, 1).equals("2") ||
                                    Map[i + 3][j].substring(0, 1).equals("3") || Map[i + 3][j].substring(0, 1).equals("4") ||
                                    Map[i + 3][j].substring(0, 1).equals("5") || Map[i + 3][j].equals("    ")|| Map[i + 3][j].substring(0, 1).equals("6")) {
                                Map[i + 3][j] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else if (Map[i + 3][j].substring(0, 1).equals("7") || Map[i + 3][j].substring(0, 1).equals("8")) {
                                System.out.print("不能这么走，请重新输入");
                            }
                        }else {
                            Map[i + 1][j] = Map[i][j];
                            Map[i][j] = "    ";
                            for (i = 0; i < 7; i++) {
                                for (j = 0; j < 9; j++) {
                                    System.out.print(Map[i][j]);
                                }
                                System.out.print("\n");
                            }
                            player = !player;
                        }
                    } else if (Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("7狮 ")) {
                        if (!Map[i + 1][j].equals("    ")&&!Map[i + 1][j].equals(" 水 ")&&!Map[i + 1][j].equals(" 陷 ")) {
                            if (Map[i + 1][j].substring(0, 1).equals("1") || Map[i + 1][j].substring(0, 1).equals("2") || Map[i + 1][j].substring(0, 1).equals("3") ||
                                    Map[i + 1][j].substring(0, 1).equals("4") || Map[i + 1][j].substring(0, 1).equals("5") || Map[i + 1][j].substring(0, 1).equals("6") ||
                                    Map[i + 1][j].substring(0, 1).equals("7") || Map[i + 1][j].substring(0, 1).equals("8")) {
                                System.out.print("不能吃自己的动物啊");
                            } else if (Integer.parseInt(Map[i][j].substring(0, 1)) >= Integer.parseInt(Map[i + 1][j].substring(1, 2))) {
                                Map[i + 1][j] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else {
                                System.out.print("不能这么走");
                            }
                        } else if (i <=5&&Map[i + 1][j].equals(" 水 ")) {
                            if (Map[i + 3][j].substring(0, 1).equals("1") || Map[i + 3][j].substring(0, 1).equals("2") ||
                                    Map[i + 3][j].substring(0, 1).equals("3") || Map[i + 3][j].substring(0, 1).equals("4") ||
                                    Map[i + 3][j].substring(0, 1).equals("5") || Map[i + 3][j].substring(0, 1).equals("6") ||
                                    Map[i + 3][j].equals("    ")|| Map[i + 3][j].substring(0, 1).equals("7")) {
                                Map[i + 3][j] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else if (Map[i + 3][j].substring(0, 1).equals("8")) {
                                System.out.print("不能这么走，请重新输入");
                            }
                        }else {
                            Map[i + 1][j] = Map[i][j];
                            Map[i][j] = "    ";
                            for (i = 0; i < 7; i++) {
                                for (j = 0; j < 9; j++) {
                                    System.out.print(Map[i][j]);
                                }
                                System.out.print("\n");
                            }
                            player = !player;
                        }
                    }
                } else if (!player && (nextPosition.substring(0, 1).equals("6") || nextPosition.substring(0, 1).equals("7"))) {
                    if (Search(Map, nextPosition, player)[0] + 1 > 6) {
                        System.out.print("出界了，请重新输入");
                    } else if (i <= 3&&Map[Search(Map, nextPosition, player)[0] + 1][Search(Map, nextPosition, player)[1]] == Map[3][8]) {
                        System.out.print("不能走进自己的家");
                    } else if (Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("虎6 ")) {
                        if ((!Map[i + 1][j].equals("    ")&&!Map[i + 1][j].equals(" 水 ")&&!Map[i + 1][j].equals(" 陷 "))) {
                            if (Map[i + 1][j].substring(1, 2).equals("1") || Map[i + 1][j].substring(1, 2).equals("2") || Map[i + 1][j].substring(1, 2).equals("3") ||
                                    Map[i + 1][j].substring(1, 2).equals("4") || Map[i + 1][j].substring(1, 2).equals("5") || Map[i + 1][j].substring(1, 2).equals("6") ||
                                    Map[i + 1][j].substring(1, 2).equals("7") || Map[i + 1][j].substring(1, 2).equals("8")) {
                                System.out.print("不能吃自己的动物啊");
                            } else if (Integer.parseInt(Map[i][j].substring(1, 2)) >= Integer.parseInt(Map[i + 1][j].substring(0, 1))) {
                                Map[i + 1][j] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else {
                                System.out.print("不能这么走");
                            }
                        } else if (i <=5&&Map[i + 1][j].equals(" 水 ")) {
                            if (Map[i + 3][j].substring(0, 1).equals("1") || Map[i + 3][j].substring(0, 1).equals("2") ||
                                    Map[i + 3][j].substring(0, 1).equals("3") || Map[i + 3][j].substring(0, 1).equals("4") ||
                                    Map[i + 3][j].substring(0, 1).equals("5") || Map[i + 3][j].equals("    ") || Map[i + 3][j].substring(0, 1).equals("6")) {
                                Map[i + 3][j] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else if (Map[i + 3][j].substring(0, 1).equals("7") || Map[i + 3][j].substring(0, 1).equals("8")) {
                                System.out.print("不能这么走，请重新输入");
                            }
                        }
                    } else if (Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("狮7 ")) {
                        if (!Map[i + 1][j].equals("    ")&&!Map[i + 1][j].equals(" 水 ")&&!Map[i + 1][j].equals(" 陷 ")) {
                            if (Map[i + 1][j].substring(1, 2).equals("1") || Map[i + 1][j].substring(1, 2).equals("2") || Map[i + 1][j].substring(1, 2).equals("3") ||
                                    Map[i + 1][j].substring(1, 2).equals("4") || Map[i + 1][j].substring(1, 2).equals("5") || Map[i + 1][j].substring(1, 2).equals("6") ||
                                    Map[i + 1][j].substring(1, 2).equals("7") || Map[i + 1][j].substring(1, 2).equals("8")) {
                                System.out.print("不能吃自己的动物啊");
                            } else if (Integer.parseInt(Map[i][j].substring(1, 2)) >= Integer.parseInt(Map[i + 1][j].substring(0, 1))) {
                                Map[i + 1][j] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else {
                                System.out.print("不能这么走");
                            }
                        } else if (i <=5&&Map[i + 1][j].equals(" 水 ")) {
                            if (Map[i + 3][j].substring(0, 1).equals("1") || Map[i + 3][j].substring(0, 1).equals("2") ||
                                    Map[i + 3][j].substring(0, 1).equals("3") || Map[i + 3][j].substring(0, 1).equals("4") ||
                                    Map[i + 3][j].substring(0, 1).equals("5") || Map[i + 3][j].substring(0, 1).equals("6") ||
                                    Map[i + 3][j].equals("    ")|| Map[i + 3][j].substring(0, 1).equals("7")) {
                                Map[i + 3][j] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else if (Map[i + 3][j].substring(0, 1).equals("8")) {
                                System.out.print("不能这么走，请重新输入");
                            }
                        } else {
                            Map[i + 1][j] = Map[i][j];
                            Map[i][j] = "    ";
                            for (i = 0; i < 7; i++) {
                                for (j = 0; j < 9; j++) {
                                    System.out.print(Map[i][j]);
                                }
                                System.out.print("\n");
                            }
                            player = !player;
                        }
                    }
                }
                if (player && (nextPosition.substring(0, 1).equals("2") || nextPosition.substring(0, 1).equals("3") ||
                        nextPosition.substring(0, 1).equals("4") || nextPosition.substring(0, 1).equals("5") ||
                        nextPosition.substring(0, 1).equals("8"))) {
                    if (Search(Map, nextPosition, player)[0] + 1 > 6) {
                        System.out.print("出界了，请重新输入");
                    } else if (i <= 3&&Map[Search(Map, nextPosition, player)[0] + 1][Search(Map, nextPosition, player)[1]] == Map[3][0]) {
                        System.out.print("不能走进自己的家");
                    } else if (Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("2猫 ") ||
                            Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("3狼 ") ||
                            Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("4狗 ") ||
                            Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("5豹 ") ||
                            Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("8象 ")) {
                        if (nextPosition.substring(1, 2).equals("s") && Map[Search(Map, nextPosition, player)[0] + 1][Search(Map, nextPosition, player)[1]].equals(" 水 ")) {
                            System.out.print("这只动物不能进水");
                        } else {
                            if (!Map[i + 1][j].equals("    ")&&!Map[i + 1][j].equals(" 陷 ")) {
                                if (Map[i + 1][j].substring(0, 1).equals("1") || Map[i + 1][j].substring(0, 1).equals("2") || Map[i + 1][j].substring(0, 1).equals("3") ||
                                        Map[i + 1][j].substring(0, 1).equals("4") || Map[i + 1][j].substring(0, 1).equals("5") || Map[i + 1][j].substring(0, 1).equals("6") ||
                                        Map[i + 1][j].substring(0, 1).equals("7") || Map[i + 1][j].substring(0, 1).equals("8")) {
                                    System.out.print("不能吃自己的动物啊");
                                } else if (Integer.parseInt(Map[i][j].substring(0, 1)) >= Integer.parseInt(Map[i + 1][j].substring(1, 2))) {
                                    Map[i + 1][j] = Map[i][j];
                                    Map[i][j] = "    ";
                                    for (i = 0; i < 7; i++) {
                                        for (j = 0; j < 9; j++) {
                                            System.out.print(Map[i][j]);
                                        }
                                        System.out.print("\n");
                                    }
                                    player = !player;
                                } else {
                                    System.out.print("不能这么走");
                                }
                            } else {
                                Map[i + 1][j] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            }
                        }
                    }
                } else if (!player && (nextPosition.substring(0, 1).equals("2") || nextPosition.substring(0, 1).equals("3") ||
                        nextPosition.substring(0, 1).equals("4") || nextPosition.substring(0, 1).equals("5") ||
                        nextPosition.substring(0, 1).equals("8"))) {
                    if (Search(Map, nextPosition, player)[0] + 1 > 6) {
                        System.out.print("出界了，请重新输入");
                    } else if (i <= 3&&Map[Search(Map, nextPosition, player)[0] + 1][Search(Map, nextPosition, player)[1]] == Map[3][8]) {
                        System.out.print("不能走进自己的家");
                    } else if (Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("猫2 ") ||
                            Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("狼3 ") ||
                            Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("狗4 ") ||
                            Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("豹5 ") ||
                            Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("象8 ")) {
                        if (nextPosition.substring(1, 2).equals("s") && Map[Search(Map, nextPosition, player)[0] + 1][Search(Map, nextPosition, player)[1]].equals(" 水 ")) {
                            System.out.print("这只动物不能进水");
                        } else {
                            if (!Map[i + 1][j].equals("    ")&&!Map[i + 1][j].equals(" 陷 ")) {
                                if (Map[i + 1][j].substring(1, 2).equals("1") || Map[i + 1][j].substring(1, 2).equals("2") || Map[i + 1][j].substring(1, 2).equals("3") ||
                                        Map[i + 1][j].substring(1, 2).equals("4") || Map[i + 1][j].substring(1, 2).equals("5") || Map[i + 1][j].substring(1, 2).equals("6") ||
                                        Map[i + 1][j].substring(1, 2).equals("7") || Map[i + 1][j].substring(1, 2).equals("8")) {
                                    System.out.print("不能吃自己的动物啊");
                                } else if (Integer.parseInt(Map[i][j].substring(1, 2)) >= Integer.parseInt(Map[i + 1][j].substring(0, 1))) {
                                    Map[i + 1][j] = Map[i][j];
                                    Map[i][j] = "    ";
                                    for (i = 0; i < 7; i++) {
                                        for (j = 0; j < 9; j++) {
                                            System.out.print(Map[i][j]);
                                        }
                                        System.out.print("\n");
                                    }
                                    player = !player;
                                } else {
                                    System.out.print("不能这么走");
                                }
                            } else {
                                Map[i + 1][j] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            }
                        }
                    }
                }
            }
            break;
            case "d": {
                String h = " 水 ";
                if (player && (nextPosition.substring(0, 1).equals("1"))) {
                    if (Search(Map, nextPosition, player)[1] + 1 > 8) {
                        System.out.print("出界了，请重新输入");
                    } else if (j <= 7&&Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1] + 1] == Map[3][0]) {
                        System.out.print("不能走进自己的家");
                    } else if (Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("1鼠 ")) {
                        if (!Map[i][j + 1].equals("    ")) {
                            if (Map[i][j + 1].substring(0, 1).equals("1") || Map[i][j + 1].substring(0, 1).equals("2") || Map[i][j + 1].substring(0, 1).equals("3") ||
                                    Map[i][j + 1].substring(0, 1).equals("4") || Map[i][j + 1].substring(0, 1).equals("5") || Map[i][j + 1].substring(0, 1).equals("6") ||
                                    Map[i][j + 1].substring(0, 1).equals("7") || Map[i][j + 1].substring(0, 1).equals("8")) {
                                System.out.print("不能吃自己的动物啊");
                            }
                            else if (Map[i][j + 1].equals(" 水 ") && Map[i][j + 2].equals(" 水 ") && Map[i][j + 3].equals(" 水 ")) {
                                Map[i][j + 1] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else if (Map[i][j + 1].equals(" 水 ") && Map[i][j + 2].equals(" 水 ") && !Map[i][j + 3].equals(" 水 ")) {
                                Map[i][j + 1] = Map[i][j];
                                Map[i][j] = h;
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else if (Map[i][j + 1].equals(" 水 ") && Map[i][j - 1].equals(" 水 ")) {
                                Map[i][j + 1] = Map[i][j];
                                Map[i][j] = h;
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else if (Map[i][j - 1].equals(" 水 ") && Map[i][j - 2].equals(" 水 ")) {
                                Map[i][j + 1] = Map[i][j];
                                Map[i][j] = h;
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else if ((Integer.parseInt(Map[i][j].substring(0, 1)) == 1 && Integer.parseInt(Map[i][j + 1].substring(1, 2)) == 8)
                                    ||(Integer.parseInt(Map[i][j].substring(0, 1)) == 1 && Integer.parseInt(Map[i][j + 1].substring(1, 2)) == 1)) {
                                Map[i][j + 1] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else {
                                System.out.print("不能这么走");
                            }
                        } else {
                            Map[i][j + 1] = Map[i][j];
                            Map[i][j] = "    ";
                            for (i = 0; i < 7; i++) {
                                for (j = 0; j < 9; j++) {
                                    System.out.print(Map[i][j]);
                                }
                                System.out.print("\n");
                            }
                            player = !player;
                        }
                    }
                } else if (!player && (nextPosition.substring(0, 1).equals("1"))) {
                    if (Search(Map, nextPosition, player)[1] + 1 > 8) {
                        System.out.print("出界了，请重新输入");
                    } else if (j <= 7&&Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1] + 1] == Map[3][8]) {
                        System.out.print("不能走进自己的家");
                    } else if (Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("鼠1 ")) {
                        if (!Map[i][j + 1].equals("    ")) {
                            if (Map[i][j + 1].substring(1, 2).equals("1") || Map[i][j + 1].substring(1, 2).equals("2") || Map[i][j + 1].substring(1, 2).equals("3") ||
                                    Map[i][j + 1].substring(1, 2).equals("4") || Map[i][j + 1].substring(1, 2).equals("5") || Map[i][j + 1].substring(1, 2).equals("6") ||
                                    Map[i][j + 1].substring(1, 2).equals("7") || Map[i][j + 1].substring(1, 2).equals("8")) {
                                System.out.print("不能吃自己的动物啊");
                            } else if (Map[i][j + 1].equals(" 水 ") && Map[i][j + 2].equals(" 水 ") && Map[i][j + 3].equals(" 水 ")) {
                                Map[i][j + 1] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else if (Map[i][j + 1].equals(" 水 ") && Map[i][j + 2].equals(" 水 ") && !Map[i][j + 3].equals(" 水 ")) {
                                Map[i][j + 1] = Map[i][j];
                                Map[i][j] = h;
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else if (Map[i][j + 1].equals(" 水 ") && Map[i][j - 1].equals(" 水 ")) {
                                Map[i][j + 1] = Map[i][j];
                                Map[i][j] = h;
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else if (Map[i][j - 1].equals(" 水 ") && Map[i][j - 2].equals(" 水 ")) {
                                Map[i][j + 1] = Map[i][j];
                                Map[i][j] = h;
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            }
                            else if ((Integer.parseInt(Map[i][j].substring(1, 2)) == 1 && Integer.parseInt(Map[i][j + 1].substring(0, 1)) == 8)
                                    ||(Integer.parseInt(Map[i][j].substring(1, 2)) == 1 && Integer.parseInt(Map[i][j + 1].substring(0, 1)) == 1) ) {
                                Map[i][j + 1] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else {
                                System.out.print("不能这么走");
                            }
                        }  else {
                            Map[i][j + 1] = Map[i][j];
                            Map[i][j] = "    ";
                            for (i = 0; i < 7; i++) {
                                for (j = 0; j < 9; j++) {
                                    System.out.print(Map[i][j]);
                                }
                                System.out.print("\n");
                            }
                            player = !player;
                        }
                    }
                }
                //狮虎跳河的部分
                if (player && (nextPosition.substring(0, 1).equals("6") || nextPosition.substring(0, 1).equals("7"))) {
                    if (Search(Map, nextPosition, player)[1] + 1 > 8) {
                        System.out.print("出界了，请重新输入");
                    } else if (j <= 7&&Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1] + 1] == Map[3][0]) {
                        System.out.print("不能走进自己的家");
                    } else if (Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("6虎 ")) {
                        if (!Map[i][j + 1].equals("    ")&&!Map[i][j + 1].equals(" 水 ")&&!Map[i][j + 1].equals(" 陷 ")) {
                            if (Map[i][j + 1].substring(0, 1).equals("1") || Map[i][j + 1].substring(0, 1).equals("2") || Map[i][j + 1].substring(0, 1).equals("3") ||
                                    Map[i][j + 1].substring(0, 1).equals("4") || Map[i][j + 1].substring(0, 1).equals("5") || Map[i][j + 1].substring(0, 1).equals("6") ||
                                    Map[i][j + 1].substring(0, 1).equals("7") || Map[i][j + 1].substring(0, 1).equals("8")) {
                                System.out.print("不能吃自己的动物啊");
                            } else if (Integer.parseInt(Map[i][j].substring(0, 1)) >= Integer.parseInt(Map[i][j + 1].substring(1, 2))) {
                                Map[i][j + 1] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else {
                                System.out.print("不能这么走");
                            }
                        } else if (Map[i][j + 1].equals(" 水 ")) {
                            if (Map[i][j + 4].substring(1, 2).equals("1") || Map[i][j + 4].substring(1, 2).equals("2") ||
                                    Map[i][j + 4].substring(1, 2).equals("3") || Map[i][j + 4].substring(1, 2).equals("4") ||
                                    Map[i][j + 4].substring(1, 2).equals("5") || Map[i][j + 4].equals("    ") || Map[i][j + 4].substring(1, 2).equals("6")) {
                                Map[i][j + 4] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else if (Map[i][j + 4].substring(0, 1).equals("7") || Map[i][j + 4].substring(0, 1).equals("8")) {
                                System.out.print("不能这么走，请重新输入");
                            }
                        }else {
                            Map[i][j + 1] = Map[i][j];
                            Map[i][j] = "    ";
                            for (i = 0; i < 7; i++) {
                                for (j = 0; j < 9; j++) {
                                    System.out.print(Map[i][j]);
                                }
                                System.out.print("\n");
                            }
                            player = !player;
                        }
                    } else if (Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("7狮 ")) {
                        if (!Map[i][j + 1].equals("    ")&&!Map[i][j + 1].equals(" 水 ")&&!Map[i][j + 1].equals(" 陷 ")) {
                            if (Map[i][j + 1].substring(0, 1).equals("1") || Map[i][j + 1].substring(0, 1).equals("2") || Map[i][j + 1].substring(0, 1).equals("3") ||
                                    Map[i][j + 1].substring(0, 1).equals("4") || Map[i][j + 1].substring(0, 1).equals("5") || Map[i][j + 1].substring(0, 1).equals("6") ||
                                    Map[i][j + 1].substring(0, 1).equals("7") || Map[i][j + 1].substring(0, 1).equals("8")) {
                                System.out.print("不能吃自己的动物啊");
                            } else if (Integer.parseInt(Map[i][j].substring(0, 1)) >= Integer.parseInt(Map[i][j + 1].substring(1, 2))) {
                                Map[i][j + 1] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else {
                                System.out.print("不能这么走");
                            }
                        } else if (Map[i][j + 1].equals(" 水 ")) {
                            if (Map[i][j + 4].substring(1, 2).equals("1") || Map[i][j + 4].substring(1, 2).equals("2") ||
                                    Map[i][j + 4].substring(1, 2).equals("3") || Map[i][j + 4].substring(1, 2).equals("4") ||
                                    Map[i][j + 4].substring(1, 2).equals("5") || Map[i][j + 4].substring(1, 2).equals("6") ||
                                    Map[i][j + 4].equals("    ")|| Map[i][j + 4].substring(1, 2).equals("7")) {
                                Map[i][j + 4] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else if (Map[i][j + 4].substring(0, 1).equals("8")) {
                                System.out.print("不能这么走，请重新输入");
                            }
                        } else {
                            Map[i][j + 1] = Map[i][j];
                            Map[i][j] = "    ";
                            for (i = 0; i < 7; i++) {
                                for (j = 0; j < 9; j++) {
                                    System.out.print(Map[i][j]);
                                }
                                System.out.print("\n");
                            }
                            player = !player;
                        }
                    }
                } else if (!player && (nextPosition.substring(0, 1).equals("6") || nextPosition.substring(0, 1).equals("7"))) {
                    if (Search(Map, nextPosition, player)[1] + 1 > 8) {
                        System.out.print("出界了，请重新输入");
                    } else if (j <= 7&&Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1] + 1] == Map[3][8]) {
                        System.out.print("不能走进自己的家");
                    } else if (Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("虎6 ")) {
                        if (!Map[i][j + 1].equals("    ")) {
                            if (Map[i][j + 1].substring(1, 2).equals("1") || Map[i][j + 1].substring(1, 2).equals("2") || Map[i][j + 1].substring(1, 2).equals("3") ||
                                    Map[i][j + 1].substring(1, 2).equals("4") || Map[i][j + 1].substring(1, 2).equals("5") || Map[i][j + 1].substring(1, 2).equals("6") ||
                                    Map[i][j + 1].substring(1, 2).equals("7") || Map[i][j + 1].substring(1, 2).equals("8")) {
                                System.out.print("不能吃自己的动物啊");
                            } else if (Integer.parseInt(Map[i][j].substring(0, 1)) >= Integer.parseInt(Map[i][j + 1].substring(1, 2))) {
                                Map[i][j + 1] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else {
                                System.out.print("不能这么走");
                            }
                        } else if (Map[i][j + 1].equals(" 水 ")) {
                            if (Map[i][j + 4].substring(0, 1).equals("1") || Map[i][j + 4].substring(0, 1).equals("2") ||
                                    Map[i][j + 4].substring(0, 1).equals("3") || Map[i][j + 4].substring(0, 1).equals("4") ||
                                    Map[i][j + 4].substring(0, 1).equals("5") || Map[i][j + 4].equals("    ")|| Map[i][j + 4].substring(0, 1).equals("6")) {
                                Map[i][j + 4] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else if (Map[i][j + 4].substring(0, 1).equals("7") || Map[i][j + 4].substring(0, 1).equals("8")) {
                                System.out.print("不能这么走，请重新输入");
                            }
                        }
                    } else if (Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("狮7 ")) {
                        if (!Map[i][j + 1].equals("    ")) {
                            if (Map[i][j + 1].substring(1, 2).equals("1") || Map[i][j + 1].substring(1, 2).equals("2") || Map[i][j + 1].substring(1, 2).equals("3") ||
                                    Map[i][j + 1].substring(1, 2).equals("4") || Map[i][j + 1].substring(1, 2).equals("5") || Map[i][j + 1].substring(1, 2).equals("6") ||
                                    Map[i][j + 1].substring(1, 2).equals("7") || Map[i][j + 1].substring(1, 2).equals("8")) {
                                System.out.print("不能吃自己的动物啊");
                            } else if (Integer.parseInt(Map[i][j].substring(0, 1)) >= Integer.parseInt(Map[i][j + 1].substring(1, 2))) {
                                Map[i][j + 1] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else {
                                System.out.print("不能这么走");
                            }
                        } else if (Map[i][j + 1].equals(" 水 ")) {
                            if (Map[i][j + 4].substring(0, 1).equals("1") || Map[i][j + 4].substring(0, 1).equals("2") ||
                                    Map[i][j + 4].substring(0, 1).equals("3") || Map[i][j + 4].substring(0, 1).equals("4") ||
                                    Map[i][j + 4].substring(0, 1).equals("5") || Map[i][j + 4].substring(0, 1).equals("6") ||
                                    Map[i][j + 4].equals("    ")|| Map[i][j + 4].substring(0, 1).equals("7")) {
                                Map[i][j + 4] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            } else if (Map[i][j + 4].substring(0, 1).equals("8")) {
                                System.out.print("不能这么走，请重新输入");
                            }
                        } else {
                            Map[i + 1][j] = Map[i][j];
                            Map[i][j] = "    ";
                            for (i = 0; i < 7; i++) {
                                for (j = 0; j < 9; j++) {
                                    System.out.print(Map[i][j]);
                                }
                                System.out.print("\n");
                            }
                            player = !player;
                        }
                    }
                }
                if (player && (nextPosition.substring(0, 1).equals("2") || nextPosition.substring(0, 1).equals("3") ||
                        nextPosition.substring(0, 1).equals("4") || nextPosition.substring(0, 1).equals("5") || nextPosition.substring(0, 1).equals("8"))) {
                    if (Search(Map, nextPosition, player)[1] + 1 > 8) {
                        System.out.print("出界了，请重新输入");
                    } else if (j <= 7&&Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1] + 1] == Map[3][0]) {
                        System.out.print("不能走进自己的家");
                    } else if (Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("2猫 ") ||
                            Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("3狼 ") ||
                            Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("4狗 ") ||
                            Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("5豹 ") ||
                            Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("8象 ")) {
                        if (nextPosition.substring(1, 2).equals("d") && Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1] + 1].equals(" 水 ")) {
                            System.out.print("这只动物不能进水");
                        } else {
                            if (!Map[i][j + 1].equals("    ")&&!Map[i][j + 1].equals(" 陷 ")) {
                                if (Map[i][j + 1].substring(0, 1).equals("1") || Map[i][j + 1].substring(0, 1).equals("2") || Map[i][j + 1].substring(0, 1).equals("3") ||
                                        Map[i][j + 1].substring(0, 1).equals("4") || Map[i][j + 1].substring(0, 1).equals("5") || Map[i][j + 1].substring(0, 1).equals("6") ||
                                        Map[i][j + 1].substring(0,1).equals("7") || Map[i][j + 1].substring(0,1).equals("8")) {
                                    System.out.print("不能吃自己的动物啊");
                                } else if (Integer.parseInt(Map[i][j].substring(0, 1)) >= Integer.parseInt(Map[i][j + 1].substring(1, 2))) {
                                    Map[i][j + 1] = Map[i][j];
                                    Map[i][j] = "    ";
                                    for (i = 0; i < 7; i++) {
                                        for (j = 0; j < 9; j++) {
                                            System.out.print(Map[i][j]);
                                        }
                                        System.out.print("\n");
                                    }
                                    player = !player;
                                } else {
                                    System.out.print("不能这么走");
                                }
                            } else {
                                Map[i][j + 1] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            }
                        }
                    }
                } else if (!player && (nextPosition.substring(0, 1).equals("2") || nextPosition.substring(0, 1).equals("3") ||
                        nextPosition.substring(0, 1).equals("4") || nextPosition.substring(0, 1).equals("5") || nextPosition.substring(0, 1).equals("8"))) {
                    if ((Search(Map, nextPosition, player)[1] + 1) > 8) {
                        System.out.print("出界了，请重新输入");
                    }  else if (j <= 7&&Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1] + 1] == Map[3][8]) {
                        System.out.print("不能走进自己的家");
                    } else if (Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("猫2 ") ||
                            Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("狼3 ") ||
                            Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("狗4 ") ||
                            Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("豹5 ") ||
                            Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1]].equals("象8 ")) {
                        if (nextPosition.substring(1, 2).equals("d") && Map[Search(Map, nextPosition, player)[0]][Search(Map, nextPosition, player)[1] + 1].equals(" 水 ")) {
                            System.out.print("这只动物不能进水");
                        } else {
                            if (!Map[i][j + 1].equals("    ")&&!Map[i][j + 1].equals(" 陷 ")) {
                                if (Map[i][j + 1].substring(1,2).equals("1") || Map[i][j + 1].substring(1,2).equals("2") || Map[i][j + 1].substring(1,2).equals("3") ||
                                        Map[i][j + 1].substring(1,2).equals("4") || Map[i][j + 1].substring(1,2).equals("5") || Map[i][j + 1].substring(1,2).equals("6") ||
                                        Map[i][j + 1].substring(1, 2).equals("7") || Map[i][j + 1].substring(1, 2).equals("8")) {
                                    System.out.print("不能吃自己的动物啊");
                                } else if (Integer.parseInt(Map[i][j].substring(1,2)) >= Integer.parseInt(Map[i][j + 1].substring(0,1))) {
                                    Map[i][j + 1] = Map[i][j];
                                    Map[i][j] = "    ";
                                    for (i = 0; i < 7; i++) {
                                        for (j = 0; j < 9; j++) {
                                            System.out.print(Map[i][j]);
                                        }
                                        System.out.print("\n");
                                    }
                                    player = !player;
                                } else {
                                    System.out.print("不能这么走");
                                }
                            } else {
                                Map[i][j + 1] = Map[i][j];
                                Map[i][j] = "    ";
                                for (i = 0; i < 7; i++) {
                                    for (j = 0; j < 9; j++) {
                                        System.out.print(Map[i][j]);
                                    }
                                    System.out.print("\n");
                                }
                                player = !player;
                            }
                        }
                    }
                }
            }
            break;
        }
        return player;
    }
}






