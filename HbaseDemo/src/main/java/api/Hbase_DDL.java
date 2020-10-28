package api;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

import javax.xml.stream.events.Namespace;
import java.io.IOException;

public class Hbase_DDL {
    @Test
    public static boolean isTableExist(String tableName) throws IOException {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum","hadoop01,hadoop02,hadoop03");
        Connection connection = ConnectionFactory.createConnection(configuration);
        Admin admin = connection.getAdmin();

        boolean b = admin.tableExists(TableName.valueOf(tableName));
        admin.close();
        connection.close();
        return b;
    }

    /**
     * 建表
     * @param tableName
     * @param cfs
     * @throws IOException
     */
    @Test
    public static void createTable(String tableName,String...cfs) throws IOException {
        if (cfs.length<=0){
            System.out.println("请设置列族信息!");
            return;
        }
        if (isTableExist(tableName)){
            System.out.println("需要创建的表已经存在!");
            return;
        }
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum","hadoop01,hadoop02,hadoop03");
        Connection connection = ConnectionFactory.createConnection(configuration);
        Admin admin = connection.getAdmin();

        TableDescriptorBuilder tableDescriptorBuilder = TableDescriptorBuilder.newBuilder(TableName.valueOf(tableName));
        for (String cf:cfs) {
            ColumnFamilyDescriptorBuilder columnFamilyDescriptorBuilder = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(cf));
            tableDescriptorBuilder.setColumnFamily(columnFamilyDescriptorBuilder.build());
            admin.createTable(tableDescriptorBuilder.build());
            admin.close();
            connection.close();
        }
    }

    /**
     * 删表
     * @param tableName
     * @throws IOException
     */
    @Test
    public static void dropTable(String tableName) throws IOException {
        if (!isTableExist(tableName)){
            System.out.println("需要删除的表不存在!");
            return;
        }
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum","hadoop01,hadoop02,hadoop03");
        Connection connection = ConnectionFactory.createConnection(configuration);
        Admin admin = connection.getAdmin();

        TableName tableName1 = TableName.valueOf(tableName);
        admin.disableTable(tableName1);
        admin.deleteTable(tableName1);

        admin.close();
        connection.close();
    }

    /**
     * 命名空间
     * @param ns
     * @throws IOException
     */
    @Test
    public static void createNameSpace(String ns) throws IOException {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum","hadoop01,hadoop02,hadoop03");
        Connection connection = ConnectionFactory.createConnection(configuration);

        Admin admin = connection.getAdmin();
        NamespaceDescriptor build = NamespaceDescriptor.create(ns).build();
        try {
            admin.createNamespace(build);
        } catch (NamespaceExistException e) {
            System.out.println("命名空间已存在!");
        } catch (Exception e){
            e.printStackTrace();
        }
        admin.close();
        connection.close();
    }

    /**
     * 插入数据
     * @param tableName
     * @param rowKey
     * @param cf
     * @param cn
     * @param value
     * @throws IOException
     */
    @Test
    public static void putData(String tableName,String rowKey,String cf,String cn,String value) throws IOException {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum","hadoop01,hadoop02,hadoop03");
        Connection connection = ConnectionFactory.createConnection(configuration);

        Table table = connection.getTable(TableName.valueOf(tableName));
        Put put = new Put(Bytes.toBytes(rowKey));
        put.addColumn(Bytes.toBytes(cf),Bytes.toBytes(cn),Bytes.toBytes(value));
        table.put(put);
        table.close();
        connection.close();
    }

    /**
     * 查询
     * @param tableName
     * @param rowKey
     * @param cf
     * @param cn
     * @throws IOException
     */
    @Test
    public static void getDate(String tableName,String rowKey,String cf,String cn) throws IOException {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum","hadoop01,hadoop02,hadoop03");
        Connection connection = ConnectionFactory.createConnection(configuration);

        Table table = connection.getTable(TableName.valueOf(tableName));
        Get get = new Get(Bytes.toBytes(rowKey));
        get.addFamily(Bytes.toBytes(cf));
        get.addColumn(Bytes.toBytes(cf),Bytes.toBytes(cn));
        Result result = table.get(get);

        for (Cell cell:result.rawCells()) {
            System.out.println("CF:"+Bytes.toString(cell.getFamilyArray())+",CN:"+Bytes.toString(cell.getQualifierArray())+",Value:"+Bytes.toString(cell.getValueArray()));
        }
        table.close();
        connection.close();
    }

    @Test
    public static void scanTable(String tableName) throws IOException {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum","hadoop01,hadoop02,hadoop03");
        Connection connection = ConnectionFactory.createConnection(configuration);

        Table table = connection.getTable(TableName.valueOf(tableName));
        Scan scan = new Scan();
        ResultScanner scanner = table.getScanner(scan);
        for (Result result:scanner) {
            for (Cell cell:result.rawCells()) {
                System.out.println("CF:"+Bytes.toString(cell.getFamilyArray())+",CN:"+Bytes.toString(cell.getQualifierArray())+",Value:"+Bytes.toString(cell.getValueArray()));
            }
        }
        table.close();
        connection.close();
    }

    public static void main(String[] args) throws IOException {
        scanTable("student");
    }
}
