package edu.olezha.sandbox.routing;

import java.util.*;

public class App {

    public static final int NETWORK_SIZE = 70_000;
    private static final int REQUESTS_TO_CHECK_ROUTING = 1_000_000;

    public static void main(String[] args) {
        List<Node> nodes = new ArrayList<>(NETWORK_SIZE);

        for (int i = 0; i < NETWORK_SIZE; i++) {
            nodes.add(new Node(i));
        }

        // TODO: move to Node peers distribution logic
        for (Node node : nodes) {
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() + 1)));
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() - 1)));
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() + 2)));
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() - 2)));
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() + 3)));
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() - 3)));
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() + 4)));
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() - 4)));
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() + 6)));
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() - 6)));
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() + 10)));
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() - 10)));
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() + 40)));
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() - 40)));
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() + 100)));
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() - 100)));
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() + 300)));
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() - 300)));
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() + 800)));
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() - 800)));
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() + 5_000)));
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() - 5_000)));
            node.getPeers().add(nodes.get(normalizeLocation(node.getLocation() + (int) (NETWORK_SIZE / 2.2))));
        }

        Random random = new Random();
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

    private static int normalizeLocation(int location) {
        location %= NETWORK_SIZE;
        if (location < 0)
            location += NETWORK_SIZE;
        return location;
    }
}
