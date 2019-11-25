package edu.olezha.sandbox.routing;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.XYChart;

import java.io.IOException;
import java.util.*;

public class App {

    public static final int NETWORK_SIZE = 70_000;

    public static void main(String[] args) throws IOException {
        List<Node> nodes = new ArrayList<>(NETWORK_SIZE);

        for (int i = 0; i < NETWORK_SIZE; i++) {
            nodes.add(new Node(i, new ArrayList<>()));
        }

        Random random = new Random();
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

        Node myNode = nodes.get(random.nextInt(nodes.size()));
        int iterations = 1_000_000;
        Map<Integer, Integer> hopsMap = new HashMap<>(Node.HTL);
        for (int i = 0; i < iterations; i++) {
            int hops = myNode.fetch(new Request(random.nextInt(nodes.size())));
            if (hops < 0) continue;
            hopsMap.put(hops, hopsMap.get(hops) == null ? 1 : hopsMap.get(hops) + 1);
        }
        int success = hopsMap.values().stream().reduce(0, Integer::sum);
        System.out.println("success: " + success * 100 / iterations + "%");
        System.out.println("hops: " + hopsMap);
        int avgHops = hopsMap.entrySet().stream()
                .map(e -> e.getKey() * e.getValue()).reduce(0, Integer::sum) / success;
        System.out.println("avg hops: " + avgHops);

//        final int width = 1_000;
//        final int height = 1_000;
//        int scale = hopsMap.values().stream().max(Integer::compare).get() / (height - 50) + 1;
//        int step = width / (hopsMap.size() + 1);
//        final BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
//        final Graphics2D g2 = image.createGraphics();
//        g2.setPaint(Color.WHITE);
//        g2.fillRect(0, 0, width, height);
//        g2.setPaint(Color.MAGENTA);
//        for (Map.Entry<Integer, Integer> e : hopsMap.entrySet())
//            g2.fillOval(e.getKey() * step, height - e.getValue() / scale - 20, 10, 10);
//        g2.dispose();
//        ImageIO.write(image, "png", new File("hopsDistribution.png"));

        XYChart chart = QuickChart.getChart("hops distribution", "hops", "quantity", " ",
                new ArrayList<>(hopsMap.keySet()),
                new ArrayList<>(hopsMap.values()));
        BitmapEncoder.saveBitmap(chart, "./target/hopsDistribution", BitmapEncoder.BitmapFormat.PNG);
    }

    private static int normalizeLocation(int location) {
        while (location < 0) location += NETWORK_SIZE;
        while (location >= NETWORK_SIZE) location -= NETWORK_SIZE;
        return location;
    }
}
