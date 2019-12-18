package edu.olezha.sandbox.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TreePrinter {

    public static void print(BTreePrinter.Node node) {
        System.out.println(cast(node));
    }

    private static TreeNode cast(BTreePrinter.Node node) {
        return new TreeNode(node.getValue(), new ArrayList<TreeNode>(2) {{
            if (node.getLeft() != null) {
                add(cast(node.getLeft()));
            }
            if (node.getRight() != null) {
                add(cast(node.getRight()));
            }
        }});
    }

    private static class TreeNode {
        final String name;
        final List<TreeNode> children;

        public TreeNode(String name, List<TreeNode> children) {
            this.name = name;
            this.children = children;
        }

        public String toString() {
            StringBuilder buffer = new StringBuilder(50);
            print(buffer, "", "");
            return buffer.toString();
        }

        private void print(StringBuilder buffer, String prefix, String childrenPrefix) {
            buffer.append(prefix);
            buffer.append(name);
            buffer.append('\n');
            for (Iterator<TreeNode> it = children.iterator(); it.hasNext();) {
                TreeNode next = it.next();
                if (it.hasNext()) {
                    next.print(buffer, childrenPrefix + "├── ", childrenPrefix + "│   ");
                } else {
                    next.print(buffer, childrenPrefix + "└── ", childrenPrefix + "    ");
                }
            }
        }
    }
}
