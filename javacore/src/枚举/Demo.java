package 枚举;


enum Season {
    春天, 夏天, 秋天, 冬天
}

/**
 * @author xieziwei99
 * 2020-07-03
 */
public class Demo {

    public static void main(String[] args) {
        System.out.println(Season.春天);
        System.out.println(Season.夏天);
        System.out.println(Season.秋天);
        System.out.println(Season.冬天);
        System.out.println(Season.class.getSuperclass());   // class java.lang.Enum

        /*
        春天 春天 0
        夏天 夏天 1
        秋天 秋天 2
        冬天 冬天 3
         */
        for (Season season : Season.values()) {
            System.out.print(season + " ");
            System.out.print(season.name() + " ");
            System.out.print(season.ordinal() + " ");
            System.out.println();
        }
    }
}