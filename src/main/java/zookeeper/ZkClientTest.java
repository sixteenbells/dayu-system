package zookeeper;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

import java.util.List;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/3/1 2:52 PM
 * @description :dubbo命令测试
 * https://www.cnblogs.com/niechen/p/8597344.html
 */
public class ZkClientTest {

    public static void main(String[] args) throws Exception {
        ZkClient zkClient = new ZkClient("127.0.0.1:2182", 1000);
        // 获取指定路径的子节点个数
        System.out.println(zkClient.getChildren("/"));
        // 删除节点
        if (zkClient.exists("/dubbo")) {
            // 如果节点下有子节点，则不能删除
            if (zkClient.getChildren("/dubbo").isEmpty()) {
                zkClient.delete("/dubbo");
            }
        }

        // 创建永久的节点
        String nodeName = zkClient.create("/dubbo", "{\"name\":\"admin\", \"score\":\"100\"}", CreateMode.PERSISTENT);
        System.out.println(nodeName);

        // 创建临时节点
        zkClient.createEphemeralSequential("/dubbo/test", "a");
        zkClient.createEphemeralSequential("/dubbo/test", "b");

        // 读取节点数据
        System.out.println(zkClient.readData("/dubbo").toString());

        // 订阅dubbo节点的变化
        zkClient.subscribeDataChanges("/dubbo", new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.println(dataPath + "节点数据变化，值为：" + data.toString());
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println(dataPath + "节点数据被删除");
            }
        });

        // 订阅dubbo子节点的变化
        zkClient.subscribeChildChanges("/dubbo", (parentPath, currentChilds) -> System.out.println(parentPath + "父节点的子节点变化：" + currentChilds.toString()));
        // 更新dubbo节点的值
        zkClient.writeData("/dubbo", "bbb");
        System.in.read();

    }

    public static class MyZkDataListener implements IZkDataListener{

        @Override
        public void handleDataChange(String dataPath, Object data) throws Exception {
            System.out.println(dataPath + "节点数据变化，值为：" + data.toString());
        }

        @Override
        public void handleDataDeleted(String dataPath) throws Exception {
            System.out.println(dataPath + "节点数据被删除");
        }
    }
}
