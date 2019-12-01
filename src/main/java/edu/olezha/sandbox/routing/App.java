package edu.olezha.sandbox.routing;

import java.util.*;

public class App {

    private static final int NETWORK_SIZE = 70_000;
    private static final int PEERS_PER_NODE = 24;
    private static final int REQUESTS_TO_CHECK_ROUTING = 1_000_000;

    public static void main(String[] args) {
        List<Node> nodes = new ArrayList<>(NETWORK_SIZE);

        for (int i = 0; i < NETWORK_SIZE; i++) {
            nodes.add(new Node(i));
        }

        Random random = new Random();

        for (Node node : nodes) {
            for (int i = 0; i < PEERS_PER_NODE; i++) {
                Node randomNode = nodes.get(random.nextInt(nodes.size()));
                if (randomNode.equals(node)) continue;
                node.getPeers().add(randomNode);
            }
        }

        System.out.println("workout");
        for (int i = 0; i < PEERS_PER_NODE; i++)
            for (Node node : nodes)
                node.tick();

        System.out.println("test routing");
        Node myNode = nodes.get(random.nextInt(nodes.size()));
        Map<Integer, Integer> hopsMap = new HashMap<>(Node.HTL);
        for (int i = 0; i < REQUESTS_TO_CHECK_ROUTING; i++) {
            int hops = myNode.fetch(new Request(random.nextInt(nodes.size())));
            if (hops < 0) continue;
            hopsMap.put(hops, hopsMap.get(hops) == null ? 1 : hopsMap.get(hops) + 1);
        }

        int success = hopsMap.values().stream().reduce(0, Integer::sum);
        System.out.println("success: " + success * 100 / REQUESTS_TO_CHECK_ROUTING + "%");
        System.out.println("hops: " + hopsMap);
        int avgHops = hopsMap.entrySet().stream()
                .map(e -> e.getKey() * e.getValue()).reduce(0, Integer::sum) / success;
        System.out.println("avg hops: " + avgHops);

        // hops distribution chart
//        XYChart chart = QuickChart.getChart("hops distribution", "hops", "quantity", " ",
//                new ArrayList<>(hopsMap.keySet()),
//                new ArrayList<>(hopsMap.values()));
//        BitmapEncoder.saveBitmap(chart, "./target/hopsDistributionChart", BitmapEncoder.BitmapFormat.PNG);
    }
}
