package easy;

import java.util.*;

/**
 * 15、完成在如下Map中查询城市信息
 * 已知有省份Provice类型，有属性省份编号id和名称name，有城市City类型，有属性城市编号id和名称name,所属省份编号pid，
 * 以及所有信息现保存在一个Map中，现在要在map中，根据省份编号，查找这个省份下所有的城市。
 */
public class exe15 {
    public static void main(String[] args) {
        System.out.println(new AreaManager().findCity(3));
    }
}

class Province {
    int id;
    String name;

    public Province(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class City {
    int id;
    String name;
    int pid;

    public City(int id, String name, int pid) {
        this.id = id;
        this.name = name;
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pid=" + pid +
                '}';
    }
}

class AreaManager {
    private HashMap<Province, HashSet<City>> map;

    public AreaManager() {
        map = new HashMap<Province, HashSet<City>>();

        HashSet<City> bj = new HashSet<City>();
        bj.add(new City(1, "北京市", 1));
        map.put(new Province(1, "北京市"), bj);

        HashSet<City> hn = new HashSet<City>();
        hn.add(new City(1, "海口市", 2));
        hn.add(new City(2, "三亚市", 2));
        map.put(new Province(2, "海南省"), hn);

        HashSet<City> zj = new HashSet<City>();
        zj.add(new City(1, "绍兴市", 3));
        zj.add(new City(2, "温州市", 3));
        zj.add(new City(3, "湖州市", 3));
        zj.add(new City(4, "嘉兴市", 3));
        zj.add(new City(5, "台州市", 3));
        zj.add(new City(6, "金华市", 3));
        zj.add(new City(7, "舟山市", 3));
        zj.add(new City(8, "衢州市", 3));
        zj.add(new City(9, "丽水市", 3));
        map.put(new Province(3, "浙江省"), zj);
    }

    public HashSet<City> findCity(int pid) {
//        补充代码
        Set<Province> province = map.keySet();
        for (Province i :
                province) {
            if (i.id == pid) {
                return map.get(i);
            }
        }
        return null;
    }
}
