package edu.olezha.sandbox.hollywoodprinciple;

import java.util.List;
import java.util.stream.Collectors;

public class HollywoodService {

    public static List<Agent> getFriendlyAgents(AgentFinder agentFinder) {
        List<Agent> agents = agentFinder.findAllAgents();
        return filterAgents(agents, "friendly");
    }

    private static List<Agent> filterAgents(List<Agent> agents, String agentType) {
        return agents.stream()
                .filter(agent -> agentType.equals(agent.getType()))
                .collect(Collectors.toList());
    }
}
